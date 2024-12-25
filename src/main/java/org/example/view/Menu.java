package org.example.view;

import jdk.jshell.execution.Util;
import org.example.model.Alumno;
import org.example.model.Materia;
import org.example.model.MateriaEnum;
import org.example.service.AlumnoService;
import org.example.service.ArchivoService;
import org.example.service.PromedioServicioImp;
import org.example.util.Utilidad;


import java.util.*;

public class Menu extends MenuTemplate {
    AlumnoService alumnoService = new AlumnoService();
    ArchivoService archivoService = new ArchivoService(new PromedioServicioImp());
    static {


        try {
            //creacion alumno prueba
            Alumno alumno = new Alumno();
            alumno.setRut("193345387");
            alumno.setNombre("Belen");
            alumno.setApellido("Gonzalez");
            AlumnoService alumnoService = new AlumnoService();
            alumnoService.crearAlumno(alumno);
            Materia materia = new Materia();
            materia.setNombre(MateriaEnum.MATEMATICAS);
            materia.addNota(5.4f);
            materia.addNota(4.5f);
            alumno.addMateria(materia);
            Materia materia2 = new Materia();
            materia2.setNombre(MateriaEnum.LENGUAJE);
            materia2.addNota(5.9f);
            materia2.addNota(6.9f);
            alumno.addMateria(materia2);

            Alumno alumno2 = new Alumno();
            alumno2.setRut("212702234");
            alumno2.setNombre("Camilo");
            alumno2.setApellido("Gonzalez");
            alumnoService.crearAlumno(alumno2);
            Materia materia3 = new Materia();
            materia3.setNombre(MateriaEnum.MATEMATICAS);
            materia3.addNota(5.7f);
            alumno2.addMateria(materia3);
            Materia materia4 = new Materia();
            materia4.setNombre(MateriaEnum.LENGUAJE);
            materia4.addNota(5.4f);
            materia4.addNota(4.1f);
            alumno2.addMateria(materia4);



        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }
    @Override
    public void exportarDatos() throws Exception {

        Utilidad.limpiarPantalla();
        archivoService.exportarDatos(alumnoService.getListaAlumnos(), "exports/promedios.txt");
        Utilidad.mensaje("-- DATOS EXPORTADOS CORRECTAMENTE --");


    }

    @Override
    public void crearAlummno() throws Exception {

        Utilidad.mensaje("-- REGISTRAR ALUMNO --");
        System.out.print("Ingresa RUT:");
        String rut = Utilidad.sc().nextLine();

        System.out.print("Ingrese Nombre:");
        String nombre = Utilidad.sc().nextLine();

        System.out.print("Ingrese Apellido:");
        String apellido = Utilidad.sc().nextLine();

        if (rut.isEmpty() || nombre.isEmpty() || apellido.isEmpty()) {
            throw new Exception("Los campos no pueden estar vacíos");
        }

        Alumno alumno = new Alumno();
        alumno.setRut(rut);
        alumno.setNombre(nombre);
        alumno.setApellido(apellido);

        alumnoService.crearAlumno(alumno);
        Utilidad.limpiarPantalla();
        Utilidad.mensaje("-- ALUMNO REGISTRADO --\n");


    }

    @Override
    public void agregarMateria() throws Exception {

        Utilidad.limpiarPantalla();

        Utilidad.mensaje("-- AÑADIR MATERIA --");
        if (alumnoService.cantidadAlumnos() == 0) {
            throw new Exception("No hay registros");
        }
        System.out.print("Ingrese RUT:");
        String rut = Utilidad.sc().nextLine();
        if (rut.isEmpty()) {
            throw new Exception("RUT no puede estar vacío");
        }
        if (!alumnoService.existeAlumno(rut)) {
            throw new Exception("Alumno no existe");
        }


        List<MateriaEnum> listaMaterias = new ArrayList<>();
        int index = 0;
        for (MateriaEnum materiaEnum : MateriaEnum.values()) {
            System.out.println(index + ". " + materiaEnum);
            listaMaterias.add(index++, materiaEnum);

        }

        Utilidad.mensaje("Seleccione la materia:");
        if (Utilidad.sc().hasNextInt()) {
            int seleccion = Utilidad.sc().nextInt();
            if (seleccion >= 0 && seleccion < index) {
                MateriaEnum materiaEnum = listaMaterias.get(seleccion);
                Materia materia = new Materia();
                materia.setNombre(materiaEnum);
                alumnoService.agregarMateria(rut, materia);
                Utilidad.limpiarPantalla();
                Utilidad.mensaje("-- MATERIA AÑADIDA --");
            }else{
                throw new InputMismatchException("Valor ingresado de materia no es válido.");
            }
        } else {
            throw new InputMismatchException("Valor ingresado de materia no es válido.");
        }
    }


    @Override
    public void agregarNotaPasoUno() throws Exception {

        Utilidad.limpiarPantalla();
        Utilidad.mensaje("-- AÑADIR NOTA --");
        if (alumnoService.cantidadAlumnos() == 0) {
            throw new Exception("No hay registros");
        }
        System.out.print("Ingrese RUT:");
        String rut = Utilidad.sc().nextLine();
        if (rut.isEmpty()) {
            throw new Exception("El rut no puede estar vacío");
        }
        if (!alumnoService.existeAlumno(rut)) {
            throw new Exception("Alumno no existe");
        }

        List<Materia> listaMaterias = alumnoService.materiasPorAlumno(rut);
        if(listaMaterias.size() == 0) {
            throw new Exception("Alumno sin materias");
        }
        Utilidad.mensaje("Alumno tiene las siguientes materias:");

        for (int i = 0; i < listaMaterias.size(); i++) {
            System.out.println(i + ". " + listaMaterias.get(i).getNombre());
        }
        Utilidad.mensaje("Seleccione materia:");

        if (Utilidad.sc().hasNextInt()) {
            int seleccion = Utilidad.sc().nextInt();
            if (seleccion >= 0 && seleccion < listaMaterias.size()) {
                Materia materia = listaMaterias.get(seleccion);
                Alumno alumno = alumnoService.obtieneAlumno(rut);
                Materia matAlumno = alumno.getMaterias().stream().filter(m -> m.getNombre().equals(materia.getNombre())).findFirst().orElse(null);
                if (matAlumno != null) {
                    Utilidad.mensaje("Ingrese nota:");
                    if (Utilidad.sc().hasNextFloat()) {
                        float nota = Utilidad.sc().nextFloat();
                        matAlumno.addNota(nota);
                        Utilidad.limpiarPantalla();
                        Utilidad.mensaje("-- NOTA INGRESADA --");
                    } else {
                        throw new InputMismatchException("Valor ingresado es inválido.");
                    }
                } else {
                    throw new Exception("Alumno sin materias");
                }
            }
        } else {
            throw new InputMismatchException("Valor ingresado es inválido.");
        }

    }

    @Override
    public void listarAlummnos() throws Exception {

        Utilidad.limpiarPantalla();
        Utilidad.mensaje("-- LISTA DE ALUMNOS --");
        Map<String, Alumno> listaAlumnos = alumnoService.listarAlumnos();
        if (listaAlumnos == null || listaAlumnos.isEmpty()) {
            throw new Exception("Sin alumnos registrados");
        } else {
            for (Map.Entry<String, Alumno> alumno : listaAlumnos.entrySet()) {
                System.out.println(alumno.getValue().toString());
            }

        }
    }


    @Override
    public void terminarPrograma() {
        Utilidad.limpiarPantalla();
        this.salir = true;
        Utilidad.mensaje("Fin del programa.");

    }


}
