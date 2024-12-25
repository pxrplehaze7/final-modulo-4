package org.example.service;

import java.util.List;

public class PromedioServicioImp {

    public Float calcularPromedio(List<Float> valores){
        try{
            if(valores==null || valores.isEmpty()){
                throw new ArithmeticException("La lista de notas está vacía");
            }else{
                float sumatoria = 0;
                int cantidad = 0;
                for (float valor : valores) {
                    sumatoria += valor;
                    cantidad++;
                }
                return sumatoria / cantidad;

            }
        } catch (ArithmeticException e){
            throw new ArithmeticException(e.getMessage());
        }


    }
}
