/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apirest.servicios.exception;

/**
 *
 * @author Nestor
 */
public class CustomException extends Exception{
    private String Mensaje;
    private String Codigo;

    public CustomException(String Mensaje, String Codigo) {
        this.Mensaje = Mensaje;
        this.Codigo = Codigo;
    }
    
}
