/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejecutable;

import Controlador.Controlador_CreacionTareas;
import Modelo.tareaDAO;
import vista.registroTareas;

/**
 * La clase que ejecuta el programa
 * crea instancias de las clases y manda a llamar al controlador, el cual recibe esta clases 
 * ejecuta la vista indicada
 * @author ludwi
 */
public class ejecutable {
    public static void main(String[] args) {
        registroTareas registro = new registroTareas();
        tareaDAO tarea = new tareaDAO();
        
        Controlador_CreacionTareas obj = new Controlador_CreacionTareas(registro, tarea);
        
        registro.setVisible(true);
        registro.setLocationRelativeTo(null);
        registro.setResizable(false);
    }
}
