/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2.tabla_hash;

/**
 *
 * @author Samuel Djekki
 */
public class NodoHash {
    public String usuario;
    public UsuarioInfo info;
    public NodoHash siguiente;
    
    public NodoHash(String usuario, UsuarioInfo info) {
        this.usuario = usuario;
        this.info = info;
        this.siguiente = null;
    }
}
