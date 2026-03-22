/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2.tabla_hash;

/**
 *
 * @author Samuel Djekki
 */
import proyecto2.DocCola;
import proyecto2.HeapB;
        
        
public class TestTablaHash {
    public static void main(String[] args) {
        TablaHash tabla = new TablaHash();
        HeapB heap = new HeapB(10);
        
        DocCola doc1 = new DocCola("jperez", "doc1.pdf", 500, 1000);
        DocCola doc2 = new DocCola("jperez", "doc2.pdf", 200, 1500);
        DocCola doc3 = new DocCola("mario", "doc3.txt", 800, 500);
        
        int pos1 = heap.insertar(doc1);
        int pos2 = heap.insertar(doc2);
        int pos3 = heap.insertar(doc3);
        
        tabla.insertarUsuario("jperez", new UsuarioInfo("jperez"));
        tabla.insertarUsuario("mario", new UsuarioInfo("mario"));
        
        tabla.registrarDocEnCola("jperez", "doc1.pdf", pos1);
        tabla.registrarDocEnCola("jperez", "doc2.pdf", pos2);
        tabla.registrarDocEnCola("mario", "doc3.txt", pos3);
        
        tabla.mostrarTabla();
        
        
        System.out.println("\nPosicion doc1.pdf de jperez: " + 
                          tabla.buscarPosicionDoc("jperez", "doc1.pdf"));
        
        
        int posEliminar = tabla.buscarPosicionDoc("jperez", "doc2.pdf");
        System.out.println("Eliminar doc2.pdf en posicion: " + posEliminar);
        heap.modificarPrioridad(posEliminar, 0);
        tabla.eliminarDocEnCola("jperez", "doc2.pdf");
        
        tabla.mostrarTabla();
    }
}
