package org.example.service;

import org.example.model.Alumno;
import org.example.model.Materia;
import org.example.model.MateriaEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AlumnoServiceTest {

    AlumnoService as;

    AlumnoService asMock;

    Materia matematicas;
    Materia lenguaje;
    Alumno mapu;

    @BeforeEach
    void setup() throws Exception {
        as = new AlumnoService();
        asMock = mock(AlumnoService.class);
        matematicas = new Materia(MateriaEnum.MATEMATICAS,List.of(6.4f, 7.0f));
        lenguaje = new Materia(MateriaEnum.LENGUAJE, List.of(5.3f, 5.7f));
        mapu = new Alumno();
        mapu.setRut("12345");
        mapu.setNombre("Mapu");
        mapu.setApellido("Garcia");
    }

    @Test
    void crearAlumnoTest() throws Exception {
        //Metodo crearAlumnoTest para verificar el funcionamiento de crearAlumno
        //no es necesario usar mockito
        as.crearAlumno(mapu);
        assertEquals(1, as.cantidadAlumnos());
        assertEquals(1,as.listarAlumnos().size());
        assertTrue(as.existeAlumno("12345"));

        Exception ex = assertThrows(Exception.class,()->as.crearAlumno(mapu));
        assertEquals("El alumno ya existe", ex.getMessage());
    }

    @Test
    void agregarMateriaTest() throws Exception {
        //Metodo agregarMateriaTest para verificar el funcionamiento de agregarMateria.
        //tampoco es necesario usar mockito
        as.crearAlumno(mapu);
        as.agregarMateria("12345", matematicas);

        assertEquals(1,as.materiasPorAlumno("12345").size());

        assertEquals(MateriaEnum.MATEMATICAS,as.materiasPorAlumno("12345").get(0).getNombre());
        assertEquals(2,as.materiasPorAlumno("12345").get(0).getNotas().size());
    }

    @Test
    void materiasPorAlumnosTest() {
        //Metodo materiasPorAlumnosTest, usando mock para verificar el funcionamiento de materiasPorAlumnos.

        when(asMock.materiasPorAlumno("12345")).thenReturn(List.of(matematicas, lenguaje));

        assertEquals(2, asMock.materiasPorAlumno("12345").size());
    }

    @Test
    void listarAlumnosTest() throws Exception {
        //Metodo listarAlumnosTest para verificar el funcionamiento de listarAlumnos
        //tampoco es necesario usar mockito
        as.crearAlumno(mapu);

        assertEquals(1, as.listarAlumnos().size());
        assertEquals(Map.of("12345", mapu), as.listarAlumnos());

        as.crearAlumno(new Alumno("54321", "Pepe", "Gomez",List.of()));

        assertEquals(2, as.listarAlumnos().size());
        assertTrue(as.listarAlumnos().containsKey("54321"));
        assertTrue(as.listarAlumnos().containsKey("12345"));


    }

}
