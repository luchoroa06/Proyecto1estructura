/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoestructura;
    import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 *Maneja la lectura y escritura de archivos CSV para persistencia del grafo
 * @author sebas
 */
public class Lectorcsv {
/**
 * Lee un archivo CSV y construye el grafo
 * Formato esperado: proteina1,proteina2,peso
 * @param archivo Archivo CSV a leer
 * @param grafo Grafo donde se insertarán los datos
 */

public void leerArchivo(File archivo, Grafo grafo) {
    String linea;
    String separador = ","; 

    try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
        

        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(separador);
            
            if (datos.length >= 3) {
                String s1 = datos[0].trim();        // Primera proteína
                String s2 = datos[1].trim();        // Segunda proteína
                int n1 = Integer.parseInt(datos[2].trim()); // Peso
                
            grafo.insertar(s1);                      // Inserta vértices
            grafo.insertar(s2);
            grafo.insertarArista(grafo.buscar(s1),grafo.buscar(s2), n1);
            
            }
        }
        
       
            System.out.println(grafo.mostrar());  // Muestra resultado
        
    } catch (Exception e) {
        System.out.println("Error al leer: " + e.getMessage());
    }
}
    /**
     * Guarda el grafo en un archivo CSV
     * @param archivo Archivo donde guardar
     * @param grafo Grafo a guardar
     */
public void guardarArchivo(File archivo, Grafo grafo) {
   
    try (PrintWriter pw = new PrintWriter(new FileWriter(archivo, false))) {
        
       Vertice aux = grafo.primero;
       while (aux != null ){
           Nodo aux2 = aux.aristas.primero ;
           while (aux2 != null) {
                // Escribe cada arista como una línea: proteina1,proteina2,peso
              String linea = String.format("%s,%s,%d", 
                    aux.proteina,
                    aux2.dato,
                    aux2.peso);
            
            pw.println(linea);
            aux2=aux2.sig;
           }
           aux=aux.sig;
       }
        
        javax.swing.JOptionPane.showMessageDialog(null, "Archivo guardado exitosamente");
        
    } catch (IOException e) {
        System.out.println("Error al guardar: " + e.getMessage());
        javax.swing.JOptionPane.showMessageDialog(null, "Error al guardar el archivo");
    }
}
}
