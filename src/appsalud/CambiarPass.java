package appsalud;
import java.io.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;

/**
* Ventana de la aplicación que permite al usuario cambiar su contraseña.
* 
* Esta clase proporciona una interfaz gráfica para que el usuario actual (obtenido desde la clase {@link Sesion})
* pueda actualizar su contraseña previa verificación. La validación incluye:
* <ul>
*   <li>Verificación de la contraseña actual.</li>
*   <li>Validación de requisitos de seguridad para la nueva contraseña (según tipo de usuario).</li>
*   <li>Confirmación de coincidencia entre nueva contraseña y su repetición.</li>
* </ul>
* 
* Tras una actualización exitosa, los datos son guardados mediante {@link UsuariosManager#guardarUsuarios()}.
* 
* @author Mariola
*/
public class CambiarPass extends javax.swing.JFrame {
    
	/** Usuario actual que está logueado en el sistema. */
    Usuario usuario = Sesion.getUsuarioActual();

    static String oldPass;
    static String newPass;
    static String newPassConf;
    
    /**
     * Constructor por defecto. Inicializa los componentes de la interfaz.
     */
    public CambiarPass() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCPass = new javax.swing.JLabel();
        lblOldPass = new javax.swing.JLabel();
        pwdOld = new javax.swing.JPasswordField();
        lblNewPass1 = new javax.swing.JLabel();
        pwdNew1 = new javax.swing.JPasswordField();
        lblNewPass2 = new javax.swing.JLabel();
        pwdNew2 = new javax.swing.JPasswordField();
        btnCPass = new javax.swing.JButton();
        lblFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCPass.setFont(new java.awt.Font("Arial Nova Light", 0, 48)); // NOI18N
        lblCPass.setForeground(new java.awt.Color(255, 255, 255));
        lblCPass.setText("Cambia tu contraseña");
        getContentPane().add(lblCPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, -1, 100));

        lblOldPass.setFont(new java.awt.Font("Arial Nova Light", 0, 20)); // NOI18N
        lblOldPass.setForeground(new java.awt.Color(255, 255, 255));
        lblOldPass.setText("Primero debes introducir tu anterior contraseña");
        getContentPane().add(lblOldPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 450, -1));
        getContentPane().add(pwdOld, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 450, 50));

        lblNewPass1.setFont(new java.awt.Font("Arial Nova Light", 0, 20)); // NOI18N
        lblNewPass1.setForeground(new java.awt.Color(255, 255, 255));
        lblNewPass1.setText("Establece aquí tu nueva contraseña");
        getContentPane().add(lblNewPass1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, 450, -1));
        getContentPane().add(pwdNew1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, 450, 50));

        lblNewPass2.setFont(new java.awt.Font("Arial Nova Light", 0, 20)); // NOI18N
        lblNewPass2.setForeground(new java.awt.Color(255, 255, 255));
        lblNewPass2.setText("Confirma aquí tu nueva contraseña");
        getContentPane().add(lblNewPass2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, 450, -1));
        getContentPane().add(pwdNew2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 400, 450, 50));

        btnCPass.setBackground(new java.awt.Color(153, 153, 255));
        btnCPass.setFont(new java.awt.Font("Arial Nova Light", 0, 24)); // NOI18N
        btnCPass.setForeground(new java.awt.Color(255, 255, 255));
        btnCPass.setText("Cambiar contraseña");
        btnCPass.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCPass.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCPassActionPerformed(evt);
            }
        });
        getContentPane().add(btnCPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 490, 240, 50));

        lblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/infitnityFondo.png"))); // NOI18N
        getContentPane().add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * Evento que se ejecuta al pulsar el botón para cambiar la contraseña.
    * 
    * Recoge la contraseña antigua, la nueva y su confirmación de los campos
    * correspondientes, y llama al método `cambiarPassword` para realizar el cambio.
    * 
    * @param evt el evento generado por la pulsación del botón.
    */
    private void btnCPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCPassActionPerformed
        oldPass = pwdOld.getText();
        newPass = pwdNew1.getText();
        newPassConf = pwdNew2.getText();
        cambiarPassword(oldPass, newPass, newPassConf);
    }//GEN-LAST:event_btnCPassActionPerformed

    /**
     * Cambia la contraseña del usuario actual tras validar los datos proporcionados.
     * 
     * Proceso de validación:
     * 1. Comprueba que la contraseña actual introducida coincide con la almacenada.
     * 2. Verifica que la nueva contraseña cumple con los requisitos de seguridad,
     *    que varían si el usuario es premium o no.
     * 3. Comprueba que la nueva contraseña y su confirmación coinciden.
     * 
     * Si alguna validación falla, muestra un mensaje de error y aborta el cambio.
     * Si todas son correctas, actualiza la contraseña, muestra un mensaje de éxito,
     * serializa los cambios y vuelve al menú principal.
     * 
     * @param actual la contraseña actual introducida por el usuario.
     * @param nueva la nueva contraseña propuesta.
     * @param confirmacion la confirmación de la nueva contraseña.
     */
    private void cambiarPassword(String actual, String nueva, String confirmacion) {
        
        // 1. Verificar la contraseña actual        
        if (!usuario.getPassword().equals(actual)) { 
            JOptionPane.showMessageDialog(this, "La contraseña actual no es correcta.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // 2. Verificar que la nueva cumple los requisitos
        //Si es premium
        if(UsuariosManager.existeUsuarioPremium(usuario.getUsername())){
            if (!UsuarioPremium.validarPasswordPremium(nueva)) { 
                JOptionPane.showMessageDialog(this, "La nueva contraseña no cumple con los requisitos de seguridad.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } else{
            //Si no es premium
            if (!usuario.validarPassword(nueva)){
                JOptionPane.showMessageDialog(this, "La nueva contraseña no cumple con los requisitos de seguridad.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        // 3. Verificar que coinciden nueva y confirmación
        if (!nueva.equals(confirmacion)) { 
            JOptionPane.showMessageDialog(this, "La confirmación de la nueva contraseña no coincide.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // 4. Actualizar la contraseña
        usuario.setPassword(nueva); 
        JOptionPane.showMessageDialog(this, "Contraseña actualizada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        
        // 5. Serializar la lista
        UsuariosManager manager = UsuariosManager.getInstance();
        manager.guardarUsuarios();

        MenuPrincipal menu = new MenuPrincipal();
        menu.setVisible(true);
        this.dispose(); 
    }

    
    
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
            java.util.logging.Logger.getLogger(CambiarPass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CambiarPass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CambiarPass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CambiarPass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCPass;
    private javax.swing.JLabel lblCPass;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JLabel lblNewPass1;
    private javax.swing.JLabel lblNewPass2;
    private javax.swing.JLabel lblOldPass;
    private javax.swing.JPasswordField pwdNew1;
    private javax.swing.JPasswordField pwdNew2;
    private javax.swing.JPasswordField pwdOld;
    // End of variables declaration//GEN-END:variables
}
