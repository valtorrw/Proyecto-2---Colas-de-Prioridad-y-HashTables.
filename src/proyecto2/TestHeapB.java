/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;


/**
 *
 * @author Samuel Djekki
 */
public class TestHeapB {
    public static void main(String[] args) {
        HeapB heap = new HeapB(10);
        
        DocCola doc1 = new DocCola("jperez", "doc1.pdf", 500, 1000);
        DocCola doc2 = new DocCola("mario", "doc2.txt", 200, 1500);
        DocCola doc3 = new DocCola("ana", "doc3.doc", 800, 500);
        
        System.out.println("=== 1. INSERTAR ===");
        heap.insertar(doc1);
        heap.insertar(doc2);
        heap.insertar(doc3);
        
        heap.mostrarSecuencia();
        heap.mostrarArbol();
        
        System.out.println("\n=== 2. ELIMINAR_MIN ===");
        DocCola impreso = heap.eliminarMin();
        System.out.println("IMPRESO: " + impreso.getNomDoc() + 
                          " (P:" + impreso.getClavePrioridad() + ")");
        heap.mostrarSecuencia();
        
        System.out.println("\n=== 3. ELIMINAR ESPECÍFICO ===");
        heap.modificarPrioridad(1, 0);
        DocCola eliminado = heap.eliminarMin();
        System.out.println("ELIMINADO: " + eliminado.getNomDoc());
        heap.mostrarSecuencia();
    }
}
