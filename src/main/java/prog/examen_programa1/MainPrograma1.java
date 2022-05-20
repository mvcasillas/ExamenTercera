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
    
    public static double[] getMaximas(ArrayList<String[]> listaEntradas){
        double tempMaximas[] = {0, 100, 0};
        //Posicion 0 es la alta, 1 la baja y 2 la media
        //Guardo los 3 valores en un array para tener que recorrer la lista 1 vez en lugar de 3
        
        int contador=0;
        for(String[] entrada: listaEntradas){
            //Paso la posición de la máxima (1) a double para poder compararla
            double leido= Double.parseDouble(entrada[1]);
            
            //Compruebo si es mayor que la que tengo guardada, si lo es, sobreescribo
            if(leido>tempMaximas[0]){
                tempMaximas[0]=leido;
            }
            
            //Compruebo si es menor que la que tengo guardada, si lo es, sobreescribo
            if(leido<tempMaximas[1]){
                tempMaximas[1]=leido;
            }
            
            tempMaximas[2]+=leido;
            contador++;
        }
        //Una vez acabado el bucle hago la media
        tempMaximas[2]=tempMaximas[2]/contador;
        
        return tempMaximas;  
    }
    
    public static double[] getMinimas(ArrayList<String[]> listaEntradas){
        double tempMinimas[] = {0, 100, 0};
        //Posicion 0 es la alta, 1 la baja y 2 la media
        //Guardo los 3 valores en un array para tener que recorrer la lista 1 vez en lugar de 3
        
        int contador=0;
        for(String[] entrada: listaEntradas){
            //Paso la posición de la mínima (2) a double para poder compararla
            double leido= Double.parseDouble(entrada[2]);
            
            //Compruebo si es mayor que la que tengo guardada, si lo es, sobreescribo
            if(leido>tempMinimas[0]){
                tempMinimas[0]=leido;
            }
            
            //Compruebo si es menor que la que tengo guardada, si lo es, sobreescribo
            if(leido<tempMinimas[1]){
                tempMinimas[1]=leido;
            }
            
            tempMinimas[2]+=leido;
            contador++;
        }
        //Una vez acabado el bucle hago la media
        tempMinimas[2]=tempMinimas[2]/contador;
        
        return tempMinimas;  
    }
 
    public static double mediaTemperaturas(double temp1, double temp2){
        return (temp1+temp2)/2;
    }
    
    public static String getDiferenciaT(ArrayList<String[]> listaEntradas){
        double diferenciaMayor=0;
        String fechaDiferencia="";
        
        for(String[] entrada: listaEntradas){
            //Paso la posición de la máxima (1) y mínima(2) a double para poder compararla
            double max= Double.parseDouble(entrada[1]);
            double min= Double.parseDouble(entrada[2]);
            
            //Si la diferencia es mayor que la guardada, sobreescribe
            if((max-min)>diferenciaMayor){
                diferenciaMayor=(max-min);
                //En la posición 0 están las fechas así que la guardo
                fechaDiferencia=entrada[0];
            }
            
        }
        //Una vez comprobada toda la lista devuelve
        return String.format("%.2f", diferenciaMayor)+"     "+fechaDiferencia;
    }
   
    
    //----------------------------------------------------------------------------------
    //-------------------------------------- MAIN ---------------------------------------
    public static void main(String[] args) {
        
        //Pide al usuario por teclado el nombre de un archivo hasta que dé un nombre de archivo correcto
        File archivo;
        do{
            Scanner teclado = new Scanner(System.in);
            System.out.println("Introduce el nombre del archivo a comprobar");
            String rutaArchivo = teclado.nextLine();
            archivo = new File("resources\\tenerife\\"+rutaArchivo);
            
            if(!archivo.exists()){
                System.err.println("El archivo no existe, prueba de nuevo.");
            }
            
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
            //Cierro el lector.
            lectorArchivo.close();
            
            double tIntermediaMedia;
            double[] tMaximas, tMinimas;
            
            tMaximas=getMaximas(listaDatos);
            tMinimas=getMinimas(listaDatos);
            tIntermediaMedia=mediaTemperaturas(tMaximas[2], tMinimas[2]);
            String mayorDiferenciaT=getDiferenciaT(listaDatos);
            
            //Muestro todos los datos con formato de dos decimales
            System.out.println("Tª máxima más alta y más baja:    "+String.format("%.2f", tMaximas[0])+"     "+String.format("%.2f", tMaximas[1]));
            System.out.println("Tª mínima más alta y más baja:    "+String.format("%.2f", tMinimas[0])+"     "+String.format("%.2f", tMinimas[1]));
            System.out.println("Tª máxima media:                  "+String.format("%.2f", tMaximas[2]));
            System.out.println("Tª mínima media:                  "+String.format("%.2f", tMinimas[2]));
            System.out.println("Tª intermedia anteriores:         "+String.format("%.2f", tIntermediaMedia));
            System.out.println("Mayor diferencia de Tª en un día: "+mayorDiferenciaT);
            
            
        }catch (Exception e){
            System.err.println("Algo ha salido mal");
            e.printStackTrace();
        }
        
        //Se acaba el programa
    }
}
