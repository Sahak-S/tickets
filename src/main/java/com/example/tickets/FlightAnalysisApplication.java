package com.example.tickets;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.StreamSupport;

@SpringBootApplication
public class FlightAnalysisApplication {


    public static void main(String[] args) {
        SpringApplication.run(FlightAnalysisApplication.class, args);




//        try {
//            URL url = new URL("https://api.publicapis.org/entries");
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            conn.setRequestProperty("Accept", "application/json");
//
//            if (conn.getResponseCode() != 200) {
//                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
//            }
//
//            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
//
//            String output;
//            StringBuilder builder = new StringBuilder();
//            while ((output = br.readLine()) != null) {
//                builder.append(output);
//            }
//
//            conn.disconnect();
//
//            JSONObject json = new JSONObject(builder.toString());
//            System.out.println(json.toString());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        Gson gson = new Gson();
////        String phat = "C:\\Users\\User\\Desktop\\tickets.json";
//        try (
//                FileReader reader = new FileReader("Tickets.json")) {
//            // Чтение JSON из файла и преобразование в объект
//            JsonObject flightsJson = gson.<JsonObject>fromJson(reader, JsonObject.class);
//
//            // Получение списка всех перелетов
//            JsonArray flights = flightsJson.getAsJsonArray("flights");
//
//            // Мап для хранения минимального времени полета по авиаперевозчикам
//            Map<String, Integer> minFlightTimes = new HashMap<>();
//
//            // Мап для хранения цен на билеты для расчета средней цены и медианы
//            Map<String, Integer> prices = new HashMap<>();
//
//            // Проход по всем перелетам
//            for (JsonElement flightElement : flights) {
//                JsonObject flight = flightElement.getAsJsonObject();
//                String carrier = flight.get("carrier").getAsString();
//
//                int flightTime = flight.get("flight_time").getAsInt();
//
//                int price = flight.get("price").getAsInt();
//
//                // Обновление минимального времени полета для авиаперевозчика
//                if (!minFlightTimes.containsKey(carrier) || flightTime < minFlightTimes.get(carrier)) {
//                    minFlightTimes.put(carrier, flightTime);
//                }
//
//                // Обновление суммы цен для расчета средней цены и медианы
//                prices.put(carrier, prices.getOrDefault(carrier, 0) + price);
//            }
//
//            // Вывод минимального времени полета для каждого авиаперевозчика
//            System.out.println("Минимальное время полета для каждого авиаперевозчика:");
//            minFlightTimes.forEach((carrier, time) -> System.out.println(carrier + ": " + time));
//
//            // Расчет средней цены для каждого авиаперевозчика
//            System.out.println("\nСредняя цена билета для каждого авиаперевозчика:");
//            prices.forEach((carrier, total) -> {
//                double averagePrice = total / (double) flights.size();
//                System.out.println(carrier + ": " + averagePrice);
//            });
//
//            // Расчет медианы для каждого авиаперевозчика
//            System.out.println("\nМедиана цен билета для каждого авиаперевозчика:");
//            prices.forEach((carrier, total) -> {
//                int[] priceArray = StreamSupport.stream(flights.spliterator(), false)
//                        .filter(flight -> flight.getAsJsonObject().get("carrier").getAsString().equals(carrier))
//                        .mapToInt(flight -> flight.getAsJsonObject().get("price").getAsInt())
//                        .sorted()
//                        .toArray();
//
//                double medianPrice;
//                if (priceArray.length % 2 == 0) {
//                    medianPrice = (priceArray[priceArray.length / 2 - 1] + priceArray[priceArray.length / 2]) / 2.0;
//                } else {
//                    medianPrice = priceArray[priceArray.length / 2];
//                }
//
//                System.out.println(carrier + ": " + medianPrice);
//            });
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//

    }
}
