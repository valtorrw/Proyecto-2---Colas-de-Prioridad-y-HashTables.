/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author valer
 */
public class HeapB {
    
    private DocCola[] heap;   // El arreglo
    private int sizeH;
    private int capacidad;

    public HeapB(int capacidadInicial) {
        capacidad = capacidadInicial;
        heap = new DocCola[capacidad];
        sizeH = 0;
    }
   
}
