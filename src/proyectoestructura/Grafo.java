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
/**
 * Realiza un recorrido BFS para encontrar todos los 
 * componentes conexos del grafo.
 * 
 * @return String con la lista de componentes conexos encontrados mediante BFS
 */
public String BFS() {
    String resultado = "Componentes Conexos (BFS):\n";
    boolean[] visitado = new boolean[t];          // Array para marcar vértices visitados
    Vertice[] todos = getVertices();               // Obtiene todos los vértices del grafo
    int contador = 1;                               // Contador de componentes

    // Itera sobre todos los vértices
    for (int i = 0; i < t; i++) {
        if (!visitado[i]) {                          // Si el vértice no ha sido visitado, es un nuevo componente
            resultado += "Componente " + (contador++) + ": ";
            
            Cola cola = new Cola();                  // Cola para BFS
            cola.encolar(todos[i]);                   // Encola el vértice inicial
            visitado[i] = true;                        // Lo marca como visitado

            // Mientras la cola no esté vacía
            while (cola.primero != null) {
                Vertice actual = cola.desencolar().dato;  // Obtiene el vértice del frente de la cola
                resultado += actual.proteina + " ";        // Agrega el vértice al resultado

                // Itera sobre todas las aristas (vecinos) del vértice actual
                Nodo arista = actual.aristas.primero;
                while (arista != null) {
                    int indiceVecino = encontrarIndice(arista.dato, todos);  // Busca índice del vecino
                    if (indiceVecino != -1 && !visitado[indiceVecino]) {     // Si no ha sido visitado
                        visitado[indiceVecino] = true;                         // Lo marca como visitado
                        cola.encolar(todos[indiceVecino]);                     // Lo encola para procesar después
                    }
                    arista = arista.sig;                                        // Pasa al siguiente
                }
            }
            resultado += "\n";
        }
    }
    return resultado;
}

/**
 * Realiza un recorrido DFS (Depth-First Search) para encontrar todos los 
 * componentes conexos del grafo.
 * 
 * @return String con la lista de componentes conexos encontrados
 */
public String DFS() {
    String resultado = "Componentes Conexos (DFS):\n";
    boolean[] visitado = new boolean[t];          // Array para marcar vértices visitados
    Vertice[] todos = getVertices();               // Obtiene todos los vértices
    int contador = 1;                               // Contador de componentes

    // Itera sobre todos los vértices
    for (int i = 0; i < t; i++) {
        if (!visitado[i]) {                          // Nuevo componente encontrado
            resultado += "Componente " + (contador++) + ": ";
            // Llama al método recursivo para recorrer el componente completo
            resultado = recorrerDFS(todos[i], visitado, todos, resultado);
            resultado += "\n";
        }
    }
    return resultado;
}

/**
 * Método auxiliar recursivo para DFS.
 * Realiza el recorrido en profundidad a partir de un vértice dado.
 * 
 * @param v Vértice actual que se está visitando
 * @param visitado Array de booleanos que indica qué vértices han sido visitados
 * @param todos Array con todos los vértices del grafo
 * @param res String acumulado con el resultado hasta el momento
 * @return String actualizado con el nuevo vértice visitado
 */
private String recorrerDFS(Vertice v, boolean[] visitado, Vertice[] todos, String res) {
    int idx = encontrarIndice(v.proteina, todos);  // Encuentra el índice del vértice actual
    visitado[idx] = true;                           // Marca como visitado
    res += v.proteina + " ";                         // Agrega al resultado

    // Itera sobre todas las aristas (vecinos) del vértice actual
    Nodo arista = v.aristas.primero;
    while (arista != null) {
        int idxVecino = encontrarIndice(arista.dato, todos);  // Índice del vecino
        if (idxVecino != -1 && !visitado[idxVecino]) {         // Si no ha sido visitado
            // Llamada recursiva para explorar el vecino en profundidad
            res = recorrerDFS(todos[idxVecino], visitado, todos, res);
        }
        arista = arista.sig;                                    // Siguiente vecino
    }
    return res;
}

/**
 * Implementación del algoritmo de Dijkstra para encontrar la ruta más corta
 * entre dos proteínas en el grafo 
 * 
 * @param inicio Nombre de la proteína de inicio
 * @param fin Nombre de la proteína de destino
 * @return String con el camino más corto y el peso total, o mensaje de error
 */
public String dijkstra(String inicio, String fin) {
    Vertice[] vertices = getVertices();               // Obtiene todos los vértices
    int n = vertices.length;
    int startIdx = encontrarIndice(inicio, vertices);  // Índice del primer vertice
    int endIdx = encontrarIndice(fin, vertices);       // Índice del vertice donde quieres llegar

    if (startIdx == -1 || endIdx == -1) return "Proteína no encontrada.";

    int[] distancias = new int[n];                     // Distancia mínima desde inicio a cada vértice
    int[] padres = new int[n];                          // Almacena el vértice anterior en la ruta óptima
    boolean[] visitados = new boolean[n];               

    // Inicialización: todas las distancias son "infinito" y padres son -1
    for (int i = 0; i < n; i++) {
        distancias[i] = Integer.MAX_VALUE;
        padres[i] = -1;
        visitados[i] = false;
    }

    distancias[startIdx] = 0;  // La distancia al vértice inicio es 0

    // Bucle principal de Dijkstra 
    for (int i = 0; i < n - 1; i++) {
        //Seleccionar el vértice no visitado con la distancia mínima
        int u = -1;
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            if (!visitados[j] && distancias[j] <= min) {
                min = distancias[j];
                u = j;
            }
        }

        //Si no hay vértice alcanzable o la distancia es infinita, terminamos
        if (u == -1 || distancias[u] == Integer.MAX_VALUE) break;
        
        //Marcar como visitado (ya tenemos la distancia mínima para este vértice)
        visitados[u] = true;

        //Relajar las aristas del vértice seleccionado
        Nodo arista = vertices[u].aristas.primero;
        while (arista != null) {
            int v = encontrarIndice(arista.dato, vertices);     // Índice del vecino
            if (v != -1 && !visitados[v]) {
                int nuevaDist = distancias[u] + arista.peso;    // Distancia a través de u
                if (nuevaDist < distancias[v]) {                // Si encontramos un camino más corto
                    distancias[v] = nuevaDist;                  // Actualizamos distancia
                    padres[v] = u;                              // Actualizamos el padre
                }
            }
            arista = arista.sig;
        }
    }

    // Verificar si hay camino
    if (distancias[endIdx] == Integer.MAX_VALUE) 
        return "No hay camino entre " + inicio + " y " + fin;

    // Reconstruir el camino desde el destino hasta el inicio usando el array de padres
    String camino = "";
    int temp = endIdx;
    while (temp != -1) {
        camino = vertices[temp].proteina + (camino.isEmpty() ? "" : " -> ") + camino;
        temp = padres[temp];
    }

    return "Camino más corto: " + camino + " (Peso total: " + distancias[endIdx] + ")";
}

/**
 * Método auxiliar para encontrar el índice de un vértice en el array
 * dado su nombre
 * 
 * @param nombre Nombre de la proteína a buscar
 * @param lista Array de vértices donde buscar
 * @return Índice del vértice o -1 si no se encuentra
 */
private int encontrarIndice(String nombre, Vertice[] lista) {
    for (int i = 0; i < lista.length; i++) {
        if (lista[i].proteina.equals(nombre)) return i;
    }
    return -1;
}

/**
 * Identifica las proteínas "hub" del grafo, es decir, aquellas con el
 * mayor número de conexiones
 * 
 * @return String con las proteínas que tienen el máximo de conexiones
 */
public String encontrarHubs() {
    if (primero == null) return "El grafo está vacío.";

    int maxConexiones = -1;
    Vertice aux = primero;

    // Encuenta el número máximo de conexiones
    while (aux != null) {
        int conexionesActuales = 0;
        Nodo arista = aux.aristas.primero;
        while (arista != null) {
            conexionesActuales++;
            arista = arista.sig;
        }

        if (conexionesActuales > maxConexiones) {
            maxConexiones = conexionesActuales;  // Actualiza el máximo
        }
        aux = aux.sig;
    }

    //Encuentra todos los vértices que tengan ese máximo
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
                hubs += ", ";  // Agrega separador si ya hay uno antes
            }
            hubs += "[" + aux.proteina + "]";
            primeroEncontrado = true;
        }
        aux = aux.sig;
    }

    return hubs;
    }
}
