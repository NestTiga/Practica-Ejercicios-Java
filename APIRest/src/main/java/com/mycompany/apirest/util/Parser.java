/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apirest.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;

/**
 *
 * @author Nestor
 */
public class Parser {
    
    private static Gson gson = new Gson();
    
    public static List<PostDto> parsearJsonListaPost(String json) {
        
        return gson.fromJson(json, new TypeToken<List<PostDto>>(){}.getType());
    }
    
    public static PostDto parsearJsonPost(String json) {
        return gson.fromJson(json, PostDto.class);
    }
    
}
