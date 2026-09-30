package ru.practice;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpJsonTask {

    public static void run() {

        String url = "https://httpbin.org/get";

        try (HttpClient client = HttpClient.newHttpClient()) {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );

            if (response.statusCode() != 200) {
                System.out.println("Задание №4");
                System.out.println(
                        "HTTP ошибка: " + response.statusCode()
                );
                return;
            }

            String host = extractJsonValue(
                    response.body(),
                    "Host"
            );

            System.out.println("Задание №4");

            if (host != null) {
                System.out.println(
                        "Значение Host: " + host
                );
            } else {
                System.out.println(
                        "Поле Host не найдено в ответе."
                );
            }

        } catch (IOException e) {

            System.out.println(
                    "Ошибка HTTP-запроса: "
                            + e.getMessage()
            );

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            System.out.println(
                    "HTTP-запрос был прерван."
            );
        }
    }

    private static String extractJsonValue(
            String json,
            String key
    ) {

        String regex =
                "\\\"" + Pattern.quote(key)
                        + "\\\"\\s*:\\s*\\\"([^\\\"]*)\\\"";

        Matcher matcher =
                Pattern.compile(regex).matcher(json);

        return matcher.find()
                ? matcher.group(1)
                : null;
    }
}