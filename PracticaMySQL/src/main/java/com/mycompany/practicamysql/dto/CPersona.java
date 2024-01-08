/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practicamysql.dto;

import com.mycompany.practicamysql.persistencia.CConexion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Nestor
 */
public class CPersona {

    private int idPersona;
    private String nombre;
    private String apellido;
    private int edad;

    //Establezco conexión
    CConexion conexion = new CConexion();

    public CPersona() {
    }

    public CPersona(int id, String nombre, String apellido, int edad) {
        this.idPersona = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "DataPersona{" + "idPersona=" + idPersona + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + '}';
    }

    public void insertarDatosPersona(JTextField nombre, JTextField apellido, JTextField edad) {
        setNombre(nombre.getText());
        setApellido(apellido.getText());
        setEdad(Integer.parseInt(edad.getText()));// convierto a entero el velor recibido como texto

        try {
            String insertar = "insert into personas(nombre,apellido,edad) values(?, ?,?);";
            CallableStatement cs = conexion.establecerConexion().prepareCall(insertar);
            cs.setString(1, getNombre()); // el 1 es la posición de los datos en los que iran asignadas en la consulta o sea "?"
            cs.setString(2, getApellido());
            cs.setInt(3, getEdad());  // usar el que dice parameter 
            cs.execute();// Ejecuta la consulta sql
            JOptionPane.showMessageDialog(null, "Se inserto correctamente los datos de persona");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al insertar datos de persona, error: " + e.toString());
            //No olvidar el toString() para que muestre los datos y no el objeto extraño xd
        }

    }

    public void listarPersonas(JTable tablaTotalPersonas) {

        //creamos un modelo
        DefaultTableModel model = new DefaultTableModel();
        //ordenamos la tabla en la que se van a mostrar los datos
        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<TableModel>(model);
        tablaTotalPersonas.setRowSorter(ordenarTabla); // ordemos nuestra tabla
        String query = "SELECT * FROM db_ejemplo01.personas;";
        // generamos las cabeceras de la tabala en nuestro modelo
        model.addColumn("Id");
        model.addColumn("Nombre");
        model.addColumn("Apellido");
        model.addColumn("Edad");

        //Insertamos a nuestra tabla el modelo creado con las cabeceras
        tablaTotalPersonas.setModel(model);

        //creamos un vector de String para guardar los datos recibidos de la base
        String[] datosRecibidos = new String[4];

        try {
            Statement st = conexion.establecerConexion().createStatement();
            ResultSet resultado = st.executeQuery(query);

            while (resultado.next()) {
                for (int i = 0; i < 4; i++) {
                    datosRecibidos[i] = resultado.getString(i + 1);
                    //0 porque los vectores trabajan desde 0 ero los de la consulta sql cuentan desde 1
                }

                // añadimos el vector que tiene los datos enviados de la base a nuestro modelo, en este 
                // caso, colocamos los datos en las filas y finalmente solo resta colocarlo en nuestra tabla
                model.addRow(datosRecibidos);
            }
            // cargamos el modelo con los datos en nuestra tabla
            tablaTotalPersonas.setModel(model);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo realizar el listado de datos de personas, error: " + e.toString());
        }

    }
    
    public void selectPersona(JTable tablaPersonas, JTextField id, JTextField nombre, JTextField apellido, JTextField edad){
        try {
            int indiceFila= tablaPersonas.getSelectedRow();
            if(indiceFila>=0){
                id.setText(String.valueOf(tablaPersonas.getValueAt(indiceFila, 0))); // en la columna 0 de la tabla está el id
                // el getValueAt de una tabla siempre devuelve un tipo object por lo que se necesita convertir a String
                nombre.setText(String.valueOf(tablaPersonas.getValueAt(indiceFila, 1)));
                apellido.setText(String.valueOf(tablaPersonas.getValueAt(indiceFila, 2)));
                edad.setText(String.valueOf(tablaPersonas.getValueAt(indiceFila, 3)));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al seleccionar, error: " + e);
        }
    }
    
    public void editarPersona(JTextField id, JTextField nombre, JTextField apellido, JTextField edad){
        setIdPersona(Integer.parseInt(id.getText()));
        setNombre(nombre.getText());
        setApellido(apellido.getText());
        setEdad(Integer.parseInt(edad.getText()));
        
        try {
            String queryEdit="UPDATE personas SET personas.nombre=?, personas.apellido=?, personas.edad=? WHERE personas.idpersonas=?;";
            CallableStatement cs= conexion.establecerConexion().prepareCall(queryEdit);
            cs.setString(1, getNombre());
            cs.setString(2, getApellido());
            cs.setInt(3, getEdad());
            cs.setInt(4, getIdPersona());
            
            cs.execute();
            JOptionPane.showMessageDialog(null, "Datos de persona actualizados correctamente");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar los datos de persona, error: " + e.toString());
        }
    }
    
    public void eliminarDatosPersona(JTextField id){
        setIdPersona(Integer.parseInt(id.getText()));
        try {
            String queryEliminar="Delete from personas where personas.idpersonas=?;";
            CallableStatement cs= conexion.establecerConexion().prepareCall(queryEliminar);
            cs.setInt(1, getIdPersona());
            cs.execute();
            JOptionPane.showMessageDialog(null, "Datos de la persona eliminadas correctamente!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar los datos de la persona, error: " + e.toString());
        }
    }

}
