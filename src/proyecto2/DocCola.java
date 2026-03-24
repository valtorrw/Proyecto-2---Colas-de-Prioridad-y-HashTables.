/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;
import proyecto2.tabla_hash.UsuarioInfo;

/**
 *
 * @author valer
 */
public class DocCola {
     private String nomUsuario;
    private String nomDoc;
    private int sizeD;   
    private int PrioridadDoc; 
    private int PrioridadTotal;
    private int indiceHeap; 
    
    
    public DocCola(String nomUsuario, String nomDoc, int sizeD, int PrioridadDoc, int PrioridadTotal){
        this.nomUsuario = nomUsuario;
        this.nomDoc = nomDoc;
        this.sizeD = sizeD;
        this.PrioridadDoc = PrioridadDoc;
        this.PrioridadTotal = PrioridadTotal;
        this.indiceHeap = -1; 
    }
    
    public void calcularPrioridad(UsuarioInfo usuario) {
        this.PrioridadTotal = usuario.prioridad * 1000 + PrioridadDoc;
    }
    
    //getters
    public void setIndiceHeap(int indice) { 
        this.indiceHeap = indice; 
    }
    
    public int getIndiceHeap() { 
        return indiceHeap;
    }
    
    public int getClavePrioridad() { 
        return PrioridadTotal;
    }
    
    public void setPrioridadTotal(int PrioridadTotal) {
        this.PrioridadTotal = PrioridadTotal; 
    }
    
    public String getNomDoc(){
        return nomDoc;
    }
    
    public String getNomUsuario(){
        return nomUsuario;
    }
    
    public int getSizeD(){
        return sizeD;
    }
    
}
