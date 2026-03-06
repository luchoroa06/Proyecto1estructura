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
    public String BFS() {
        String resultado = "Componentes Conexos (BFS):\n";
        boolean[] visitado = new boolean[t];
        Vertice[] todos = getVertices();
        int contador = 1;

        for (int i = 0; i < t; i++) {
            if (!visitado[i]) {
                resultado += "Componente " + (contador++) + ": ";
                Cola cola = new Cola();
                cola.encolar(todos[i]);
                visitado[i] = true;

                while (cola.primero != null) {
                    Vertice actual = cola.desencolar().dato;
                    resultado += actual.proteina + " ";

                    Nodo arista = actual.aristas.primero;
                    while (arista != null) {
                        int indiceVecino = encontrarIndice(arista.dato, todos);
                        if (indiceVecino != -1 && !visitado[indiceVecino]) {
                            visitado[indiceVecino] = true;
                            cola.encolar(todos[indiceVecino]);
                        }
                        arista = arista.sig;
                    }
                }
                resultado += "\n";
            }
        }
        return resultado;
    }

    public String DFS() {
        String resultado = "Componentes Conexos (DFS):\n";
        boolean[] visitado = new boolean[t];
        Vertice[] todos = getVertices();
        int contador = 1;

        for (int i = 0; i < t; i++) {
            if (!visitado[i]) {
                resultado += "Componente " + (contador++) + ": ";
                resultado = recorrerDFS(todos[i], visitado, todos, resultado);
                resultado += "\n";
            }
        }
        return resultado;
    }

    private String recorrerDFS(Vertice v, boolean[] visitado, Vertice[] todos, String res) {
        int idx = encontrarIndice(v.proteina, todos);
        visitado[idx] = true;
        res += v.proteina + " ";

        Nodo arista = v.aristas.primero;
        while (arista != null) {
            int idxVecino = encontrarIndice(arista.dato, todos);
            if (idxVecino != -1 && !visitado[idxVecino]) {
                res = recorrerDFS(todos[idxVecino], visitado, todos, res);
            }
            arista = arista.sig;
        }
        return res;
    }

    public String dijkstra(String inicio, String fin) {
        Vertice[] vertices = getVertices();
        int n = vertices.length;
        int startIdx = encontrarIndice(inicio, vertices);
        int endIdx = encontrarIndice(fin, vertices);

        if (startIdx == -1 || endIdx == -1) return "Proteína no encontrada.";

        int[] distancias = new int[n];
        int[] padres = new int[n];
        boolean[] visitados = new boolean[n];

        for (int i = 0; i < n; i++) {
            distancias[i] = Integer.MAX_VALUE;
            padres[i] = -1;
            visitados[i] = false;
        }

        distancias[startIdx] = 0;

        for (int i = 0; i < n - 1; i++) {
            int u = -1;
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (!visitados[j] && distancias[j] <= min) {
                    min = distancias[j];
                    u = j;
                }
            }

            if (u == -1 || distancias[u] == Integer.MAX_VALUE) break;
            visitados[u] = true;

            Nodo arista = vertices[u].aristas.primero;
            while (arista != null) {
                int v = encontrarIndice(arista.dato, vertices);
                if (v != -1 && !visitados[v]) {
                    int nuevaDist = distancias[u] + arista.peso;
                    if (nuevaDist < distancias[v]) {
                        distancias[v] = nuevaDist;
                        padres[v] = u;
                    }
                }
                arista = arista.sig;
            }
        }

        if (distancias[endIdx] == Integer.MAX_VALUE) return "No hay camino entre " + inicio + " y " + fin;

        String camino = "";
        int temp = endIdx;
        while (temp != -1) {
            camino = vertices[temp].proteina + (camino.isEmpty() ? "" : " -> ") + camino;
            temp = padres[temp];
        }

        return "Camino más corto: " + camino + " (Peso total: " + distancias[endIdx] + ")";
    }

    private int encontrarIndice(String nombre, Vertice[] lista) {
        for (int i = 0; i < lista.length; i++) {
            if (lista[i].proteina.equals(nombre)) return i;
        }
        return -1;
    }
    
    public String encontrarHubs() {
    if (primero == null) return "El grafo está vacío.";

    int maxConexiones = -1;
    Vertice aux = primero;

    while (aux != null) {
        int conexionesActuales = 0;
        Nodo arista = aux.aristas.primero;
        while (arista != null) {
            conexionesActuales++;
            arista = arista.sig;
        }

        if (conexionesActuales > maxConexiones) {
            maxConexiones = conexionesActuales;
        }
        aux = aux.sig;
    }

    String hubs = "Proteína(s) Hub (con " + maxConexiones + " conexiones): ";
    aux = primero;
    boolean primeroEncontrado = false;

    while (aux != null) {
        int conexionesActuales = 0;
        Nodo arista = aux.aristas.primero;
        while (arista != null) {
            conexionesActuales++;
            arista = arista.sig;
        }

        if (conexionesActuales == maxConexiones) {
            if (primeroEncontrado) {
                hubs += ", ";
            }
            hubs += "[" + aux.proteina + "]";
            primeroEncontrado = true;
        }
        aux = aux.sig;
    }

    return hubs;
}
}
