package org.example.view;


import jdk.jshell.execution.Util;
import org.example.util.Utilidad;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class MenuTemplate {
    Scanner sc = new Scanner(System.in);
    protected boolean salir = false;



    public void exportarDatos() throws Exception {

    }

    public void crearAlummno() throws Exception {

    }

    public void agregarMateria() throws Exception {

    }

    public void agregarNotaPasoUno() throws Exception {

    }

    public void listarAlummnos() throws Exception {

    }

    public void terminarPrograma(){

    }

    public final void iniciarMenu(){

        while (!salir) {
            try {

                Utilidad.mensaje("-- MENÚ DE OPCIONES --");
                System.out.print("""
                1. Registrar Alumno
                2. Listar Alumnos
                3. Agregar Materia
                4. Agregar Notas
                5. Salir
                6. Exportar Registros
                """);

                Utilidad.mensaje("Seleccion:",true);
                if( sc.hasNextInt()){
                    int opcion = sc.nextInt();
                    switch (opcion) {
                        case 1 -> crearAlummno();
                        case 2 -> listarAlummnos();
                        case 3 -> agregarMateria();
                        case 4 -> agregarNotaPasoUno();
                        case 5 -> terminarPrograma();
                        case 6 -> exportarDatos();
                        default -> throw new InputMismatchException("Opcion no valida.");
                    }

                }else{
                    throw new InputMismatchException("Debe ingresar un número.");
                }
            }catch (Exception e) {
                Utilidad.mensaje("ERROR: "+e.getMessage());
//                System.out.flush();
            }
        }

    }


}
