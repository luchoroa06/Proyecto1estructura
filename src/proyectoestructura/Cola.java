/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoestructura;

/**
 *Implementación de una cola para vértices
 * utilizada para BFS 
 * @author sebas
 */
public class Cola {
    NodoCola primero;       // Primer nodo de la cola 
    NodoCola ultimo;        // Último nodo de la cola (para encolar)

    public Cola() {
        this.primero = null;
        this.ultimo = null;
    }
   /**
     * Agrega un vértice al final de la cola
     * @param a Vértice a encolar
     */
    public void encolar(Vertice a){
        NodoCola nuevo = new NodoCola(a);
        if(primero == null){
            primero = ultimo = nuevo;
        }
        ultimo.sig = nuevo;
        ultimo = nuevo;
    }
     /**
     * Remueve y retorna el primer vértice de la cola
     * @return Vértice removido o null si cola vacía
     */   
    public NodoCola desencolar(){
        if(primero!= null){
        NodoCola denigrado = primero;
        primero = primero.sig;
        return denigrado;
        }
        return null;
    
    } 
    
    
}

