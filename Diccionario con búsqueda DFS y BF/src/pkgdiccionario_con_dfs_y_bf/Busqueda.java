/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgdiccionario_con_dfs_y_bf;

import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.util.*;
import javax.swing.*;


/**
 *
 * @authors
 *      Osvaldo Domínguez
 *      Ambar Pang
 *      Joel Ruiz
 *      Guiomar Lara
 *      Randy Urriola
 */
public class Busqueda {
    //String que captura la dirección del usuario para determinar la ubicación del diccionario
    private String ruta_palabras = System.getProperties().getProperty("user.dir");
    private Fichero a = new Fichero();
    private File archivo = new File(ruta_palabras+"//DiccionarioDEF.txt");//Objeto archivo con la ruta del diccionario
    private String [] diccionario = a.LeerPalabras(archivo);
    private ArrayList<Nodo> palabras = new ArrayList<Nodo>();//Lista que contiene todas las palabras posibles
    public String letra_hijos = "";
    
    public void Buscar(String guia, int alg)
    {
        Nodo raiz = new Nodo(guia, diccionario);
        Principal p = new Principal();
        if(alg == 1)
        {
            DFS(raiz);
        }
        else if(alg == 2)
        {
            BF(raiz);
        }
        letra_hijos = raiz.hijos_letras;       
    } 
    
    //Método de primero en profundidad
    public void DFS(Nodo raiz)
    {
        Stack<Nodo> abiertos = new Stack<Nodo>();//Pila de Nodos abiertos 
        abiertos.push(raiz);
        palabras.clear();
        Nodo actual = abiertos.peek();
        while(!abiertos.isEmpty() && palabras.size() < 15)
        {
            abiertos.pop();
            actual.Expandir(actual);
            if(!actual.hijos.isEmpty())
            {
                //Ciclo For que guarda la lista de hijos desde el último al primero para que la pila esté en el orden necesario
                for(int i = actual.hijos.size(); i > 0; i--)
                {
                    abiertos.push(actual.hijos.get(i-1));
                }
            }
            //Si la palabra del Nodo es una posible solución se agrega
            if(actual.pertenece)
            {
                palabras.add(actual);
            }
            if(!abiertos.isEmpty())
            {
                actual = abiertos.peek();
            }        
        }
    }
    
    //Método de primero el mejor
    public void BF(Nodo raiz)
    {
        ArrayList<Nodo> abiertos = new ArrayList<Nodo>();//Lista que guarda los Nodos abiertos
        abiertos.add(raiz);
        palabras.clear();
        Nodo actual = abiertos.get(0);
        while(!abiertos.isEmpty() && palabras.size()<15){
            abiertos.remove(0);
            actual.Expandir(actual);
            if(!actual.hijos.isEmpty())
            {
                for(int i = 0; i < actual.hijos.size(); i++)
                {
                    abiertos.add(actual.hijos.get(i));
                }
                //Método sort que ordena la lita @abiertos en orden ascendente
                Collections.sort(abiertos);
            }
            //Si la palabra del Nodo es una posible solución se agrega
            if(actual.pertenece)
            {
                palabras.add(actual);
            }
            if(!abiertos.isEmpty())
            {
                actual = abiertos.get(0);
            } 
        }
    }
    
    //Método que imprime las respuestas en los JTextField
    public void Imprimir(JTextField [] respuestas)
    {
        if(!palabras.isEmpty())
        {
            int i=0;
            for(Nodo resp : palabras)
            {
                respuestas[i].setBackground(Color.WHITE);
                respuestas[i].setText(resp.cont_antes);
                respuestas[i].setForeground(Color.BLACK);
                i++;
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Disculpe, no se ha podido encontrar ninguna "
                    + "palabras con estas letras.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    //Retorna las letraspara imprimirlas en el árbol 
    public String getLetas_Hijo()
    {
        return letra_hijos;
    }
    
}

