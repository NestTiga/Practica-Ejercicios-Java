/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package listas;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Nestor
 */
public class Listas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        List<String> lista= new ArrayList<>();
        
        lista.add("Néstor");
        lista.add("Tigasi");
        
        lista.forEach(elemento->{
            System.out.println("Elemento " + elemento);
        });
        
        System.out.println("Otro método");
        
        for(String elemento:lista){
            System.out.println("Dato " + elemento);
        }
        
        System.out.println("Filtrado" + lista.stream().filter(elemento->elemento.indexOf('N')>=0).collect(Collectors.toList()));
    }
    
}
