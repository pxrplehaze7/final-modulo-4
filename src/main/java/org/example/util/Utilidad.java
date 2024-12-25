package org.example.util;

import java.util.Scanner;

public class Utilidad {
    // Códigos ANSI para colores
//    public static final String RESET = "\033[0m";  // Resetear color
//    public static final String ROJO = "\033[31m"; // Texto rojo
//    public static final String VERDE = "\033[32m"; // Texto verde
//    public static final String AMARILLO = "\033[33m"; // Texto amarillo
//    public static final String AZUL = "\033[34m"; // Texto azul
//    public static final String MAGENTA = "\033[35m"; // Texto magenta
//    public static final String CIAN = "\033[36m"; // Texto cian
//    public static final String BLANCO = "\033[37m"; // Texto blanco

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void limpiarPantalla(){
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void mostrarMensaje(String mensaje){
        System.out.println(mensaje);
    }


    public static void mensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public static void mensaje(String mensaje,boolean sinSalto) {
        if (sinSalto) {
            System.out.print(mensaje);
        } else {
            mensaje(mensaje);
        }
    }

    public static Scanner sc(){
        return SCANNER;
    }

}
