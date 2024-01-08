/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practicamysql.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Nestor
 */
public class CConexion {
    Connection conectar=null;
    private String usuario="root";
    private String contrasenia="root";
    private String bdNombre="db_ejemplo01";
    private String ip="localhost";
    private String puerto="3306";
    private String cadenaConexion="jdbc:mysql://"+ip+":"+puerto+"/"+bdNombre;
    
    public Connection establecerConexion(){
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar=DriverManager.getConnection(cadenaConexion, usuario, contrasenia);
            //JOptionPane.showMessageDialog(null, "Conexión exitosa!!!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro al conectar en la base de datos: " + e.toString());
        }
        
        return conectar;
    }
}
