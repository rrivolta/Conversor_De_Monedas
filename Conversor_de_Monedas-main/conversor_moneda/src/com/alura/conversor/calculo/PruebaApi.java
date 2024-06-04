package com.alura.conversor.calculo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class PruebaApi {
        public static void main(String[] args) throws IOException, InterruptedException {
                Scanner busca = new Scanner(System.in);
                System.out.println("Ingrese la moneda de origen (por ejemplo, MXN): ");
                String monedaOrigen = busca.nextLine().toUpperCase();

                System.out.println("Ingrese la moneda de destino (por ejemplo, USD): ");
                String monedaDestino = busca.nextLine().toUpperCase();

                System.out.println("Ingrese la cantidad: ");
                double cantidad = busca.nextDouble();

                convertirMoneda(monedaOrigen, monedaDestino, cantidad);
                busca.close();
        }

        public static void convertirMoneda(String monedaOrigen, String monedaDestino, double cantidad) {
                try {
                        String direccion = "https://v6.exchangerate-api.com/v6/c35c67e54cc23de77ef1073f/latest/" + monedaOrigen;
                        HttpClient client = HttpClient.newHttpClient();
                        HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(direccion))
                                .build();
                        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                        String jsonResponse = response.body();

                        Gson gson = new Gson();
                        JsonObject json = gson.fromJson(jsonResponse, JsonObject.class);
                        JsonObject rates = json.getAsJsonObject("conversion_rates");

                        double tasaMonedaOrigen = rates.get(monedaOrigen).getAsDouble();
                        double tasaMonedaDestino = rates.get(monedaDestino).getAsDouble();

                        double resultado = cantidad / tasaMonedaOrigen * tasaMonedaDestino;

                        System.out.println(cantidad + " " + monedaOrigen + " equivale a " + resultado + " " + monedaDestino);
                } catch (IOException | InterruptedException e) {
                        System.out.println("Error al realizar la conversión: " + e.getMessage());
                }
        }
}
