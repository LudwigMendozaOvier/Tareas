/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
/**
 * Se importan las aclases y librerias
 */
import Modelo.tareaDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import vista.registroTareas;

/**
 * El controlador implementa los ActionListeners
 * Se crean instancias de las clases (del paquete modelo y vista)
 * @author ludwi
 */
public class Controlador_CreacionTareas implements ActionListener
{
    int segundos;
    int minutos;
    int horas;
    int contador;
    boolean suspenderTiempo = false;
    
    tareaDAO tarea = new tareaDAO();
    registroTareas registro = new registroTareas();

    /**
     * El constructor del controlador imlementa dentro de el las clases que se encuentran en el modelo y la vista
     * Se inicializan los parametros  
     * @param registro recibe la clase registroTareas
     * @param tarea  recibe la clase tareaDAO
     * Dentro del constructor se implementan los ActionListeners para cada elemento que se requiera utilizar (estos se encuentran dentrto de la vista registroTareas)
     * Tambien inicializamos unas variables que serviran para marcar el tiempo del cronometro
     */
    public Controlador_CreacionTareas(registroTareas registro, tareaDAO tarea) {
        this.tarea=tarea;
        this.registro=registro;
        this.registro.btnnuevaTarea.addActionListener(this);
        this.registro.btniniciar.addActionListener(this);
        this.registro.btndetener.addActionListener(this);
        this.registro.btneliminar.addActionListener(this);
        this.registro.txtnombre.addActionListener(this);
        this.registro.txtdescripcion.setLineWrap(true);
        this.registro.btniniciar.addActionListener(this);
        this.registro.btnpausar.addActionListener(this);
        Rellenar_Tabla(registro.jtabla);
        registro.btndetener.setEnabled(false);
        registro.btnpausar.setEnabled(false);
        registro.btnnuevaTarea.setEnabled(false);
        
        segundos=0;
        minutos=0;
        horas=0;
        contador=1;
    }
    
    /**
     * Fuera de cualquier metodo se establece un Thread (hilo) el cual sirve para ejecutar varios procesos simultaneamente
     * este mismo encierra un metodo el cual ejecutara el cronometro
     * Se crean variables que recibiran la hora los minutos y segundos
     */
    Thread hilo = new Thread(){
        public void run()
        {
            String segundosReloj="";
            String minutosReloj="";
            String horasReloj="";
            
            try{
                /**
                 * Empieza la condicion while que nos dice:mientras sea verdadera(del mismo hilo) realizara lo siguiente
                 * Empiezan las condiciones if
                 */
                while(true){
                    /**
                     * Si la variable segundo llega a ser menor a 59 los segundos veulven a inicializarse en cero y el minuto aumenta en uno
                     */
                    if(segundos>59){
                        segundos=0;
                        minutos++;
                    }
                    /**
                     * Si la variable minuto llega a ser menor a 59 los minutos veulven a inicializarse en cero y la hora aumenta en uno
                     */
                    if(minutos>59){
                        minutos=0;
                        horas++;
                    }
                    
                    /**
                     * Si los segundos son menores a 10 se escribe lo siguiente(01,02,03...09) en caso contrario se concatena con su variable que igual cuenta segundos y asi logran lo siguiente (11,12,13,14.....59)
                     */
                    if(segundos<10){
                        segundosReloj="0"+segundos;
                    }else{
                        segundosReloj=""+segundos;
                    }
                    /**
                     * Si los minutos son menores a 10 se escribe lo siguiente(01,02,03...09) en caso contrario se concatena con su variable que igual cuenta minutos y asi logran lo siguiente (11,12,13,14.....59)
                     */
                    if(minutos<10){
                        minutosReloj="0"+minutos;
                    }else{
                        minutosReloj=""+minutos;
                    }
                    /**
                     * Si las horas son menores a 10 se escribe lo siguiente(01,02,03...09) en caso contrario se concatena con su variable que igual cuenta horas y asi logran lo siguiente (11,12,13,14.....59)
                     */
                    if(horas<10){
                        horasReloj="0"+horas;
                    }else{
                        horasReloj=""+horas;
                    }
                    
                    /**
                     * Los segundos incrementan cada vez que se cumpla el ciclo (continuara contando uno en uno sin parar)
                     */
                    segundos++;
                    /**
                     * Seimplementa un sleep el cual realiza un intervalo de tiempo (cuanto tardara en aumentar al siguiente segundo)
                     * En este caso se utiliza el numero 1000 ya que es equivalente a un segundo (Segun san google)
                     */
                    hilo.sleep(1000);
                    /**
                     * Se le envia al label (de la vista registroTareas) las variables en forma de texto  
                     */
                    registro.labeltiempo.setText(horasReloj+":"+minutosReloj+":"+segundosReloj);
                }
                /**
                 * Se imprime el error (si ocurriera uno)
                 */
            }catch (InterruptedException e){
                JOptionPane.showMessageDialog(null,"Error (Tiempo) " + e);
            }
        }
    };
    
    /**
     * Se crea el metodo Rellenar_Tabla 
     * @param lista el cuel recibe a la tabla de la vista registroTareas
     * Simplemente crea un DefaulTableModel y le envia columnas
     */
    public void Rellenar_Tabla(JTable lista )
    {
       DefaultTableModel modelo = new DefaultTableModel();
        lista.setModel(modelo);
        modelo.addColumn("#Tarea");
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripción");
        modelo.addColumn ("Duracion");
        modelo.addColumn("Fecha");
 
        /**
         * Se crea un objeto donde se almacenaran los datos correspondientes
         */
        Object [] columna =  new Object[5];
        /**
         * Se declara el tamaño de que llegase a dar el metodo listaTareas que se encuentra en tareaDAO
         */
        int Numero_Registros = tarea.Lista_Tareas().size();
        /**
         * Se crea un for que permite el recorrido de las columnas para ingresar los datos de las variables que se encuentran en la clase tarea
         */
        for(int i=0; i<Numero_Registros;i++){
            columna[0] = tarea.Lista_Tareas().get(i).getTarea();
            columna[1] = tarea.Lista_Tareas().get(i).getNombre();
            columna[2] = tarea.Lista_Tareas().get(i).getDescripcion();
            columna[3] = tarea.Lista_Tareas().get(i).getTiempo();
            columna[4] = tarea.Lista_Tareas().get(i).getFecha();
            modelo.addRow(columna);
        } 
    }
    
    /**
     * Metodo que limpia los txt de la vista registroTareas
     */
    public void limpiar_Txt()
    {
        registro.txtnombre.setText("");
        registro.txtdescripcion.setText("");
    }
    
    /**
     * Metodo que le asigna y recibe el formato de la fecha 
     * @return la fecha actual (de la computadora) con su respectivo formato
     */
    public String Asignar_Fecha()
    {
       Date obtenerFecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-YYYY");
        return formatoFecha.format(obtenerFecha);
    }
    
    /**
     * Este es el actionPerformed aqui se ejecutan todas las acciones
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==registro.btnnuevaTarea)
        {
            /**
             * Dentro de la condicion del evento se crean variables que reciben los txt, el label del tiempo y la fecha actual (Todo esto se encuentra en la vista principal)
             */
            int numeroTarea = 0;
            String nombre = registro.txtnombre.getText();
            String descripcion = registro.txtdescripcion.getText();
            String tiempoCapturado = registro.labeltiempo.getText();
            String fecha = Asignar_Fecha();
            /**
             * Se llama al metodo y se le asignan las variav¿bles declaradas anteriormente
             */
            String agregarTarea = tarea.Insertar_Tarea(numeroTarea, nombre, descripcion, tiempoCapturado, fecha);
            /**
             * Si los datos estan completos se ejecuta el metodo que rellena la tabla y agrega las tareas (aunque sea una)
             */
            if(agregarTarea!=null){
                Rellenar_Tabla(registro.jtabla);
                JOptionPane.showMessageDialog(null,agregarTarea);
            }
            /**
             * Si no es asi manda error
             */
            else{
                JOptionPane.showMessageDialog(null,"Registro erroneo");
            }
        }
        
        if(e.getSource()==registro.btneliminar)
        {
            /**
             * Dentro de la condicion del evento se crean dos variables las cuales sirven para seleccionar la fila de una tarea y eliminarla
             */
            int filaEliminar = registro.jtabla.getSelectedRow();
            int filaSeleccionada = registro.jtabla.getSelectedRowCount();
            
            /**
             * Dentro de un arrayList llamado lista se obtienen los valores de la fila seleccionada y se manda unmensaje con la confirmacion del borrado
             */
            ArrayList<String> lista = new ArrayList();
            String folio;
                if(filaEliminar>=0)
                {
                    for(int i=0; i<filaSeleccionada;i++){
                        folio = String.valueOf(registro.jtabla.getValueAt(i+filaEliminar,0));
                        lista.add(i,folio);
                    }
                    /**
                     * en el for establecido puede borarrse mas de una fila
                     */
                    for(int i=0;i<filaSeleccionada;i++){
                        int avisoConfirmacion = JOptionPane.showConfirmDialog(null,"Esta seguro de eliminar el registro "+lista.get(i)+ " ?");
                        if(avisoConfirmacion==0){
                            tarea.Eliminar_Tarea(lista.get(i));
                        }
                    }
                    Rellenar_Tabla(registro.jtabla);
                }
                /**
                 * Si solo se presiona el boton y no se selecciona una fila, muestra un mensaje
                 */
            else
            {
                JOptionPane.showMessageDialog(null,"Seleccione almenos 1 fila");
            }
        }
        
        if(e.getSource()==registro.btniniciar)
        {
            /**
             * Dentro de la condicion del evento, se habilitan los otones de pausar y detener
             */
            registro.btnpausar.setEnabled(true);
            registro.btndetener.setEnabled(true);
            registro.btniniciar.setEnabled(false);
            registro.btnnuevaTarea.setEnabled(false);
            /**
             * Si el tiempo suspendido es diferente (osea que no hay nada: 00:00:00),Empieza a correr
             */
            if(!suspenderTiempo){
                hilo.start();
                suspenderTiempo = true;
                /**
                 *  Si no es asi se mantiene
                 */
            }else{
                hilo.resume();
            }
        }
        
        if(e.getSource()==registro.btnpausar)
        {
            /**
             * Dentro de la condicion del evento se inhabilita el boton pausar y se suspende el tiempo (para de correr)
             */
            registro.btnpausar.setEnabled(false);
            registro.btniniciar.setEnabled(true);
            registro.btnnuevaTarea.setEnabled(true);
            hilo.suspend();
            suspenderTiempo = true;
        }
        
        if(e.getSource()==registro.btndetener)
        {
            /**
             * Dentro de la condicion del evento, se crea una condicion que nos dice si se presiona este boton mado un mensaje de confirmacion
             */
            int avisoConfirmacion = JOptionPane.showConfirmDialog(null,"¿Esta seguro que desea borar el tiempo actual? ");
            /**
             * Sie el mensaje en la posicion 0 (osea "si" de: si,no,cancelar) es seleccionado, limpia los campos y vuelve a iniciar en cero
             */
            if(avisoConfirmacion==0)
            {            
                hilo.suspend();
                suspenderTiempo = true;
                segundos=0;
                minutos=0;
                horas=0;
                contador=1;
                registro.labeltiempo.setText("00:00:00");
                registro.txtdescripcion.setText("");
            }

        }
    }          
}
