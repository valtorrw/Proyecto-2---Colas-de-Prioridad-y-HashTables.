/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2.tabla_hash;

/**
 *
 * @author Samuel Djekki
 */
public class UsuarioInfo {
    public String nombre;
    public int  prioridad;
    public ListaDocumentosEnCola docsEnCola;
    
    public UsuarioInfo(String nombre, int prioridad) {
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.docsEnCola = new ListaDocumentosEnCola();
    }
    
    public void registrarDocumento(String doc, int indiceHeap) {
        docsEnCola.agregar(doc, indiceHeap);
    }
    
    public int buscarPosicion(String doc) {
        return docsEnCola.buscarPosicion(doc);
    }
    
    public void eliminarDocumento(String doc) {
        docsEnCola.eliminar(doc);
    }
    
    @Override
    public String toString() {
        return docsEnCola.toString();
    }
}