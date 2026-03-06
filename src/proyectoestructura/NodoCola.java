/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoestructura;

/**
 * Nodo especial para implementar una cola de vértices
 * @author sebas
 */
public class NodoCola {
    NodoCola sig;       // Siguiente nodo en la cola
    Vertice dato;       // Vértice almacenado


    public NodoCola(Vertice dato) {
        this.sig = null;
        this.dato = dato;
    }
    
    
    
    
}

