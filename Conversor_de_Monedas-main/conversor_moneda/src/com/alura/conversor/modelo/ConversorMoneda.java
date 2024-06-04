package com.alura.conversor.modelo;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import com.alura.conversor.calculo.PruebaApi;

public class ConversorMoneda {
    static String menu ="""
                    --------------------------------------------
                    1) Dolar
                    2) Peso Argentino
                    3) Real Brasileño
                    4) Peso Colombiano
                    5) Euro
                    6) Peso Mexicano
                    7) Salir""";
    static  String salida ="""                  
                    
                    ~~GRACIAS~~~

                   """;
    static  String eleccion ="""
                    --------------------------------------------
                    Elija una moneda de la lista (7 para salir)
                    --------------------------------------------""";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println(menu);
            System.out.println(eleccion);
            opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion >= 1 && opcion <= 6) {
                convertirMoneda(scanner, opcion);
            } else if (opcion == 7) {
                System.out.println(salida);
            } else {
                System.out.println("Opción no válida, elija una opción del menú.");
            }
        } while (opcion != 7);
        scanner.close();
    }

    public static void convertirMoneda(Scanner scanner, int opcion) {
        String monedaOrigen = switch (opcion) {
            case 1 -> "USD";
            case 2 -> "ARS";
            case 3 -> "BRL";
            case 4 -> "COP";
            case 5 -> "EUR";
            case 6 -> "MXN";
            default -> "";
        };

        System.out.println("Ingrese la cantidad en " + monedaOrigen + " que desea convertir: ");
        double cantidad = scanner.nextDouble();
        scanner.nextLine();
        LocalDateTime timeStamp = LocalDateTime.now();

        System.out.println(menu);
        System.out.println("Elija una moneda de la lista: ");
        int opcionDestino = scanner.nextInt();
        scanner.nextLine();

        String monedaDestino;
        switch (opcionDestino) {
            case 1:
                monedaDestino = "USD";
                break;
            case 2:
                monedaDestino = "ARS";
                break;
            case 3:
                monedaDestino = "BRL";
                break;
            case 4:
                monedaDestino = "COP";
                break;
            case 5:
                monedaDestino = "EUR";
                break;
            case 6:
                monedaDestino = "MXN";
                break;
            case 7:
                System.out.println(salida);
                System.exit(0);
                return;
            default:
                monedaDestino = "";
        }

        try {
            PruebaApi.convertirMoneda(monedaOrigen, monedaDestino, cantidad);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = timeStamp.format(formatter);
            System.out.println("Conversión realizada el " + formattedDateTime + ": " + cantidad + " " + monedaOrigen + " a " + monedaDestino);
            System.out.println("¿Qué desea hacer?");
            System.out.println("1) Realizar otra conversión");
            System.out.println("2) Salir");
            System.out.println("Elija una opción: ");
            int opcionMenu = scanner.nextInt();
            switch (opcionMenu) {
                case 1:
                    break;
                case 2:
                    System.out.println(salida);
                    System.exit(0);
                    return;
                default:
                    System.out.println("Opción no válida. Reiniciando el programa...");
            }
        }
        catch (Exception e) {
          System.out.println("Error al realizar la conversión: " + e.getMessage());
        }
    }
}
