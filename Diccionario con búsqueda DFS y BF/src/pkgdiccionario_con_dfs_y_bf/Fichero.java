/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgdiccionario_con_dfs_y_bf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @authors
 *      Osvaldo Domínguez
 *      Ambar Pang
 *      Joel Ruiz
 *      Guiomar Lara
 *      Randy Urriola
 */
public class Fichero {
    
    //Método con que el archivo retornará todas las palabras existentes en el diccionario
    public String [] LeerPalabras(File ruta){
        int cant_plb = 0;
        //Captura de excepciones si la ruta no es encontrada
        try{
            //Si el archivo existe
            if(ruta.exists()){
                BufferedReader bf = new BufferedReader(new FileReader(ruta));
                //Ciclo hasta que bf tenga datos
                while(bf.readLine() != null){
                    cant_plb = cant_plb +1;
                }
            }
        }catch(Exception e){
                JOptionPane.showMessageDialog(null, e.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
         }
        
        String [] diccionario = new String[cant_plb];
        int i = 0;
        //Lectura y guardado de cada palabra en el arreglo
        try{
            Scanner leer = new Scanner(ruta);
            while(leer.hasNext()){
                diccionario[i]= leer.nextLine();
                i++;
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"El error es: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
         }
        return diccionario;
    }
    
}
