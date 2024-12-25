package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Alumno {
    private String rut;
    private String nombre;
    private String apellido;
    private List<Materia> materias;


    public Alumno(String rut, String nombre, String apellido, List<Materia> materias) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.materias = materias;
    }

    public Alumno() {
        this.materias = new ArrayList<>();
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }

    public void addMateria(Materia materia) {
        this.materias.add(materia);
    }

    @Override
    public String toString() {
        String dataAlumno = String.format("""
                Datos Alumno
                    RUT: %s
                    Nombre: %s
                    Apellido: %s

                    Materias
                """, this.rut, this.nombre, this.apellido);

        String dataMaterias = "";

        if(this.materias != null && !this.materias.isEmpty()){
            for (Materia materia : this.materias) {
                dataMaterias += "       "+materia.getNombre() + "\n";
                dataMaterias+=  "           Notas:\n";
                for (Float nota : materia.getNotas()) {
                    dataMaterias += "           [" + String.format("%.2f",nota) + "]\n";
                }
            }
        }else{
            dataMaterias = "           No tiene materias";
        }

        return dataAlumno.concat(dataMaterias);

    }
}
