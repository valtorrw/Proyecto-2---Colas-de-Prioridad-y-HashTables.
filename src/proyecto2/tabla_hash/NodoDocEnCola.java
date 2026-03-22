/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2.tabla_hash;

/**
 *
 * @author Samuel Djekki
 */
public class NodoDocEnCola {
    public String doc;
    public int indiceHeap;
    public NodoDocEnCola siguiente;
    
    public NodoDocEnCola(String doc, int indiceHeap) {
        this.doc = doc;
        this.indiceHeap = indiceHeap;
        this.siguiente = null;
    }
}