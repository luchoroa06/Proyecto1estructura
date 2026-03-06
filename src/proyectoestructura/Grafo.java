/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoestructura;

/**
 * Implementacion del grafo
 * maneja todos los vertices y sus conexiones
 * @author PC
 */
public class Grafo {
    Vertice primero;        //primer vertices de la lista de vertice
    int t;                  // Cantidad total de vertices

    /**
     * Contructor. crea un grafo vacio
     */
    public Grafo() {
        this.primero = null;
        t = 0;
    }

    /**
     * Metodo que inserta un nuevo vertice aol grafo
     * @param dato Nombre de la proteina
     */
    public void insertar(String dato) {
        Vertice a = this.buscar(dato);
        if (a != null) {            //si ya existe no lo inserta
            return;
        }
        Vertice nuevo = new Vertice(dato);
        if (primero == null) {          //si el grafo esta vacio inserta el primero
            primero = nuevo;
            t++;
        } else {
            Vertice aux = primero;
            while (aux.sig != null) {
                aux = aux.sig;          //va al final del grafo
            }
            aux.sig = nuevo;            //Conecta el apuntador al nuevo
            nuevo.ant = aux;
            t++;
        }

    }

    /**
     * Metodo que elimina el vertice del grafo
     * @param dato Nombre de la porteina a eliminar
     */
    public void eliminar(String dato) {
        if (primero != null) {
            Vertice aux = primero;
            if (aux.proteina.equals(dato)) {        //Por si es el primero
                primero = primero.sig;
                primero.ant = null;
                t--;

                return;
            }
            while (aux.sig != null && !aux.sig.proteina.equals(dato)) {     //busca el vertice a eliminar
                aux = aux.sig;
            }
            if (aux.sig != null) {
                aux.sig = aux.sig.sig;
                aux.sig.ant = aux;
                t--;
            }
            aux = primero;
            while (aux != null) {                   // elimina todas las aristas que estaban conectada ese vertice
                if (aux.aristas.primero.equals(dato)) {
                    aux.aristas.primero = aux.aristas.primero.sig;
                    aux.aristas.primero.ant = null;

                } else {
                    Nodo aux2 = aux.aristas.primero;
                    while (!aux2.dato.equals(dato)) {
                        aux2 = aux2.sig;

                    }
                    aux2.ant.sig = aux2.sig;
                    aux2.sig.ant = aux2.ant;
                }
                aux = aux.sig;
            }

        }
    }
/**
 * Busca el vertice por su nombre
 * @param dato Nombre de la proteina a buscar
 * @return Vertice encontrado o null por si no esta
 */
    public Vertice buscar(String dato) {
        if (primero != null) {
            Vertice aux = primero;
            while (aux != null && !aux.proteina.equals(dato)) {
                aux = aux.sig;

            }
            return aux;
        }
        return null;
    }
/**
 * Muestra todo el grafo
 * @return la representacion del grafo en String
 */
    public String mostrar() {
        String salida = "";
        Vertice aux = primero;

        while (aux != null) {
            salida += "[" + aux.proteina + "] ---> " + aux.aristas.mostrar() + "\n";
            aux = aux.sig;
        }
        return salida;
    }
/**
 * Inserta una arista entre dos vertices
 * @param a Vertice de oriigen
 * @param b Vertice destino
 * @param peso Peso de la arista
 */
    public void insertarArista(Vertice a, Vertice b, int peso) {
        if (a != null && b != null) {
            b.aristas.insertar(a.proteina, peso);
            a.aristas.insertar(b.proteina, peso);
        }
        
    }

 /** 
 *Encuentra el vertice con mas conexiones
 * @return Vertice de mayor conexiones o mayor numero de aristas
 */ 
    public Vertice mostrador_proteina(){
        int cont = 0;
        Vertice aux = primero;
        Vertice Mayorzote = aux;
        int Valorista = 0;
        while(aux != null){
            cont = 0;
            Nodo aux2 = aux.aristas.primero;
            while(aux2 != null){
                aux2 = aux2.sig;
                cont++;
                }
            if(cont > Valorista){
                Valorista = cont;
                Mayorzote = aux;
            }
            aux = aux.sig;
        }
        return Mayorzote;
    }
    /**
     * Obtiene array con todos los vertices
     * @return array de vertices
     */
    public Vertice []getVertices(){
        Vertice[]x = new Vertice [this.t];
        Vertice aux = primero;
        int i = 0;
        while (aux != null){
            x [i]= aux;
            aux = aux.sig;
            i++;
        }
        return x ;
    }
}
