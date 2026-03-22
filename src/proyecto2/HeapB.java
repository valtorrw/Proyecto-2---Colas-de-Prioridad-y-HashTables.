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
    private final int capacidad;

    public HeapB(int capacidadInicial) {
        capacidad = capacidadInicial;
        heap = new DocCola[capacidad];
        sizeH = 0;
    }
    
    private void intercambiar(int i, int j) {
        DocCola temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
        
        // Actualiza indices
        heap[i].setIndiceHeap(i);
        heap[j].setIndiceHeap(j);
    }
    
    private void heapifyUp(int posicion) {
        while (posicion > 0) {
            int padre = (posicion - 1) / 2;
            
            if (heap[padre].getClavePrioridad() <= heap[posicion].getClavePrioridad()) {
                break;
            }
            
            intercambiar(padre, posicion);
            posicion = padre;
        }
    }
    
    private void heapifyDown(int posicion) {
        while (true) {
            int menor = posicion;
            int hijoIzq = 2 * posicion + 1;
            int hijoDer = 2 * posicion + 2;

            if (hijoIzq < sizeH && 
                heap[hijoIzq].getClavePrioridad() < heap[menor].getClavePrioridad()) {
                menor = hijoIzq;
            }
            
            if (hijoDer < sizeH && 
                heap[hijoDer].getClavePrioridad() < heap[menor].getClavePrioridad()) {
                menor = hijoDer;
            }
            
            if (menor == posicion) {
                break;
            }
            
            intercambiar(posicion, menor);
            posicion = menor;
        }
    }
    
    public int insertar(DocCola doc) {
        if (sizeH >= capacidad) {
            System.out.println("Error: Heap lleno");
            return -1;
        }
        
        heap[sizeH] = doc;
        doc.setIndiceHeap(sizeH);
        int posicion = sizeH;
        sizeH++;
        
        heapifyUp(posicion);
        return posicion;
    }
   
        public DocCola eliminarMin() {
        if (sizeH == 0) {
            return null;
        }
        
        DocCola minimo = heap[0];
        heap[0] = heap[sizeH - 1];
        heap[0].setIndiceHeap(0);
        sizeH--;
        
        if (sizeH > 0) {
            heapifyDown(0);
        }
        return minimo;
    }
    
        public void modificarPrioridad(int indice, int nuevaPrioridad) {
        if (indice < 0 || indice >= sizeH) {
            return;
        }
        heap[indice].setClavePrioridad(nuevaPrioridad);
        heapifyUp(indice);   // Sube
        heapifyDown(indice); // Baja
        }
        
        
    public DocCola obtenerEnPosicion(int indice) {
        if (indice < 0 || indice >= sizeH) {
            return null;
        }
        return heap[indice];
    }
    
    //Unos getters
    
    public int getSizeH() { 
        return sizeH; 
    }
    
    public boolean estaVacia() { 
        return sizeH == 0; 
    }
    
    
    // para testeo
    
    public void mostrarSecuencia() {
        if (estaVacia()) {
            System.out.println("Cola vacia");
            return;
        }
        System.out.println("=== COLA SECUENCIA (" + sizeH + " docs) ===");
        for (int i = 0; i < sizeH; i++) {
            System.out.println((i+1) + ". " + heap[i].getNomDoc() + 
                             " (P:" + heap[i].getClavePrioridad() + 
                             " Idx:" + heap[i].getIndiceHeap() + 
                             " " + heap[i].getNomUsuario() + ")");
        }
    }

    public void mostrarArbol() {
        if (estaVacia()) {
            System.out.println("Heap vacio");
            return;
        }
        System.out.println("RAIZ: " + heap[0].getNomDoc() + " (P:" + heap[0].getClavePrioridad() + ")");
        mostrarNivel(0, 1);
    }
    
    private void mostrarArbolRecursivo(int indice, int nivel) {
        if (indice >= sizeH) return;
        
        // Espacios para indentación
        for (int i = 0; i < nivel; i++) {
            System.out.print("  ");
        }
        
        // Mostrar nodo actual
        System.out.print("--" + heap[indice] + "\n");
        
        // Hijos
        int hijoIzq = 2 * indice + 1;
        int hijoDer = 2 * indice + 2;
        
        mostrarArbolRecursivo(hijoIzq, nivel + 1);
        mostrarArbolRecursivo(hijoDer, nivel + 1);
    }

    private void mostrarNivel(int pos, int nivel) {
        if (pos >= sizeH) return;
        
        for (int i = 0; i < nivel; i++) {
            System.out.print("  ");
        }
        System.out.print("-- " + heap[pos].getNomDoc() + 
                        "(" + heap[pos].getClavePrioridad() + ") ");
        
        int hermano = pos + 1;
        while (hermano < sizeH && padreDe(hermano) == padreDe(pos)) {
            System.out.print(heap[hermano].getNomDoc() + 
                           "(" + heap[hermano].getClavePrioridad() + ") ");
            hermano++;
        }
        System.out.println();
        
        mostrarNivel(2 * pos + 1, nivel + 1);
        mostrarNivel(2 * pos + 2, nivel + 1);
    }
    
    private int padreDe(int hijo) {
        return (hijo - 1) / 2;
    }

    private boolean esHijo(int padre, int hijo) {
        return (hijo - 1) / 2 == padre;
    }
}
