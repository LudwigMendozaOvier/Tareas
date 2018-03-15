/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
/**
 *
 * @author ludwi
 */
public class tarea {
    
    /**
     * Se crean la variables a utilizar 
     */
    int tarea;
    String nombre;
    String descripcion;
    String tiempo;
    String fecha;
    
    /**
     * El constructor las inicializa
     */
    public tarea()
    {
      tarea =0;
      nombre="";
      descripcion ="";
      tiempo = "";
      fecha ="";
    }
    
    /**
     * Se obtiennen sus set y get 
     * @return  retorna el valor asignado
     */
    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }
    
    public int getTarea() {
        return tarea;
    }

    public void setTarea(int tarea) {
        this.tarea = tarea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
