/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Programa3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author DAW
 */
public class Programa3 {
    public static void main(String[] args) {
        
        try{
            
            Scanner teclado = new Scanner(System.in);
            System.out.println("¿Cuántos alumnos juegan?");
            int nAlumnos=teclado.nextInt();
            
            System.out.println("¿Cuántos bombones máximo tendrá cada uno?");
            int mBombones=teclado.nextInt();
            //Teclado next porque después de leer un int se ralla
            teclado.nextLine();
            
            File archivo = new File("resources\\participants.csv");
            FileReader lector = new FileReader(archivo);
            BufferedReader bffLector = new BufferedReader(lector);
            //RandomAccessFile lectorRandom = new RandomAccessFile (archivo, "r");
            
            //Scanner lectorArchivo = new Scanner(archivo);
            //lectorArchivo.useDelimiter(","); No funcionaba esto porfa Cris ten piedad
            
            ArrayList<String> alumnosLista = new ArrayList<>();
            
            String linea;
            int lineas=0;
            while((linea= bffLector.readLine())!=null){
                String[] contenido = linea.split(",");
                //Quito comillas
                String nombre = contenido[0].substring(1,(contenido[0].length()-1));
                alumnosLista.add(nombre);
                lineas++;
                //System.out.println("algo????");
            }

            
            HashMap<String, Integer> alumnosMap = new HashMap<>();
            
            int contador=0;
            while(contador<nAlumnos){
                int posicion=(int)(Math.random()*lineas);
                String nombre = alumnosLista.get(posicion);
                //System.out.println(nombre);
                int bombonesParaTi = (int)(Math.random()*mBombones);
                //System.out.println(bombonesParaTi);
                alumnosMap.put(nombre, bombonesParaTi);
                contador++;
            }
            
            //lectorRandom.close();
            lector.close();
            bffLector.close();
            
            //Ahora a meter el nombre de un alumno para comprobar si está
            System.out.println("Escribe el nombre del alumno a comprobar");
            String nombreAlumno=teclado.nextLine();
            
            if(alumnosMap.containsKey(nombreAlumno)){
                //Si está, lo imprime
                System.out.println("A " + nombreAlumno+" le han tocado "+alumnosMap.get(nombreAlumno)+" bombones");
            }else{
                //Si no está, lanza excepción NoHayBombonesParaTi
                throw new NoHayBombonesParaTi("Lo siento, no te han tocado bombones esta vez :(");
            }

        }catch(InputMismatchException e){
            //Si has metido una letra en vez de un número en las primeras preguntas
            System.err.println("No has metido un número entero");
            e.printStackTrace();
        }catch(FileNotFoundException e){
            //Si el archivo no existe o no se encuentra o no se lee bien
            System.err.println("No se encuentra el archivo");
            e.printStackTrace();
        }catch(NoHayBombonesParaTi e){
            System.err.println(e.toString());
        }catch(Exception e){
            //Todo lo demás
            System.err.println("Algo ha salido mal");
            e.printStackTrace();
        }
        
        
    }
}
