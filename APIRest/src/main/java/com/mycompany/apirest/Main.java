/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apirest;

import com.mycompany.apirest.util.PostDto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.apirest.servicios.PostService;
import com.mycompany.apirest.util.Parser;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Nestor
 */
public class Main {
 
    public static void main(String[] args) {     
        Scanner sc = new Scanner(System.in);
        int id;
        int opc;
        
        String respuestaListaPost=PostService.devolverTodosPost();
        System.out.println(respuestaListaPost);
        System.out.println("Ingrese el id:");
        id=sc.nextInt();
        String respuestaPostPorId=PostService.devolverPostPorId(Integer.toString(id));
        PostDto post= Parser.parsearJsonPost(respuestaPostPorId);
        System.out.println(post);
        System.out.println("¿Desea leer los comentarios?");
        System.out.println("1. SI");
        System.out.println("2. NO");
        opc=sc.nextInt();
        
        if(opc==1){
            System.out.println(PostService.devolverComentarios(Integer.toString(id),"comments"));
        }else{
            System.out.println("Opción diferente de 1 seleccionada");
        }
             
        System.out.println("Parseando listado...");
        List<PostDto> listadoPost= new ArrayList<>();
        listadoPost=Parser.parsearJsonListaPost(respuestaListaPost);
        System.out.println("Parseado de varios..");
        System.out.println(listadoPost);
    }
    
    
}
