package com.coderforfun.conversordemoneda.principal;

import com.coderforfun.conversordemoneda.service.ConsumoApi;
import com.coderforfun.conversordemoneda.service.ConvierteDatos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        ConsumoApi consumoApi = new ConsumoApi();
        ConvierteDatos conversor = new ConvierteDatos();
        Scanner teclado = new Scanner(System.in);

        var menu = """
                    
                    Bienvenido al Conversor de Monedas
                    
                    1 - Dólar >>> Peso Mexicano
                    2 - Peso Mexicano >>> Dólar
                    3 - Dólar >>> Real brasileño
                    4 - Real brasileño >>> Dólar
                    5 - Dólar >>> Peso colombiano
                    6 - Peso colombiano >>> Dólar
                    
                    7 - Salir
                    
                    Elija una opción valida
                    """;

        while (true){

            try {
                System.out.println(menu);
                var opcionUsuario = teclado.nextInt();

                if (opcionUsuario == 7) {
                    break;
                }if (opcionUsuario <= 0){
                    System.out.println("Opción Invalida, ejecutar nuevamente por favor.");
                    break;
                }if (opcionUsuario >= 8){
                    System.out.println("Opcion Invalida, ejecutar nuevamente por favor.");
                    break;
                }

                System.out.println("Escribe el valor que deseas convertir");
                var montoUsuario = teclado.nextDouble();

                String urlBase = "https://v6.exchangerate-api.com/v6/";
                String apiKey = "c426f4ba47565aaa2c5e2e42";
                String urlRespuesta = "/pair/";
                String monedaBase = "";
                String monedaDestino = "";

                switch (opcionUsuario) {
                    case 1:
                        monedaBase = "USD";
                        monedaDestino = "MXN";
                        break;
                    case 2:
                        monedaBase = "MXN";
                        monedaDestino = "USD";
                        break;
                    case 3:
                        monedaBase = "USD";
                        monedaDestino = "BRL";
                        break;
                    case 4:
                        monedaBase = "BRL";
                        monedaDestino = "USD";
                        break;
                    case 5:
                        monedaBase = "USD";
                        monedaDestino = "COP";
                        break;
                    case 6:
                        monedaBase = "COP";
                        monedaDestino = "USD";
                        break;
                }

                URI direccion = URI.create(urlBase + apiKey + urlRespuesta + monedaBase + "/" + monedaDestino + "/" + montoUsuario);

                String json = consumoApi.obtenerDatos(direccion);

                var conversion = conversor.convierteDatos(json);

                BigDecimal resultado = BigDecimal.valueOf(montoUsuario * conversion.conversion_rate());

                System.out.println("El valor de: $" + montoUsuario + " ["
                        + monedaBase + "] corresponde al valor final de >>> $"
                        + resultado.setScale(2, RoundingMode.HALF_UP) + " [" + monedaDestino + "].");

                var comprobacionResultado = conversion.conversion_result();

            } catch (InputMismatchException e) {
                System.out.println("Acepta solo valores numericos, ejecuta de nuevo por favor");
                break;
            }
        }
    }
}
