/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class tareaDAO {
    /**
     * se inisializa una variable de tipo conexion (clase declarada que conecta con al base de datos)
     */

    conexion conexion;
    //El constructor inicaia la instancia para su uso inmediato
    public tareaDAO()
    {
        conexion= new conexion();
    }
    
    /**
     * Se crea un metodo que recibe dos parametros (los cuales se insertaran en la vista registroTareas)
     * se conecta con la base de datos
     * crea una consulta de tipo CallableStatement que sirve para llamar a procediemientos almacenados
     * se envian los paramentos al priocedimiento almacenado
     * Se crea una condicion, si los campos se encuentran llenos (los txt de la vista). entonces se envia un mensaje
     * @param id recibe el perametro id
     * @param nombre recibe el parametro nombre
     * @param descripcion recibe el parametro descricion
     * @param tiempo recibe el parametro tiempo
     * @param fecha recibe el parametro fecha
     * @return retorna el metodo implementado
     */
    public String Insertar_Tarea(int id, String nombre, String descripcion, String tiempo, String fecha)
    {
        String listaTareas = null;
        try
        {
            Connection acceso_BD = conexion.getConexion();
            CallableStatement sp = acceso_BD.prepareCall("{call sp_agregarTarea(?,?,?,?,?)}");
            sp.setString(1, String.valueOf(id));
            sp.setString(2, nombre); 
            sp.setString(3, descripcion);
            sp.setString(4, tiempo);
            sp.setString(5, fecha);
            
            int camposllenos = sp.executeUpdate();
            if(camposllenos>0)
            {
                listaTareas = "Se ha completado el resgistro";
            }
            else
            {
                listaTareas = "Aun faltan campos por llenar, desea continuar?";
            }
            
        }
        catch(SQLException e)
                {
                 JOptionPane.showMessageDialog(null, " Se ha producido un error (Insertar)"+ e );
                }
        return listaTareas;
    }
    /**
     * Se crea un metodo de tipo ArrayList. este metodo devolvera la lista de tareas
     * @return devuelve el listado de tareas con su información
     * se implementa una lista de arrays
     * realiza la conexion a la base de datos 
     * crea una consulta de tipo CallableStatement que sirve para llamar a procediemientos almacenados
     * Implementa una condicion while que lo ejecutara mientras se realiza la conexion
     * recibe los los atributos de la clase tarea (con sus respectivos set y get)
     */
    public ArrayList<tarea> Lista_Tareas()
    {
        ArrayList listaTareas =  new ArrayList();
        tarea tareas;
        try
        {
            Connection acceso_BD = conexion.getConexion();
            PreparedStatement ps = acceso_BD.prepareStatement("select * from registroTarea");
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                tareas = new tarea();
                tareas.setTarea(rs.getInt(1));
                tareas.setNombre(rs.getString(2));
                tareas.setDescripcion(rs.getString(3));
                tareas.setTiempo(rs.getString(4));
                tareas.setFecha(rs.getString(5));
                
                listaTareas.add(tareas);
            }
        }
        catch (SQLException e)
                {
                    JOptionPane.showMessageDialog(null, " Se ha producido un error (Listar)"+ e);
                }
        return listaTareas;
    }
    
    /**
     * 
     * @param id recibe una fila (la cual se encuentra dentro de la tabla de la vista registroTareas)
     * @return retorna el numero de fila seleccionada
     * realiza una conexion con el try y catch donde si la fila se selecciono, ejecuta un procedimiento almacenado que elimina
     * por id (de la tarea) una fila completa (todo el registro que se encuentre en ella)
     */
    public int Eliminar_Tarea(String id){
        int numeroFila = 0;
        try
        {
            Connection acceso_BD = conexion.getConexion();
            CallableStatement sp = acceso_BD.prepareCall("{call sp_eliminarTarea(?)}");
            sp.setString(1, id);
            numeroFila = sp.executeUpdate();
            JOptionPane.showMessageDialog(null, "Tarea eliminada");
        }
        catch(SQLException e)
                {
                    JOptionPane.showMessageDialog(null, " Se ha producido un error (Eliminar)"+ e );
                }
        return numeroFila;
    }
}
