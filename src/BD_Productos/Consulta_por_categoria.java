/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package BD_Productos;


import static BD_Productos.ConsultarProductos.jTableListarCliente;
import Control_BD.ConexionConBaseDatos;
import Control_BD.Control_Productos;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author RICHARD
 */
public class Consulta_por_categoria extends javax.swing.JDialog {
   ConexionConBaseDatos conectar = new ConexionConBaseDatos();
        String codigo_barra="";
    String idproducto="";
       Connection conexion = null;
    Statement sentencia = null;
    ResultSet resultado = null;
    PreparedStatement ps = null;
        String[] registros = new String[2];
    /**
     * Creates new form Consulta_por_categoria
     */
    public Consulta_por_categoria(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
            llamarProductos();
         LlenaCombo();
    }

            private void llamarProductos() {

        // llamada de datos
        Control_Productos load = new Control_Productos();
        load.CargarProductostodoscategoria();
                                          int[] anchos = {35, 250, 400, 150, 150, 90,150};
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);}
//ocultar_columnas();
    }
     public void LlenaCombo(){
       // int itemId=0;
        //String caption="";
            conexion = ConexionConBaseDatos.getConexion();
//    conexion = DriverManager.getConnection(AQUI PONES TU RUTA);
   


 

       try {
                sentencia = conexion.createStatement();
                String consultaSQL = "SELECT * FROM table_categoria ORDER BY nombre_categoria ASC";
            resultado = sentencia.executeQuery(consultaSQL);



           //miCombo = new JComboBox("DATO");
           
           while(resultado.next()){
                  //Integer itemId = resultado.getInt(1);
                //String caption = resultado.getString(2);
//registros[1] = resultado.getString("id_categoria");
         //   registros[2] = resultado.getString("nombre_categoria");
              //  jComboBox_categoria.addItem(resultado.getString(1));
                //jComboBox_categoria.set(resultado.getString(1), caption);  
               jComboBox_categoria.addItem(resultado.getString(2));
              
               
           }   } catch (SQLException ex) {
           Logger.getLogger(Consulta_por_categoria.class.getName()).log(Level.SEVERE, null, ex);
       }

 }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton_imprimir_codigobarra = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton_buscar_categoria = new javax.swing.JButton();
        jComboBox_categoria = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(700, 410));

        jButton_imprimir_codigobarra.setText("GENERAR CODIGO DE BARRA");
        jButton_imprimir_codigobarra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_imprimir_codigobarraActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setPreferredSize(new java.awt.Dimension(700, 400));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton_buscar_categoria.setText("Buscar por categoria");
        jButton_buscar_categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_buscar_categoriaActionPerformed(evt);
            }
        });
        jButton_buscar_categoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jButton_buscar_categoriaKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Seleciona categoria");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jButton_imprimir_codigobarra))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_buscar_categoria)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jButton_buscar_categoria))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_imprimir_codigobarra)
                .addGap(170, 170, 170))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_buscar_categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_buscar_categoriaActionPerformed
        // TODO add your handling code here:
                String categoira = (String)jComboBox_categoria.getSelectedItem();
           //     String posicion=(String)jComboBox_categoria.getSelectedItem();
                      try{
           Control_Productos cc = new Control_Productos();
        cc.buscarProductosporcategoria(categoira);
                    int[] anchos = {35, 250, 400, 150, 150, 90,150};
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);}
        
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null," bash "+e);
        }
    }//GEN-LAST:event_jButton_buscar_categoriaActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
          int fila = jTable1.rowAtPoint(evt.getPoint());
                idproducto=(jTable1.getValueAt(fila, 0).toString());
                        codigo_barra=(jTable1.getValueAt(fila, 1).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton_imprimir_codigobarraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_imprimir_codigobarraActionPerformed
        // TODO add your handling code here:
                if(!idproducto.equals("")){
   String codigob=codigo_barra;
     // Por defecto es A4


                          String file = new String("/etiqueta/" + codigob + ".pdf"); 
     String master = System.getProperty("user.dir") + file;
try {
     File path = new File (master);
     Desktop.getDesktop().open(path);
}catch (IOException ex) {
     ex.printStackTrace();
}
}
else
{
          JOptionPane.showMessageDialog(null,"Selecciona una fila la tabla" );
}
    }//GEN-LAST:event_jButton_imprimir_codigobarraActionPerformed

    private void jButton_buscar_categoriaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton_buscar_categoriaKeyTyped
         char c = evt.getKeyChar();
        if((c<'a' || c>'z') && (c<'A' )|c>'Z') evt.consume();
    }//GEN-LAST:event_jButton_buscar_categoriaKeyTyped

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
            java.util.logging.Logger.getLogger(Consulta_por_categoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Consulta_por_categoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Consulta_por_categoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Consulta_por_categoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Consulta_por_categoria dialog = new Consulta_por_categoria(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_buscar_categoria;
    private javax.swing.JButton jButton_imprimir_codigobarra;
    public static javax.swing.JComboBox<String> jComboBox_categoria;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
