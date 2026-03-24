/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;
import javax.swing.JFileChooser;
import java.io.*;
import proyecto2.tabla_hash.TablaHash;
import proyecto2.tabla_hash.UsuarioInfo;

/**
 *
 * @author valer
 */
public class CargarCVS {
    
    //Hay que cambiar esto para integrarlo a la interfaz
    public static File seleccionarArchivo() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Seleccione el archivo CSV");
    
    int resultado = fileChooser.showOpenDialog(null);
    
    if (resultado == JFileChooser.APPROVE_OPTION) {
        return fileChooser.getSelectedFile();} 
    else {
        return null;}
    }
    
 public static TablaHash cargarUsuariosDesdeCSV() {
        File archivo = seleccionarArchivo();
        if (archivo == null) return new TablaHash();
        
        return leerCSV(archivo);
    }

    public static TablaHash leerCSV(File archivo) {
        TablaHash HashUs = new TablaHash();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea = br.readLine(); // Salta el encabezado
            
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;

                String[] partes = linea.split("\\s*,\\s*");
                
                if (partes.length == 2) {
                    String nombre = partes[0].trim();
                    String prioridadTexto = partes[1].trim();

                    // Pasa la prioridad de String a int
                    int prioridadNum = convertirPrioridad(prioridadTexto);

                    UsuarioInfo nuevoUsuario = new UsuarioInfo(nombre, prioridadNum);
                    
                    HashUs.insertarUsuario(nombre, nuevoUsuario);
                    
                    System.out.println("Cargado en Hash: " + nombre);
                }
            }
        } catch (Exception e) {
            System.err.println(" Error: " + e.getMessage());
        }

        return HashUs;
    }

    //asigna cuanto vale cada prioridad (editar si es nesesario)
    public static int convertirPrioridad(String prioridad) {
        switch (prioridad.toLowerCase()) {
            case "prioridad_alta": return 0;
            case "prioridad_media": return 1;
            case "prioridad_baja": return 2;
            default: return 3;
        }
    }
}
