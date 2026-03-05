/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoestructura;

/**
 *
 * @author PC
 */
public class Vertice {
    String proteina;
    Lista aristas;
    Vertice sig;
    Vertice ant;

    public Vertice(String proteina) {
        this.proteina = proteina;
        this.aristas = new Lista();
        this.sig = null;
        this.ant = null;
    }
    
    
    
}
