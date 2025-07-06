package appsalud;


import java.awt.Component;
import java.awt.Font;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * Clase que representa la ventana gráfica para mostrar un informe
 * detallado del usuario actual, incluyendo información personal,
 * historial de actividades físicas y historial de peso.
 * 
 * Esta clase extiende de {@code javax.swing.JFrame} y muestra
 * tablas con el historial de actividades y pesos, con formatos
 * personalizados.
 * 
 * @author Mariola
 */
public class MostrarInforme extends javax.swing.JFrame {
    
	/**
     * Lista estática con las opciones de sexo que un usuario puede tener.
     */
    private static final ArrayList<String> SEXO = new ArrayList<>(List.of("Varón", "Hembra"));
    
    /**
     * Lista estática con las opciones del factor de actividad física del usuario.
     */    private static final ArrayList<String> FACTORACTIVIDAD = new ArrayList<>(List.of("Sedentarismo", "Actividad ligera (1 a 3 veces por semana)", 
    "Actividad moderada (3 a 5 veces por semana)", "Actividad intensa (6 o 7 veces por semana)", "Actividad extremadamente alta (Atleta profesional)"));

     /**
      * Usuario actual de la sesión, del cual se mostrarán los datos e historial.
      */
     Usuario usuario = Sesion.getUsuarioActual();

     /**
      * Constructor que inicializa la ventana de informe y carga los datos
      * personales, historial de actividades y pesos del usuario.
      * Configura la apariencia de las tablas y ajusta el ancho de columnas.
      */
    public MostrarInforme() {
        initComponents();
        lbl1.setText("Informe de " + usuario.getUsername());
        lbl2.setText("Nombre y apellidos: " + usuario.getNombre() + " " + usuario.getApellidos());
        lbl3.setText("Password: " + "*".repeat(usuario.getPassword().length()));
        lbl4.setText("Fecha de nacimiento: " + usuario.getFechaNacimiento().toString() + "   Edad: " + usuario.getEdad());
        lbl5.setText("Sexo: " + SEXO.get(usuario.getSexo()) + "   Peso: " +  usuario.getPeso() + "   Altura: " + usuario.getAltura());
        lbl6.setText(usuario.getIMC(usuario.getPeso()) + "   Factor de actividad: " + FACTORACTIVIDAD.get(usuario.getFactorActividad()));
        cargarHistorialActividades();
        JTableHeader header = tableAct.getTableHeader();
        header.setFont(new Font("Arial Nova Light", Font.PLAIN,14));
        tableAct.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        //Configuración de las columnas de la tabla de actividades
        tableAct.getColumnModel().getColumn(0).setPreferredWidth(100);  // Tipo de actividad
        tableAct.getColumnModel().getColumn(1).setPreferredWidth(150);  // Fecha y hora inicio
        tableAct.getColumnModel().getColumn(2).setPreferredWidth(150);  // Fecha y hora fin
        tableAct.getColumnModel().getColumn(3).setPreferredWidth(110);  // Duración
        tableAct.getColumnModel().getColumn(4).setPreferredWidth(110);  // Distancia
        tableAct.getColumnModel().getColumn(5).setPreferredWidth(120);  // Kcal consumidas
        tableAct.getColumnModel().getColumn(6).setPreferredWidth(150);  // FC mínima
        tableAct.getColumnModel().getColumn(7).setPreferredWidth(150);  // FC media
        tableAct.getColumnModel().getColumn(8).setPreferredWidth(150);  // FC máxima
        tableAct.getColumnModel().getColumn(9).setPreferredWidth(100);  // Ritmo
        tableAct.getColumnModel().getColumn(10).setPreferredWidth(100); // Elevación
        tableAct.getColumnModel().getColumn(11).setPreferredWidth(120); // Pasos totales
        tableAct.getColumnModel().getColumn(12).setPreferredWidth(100); // Cadencia
        tableAct.getColumnModel().getColumn(13).setPreferredWidth(120); // Nº de largos
        tableAct.getColumnModel().getColumn(14).setPreferredWidth(140); // Tipo de natación (playa/piscina)

        tableAct.setShowGrid(false);
        
        //Configuración de las columnas de la tabla de pesos
        tablePeso.getColumnModel().getColumn(0).setPreferredWidth(50);  // Fecha
        tablePeso.getColumnModel().getColumn(1).setPreferredWidth(50);  // Peso
        tablePeso.getColumnModel().getColumn(2).setPreferredWidth(150);  // IMC
        cargarHistorialPesos();
    }
    
    /**
     * Carga el historial de actividades físicas del usuario en la tabla de actividades.
     * Extrae los datos de cada actividad, formatea fechas y valores, y los añade como filas
     * al modelo de la tabla. 
     */
    private void cargarHistorialActividades() {
        DefaultTableModel model = (DefaultTableModel) tableAct.getModel();
        model.setRowCount(0); // Limpiar tabla antes de cargar datos

        for (Actividad a : usuario.getHistorialActividades()) {
            ArrayList<String> fila = new ArrayList<>();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            
            if (a instanceof Running running) {
                fila.add("Running");
                fila.add(running.getFhInicio().format(formatter));
                fila.add(running.getFhFin().format(formatter));
                fila.add(running.getDuracion());
                fila.add(String.format("%.2f km", running.getDistancia()));                
                fila.add(String.format("%.2f kcal", running.getKcal()));
                fila.add(String.valueOf(running.getFcMin()));
                fila.add(String.valueOf(running.getFcMedia()));
                fila.add(String.valueOf(running.getFcMax()));
                fila.add(running.getRitmoMedio());
                fila.add(String.format("%.2f m", running.getElevacion()));
                fila.add(String.valueOf(running.getPasosTotales()));
                fila.add(String.format("%.0f rpm", running.getCadencia()));
                fila.add(""); // número de largos no aplica
                fila.add(""); // tipo de agua no aplica
            } else if (a instanceof Cycling cycling) {
                fila.add("Ciclismo");
                fila.add(cycling.getFhInicio().format(formatter));
                fila.add(cycling.getFhFin().format(formatter));
                fila.add(cycling.getDuracion());
                fila.add(String.format("%.2f km", cycling.getDistancia()));
                fila.add(String.format("%.2f kcal", cycling.getKcal()));
                fila.add(String.valueOf(cycling.getFcMin()));
                fila.add(String.valueOf(cycling.getFcMedia()));
                fila.add(String.valueOf(cycling.getFcMax()));
                fila.add(""); // ritmo no aplica
                fila.add(""); // elevación no aplica
                fila.add(""); // pasos no aplica
                fila.add(String.format("%.0f rpm", cycling.getCadencia()));
                fila.add(""); // número de largos no aplica
                fila.add(""); // tipo de agua no aplica
            } else if (a instanceof Swimming swimming) {
                fila.add("Natación");
                fila.add(swimming.getFhInicio().format(formatter));
                fila.add(swimming.getFhFin().format(formatter));
                fila.add(swimming.getDuracion());
                fila.add(String.format("%.2f km", swimming.getDistancia()));
                fila.add(String.format("%.2f kcal", swimming.getKcal()));
                fila.add(String.valueOf(swimming.getFcMin()));
                fila.add(String.valueOf(swimming.getFcMedia()));
                fila.add(String.valueOf(swimming.getFcMax()));
                fila.add(""); // ritmo no aplica
                fila.add(""); // elevación no aplica
                fila.add(""); // pasos no aplica
                fila.add(""); // cadencia no aplica
                fila.add(String.valueOf(swimming.getNumLargos()));
                fila.add(swimming.getTipoNatacion() == 0 ? "Piscina" : "Mar");
            } else {
                // Actividad genérica
                fila.add("Actividad genérica");
                fila.add(a.getFhInicio().format(formatter));
                fila.add(a.getFhFin().format(formatter));
                fila.add(a.getDuracion());
                fila.add(String.format("%.2f km", a.getDistancia()));
                fila.add(String.format("%.2f kcal", a.getKcal()));
                fila.add(Integer.toString(a.getFcMin()));
                fila.add(String.valueOf(a.getFcMedia()));
                fila.add(String.valueOf(a.getFcMax()));
                fila.add("");
                fila.add("");
                fila.add("");
                fila.add("");
                fila.add("");
                fila.add("");
            }

            // Convertir ArrayList a array y añadir al modelo
            model.addRow(fila.toArray(new String[0]));
        
        }

        // Estilo del encabezado
        JTableHeader header = tableAct.getTableHeader();
        header.setFont(new Font("Arial Nova Light", Font.PLAIN, 14));

    }

    /**
     * Carga el historial de pesos del usuario en la tabla de pesos.
     * Muestra la fecha, peso y el IMC. 
     */
    private void cargarHistorialPesos() {
        DefaultTableModel modeloPeso = (DefaultTableModel) tablePeso.getModel();
        modeloPeso.setRowCount(0);

        // Estilizar tabla
        tablePeso.setRowHeight(28);
        tablePeso.setFont(new Font("Arial Nova Light", Font.PLAIN, 12));
        tablePeso.getTableHeader().setFont(new Font("Arial Nova Light", Font.PLAIN, 14));


        // Obtener datos del historial
        ArrayList<Peso> historial = usuario.getHistorialPesos();

        for (Peso peso : historial) {
            ArrayList<String> fila = new ArrayList<>();
            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            
            fila.add(peso.getFecha().format(formatoFecha));
            fila.add(String.format("%.1f", peso.getPeso()));
            fila.add(peso.getIMC());

            modeloPeso.addRow(fila.toArray(new String[0]));
        }
        
        tablePeso.setModel(modeloPeso);
        
    }




    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl1 = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        lbl3 = new javax.swing.JLabel();
        lbl4 = new javax.swing.JLabel();
        lbl5 = new javax.swing.JLabel();
        lbl6 = new javax.swing.JLabel();
        lbl7 = new javax.swing.JLabel();
        scrollAct = new javax.swing.JScrollPane();
        tableAct = new javax.swing.JTable();
        lbl8 = new javax.swing.JLabel();
        scrollPeso = new javax.swing.JScrollPane();
        tablePeso = new javax.swing.JTable();
        lblFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl1.setBackground(new java.awt.Color(51, 51, 255));
        lbl1.setFont(new java.awt.Font("Arial Nova Light", 0, 36)); // NOI18N
        lbl1.setForeground(new java.awt.Color(255, 255, 255));
        lbl1.setText("Informe de");
        getContentPane().add(lbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 700, -1));

        lbl2.setBackground(new java.awt.Color(51, 51, 255));
        lbl2.setFont(new java.awt.Font("Arial Nova Light", 0, 18)); // NOI18N
        lbl2.setForeground(new java.awt.Color(255, 255, 255));
        lbl2.setText("Nombre y apellidos:");
        getContentPane().add(lbl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 700, -1));

        lbl3.setBackground(new java.awt.Color(51, 51, 255));
        lbl3.setFont(new java.awt.Font("Arial Nova Light", 0, 18)); // NOI18N
        lbl3.setForeground(new java.awt.Color(255, 255, 255));
        lbl3.setText("Password:");
        getContentPane().add(lbl3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 700, -1));

        lbl4.setBackground(new java.awt.Color(51, 51, 255));
        lbl4.setFont(new java.awt.Font("Arial Nova Light", 0, 18)); // NOI18N
        lbl4.setForeground(new java.awt.Color(255, 255, 255));
        lbl4.setText("Fecha de nacimiento -            Edad -");
        getContentPane().add(lbl4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 700, -1));

        lbl5.setBackground(new java.awt.Color(51, 51, 255));
        lbl5.setFont(new java.awt.Font("Arial Nova Light", 0, 18)); // NOI18N
        lbl5.setForeground(new java.awt.Color(255, 255, 255));
        lbl5.setText("Sexo -         Peso -               Altura -");
        getContentPane().add(lbl5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 700, -1));

        lbl6.setBackground(new java.awt.Color(51, 51, 255));
        lbl6.setFont(new java.awt.Font("Arial Nova Light", 0, 16)); // NOI18N
        lbl6.setForeground(new java.awt.Color(255, 255, 255));
        lbl6.setText("IMC actual - Factor actividad");
        getContentPane().add(lbl6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 700, -1));

        lbl7.setBackground(new java.awt.Color(51, 51, 255));
        lbl7.setFont(new java.awt.Font("Arial Nova Light", 0, 18)); // NOI18N
        lbl7.setForeground(new java.awt.Color(255, 255, 255));
        lbl7.setText("Historial de actividades");
        getContentPane().add(lbl7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 700, -1));

        scrollAct.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        tableAct.setFont(new java.awt.Font("Arial Nova Light", 0, 12)); // NOI18N
        tableAct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Tipo", "Fecha y hora de inicio", "Fecha y hora de fin", "Duración", "Distancia", "Kcal consumidas", "Frecuencia Cardíaca Mínima", "Frecuencia Cardíaca Media", "Frecuencia Cardíaca Máxima", "Ritmo", "Elevación", "Pasos totales", "Cadencia", "Número de largos", "Tipo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableAct.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tableAct.setCellSelectionEnabled(true);
        scrollAct.setViewportView(tableAct);

        getContentPane().add(scrollAct, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 700, 100));

        lbl8.setBackground(new java.awt.Color(51, 51, 255));
        lbl8.setFont(new java.awt.Font("Arial Nova Light", 0, 18)); // NOI18N
        lbl8.setForeground(new java.awt.Color(255, 255, 255));
        lbl8.setText("Historial de pesos");
        getContentPane().add(lbl8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, 700, -1));

        tablePeso.setFont(new java.awt.Font("Arial Nova Light", 0, 12)); // NOI18N
        tablePeso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Fecha", "Peso", "IMC"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        scrollPeso.setViewportView(tablePeso);

        getContentPane().add(scrollPeso, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 420, 700, 100));

        lblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/infitnityFondo.png"))); // NOI18N
        getContentPane().add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MostrarInforme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MostrarInforme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MostrarInforme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MostrarInforme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MostrarInforme().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lbl5;
    private javax.swing.JLabel lbl6;
    private javax.swing.JLabel lbl7;
    private javax.swing.JLabel lbl8;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JScrollPane scrollAct;
    private javax.swing.JScrollPane scrollPeso;
    private javax.swing.JTable tableAct;
    private javax.swing.JTable tablePeso;
    // End of variables declaration//GEN-END:variables
}
