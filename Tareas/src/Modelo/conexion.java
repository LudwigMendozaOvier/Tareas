/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ludwi
 */
public class conexion {
    /**
     * Se declaran la las variables para el acceso a la conexion de la base de datos
     */
    Connection con;
    String db= "programa";
    String url= "jdbc:mysql://localhost/"+db;
    String user= "root";
    String pass= "";
    /**
     * Se implementa un metodo que regrese la conexion, este metodo recibe las variables ya establecidas y retorna la conexion
     * En dado caso de que falle la conexion devolvera un mensaje con el error encontrado en ella
     * @return retorna la conexion
     */
    public Connection getConexion(){ 
        try {
            con= DriverManager.getConnection(url,user,pass);
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getErrorCode() + "" + ex.getMessage());
        }
        return con;
    }
}
