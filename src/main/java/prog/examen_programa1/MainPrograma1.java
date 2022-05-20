/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog.examen_programa1;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author DAW
 */
public class MainPrograma1 {
    
    public static double getMaximaAlta(ArrayList<String[]> listaEntradas){
        double tempMaximaAlta=0;
        for(String[] entrada: listaEntradas){
            //Paso la posición de la máxima (1) a double para poder compararla
            double leido= Double.parseDouble(entrada[1]);
            
            //Compruebo si es mayor que la que tengo guardada, si lo es, sobreescribo
            if(leido>tempMaximaAlta){
                tempMaximaAlta=leido;
            }
        }
        return tempMaximaAlta;  
    }
    
        public static double getMaximaBaja(ArrayList<String[]> listaEntradas){
        double tempMaximaBaja=100;
        for(String[] entrada: listaEntradas){
            //Paso la posición de la máxima (1) a double para poder compararla
            double leido= Double.parseDouble(entrada[1]);
            
            //Compruebo si es menor que la que tengo guardada, si lo es, sobreescribo
            if(leido<tempMaximaBaja){
                tempMaximaBaja=leido;
            }
        }
        return tempMaximaBaja;  
    }
    
    
    public static void main(String[] args) {
        
        //Pide al usuario por teclado el nombre de un archivo hasta que dé un nombre de archivo correcto
        File archivo;
        do{
            Scanner teclado = new Scanner(System.in);
            System.out.println("Introduce el nombre del archivo a comprobar");
            String rutaArchivo = teclado.nextLine();
            archivo = new File("resources\\tenerife\\"+rutaArchivo);
        }while(!archivo.exists());
        
        try{
            Scanner lectorArchivo = new Scanner(archivo);
            
            //Lee los datos del archivo y lo svoy a guardar en un arraylist
            ArrayList<String[]> listaDatos = new ArrayList<>();
            while(lectorArchivo.hasNext()){
                String[] entrada = lectorArchivo.nextLine().split(" ");
                //Me deja la fecha en la posición 0, la máxima en la 1 y la mínima en la 2
                listaDatos.add(entrada);
            }
            lectorArchivo.close();
            
            double tMaximaAlta, tMaximaBaja, tMaximaMedia, tMinimaAlta, tMinimaBaja, tMinimaMedia, tIntermediaMedia, mayorDiferenciaT;
            String fechaMayorDiferencia;
            
            tMaximaAlta=getMaximaAlta(listaDatos);
            tMaximaBaja=getMaximaBaja(listaDatos);
            
            System.out.println("Tª máxima más alta y más baja:    "+tMaximaAlta+"     "+tMaximaBaja);
            System.out.println("Tª mínima más alta y más baja:    "+tMinimaAlta+"     "+tMinimaBaja);
            System.out.println("Tª máxima media:                  "+tMaximaMedia);
            System.out.println("Tª mínima media:                  "+tMinimaMedia);
            System.out.println("Tª intermedia anteriores:         "+tIntermediaMedia);
            System.out.println("Mayor diferencia de Tª en un día: "+mayorDiferenciaT+"     "+fechaMayorDiferencia);

            
            
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            
        }
        
        
        
        
        //Muestra por pantalla la info
        
        //Termina el programa
     
    }
}
