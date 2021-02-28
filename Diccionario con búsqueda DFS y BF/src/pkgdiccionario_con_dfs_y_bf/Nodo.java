/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgdiccionario_con_dfs_y_bf;

import java.awt.List;
import java.text.Collator;
import java.util.*;

/**
 *
 * @authors 
 *      Osvaldo Domínguez
 *      Ambar Pang
 *      Joel Ruiz
 *      Guiomar Lara
 *      Randy Urriola
 * A la clase @Nodo se le implementa la clase Comparable para poder comparar un ArraList y ordenarlo.
 */
public class Nodo implements Comparable<Nodo>{
    
    public char valor;//Caracter del nodo
    public Nodo padre;//Nodo padre del nodo actual
    public int profundidad = 0;//La profundidad del Nodo
    public int heuristica = 0;//La heurística del nodo 
    public ArrayList<Nodo> hijos = new ArrayList<Nodo>();//Lista de Nodos hijos
    public String hijos_letras;//Caracteres con los cuales se puede relacionar la cadena a buscar
    public String cont_antes;//Caracteres contenidos antes del caracter actual
    public String [] diccionario;//Guarda todas las palabras existentes en el fichero DiddionarioDEF.txt
    public boolean pertenece = false;//Determina si el nodo que se manipula es igual a alguna palabra del diccionario y así añadirla a las respuestas
    private Random rnd = new Random();//Función random para darle una heurística a cada nodo

    //Método constructor para inicializar el Nodo con su valor
    public Nodo(char v)
    {
        this.valor = v;
        this.padre = null;
    }
    
    //Método constructor en el cual se le asigna todas las palabras al Nodo y el String ingresado en el JTextField
    public Nodo(String guia, String [] dicc)
    {
        this.cont_antes = guia;
        this.diccionario = new String[dicc.length];
        for(int i = 0; i < dicc.length; i++)
        {
            this.diccionario[i] = dicc[i];
        }
    }
    
    //Método en el que se crean los hijos del Nodo actual
    public void Expandir(Nodo raiz)
    {
        //Función que configura que 'A', 'a' y 'á' sean iguales
        Collator comparar = Collator.getInstance(new Locale("es"));
        hijos_letras="";//Guarda todos los caracteres de los hijos que puede formar
        for(int i = 0; i < diccionario.length; i++)
        {
            comparar.setStrength(Collator.PRIMARY);
            String palabra = diccionario[i];
            //Siempre que la palabra sea mayor al String guía se podrá realizar una comparación
            if(palabra.length() >= raiz.cont_antes.length())
            {
                String temp = palabra.substring(0, raiz.cont_antes.length());
                if(!palabra.equals(raiz.cont_antes))
                {
                    if(temp == raiz.cont_antes || comparar.compare(temp, raiz.cont_antes) == 0)
                    {
                        if(palabra.length() != raiz.cont_antes.length())
                        {
                            //@letra obtiene el nuevo caracter con el cual se comparará
                            char letra = palabra.charAt(raiz.cont_antes.length());
                            //Si el caracter es mayúscula lo transformará a minúscula
                            if(Character.isUpperCase(letra))
                            {
                                letra = Character.toLowerCase(letra);
                            }
                            //Si la letra a evaluar ya no se ha añadido a lista de Nodos hijos entonces se crea un nuevo nodo
                            if(!ContieneLetra(raiz, letra))
                            {
                                this.hijos_letras = this.hijos_letras + letra;
                                Nodo nuevo = new Nodo(this. cont_antes + letra, this.diccionario);
                                hijos.add(nuevo);
                                nuevo.valor = letra;
                                nuevo.heuristica = rnd.nextInt(849)+1;
                                nuevo.profundidad = this.profundidad + 1;
                                nuevo.padre = raiz;
                                nuevo.heuristica = this.heuristica + nuevo.heuristica;
                            }
                        }
                    }
                }
                //Si la palabra que se evalua es igual a la palabra del Nodo entonces esta es una posible respuesta
                if((cont_antes.equals(palabra) || comparar.compare(cont_antes,palabra) == 0))
                {
                    this.pertenece = true;
                }
            }
        }
    }
    
    //Método que compara si ya la letra está incluida en la lista de Nodos hijos
    public boolean ContieneLetra(Nodo raiz, char letra)
    {
        boolean cont = false;
        for(Nodo evaluar : raiz.hijos)
        {
            if(evaluar.valor == letra)
            {
                cont = true;
            }
        }
        return cont;
    }
    
    //Método que compara si la heurística del Nodo es mayor y así ordenarlo de menor a mayor
    public int compareTo(Nodo n){
        if(n.heuristica > this.heuristica){
            return -1;
        }
        else if(n.heuristica > this.heuristica){
            return 0;
        }
        else{
            return 1;
        }
    }
    
}
