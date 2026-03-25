package proyecto2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import proyecto2.tabla_hash.TablaHash;
import proyecto2.tabla_hash.UsuarioInfo;

/*author Juan Ferreira

*/
public class VentanaPrincipal extends JFrame {
    
    private TablaHash tablaUsuarios;
    private HeapB colaImpresion;
    private int tiempoSimulacion = 0;
    private Timer timerReloj;

    
    private JTextArea areaConsola;
    private JLabel labelReloj;
    private JPanel panelDibujoHeap;

    public VentanaPrincipal() {
        
        tablaUsuarios = new TablaHash();
        colaImpresion = new HeapB(100); 

        setTitle("Simulador de Impresión UNIMET - Alejandro Simanca");
        setSize(1100, 750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        inicializarComponentes();
        iniciarReloj();
    }

    private void iniciarReloj() {
        timerReloj = new Timer(1000, e -> {
            tiempoSimulacion++;
            labelReloj.setText("Tiempo: " + tiempoSimulacion + "s");
        });
        timerReloj.start();
    }

    private void inicializarComponentes() {
        
        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));
        labelReloj = new JLabel("Tiempo: 0s");
        labelReloj.setFont(new Font("Arial", Font.BOLD, 14));
        
        JButton btnCargar = new JButton("Cargar Usuarios (CSV)");
        btnCargar.addActionListener(e -> {
            tablaUsuarios = CargarCVS.cargarUsuariosDesdeCSV();
            areaConsola.append("[SISTEMA] Tabla Hash cargada con éxito.\n");
        });
        
        panelNorte.add(labelReloj);
        panelNorte.add(new JSeparator(SwingConstants.VERTICAL));
        panelNorte.add(btnCargar);
        add(panelNorte, BorderLayout.NORTH);

        
        panelDibujoHeap = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (colaImpresion != null && colaImpresion.getSizeH() > 0) {
                    dibujarArbol(g, 0, getWidth() / 2, 60, getWidth() / 4);
                } else {
                    g.setColor(Color.GRAY);
                    g.drawString("Cola vacía. Cargue el CSV y envíe un documento.", 20, 20);
                }
            }
        };
        panelDibujoHeap.setBackground(Color.WHITE);
        panelDibujoHeap.setBorder(BorderFactory.createTitledBorder("Vista de Árbol (Min-Heap)"));
        add(panelDibujoHeap, BorderLayout.CENTER);

        
        JPanel panelEste = new JPanel(new GridLayout(6, 1, 10, 10));
        panelEste.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JButton btnInsertar = new JButton("Enviar Documento");
        JButton btnImprimir = new JButton("Imprimir Siguiente");
        JButton btnEliminar = new JButton("Eliminar Específico");
        JButton btnHash = new JButton("Ver Tabla Hash");

        btnInsertar.addActionListener(e -> accionInsertar());
        
        btnImprimir.addActionListener(e -> {
            DocCola impreso = colaImpresion.eliminarMin();
            if (impreso != null) {
                tablaUsuarios.eliminarDocEnCola(impreso.getNomUsuario(), impreso.getNomDoc());
                areaConsola.append("[OK] Impreso: " + impreso.getNomDoc() + " de " + impreso.getNomUsuario() + "\n");
                panelDibujoHeap.repaint();
            } else {
                JOptionPane.showMessageDialog(this, "La cola está vacía.");
            }
        });

        btnEliminar.addActionListener(e -> accionEliminarEspecifico());
        btnHash.addActionListener(e -> tablaUsuarios.mostrarTabla());

        panelEste.add(new JLabel("CONTROLES:", SwingConstants.CENTER));
        panelEste.add(btnInsertar);
        panelEste.add(btnImprimir);
        panelEste.add(btnEliminar);
        panelEste.add(btnHash);
        add(panelEste, BorderLayout.EAST);

        
        areaConsola = new JTextArea(10, 20);
        areaConsola.setEditable(false);
        areaConsola.setBackground(new Color(25, 25, 25));
        areaConsola.setForeground(new Color(0, 255, 127)); // Color verde primavera
        add(new JScrollPane(areaConsola), BorderLayout.SOUTH);
    }

    private void accionInsertar() {
        try {
            // pedir user
            String user = JOptionPane.showInputDialog(this, "Nombre del Usuario:");
            if (user == null || user.trim().isEmpty()) return;
            
            // searh en el hash
            UsuarioInfo info = tablaUsuarios.buscarUsuario(user);
            if (info == null) {
                areaConsola.append("[ERROR] El usuario '" + user + "' no existe en la base de datos CSV.\n");
                return;
            }

            // pedir datos del doc
            String docNombre = JOptionPane.showInputDialog(this, "Nombre del Documento:");
            if (docNombre == null || docNombre.trim().isEmpty()) return;

            String sizeStr = JOptionPane.showInputDialog(this, "Tamaño (KB):");
            if (sizeStr == null) return;
            int tamaño = Integer.parseInt(sizeStr);
            
            int tipo = JOptionPane.showConfirmDialog(this, "¿Es urgente?", "Prioridad", JOptionPane.YES_NO_OPTION);
            int pDoc = (tipo == JOptionPane.YES_OPTION) ? 0 : 1;

            // logica
            DocCola nuevoDoc = new DocCola(user, docNombre, tamaño, pDoc, tiempoSimulacion);
            nuevoDoc.calcularPrioridad(info); 
            
            int posEnHeap = colaImpresion.insertar(nuevoDoc); 
            tablaUsuarios.registrarDocEnCola(user, docNombre, posEnHeap);
            
            areaConsola.append("[COLA] Doc: " + docNombre + " | Prioridad: " + nuevoDoc.getClavePrioridad() + "\n");
            
            // refresh visual
            panelDibujoHeap.revalidate();
            panelDibujoHeap.repaint();
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El tamaño debe ser solo números (ej: 500)");
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(this, "Error: Objeto no inicializado. ¿Cargaste el CSV?");
            ex.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error técnico: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void accionEliminarEspecifico() {
        try {
            String user = JOptionPane.showInputDialog(this, "Usuario del documento:");
            String doc = JOptionPane.showInputDialog(this, "Nombre del documento:");
            if (user == null || doc == null) return;

            int pos = tablaUsuarios.buscarPosicionDoc(user, doc);

            if (pos != -1) {
                colaImpresion.modificarPrioridad(pos, -1); 
                colaImpresion.eliminarMin();
                tablaUsuarios.eliminarDocEnCola(user, doc);
                areaConsola.append("[SISTEMA] Documento '" + doc + "' eliminado por petición del usuario.\n");
                panelDibujoHeap.repaint();
            } else {
                areaConsola.append("[!] No se encontró el documento '" + doc + "' para " + user + "\n");
            }
        } catch (Exception ex) {
            areaConsola.append("[ERROR] Fallo al eliminar: " + ex.getMessage() + "\n");
        }
    }

    private void dibujarArbol(Graphics g, int i, int x, int y, int hGap) {
        if (i < colaImpresion.getSizeH()) {
            DocCola doc = colaImpresion.obtenerEnPosicion(i);
            if (doc == null) return;

            int hijoIzq = 2 * i + 1;
            int hijoDer = 2 * i + 2;

            g.setColor(new Color(180, 180, 180));
            if (hijoIzq < colaImpresion.getSizeH()) {
                g.drawLine(x, y, x - hGap, y + 60);
                dibujarArbol(g, hijoIzq, x - hGap, y + 60, hGap / 2);
            }
            if (hijoDer < colaImpresion.getSizeH()) {
                g.drawLine(x, y, x + hGap, y + 60);
                dibujarArbol(g, hijoDer, x + hGap, y + 60, hGap / 2);
            }

            // Nodo
            g.setColor(new Color(70, 130, 180)); // Azul acero
            g.fillOval(x - 25, y - 25, 50, 50);
            g.setColor(Color.WHITE);
            g.drawOval(x - 25, y - 25, 50, 50);
            
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 9));
            g.drawString(doc.getNomDoc(), x - 20, y + 2);
            g.setFont(new Font("Arial", Font.PLAIN, 8));
            g.drawString("P:" + doc.getClavePrioridad(), x - 10, y + 12);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }
}