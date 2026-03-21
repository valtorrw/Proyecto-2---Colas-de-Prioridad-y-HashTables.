/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author valer
 */
public class DocCola {
     private String nomUsuario;
    private String nomDoc;
    private int sizeD;   
    private int clavePrioridad; 
    private int indiceHeap; 
    
    public DocCola(String nomUsuario, String nomDoc, int sizeD, int clavePrioridad){
        this.nomUsuario = nomUsuario;
        this.nomDoc = nomDoc;
        this.sizeD = sizeD;
        this.clavePrioridad = clavePrioridad;
        this.indiceHeap = -1; 
    }
    
    
    //getters
    public void setIndiceHeap(int indice) { 
        this.indiceHeap = indice; 
    }
    
    public int getIndiceHeap() { 
        return indiceHeap;
    }
    
    public int getClavePrioridad() { 
        return clavePrioridad;
    }
    
    public void setClavePrioridad(int clavePrioridad) {
        this.clavePrioridad = clavePrioridad; 
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
