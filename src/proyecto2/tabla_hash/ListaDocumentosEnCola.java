/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2.tabla_hash;

/**
 *
 * @author Samuel Djekki
 */
public class ListaDocumentosEnCola {
    private NodoDocEnCola primero;
    
    public void agregar(String doc, int indiceHeap) {
        NodoDocEnCola nuevo = new NodoDocEnCola(doc, indiceHeap);
        nuevo.siguiente = primero;
        primero = nuevo;
    }
    
    public int buscarPosicion(String doc) {
        NodoDocEnCola actual = primero;
        while (actual != null) {
            if (actual.doc.equals(doc)) {
                return actual.indiceHeap;
            }
            actual = actual.siguiente;
        }
        return -1;
    }
    
    public void eliminar(String doc) {
        NodoDocEnCola actual = primero;
        NodoDocEnCola anterior = null;
        while (actual != null) {
            if (actual.doc.equals(doc)) {
                if (anterior == null) {
                    primero = actual.siguiente;
                } else {
                    anterior.siguiente = actual.siguiente;
                }
                return;
            }
            anterior = actual;
            actual = actual.siguiente;
        }
    }
    
    @Override
    public String toString() {
        if (primero == null) return "[]";
        StringBuilder sb = new StringBuilder("[");
        NodoDocEnCola actual = primero;
        while (actual != null) {
            sb.append(actual.doc).append(":").append(actual.indiceHeap);
            if (actual.siguiente != null) sb.append(", ");
            actual = actual.siguiente;
        }
        sb.append("]");
        return sb.toString();
    }
}