/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoestructura;

/**
 *
 * @author PC
 */
public class Cola {
    NodoCola primero;
    NodoCola ultimo;

    public Cola() {
        this.primero = null;
        this.ultimo = null;
    }

    public void encolar(Vertice a){
        NodoCola nuevo = new NodoCola(a);
        if(primero == null){
            primero = ultimo = nuevo;
        }
        ultimo.sig = nuevo;
        ultimo = nuevo;
    }
    
    public NodoCola desencolar(){
        if(primero!= null){
        NodoCola denigrado = primero;
        primero = primero.sig;
        return denigrado;
        }
        return null;
    
    } 
    
    
}
