/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2.tabla_hash;


/**
 *
 * @author Samuel Djekki
 */
public class TablaHash {
    private final NodoHash[] tabla;
    private static final int CANTIDAD = 101;  
    
    public TablaHash() {
        tabla = new NodoHash[CANTIDAD];
    }
    
    private int hash(String clave) {
        int h = 0;
        for (int i = 0; i < clave.length(); i++) {
            h = (h * 31 + clave.charAt(i)) % CANTIDAD;
        }
        return h;
    }
    
    public void insertarUsuario(String usuario, UsuarioInfo info) {
        int indice = hash(usuario);
        NodoHash nodo = tabla[indice];
        
        while (nodo != null) {
            if (nodo.usuario.equals(usuario)) {
                nodo.info = info;  
                return;
            }
            nodo = nodo.siguiente;
        }
        
        // Nuevo nodo
        NodoHash nuevo = new NodoHash(usuario, info);
        nuevo.siguiente = tabla[indice];
        tabla[indice] = nuevo;
    }
    
    // Buscar usuario
    public UsuarioInfo buscarUsuario(String usuario) {
        int indice = hash(usuario);
        NodoHash nodo = tabla[indice];
        
        while (nodo != null) {
            if (nodo.usuario.equals(usuario)) {
                return nodo.info;
            }
            nodo = nodo.siguiente;
        }
        return null;
    }
    
    public void registrarDocEnCola(String usuario, String doc, int indiceHeap) {
        UsuarioInfo info = buscarUsuario(usuario);
        if (info != null) {
            info.registrarDocumento(doc, indiceHeap);
        }
    }
    
    
    //Esto es en 0(1) si encuentran una forma mas rapida de buscar la posicion
    //reemplazar
    public int buscarPosicionDoc(String usuario, String doc) {
        UsuarioInfo info = buscarUsuario(usuario);
        if (info != null) {
            return info.buscarPosicion(doc);
        }
        return -1;
    }
    
    public void eliminarDocEnCola(String usuario, String doc) {
        UsuarioInfo info = buscarUsuario(usuario);
        if (info != null) {
            info.eliminarDocumento(doc);
        }
    }
    
    public void mostrarTabla() {
        System.out.println("\n=== TABLA DE DISPERSION ===");
        for (int i = 0; i < CANTIDAD; i++) {
            if (tabla[i] != null) {
                System.out.print("Bucket " + i + ": ");
                NodoHash nodo = tabla[i];
                while (nodo != null) {
                    System.out.print(nodo.usuario + " --> " + nodo.info + " | ");
                    nodo = nodo.siguiente;
                }
                System.out.println();
            }
        }
    }
}