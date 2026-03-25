package proyecto2;

import javax.swing.UIManager;

public class Proyecto2 {
    public static void main(String[] args) {
        try {
            // Configura la interfaz para que se vea como Windows/Mac
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Inicia la ventana principal
        java.awt.EventQueue.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }
}