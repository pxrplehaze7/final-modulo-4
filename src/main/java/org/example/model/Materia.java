package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Materia {
    private MateriaEnum nombre;
    private List<Float> notas;


    public Materia() {
        notas = new ArrayList<Float>();
    }

    public Materia(MateriaEnum nombre, List<Float> notas) {
        this.nombre = nombre;
        this.notas = notas;

    }

    public MateriaEnum getNombre() {
        return nombre;
    }

    public void setNombre(MateriaEnum nombre) {
        this.nombre = nombre;
    }

    public List<Float> getNotas() {
        return notas;
    }

    public void setNotas(List<Float> notas) {
        this.notas = notas;
    }

    public void addNota(Float nota){
        this.notas.add(nota);
    }


}
