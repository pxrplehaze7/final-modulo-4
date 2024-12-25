package org.example.service;

import org.example.model.Alumno;
import org.example.model.Materia;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ArchivoService {
    List<Alumno> alumnosACargar;
    PromedioServicioImp promediosServicioImp;

    public ArchivoService(PromedioServicioImp promediosServicioImp) {
        this.promediosServicioImp = promediosServicioImp;
    }

    public void exportarDatos(Map<String, Alumno> alumnos, String ruta) throws Exception {
        try {
            if (alumnos == null || alumnos.isEmpty()) {
                throw new Exception("Lista de alumnos está vacía.");
            }else if(ruta.isEmpty()){
                throw new Exception("La ruta no puede estar vacia.");
            }            else {
                BufferedWriter writer = new BufferedWriter(new FileWriter(ruta));
                for (Alumno alumno : alumnos.values()) {
                    writer.write(String.format("Alumno: %s  - %s\n", alumno.getRut(), alumno.getNombre() + " " + alumno.getApellido()));
                    if(alumno.getMaterias()!=null){
                        for (Materia materia : alumno.getMaterias()) {
                            writer.write(String.format("Materia: %s Notas:%s\n\t\t  Promedio: %s\n",
                                    materia.getNombre(),
                                    materia.getNotas().stream().map(nota -> String.format("%.1f",nota)).collect(Collectors.toList()),
                                    String.format("%.1f",promediosServicioImp.calcularPromedio(materia.getNotas()))
                            ));
                        }
                    }else{
                        writer.write("No tiene materias");
                    }
                    writer.write("\n");
                }
                writer.close();
            }

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


}
