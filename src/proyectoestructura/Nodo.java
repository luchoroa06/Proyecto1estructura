/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoestructura;

/**
 *  Representa un nodo en la listas
 *  almacena una arista del grafo
 */
public class Nodo {
    Nodo sig;              //puntero al siguiente de las lista de aristas
    Nodo ant;              //puntero del anterior de la lista de aristas
    String dato;           //nombre de la nueva proteina
    int peso;              //Peso de la arista

    
    /**
     * contrustor para crear un nuevo nodo para una arista
     * @param dato Nombre de la proteina
     * @param peso Peso de la proteina
     */
    public Nodo(String dato, int peso) {
        this.sig = null;
        this.ant = null;
        this.dato = dato;
        this.peso = peso;
    }

   
    
    
    
    
}
