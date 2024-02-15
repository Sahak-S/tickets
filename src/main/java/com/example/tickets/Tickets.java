package com.example.tickets;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.StreamSupport;

@RestController
public class Tickets {

    @GetMapping("/api/tickets")
    public String main() {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader("Tickets.json")) {
            // Чтение JSON из файла и преобразование в объект
            JsonObject flightsJson = gson.fromJson(reader, JsonObject.class);

            // Получение списка всех перелетов
            JsonArray flights = flightsJson.getAsJsonArray("flights");

            // Мап для хранения минимального времени полета по авиаперевозчикам
            Map<String, Integer> minFlightTimes = new HashMap<>();

            // Мап для хранения цен на билеты для расчета средней цены и медианы
            Map<String, Integer> prices = new HashMap<>();

            // Проход по всем перелетам
            for (JsonElement flightElement : flights) {
                JsonObject flight = flightElement.getAsJsonObject();
                String carrier = flight.get("carrier").getAsString();
//                String departure = flight.get("departure").getAsString();
//                String arrival = flight.get("arrival").getAsString();
                int flightTime = flight.get("flightTime").getAsInt();
                int price = flight.get("price").getAsInt();

                // Обновление минимального времени полета для авиаперевозчика
                if (!minFlightTimes.containsKey(carrier) || flightTime < minFlightTimes.get(carrier)) {
                    minFlightTimes.put(carrier, flightTime);
                }

                // Обновление суммы цен для расчета средней цены и медианы
                prices.put(carrier, prices.getOrDefault(carrier, 0) + price);
            }

            // Вывод минимального времени полета для каждого авиаперевозчика
            System.out.println("Минимальное время полета для каждого авиаперевозчика:");
            minFlightTimes.forEach((carrier, time) -> System.out.println(carrier + ": " + time));

            // Расчет средней цены для каждого авиаперевозчика
            System.out.println("\nСредняя цена билета для каждого авиаперевозчика:");
            prices.forEach((carrier, total) -> {
                double averagePrice = total / (double) flights.size();
                System.out.println(carrier + ": " + averagePrice);
            });

            // Расчет медианы для каждого авиаперевозчика
            System.out.println("\nМедиана цен билета для каждого авиаперевозчика:");
            prices.forEach((carrier, total) -> {
                int[] priceArray = StreamSupport.stream(flights.spliterator(), false)
                        .filter(flight -> flight.getAsJsonObject().get("carrier").getAsString().equals(carrier))
                        .mapToInt(flight -> flight.getAsJsonObject().get("price").getAsInt())
                        .sorted()
                        .toArray();

                double medianPrice;
                if (priceArray.length % 2 == 0) {
                    medianPrice = (priceArray[priceArray.length / 2 - 1] + priceArray[priceArray.length / 2]) / 2.0;
                } else {
                    medianPrice = priceArray[priceArray.length / 2];
                }

                System.out.println(carrier + ": " + medianPrice);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(gson.toJson(gson));
        return null;
    }

}
