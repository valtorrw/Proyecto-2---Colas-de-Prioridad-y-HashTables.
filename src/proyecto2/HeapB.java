package proyecto2;

import proyecto2.tabla_hash.UsuarioInfo;

/**
 * @author valer
 * Corregido para integración con VentanaPrincipal
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
        
        // Actualiza indices internos del documento
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
        
        // CORRECCIÓN: Eliminamos la referencia a 'Tabla' ya que la prioridad 
        // se calcula en VentanaPrincipal antes de llamar a este método.
        heap[sizeH] = doc;
        doc.setIndiceHeap(sizeH);
        int posicion = sizeH;
        sizeH++;
        
        heapifyUp(posicion);
        
        // Retornamos la posición real donde quedó después de subir
        for (int k = 0; k < sizeH; k++) {
            if (heap[k] == doc) return k;
        }
        return posicion;
    }
   
    public DocCola eliminarMin() {
        if (sizeH == 0) {
            return null;
        }
        
        DocCola minimo = heap[0];
        heap[0] = heap[sizeH - 1];
        
        sizeH--;
        
        if (sizeH > 0) {
            heap[0].setIndiceHeap(0);
            heapifyDown(0);
        }
        return minimo;
    }
    
    public void modificarPrioridad(int indice, int nuevaPrioridad) {
        if (indice < 0 || indice >= sizeH) {
            return;
        }
        // CORRECCIÓN: Cambiamos el valor directamente y re-ordenamos
        heap[indice].setClavePrioridad(nuevaPrioridad);
        heapifyUp(indice);   
        heapifyDown(indice); 
    } 
        
    public DocCola obtenerEnPosicion(int indice) {
        // CORRECCIÓN: Usamos 'indice' (el parámetro) en lugar de 'i'
        if (indice >= 0 && indice < sizeH) {
            return heap[indice];
        }
        return null;
    }
    
    public int getSizeH() { 
        return sizeH; 
    }
    
    public boolean estaVacia() { 
        return sizeH == 0; 
    }

    // --- Métodos de Testeo de Valeria ---
    
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
    
    private void mostrarNivel(int pos, int nivel) {
        if (pos >= sizeH) return;
        
        for (int i = 0; i < nivel; i++) {
            System.out.print("  ");
        }
        System.out.print("-- " + heap[pos].getNomDoc() + 
                        "(" + heap[pos].getClavePrioridad() + ") ");
        
        System.out.println();
        
        mostrarNivel(2 * pos + 1, nivel + 1);
        mostrarNivel(2 * pos + 2, nivel + 1);
    }
}
