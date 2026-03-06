/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoestructura;

/**
 * represneta un vertice en el grafo
 * cada vertice almacena una proteina y sus conexiones 
 */
public class Vertice {
    public String proteina;         // Nombre de la proteina que almacena el vertice
    public Lista aristas;                  // Lista de aristas que conectan el vertice
    Vertice sig;                    // puntero de siguinte
    Vertice ant;                    // puntero del anterior

    //contructor para la creacion del vertice con el nombre de su proteina
    public Vertice(String proteina) {
        this.proteina = proteina;              
        this.aristas = new Lista();       //crea una nueva lista vacia de las aristas
        this.sig = null;                  
        this.ant = null;
    }
    
    
    
}
