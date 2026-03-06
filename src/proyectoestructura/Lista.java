/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoestructura;

/**
 *
 * Implementaciones de los metodos de una lista doblemente enlazada que manejan las aristas de cada vertice
 */
public class Lista {

    Nodo primero;       //Primero nodo de la lista de aristas
    int t;              // Tamaño de la lista
/**
 * constructor que crea la lista vacia
 */
    public Lista() {
        this.primero = null;
        this.t = 0;

    }
    /**
     * metodo de insertar arista al final de la lista
     * @param dato Nombre de la proteina
     * @param peso peso de la proteina
     */

    public void insertar(String dato, int peso) {
        Nodo nuevo = new Nodo(dato, peso);
        if (primero == null) {          //por si la lsita esta vacia
            primero = nuevo;
            t++;
        } else {
            Nodo aux = primero;
            while (aux.sig != null) {
                aux = aux.sig;        //recorre hasta el final de la lista
            }
            aux.sig = nuevo;          // conecta el nuevo nodo
            nuevo.ant = aux;
            t++;
        }

    }
    /**
     * Metodo que elimina una arista por su nombre
     * @param dato Nombre de la proteina a eliminar
     */

    public void eliminar(String dato) {
        if (primero != null) {
            Nodo aux = primero;
            if (aux.dato.equals(dato)) {        // Si es el primero
                primero = primero.sig;
                primero.ant = null;
                t--;
                
                return;
            }
            while (aux.sig != null && !aux.sig.dato.equals(dato)) {
                aux = aux.sig;                  //busca el nodo a eliminar
            }
            if (aux.sig != null) {
                aux.sig = aux.sig.sig;          //reasigna los punteros 
                aux.sig.ant = aux;
                t--;
            }

        }
    }
    /**
     * Metodo de buscar una arista por su nombre de 
     * @param dato Proteina a buscar
     * @return retorna el nodo buscado o null por si no existe el nodo
     */

    public Nodo buscar(String dato) {
        if (primero != null) {
            Nodo aux = primero;
            while (aux != null && !aux.dato.equals(dato)) {
                aux = aux.sig;

            }
            return aux;
        }
        return null;
    }

    /**
     * genera en string todas las aristas
     * @return El string con todas las aristas 
     */
    public String mostrar() {
        String salida = "";
        Nodo aux = primero;

        while (aux != null) {
            salida += "[" + aux.dato + "] ";
            aux = aux.sig;
        }
        return salida; 
    }
    /**
     * Obtiene una array con todos los nombres de las proteinas
     * @return Array de string con los nombres
     */
    public String [] getNombres(){
            Nodo aux = primero;
            String [] x = new String[t];
            int i = 0;
            while (aux != null){
                x [i]=aux.dato;
                aux= aux.sig;
                i++;
                        
            }
            return x;
    }
}
