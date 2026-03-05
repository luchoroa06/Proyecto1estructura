/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoestructura;
    import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author sebas
 */
public class Lectorcsv {


public void leerArchivo(File archivo, Grafo grafo) {
    String linea;
    String separador = ","; 

    try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
        

        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(separador);
            
            if (datos.length >= 3) {
                String s1 = datos[0].trim();
                String s2 = datos[1].trim();
                int n1 = Integer.parseInt(datos[2].trim());
                
            grafo.insertar(s1);
            grafo.insertar(s2);
            grafo.insertarArista(grafo.buscar(s1),grafo.buscar(s2), n1);
            
            }
        }
        
       
            System.out.println(grafo.mostrar());
        
    } catch (Exception e) {
        System.out.println("Error al leer: " + e.getMessage());
    }
}
public void guardarArchivo(File archivo, Grafo grafo) {
   
    try (PrintWriter pw = new PrintWriter(new FileWriter(archivo, false))) {
        
       Vertice aux = grafo.primero;
       while (aux != null ){
           Nodo aux2 = aux.aristas.primero ;
           while (aux2 != null) {
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
