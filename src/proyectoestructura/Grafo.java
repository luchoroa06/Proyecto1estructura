/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoestructura;

/**
 *
 * @author PC
 */
public class Grafo {
    Vertice primero;
    int t;

    public Grafo() {
        this.primero = null;
        t = 0;
    }

    public void insertar(String dato) {
        Vertice a = this.buscar(dato);
        if (a != null) {
            return;
        }
        Vertice nuevo = new Vertice(dato);
        if (primero == null) {
            primero = nuevo;
            t++;
        } else {
            Vertice aux = primero;
            while (aux.sig != null) {
                aux = aux.sig;
            }
            aux.sig = nuevo;
            nuevo.ant = aux.sig;
            t++;
        }

    }

    public void eliminar(String dato) {
        if (primero != null) {
            Vertice aux = primero;
            if (aux.proteina.equals(dato)) {
                primero = primero.sig;
                primero.ant = null;
                t--;

                return;
            }
            while (aux.sig != null && !aux.sig.proteina.equals(dato)) {
                aux = aux.sig;
            }
            if (aux.sig != null) {
                aux.sig = aux.sig.sig;
                aux.sig.ant = aux;
                t--;
            }
            aux = primero;
            while (aux != null) {
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

    public String mostrar() {
        String salida = "";
        Vertice aux = primero;

        while (aux != null) {
            salida += "[" + aux.proteina + "] ---> " + aux.aristas.mostrar() + "\n";
            aux = aux.sig;
        }
        return salida;
    }

    public void insertarArista(Vertice a, Vertice b, int peso) {
        if (a != null && b != null) {
            b.aristas.insertar(a.proteina, peso);
            a.aristas.insertar(b.proteina, peso);
        }
        
        
    
    }
    
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
}
