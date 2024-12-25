package org.example.service;

import org.example.model.Alumno;
import org.example.model.Materia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlumnoService {
    private static Map<String, Alumno> listaAlumnos;
//    private static Map<String, Alumno> listaAlumnos;//se comentó porque en los test se agregaba uno en cada uno

    public AlumnoService() {}


    public Map<String, Alumno> getListaAlumnos() {
        return listaAlumnos;
    }

    public void setListaAlumnos(Map<String, Alumno> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }

    public void crearAlumno(Alumno alumno) throws Exception {
        if (listaAlumnos == null) {
            listaAlumnos = new HashMap<>();
        }
        if (alumno == null) {
            throw new Exception("El alumno no puede ser null");
        }else if(existeAlumno(alumno.getRut())){
            throw new Exception("El alumno ya existe");

        } else {
            this.listaAlumnos.put(alumno.getRut(), alumno);
        }
    }

    public void agregarMateria(String rutAlumno, Materia currentMate) {
        if (rutAlumno != null && currentMate != null) {
            Alumno alumno = listaAlumnos.get(rutAlumno);
            if (alumno != null) {
                alumno.addMateria(currentMate);
            }
        }
    }

    public List<Materia> materiasPorAlumno(String rutAlumno) {
        if (rutAlumno != null) {
            Alumno alumno = listaAlumnos.get(rutAlumno);

            if (alumno != null) {
                return alumno.getMaterias();
            }
        }
        return null;


    }

    public Map<String, Alumno> listarAlumnos() {
        return listaAlumnos;
    }

    public boolean existeAlumno(String rut) {
        return listaAlumnos.containsKey(rut);
    }

    public Alumno obtieneAlumno(String rut){
        return listaAlumnos.get(rut);
    }

    public int cantidadAlumnos() {
        if(listaAlumnos == null || listaAlumnos.isEmpty()) return 0;
        return listaAlumnos.size();
    }
}
