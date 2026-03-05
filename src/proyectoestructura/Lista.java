/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoestructura;

/**
 *
 * @author PC
 */
public class Lista {

    Nodo primero;
    int t;

    public Lista() {
        this.primero = null;
        this.t = 0;

    }

    public void insertar(String dato, int peso) {
        Nodo nuevo = new Nodo(dato, peso);
        if (primero == null) {
            primero = nuevo;
            t++;
        } else {
            Nodo aux = primero;
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
            Nodo aux = primero;
            if (aux.dato.equals(dato)) {
                primero = primero.sig;
                primero.ant = null;
                t--;
                
                return;
            }
            while (aux.sig != null && !aux.sig.dato.equals(dato)) {
                aux = aux.sig;
            }
            if (aux.sig != null) {
                aux.sig = aux.sig.sig;
                aux.sig.ant = aux;
                t--;
            }

        }
    }

    public Nodo buscar(String dato) {
        if (primero != null) {
            Nodo aux = primero;
            while (aux != null && aux.dato.equals(dato)) {
                aux = aux.sig;

            }
            return aux;
        }
        return null;
    }

    public String mostrar() {
        String salida = "";
        Nodo aux = primero;

        while (aux != null) {
            salida += "[" + aux.dato + "] ";
            aux = aux.sig;
        }
        return salida; 
    }
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
