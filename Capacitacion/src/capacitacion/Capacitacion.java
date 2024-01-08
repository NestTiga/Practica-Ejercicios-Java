/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package capacitacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.Collectors;

/**
 *
 * @author Nestor
 */
public class Capacitacion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        // String cedula, String nombre, String apellido, Integer edad, Genero genero, Pais pais
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona("1711165757","Luis","xx",10,Genero.MASCULINO,
                new Pais("Ecuador",13L)));
        personas.add(new Persona("1713073329","Mari","yy",15,Genero.FEMENINO,
                new Pais("Ecuador",13L)));
        personas.add(new Persona("1713073330","Maria","zz",20,Genero.FEMENINO,
                new Pais("Colombia",13L)));
        personas.add(new Persona("1713073331","Manuel","zz",25,Genero.MASCULINO,
                new Pais("Colombia",13L)));
        personas.add(new Persona("1711165731","Nestor","ww",30,Genero.MASCULINO,
                new Pais("Venezuela",13L)));
        personas.add(new Persona("3456865456","Pedro","aa",15,Genero.MASCULINO,
                new Pais("Uruguay",15L)));
        personas.add(new Persona("3453695764","Enrique","bb",20,Genero.MASCULINO,
                new Pais("Ecuador",13L)));
        
        
        // repaso
        
        System.out.println("nombre y apellido  " + personas.stream().map(persona ->{
            return persona.getNombreCompleto(persona.getNombre(), persona.getApellido());
        }).collect(Collectors.toList()));
        System.out.println("filter objeto  " +
                personas.stream()
                        .filter(persona->persona.getNombre().indexOf("M")>=0 && persona.getEdad()>15)
                        .map(persona ->{
                            return persona.getNombre() + " " + persona.getApellido();
                        })
                        .collect(Collectors.toList())
        );

        System.out.println("Sum objeto  " +
                personas.stream()
                        .filter(persona->persona.getNombre().indexOf("M")>=0 && persona.getEdad()>15)
                        .mapToInt(Persona::getEdad)
                        .sum()
        );

        System.out.println("Group by names containing O  " +
                personas.stream().collect(Collectors.groupingBy(persona->persona.getNombre().indexOf("o")>0))
        );
        

        /*
        TAREAS
        CONVERTIR LOS NOMBRES A UPPERCASE
        FILTRAR LOS NOMBRES QUE TIENEN MENOS DE 5 CARACTERES
        EXTRAER LA PERSONA DE MAYOR EDAD
        GENERAR UN RESUMEN DE: EDAD PROMEDIO, MIN EDAD , MAXEDAD
        AGRUPAR PERSONA POR EDAD
        AGRUPAR PERSONA POR ESTADO
        uNIR NOMBRES DE LAS PERSONAS SEPADAS POR _
        FILTRAR HOMBRES DE ECUADO
         */
        
        // convertir los nombres a uppercase
        System.out.println("UpperCase  " + personas.stream().map(persona -> persona.getNombre().toUpperCase()
        ).collect(Collectors.toList()));
        //Filtrar los nombre que tienen menos de 5 caracteres
        System.out.println("Nombres con menos de 5 caracteres  " +
                personas.stream()
                        .filter(persona->persona.getNombre().length()<5)
                        .map(persona -> persona.getNombre())
                        .collect(Collectors.toList())
        );
        
        // Persona de mayor edad
        OptionalInt edad=personas.stream()
                        .mapToInt(Persona::getEdad)
                        .max();
        int intEdad = edad.getAsInt();
        System.out.println("Mayor edad  " +
                personas.stream()
                        .filter(persona->persona.getEdad()==intEdad)
                        .map(persona -> persona.getNombre())
                        .collect(Collectors.toList())
        );
        // resumen de edad promedio, min edad, max edad
        System.out.println("Edad promedio  " +
                personas.stream()
                        .mapToInt(Persona::getEdad)
                        .average() +
                ", edad mínima  " +
                personas.stream()
                        .mapToInt(Persona::getEdad)
                        .min() +
                ", edad máxima  " +
                personas.stream()
                        .mapToInt(Persona::getEdad)
                        .max()
        );
        
        // Agrupar persona por edad
        System.out.println("Group by age  " +
                personas.stream().collect(Collectors.groupingBy(persona->persona.getEdad()))
        );
        // Agrupar persona por estado
        System.out.println("Group by state  " +
                personas.stream().collect(Collectors.groupingBy(persona->persona.getPais().getNombre()))
        );
        // Unir nombres con _
        System.out.println("nombre y apellido unido  " + personas.stream().map(persona ->{
            return persona.getNombreUnido(persona.getNombre(), persona.getApellido());
        }).collect(Collectors.toList()));
        //Filtrar hombres de Ecuador
        System.out.println("Hombres de Ecuador  " +
                personas.stream()
                        .filter(persona->persona.getPais().getNombre()=="Ecuador" && persona.getGenero()==Genero.MASCULINO)
                        .map(persona -> persona.getNombre())
                        .collect(Collectors.toList())
        );
    }
    
}
