/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimarket;
import BD_Proveedor.addProveedor;
import BD_Usuario.addUsuario;
import Control_BD.*;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Santa Isabel Minimarket
 */
public class Compras extends javax.swing.JDialog {
    int j=0;
        int k=0;
    int n = 0;
    float totals = 0;
    String son="";
    DefaultTableModel modelo;
    DefaultTableModel modelo2;
    int w=0;
    // llamada clase de conexion
    ConexionConBaseDatos conectar = new ConexionConBaseDatos();
    String ProveedorExistente = "no";
Control_Configuracion config=new Control_Configuracion();
    /**
     * Creates new form Ventas
     */
    public Compras(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        modelo = new DefaultTableModel();
        // Agrega fila

        modelo.addColumn("Cod");
          modelo.addColumn("Cantidad");
        modelo.addColumn("Productos");
         modelo.addColumn("Descripción");
 
        modelo.addColumn("Precio Unitario");
        modelo.addColumn("Importe");
        

        this.JTableProduct.setModel(modelo);
        setLocationRelativeTo(null);

        // CONFIGURAR el taño de ancho de la tabla
        int[] anchos = {50, 80, 150, 150, 100, 100};
        for (int i = 0; i < JTableProduct.getColumnCount(); i++) {
            JTableProduct.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            
            
            ocultar_columnas(); 
            
            
        //tbla 2
            
            
            
   
                 modelo2 = new DefaultTableModel();
        // Agrega fila

        modelo2.addColumn("importe");
  
        

   
       // setLocationRelativeTo(null);

        // CONFIGURAR el taño de ancho de la tabla
        
        
        
        
        
        
        
        
        
        
        
        

        // Leer la fecha de computador y colocarlo el cuadro de fecha
        //-------------------------------------
        Date fechaActual = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy/MM/dd");
        String fecha = formateador.format(fechaActual);
        jTextFieldFecha.setText(fecha);
        jTextFieldFecha.setEditable(false);
        //-------------------------------------------
        }  }

    //ventana de productos
    private void llamarProductos() {

        // llamada de datos
        Control_Productos load = new Control_Productos();
        load.CargarProductosCompras();
        //Centramos nuestro jDialog
        jDialogProductos.setLocation(200, 100);
        //La hacemos modal
        jDialogProductos.setModal(true);
        //Establecemos dimensiones.
        jDialogProductos.setMinimumSize(new Dimension(747, 385));

        //Establecemos un título para el jDialog
        jDialogProductos.setTitle("Lista de Productos.");
        //La hacemos visible.
        jDialogProductos.setVisible(true);
    }

    //ventana de productos


    //ventana de productos
    private void llamarProveedor() {

        // llamada de datos
        Control_Proveedor loadss = new Control_Proveedor();
        loadss.CargarProveedoresVentas();
        //Centramos nuestro jDialog
        jDialogProveedor.setLocation(250, 150);
        //La hacemos modal
        jDialogProveedor.setModal(true);
        //Establecemos dimensiones.
        jDialogProveedor.setMinimumSize(new Dimension(530, 358));
        //Establecemos un título para el jDialog
        jDialogProveedor.setTitle("Lista de Proveedores.");
        JTextField texto=new JTextField();
        jDialogProveedor.add(texto);
        //La hacemos visible.
        jDialogProveedor.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogVendedor = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        SeleccionarVendedor = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jDialogProductos = new javax.swing.JDialog();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        SeleccionarProductos = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jTextFieldParametroBusqueda = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jDialogProveedor = new javax.swing.JDialog();
        jScrollPane4 = new javax.swing.JScrollPane();
        SeleccionarProveedor = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        JTableProduct = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTextFieldCodigoCliente = new javax.swing.JTextField();
        jTextField_direccion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jTextField_razon_social = new javax.swing.JTextField();
        jTextField_ruc = new javax.swing.JTextField();
        jButton_reg_cliente = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jTextFieldNo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jTextFieldCant = new javax.swing.JTextField();
        jTextFieldImporte = new javax.swing.JTextField();
        jTextFieldTotals = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jButton_nuevo = new javax.swing.JButton();
        btn_boleta = new javax.swing.JButton();
        jTextField_son = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea_descripcion = new javax.swing.JTextArea();
        xcant = new javax.swing.JSpinner();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldFecha = new javax.swing.JTextField();
        jTextFieldCodProducto = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldProductos = new javax.swing.JTextField();
        jTextFieldValor = new javax.swing.JTextField();

        jDialogVendedor.setResizable(false);
        jDialogVendedor.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        SeleccionarVendedor.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        SeleccionarVendedor.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(SeleccionarVendedor);

        jDialogVendedor.getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 330, 120));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 102, 102));
        jButton1.setText("Seleccionar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jDialogVendedor.getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, -1, -1));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/FotoliaComp_65788270_jz4eEB4qgaZ0kMopuwpgOffUvlmIpQbP.jpg"))); // NOI18N
        jDialogVendedor.getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jDialogProductos.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 204, 0));
        jLabel16.setText("Productos y Articulos de Ferreteria");
        jDialogProductos.getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jScrollPane2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        SeleccionarProductos.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        SeleccionarProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane2.setViewportView(SeleccionarProductos);

        jDialogProductos.getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 610, 230));

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton7.setForeground(new java.awt.Color(0, 102, 102));
        jButton7.setText("Seleccionar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jDialogProductos.getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 300, -1, -1));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setText("Buscar ");

        jTextFieldParametroBusqueda.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldParametroBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldParametroBusquedaActionPerformed(evt);
            }
        });
        jTextFieldParametroBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldParametroBusquedaKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel21)
                    .addGap(12, 12, 12)
                    .addComponent(jTextFieldParametroBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel21)
                        .addComponent(jTextFieldParametroBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jDialogProductos.getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 250, 40));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/ferreteria-la-llave-portada-001.jpg"))); // NOI18N
        jDialogProductos.getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        SeleccionarProveedor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        SeleccionarProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(SeleccionarProveedor);

        jLabel20.setFont(new java.awt.Font("Sylfaen", 0, 36)); // NOI18N
        jLabel20.setText("Lista de Proveedores");

        jButton10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton10.setForeground(new java.awt.Color(0, 51, 51));
        jButton10.setText("Seleccionar");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialogProveedorLayout = new javax.swing.GroupLayout(jDialogProveedor.getContentPane());
        jDialogProveedor.getContentPane().setLayout(jDialogProveedorLayout);
        jDialogProveedorLayout.setHorizontalGroup(
            jDialogProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogProveedorLayout.createSequentialGroup()
                .addGroup(jDialogProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogProveedorLayout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addComponent(jButton10))
                    .addGroup(jDialogProveedorLayout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(jLabel20)))
                .addContainerGap(95, Short.MAX_VALUE))
            .addGroup(jDialogProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDialogProveedorLayout.createSequentialGroup()
                    .addGap(0, 30, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 30, Short.MAX_VALUE)))
        );
        jDialogProveedorLayout.setVerticalGroup(
            jDialogProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogProveedorLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
                .addComponent(jButton10)
                .addContainerGap())
            .addGroup(jDialogProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDialogProveedorLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JTableProduct.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        JTableProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        JTableProduct.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        JTableProduct.setRowHeight(30);
        JTableProduct.setSelectionBackground(new java.awt.Color(153, 153, 153));
        jScrollPane3.setViewportView(JTableProduct);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 580, 200));

        jLabel1.setFont(new java.awt.Font("Constantia", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Regristro de Compra");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 360, 40));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Datos personales de Cliente", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jTextFieldCodigoCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldCodigoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldCodigoClienteKeyTyped(evt);
            }
        });

        jTextField_direccion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField_direccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_direccionKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel4.setText("Dirección");

        jLabel6.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel6.setText("Cod_proveedor");

        jButton3.setText("Proveedor existente ?");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel22.setText("RUC");

        jLabel23.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel23.setText("Razón social");

        jTextField_razon_social.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField_razon_social.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_razon_socialKeyTyped(evt);
            }
        });

        jTextField_ruc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField_ruc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_rucKeyTyped(evt);
            }
        });

        jButton_reg_cliente.setText("Registrar proveedor");
        jButton_reg_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_reg_clienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addGap(47, 47, 47)
                                .addComponent(jTextField_razon_social, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jTextField_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField_ruc, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel22))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jButton_reg_cliente)
                .addGap(0, 80, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jButton3)
                    .addComponent(jButton_reg_cliente))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jTextField_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel22)
                        .addGap(5, 5, 5)
                        .addComponent(jTextField_ruc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField_razon_social, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, 660, 160));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 490, 10));

        jTextFieldNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNoActionPerformed(evt);
            }
        });
        jPanel1.add(jTextFieldNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 410, 40, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/FotoliaComp_65790455_A9698WOdTOIEhPxl3ywQ0nZPi07EeRmf.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-40, 50, 370, -1));

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 0, 0));
        jButton4.setText("Eliminar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 450, -1, -1));

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(204, 0, 0));
        jButton5.setText("Cerrar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 590, -1, -1));

        jTextFieldCant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCantActionPerformed(evt);
            }
        });
        jPanel1.add(jTextFieldCant, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 410, 40, -1));
        jPanel1.add(jTextFieldImporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 410, 40, -1));

        jTextFieldTotals.setEditable(false);
        jTextFieldTotals.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldTotals.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldTotals.setForeground(new java.awt.Color(0, 102, 0));
        jTextFieldTotals.setText("00.0");
        jPanel1.add(jTextFieldTotals, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 530, 130, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setText("Totals a Pagar G.");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 500, -1, -1));

        jButton_nuevo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_nuevo.setForeground(new java.awt.Color(204, 0, 0));
        jButton_nuevo.setText("Nuevo");
        jButton_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_nuevoActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 590, -1, -1));

        btn_boleta.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_boleta.setForeground(new java.awt.Color(0, 51, 51));
        btn_boleta.setText(" Boleta");
        btn_boleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_boletaActionPerformed(evt);
            }
        });
        jPanel1.add(btn_boleta, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 580, -1, -1));

        jTextField_son.setEditable(false);
        jTextField_son.setBackground(new java.awt.Color(255, 255, 255));
        jTextField_son.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField_son.setEnabled(false);
        jPanel1.add(jTextField_son, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 390, 310, -1));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/trabajador.png"))); // NOI18N
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Cant");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Valor G.");

        jTextArea_descripcion.setColumns(20);
        jTextArea_descripcion.setRows(5);
        jTextArea_descripcion.setEnabled(false);
        jScrollPane5.setViewportView(jTextArea_descripcion);

        xcant.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        xcant.setModel(new javax.swing.SpinnerNumberModel(1, 0, 30, 1));
        xcant.setToolTipText("");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Producto");

        jTextFieldFecha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTextFieldCodProducto.setEditable(false);
        jTextFieldCodProducto.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldCodProducto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldCodProducto.setEnabled(false);
        jTextFieldCodProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCodProductoActionPerformed(evt);
            }
        });

        jButton2.setText("Nuevo Producto");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Source Sans Pro", 0, 18)); // NOI18N
        jLabel7.setText("Id-Compras");

        jLabel24.setFont(new java.awt.Font("Sylfaen", 1, 12)); // NOI18N
        jLabel24.setText("Fecha");

        jButton6.setText("Agregar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("COD");

        jLabel14.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel14.setText("Descripción");

        jTextFieldProductos.setEditable(false);
        jTextFieldProductos.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldProductos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldProductos.setEnabled(false);

        jTextFieldValor.setEditable(false);
        jTextFieldValor.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldValor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldValorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(300, 300, 300)
                        .addComponent(jLabel24)
                        .addGap(30, 30, 30)
                        .addComponent(jTextFieldFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel14))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(jButton2)
                                .addGap(3, 3, 3)
                                .addComponent(jButton6)
                                .addGap(8, 8, 8)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel8)
                                .addGap(51, 51, 51)
                                .addComponent(jLabel13)
                                .addGap(113, 113, 113)
                                .addComponent(jLabel11)
                                .addGap(59, 59, 59)
                                .addComponent(jLabel12))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jTextFieldCodProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jTextFieldProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jTextFieldValor, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(xcant, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(7, 7, 7)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2)
                                    .addComponent(jButton6))))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel13)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldCodProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(xcant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 210, 650, 170));
        jPanel5.getAccessibleContext().setAccessibleName("Facturas y Compras");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldCodProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCodProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCodProductoActionPerformed

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
    
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        llamarProductos();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // boton de selecciona productos
        int i = SeleccionarProductos.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Favor... seleccione una fila");
        } else//de lo contrario si se selecciono la fila 
        {
            j=1;
//            SeleccionarProductos.setValueAt(0, "");
            // obtener valor de tabla por columna y enviar datos a texfield
            jTextFieldCodProducto.setText(String.valueOf(SeleccionarProductos.getValueAt(i, 0)));
          //  jTextFieldProductos.setText(String.valueOf(SeleccionarProductos.getValueAt(i, 1)));
            jTextFieldProductos.setText(String.valueOf(SeleccionarProductos.getValueAt(i, 2)));
            jTextFieldValor.setText(String.valueOf(SeleccionarProductos.getValueAt(i, 6)));
            jTextArea_descripcion.setText(String.valueOf(SeleccionarProductos.getValueAt(i, 4)));
        }
        jDialogProductos.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        /*int i = SeleccionarVendedor.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Favor... seleccione una fila");
        } else//de lo contrario si se selecciono la fila 
        {
            // obtener valor de tabla por columna y enviar datos a texfield
           // jTextFieldCodVendedor.setText(String.valueOf(SeleccionarVendedor.getValueAt(i, 0)));
            //vendedors.setText(String.valueOf(SeleccionarVendedor.getValueAt(i, 1)));
        }
        jDialogVendedor.dispose();
*/
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        //sumar contador
System.out.println(j);
  if (j == 0) {
            JOptionPane.showMessageDialog(null, "Favor... seleccione producto");
        } else//de lo contrario si se selecciono la fila 
        {

            n = 1 + n;
            jTextFieldNo.setText(String.valueOf(n));
            // leer la cantidad pedidas
            int cant = (Integer) xcant.getValue();
            //problema de obrtener valor de spinner toca dar vuelta
            jTextFieldCant.setText(String.valueOf(cant));

            float a = Float.valueOf(jTextFieldValor.getText());
            float b = Float.valueOf(jTextFieldCant.getText());
            // Calcular la cantidad por valor
            float importe = a * b;
            jTextFieldImporte.setText(String.valueOf(importe));

            //Agregar datos a la tabla        
            String datos[] = new String[7];
                  
            datos[0] = jTextFieldCodProducto.getText();
            datos[1] = jTextFieldCant.getText();
            datos[2] = jTextFieldProductos.getText();
            datos[3] = jTextArea_descripcion.getText();
 
            datos[4] = jTextFieldValor.getText();
            datos[5] = jTextFieldImporte.getText();
            
            modelo.addRow(datos);

            totals = totals + importe;
            jTextFieldTotals.setText(String.valueOf(totals));
            //pasamos el total como con un entero a la funcion y nos convierte en string 
            //luego escribimos el son en string en el jtexfield.settex(son)
                    Numero_a_Letra NumLetra = new Numero_a_Letra();
        String numero;
         numero = jTextFieldTotals.getText();
   jTextField_son.setText(NumLetra.Convertir(numero,band()));
           // son=jTextField_son.getText();
 xcant.setValue(1);
 }
       // jTextFieldCant.setText("1");
    }//GEN-LAST:event_jButton6ActionPerformed
    private static boolean band(){
        if ( Math.random() > .5) {
            return true;
        }else{
            return false;
        }
    }
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:

        // seleccion la fila
        int i = JTableProduct.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Favor... seleccione una fila");
        } else//de lo contrario si se selecciono la fila 
        {
            
            
            try {
                
           
            // coje variable de importe que elimino
            String nums = (String) JTableProduct.getValueAt(i, 5);
            float entero = Float.valueOf(nums);

            // cambiarlo de subtotalas que va eliminar 
            // sumar total
            totals = totals - entero;
            jTextFieldTotals.setText(String.valueOf(totals));
                      Numero_a_Letra NumLetra = new Numero_a_Letra();
        String numero;
         numero = jTextFieldTotals.getText();
   jTextField_son.setText(NumLetra.Convertir(numero,band()));

            // eliminar fila
            this.modelo.removeRow(i);
            // restar un aticulos
            n = n - 1;
            System.out.println("n: "+n);
            // poner nuevo ciclo
            int num = 1;
        //     int smm = Integer.parseInt(mm);
     /*       for (int w = 0; w < n; w = w + 1) {
                //agregado de nuevo 
                JTableProduct.setValueAt(num, w, 0);
                num = num + 1;
            }*/
             } catch (Exception e) {
            }

        }
    }//GEN-LAST:event_jButton4ActionPerformed
    void ocultar_columnas() {
        JTableProduct.getColumnModel().getColumn(0).setMaxWidth(0);
        JTableProduct.getColumnModel().getColumn(0).setMinWidth(0);
        JTableProduct.getColumnModel().getColumn(0).setPreferredWidth(0);
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        llamarProveedor();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed

        // boton de seleccionado
        int i = SeleccionarProveedor.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Favor... seleccione una fila");
        } else//de lo contrario si se selecciono la fila 
        {
                k=1;
            ProveedorExistente = "si";
            // obtener valor de tabla por columna y enviar datos a texfield
            jTextFieldCodigoCliente.setText(String.valueOf(SeleccionarProveedor.getValueAt(i, 0)));
 
            jTextField_razon_social.setText(String.valueOf(SeleccionarProveedor.getValueAt(i, 1)));
             jTextField_ruc.setText(String.valueOf(SeleccionarProveedor.getValueAt(i, 2)));
             jTextField_direccion.setText(String.valueOf(SeleccionarProveedor.getValueAt(i, 3)));

        }
        jDialogProveedor.dispose();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jTextFieldParametroBusquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldParametroBusquedaKeyPressed
        // TODO add your handling code here:
        Control_Productos cc = new Control_Productos();
        String parametroBusqueda = jTextFieldParametroBusqueda.getText();
        cc.buscarProductosparaCompras(parametroBusqueda);
                                                    int[] anchos = {35,150,360, 50, 100, 40, 50};
        for (int i = 0; i < Compras.SeleccionarProductos.getColumnCount(); i++) {
            Compras.SeleccionarProductos.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);}


    }//GEN-LAST:event_jTextFieldParametroBusquedaKeyPressed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_nuevoActionPerformed
        // TODO add your handling code here:
            dispose();
                      Compras.main(null);
            
    }//GEN-LAST:event_jButton_nuevoActionPerformed
public void  botonerror(){
 if (jTextFieldCodigoCliente.getText().equals("") || jTextFieldCodProducto.equals("")) {
                JOptionPane.showMessageDialog(null, "Falta llenar datos ");
                   dispose();
                      Compras.main(null);
            }
    
}
    private void btn_boletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_boletaActionPerformed
        // TODO add your handling code here:
   //     botonerror();
    if (k==0) {
                JOptionPane.showMessageDialog(null, "Falta llenar datos proveedor ");
                   dispose();
                      Compras.main(null);
            }
        if ( j==0) {
                JOptionPane.showMessageDialog(null, "Falta seleccionar producto");
                   dispose();
                      Compras.main(null);
            }
     if(j==1 && k==1){
              
                 Object [] fila=new Object[6]; 
 
        //Sección 3
        fila[0]=jTextFieldTotals.getText(); 

 
         //Sección 4
         modelo2.addRow(fila); 
  
        //Sección 5
    
        
        
        //
        Connection reg = conectar.getConexion();

        //PARA DETERMINAR SI ES CLIENTE EXISTENTE O NO
        if (ProveedorExistente == "si") {
            JOptionPane.showMessageDialog(null, " proveedor existente");
        } //sino agregado cliente existente entonces lo guarda nuevo cliente
        else {
        // regristar bd a la tabla de cliente
            // obtener datos         
          

            String direccion = (jTextField_direccion.getText());
  
            
            
            
            
            
            
        //String razon_social=jTextField_razon_social.getText();
            //String ruc=jTextField_razon_social.getText();
            Control_Proveedor add = new Control_Proveedor();
            // enviar datos a regristar en el control_cliente

     //       add.agregarCliente( nombre, apellido, direccion,razon_social,ruc);
            

        }

        // regristo de BD a la tabla de Facturas
        String sql_Facturas = "INSERT INTO table_facturas_compras (No_Facturas_Compras,proveedor,fecha_compras,totals_compras)VALUES (?,?,?,?)";
        try {
            PreparedStatement pst = reg.prepareStatement(sql_Facturas);
            pst.setString(1, (""));
            pst.setString(2, (jTextFieldCodigoCliente.getText()));
            pst.setString(3, jTextFieldFecha.getText());
//            pst.setInt(4, Integer.parseInt(jTextFieldCodVendedor.getText()));
            pst.setString(4,(jTextFieldTotals.getText()));
            int ns = pst.executeUpdate();
            if (ns > 0) {
                JOptionPane.showMessageDialog(null, "Regristado Exitosamente de Facturas ");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error - " + ex);
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //
         
      String nro_factura="";
//consulta mostrar cargar tabla plan con parametros nombre o dias
        //String[] titulos = {"id", "nombres", "precio", "dias"};

        String[] registro = new String[14];

        int totalregistros = 0;
   //     modelo = new DefaultTableModel(null, titulos);
     String sSQL = "";
        sSQL = "select No_Facturas_Compras"
                + " from table_facturas_compras "
                + "";

        try {
            Statement st = reg.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("No_Facturas_Compras");
 
   nro_factura=(registro[0]);
                
                totalregistros = totalregistros + 1;
              //  modelo.addRow(registro);

            }


        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
          //  return null;

        }

    
        
        
        //

        // regristar bd a la tabla de ventas
        // guardar base de datos a la tabla de ventas
        // se regrista por fila con el ciclo hasta No. de ultimo.
        System.out.println("n compras: "+n);
        for (int w = 0; w < n; w = w + 1) {
         
            //obteniendo valor fila por columna a la ves y w es fila y n es total de articulos comprados
            String col1 = (String) JTableProduct.getValueAt(w, 0);// valor de codigo ventas
                  System.out.println("col1 compras: "+col1);
            String col2 = (String) JTableProduct.getValueAt(w, 1);// valor de codigo productos
 //           suma=(String) JTableProduct.getValueAt(w, 3);
       //     String col3 = (String) JTableProduct.getValueAt(w, 4);// valor de cantidad productos
            String col3 = (String) JTableProduct.getValueAt(w, 5);// valor de importe

            String sql_Ventas = "INSERT INTO table_compras (idCompras,No_facturas_compras,Productos_Compras,Cantidad,importe)VALUES (?,?,?,?,?)";
            try {
                PreparedStatement pst = reg.prepareStatement(sql_Ventas);
                pst.setString(1, "");
                pst.setString(2, nro_factura);
                pst.setString(3, col1);
                pst.setString(4, col2);
                pst.setString(5, col3);
                int ns = pst.executeUpdate();
                if (ns > 0) {
                    System.out.println("Regristado Exitosamente Compras " + col1);

                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error - " + ex);
                Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
            }
            //buscar cantidad actual de los prodcutos
                           Connection conexion = null;
    Statement sentencia = null;
    ResultSet resultado = null;
    PreparedStatement ps = null; 
    int cantidad_nueva=0;
        try {
            conexion = ConexionConBaseDatos.getConexion();

            sentencia = conexion.createStatement();
            String consultaSQL = "SELECT * FROM table_Productos WHERE idProductos=" + col1;
            resultado = sentencia.executeQuery(consultaSQL);


            //mientras haya datos en la BD ejecutar eso...
            while (resultado.next()) {



                cantidad_nueva= resultado.getInt("cantidadProductos")+Integer.parseInt(col2) ;




            }//cierra while (porque no hay mas datos en la BD)


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error SQL:\n" + e);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
            conexion = null;
        } finally {
            CerrarConexiones.metodoCerrarConexiones(conexion, sentencia, resultado, ps);
        }
            //actualizar stop del producto
  
        try {
             conexion = ConexionConBaseDatos.getConexion();
            Statement comando = conexion.createStatement();

            // linea de codigo de mysql que actualiza regristos que va modificar
            int cantidad = comando.executeUpdate("update table_Productos set cantidadProductos =" + cantidad_nueva + ""+ " where idProductos=" + col1);
            if (cantidad == 1) {
                System.out.println("Modifico con Exito");

            } else {
                JOptionPane.showMessageDialog(null,"No existe  de un codigo "+col1);
            }
            conexion.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null," Error -->"+ex);
        }
            
            
            
            
        }
        // cierra de ventas

        // y ahora genera facturas guardada en la carpeta Facturas
        Image portada;

        // CREA DOCUMENTOS CON TAMAÑO CARTAS Y MARGENE DE TODO LADO DE 50   
        Document document = new Document(PageSize.LETTER, 50, 50, 50, 50);

        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("facturas_compras/" + nro_factura + ".pdf"));
            document.open();
//Image imagen = Image.getInstance("/codigobarra/Configuraciones"+".png");
//document.add(imagen);@
//       portada = Image.getInstance("1.jpg");

 //String file = new String("\1.jpg" );
 //String directorio = "codigobarra/";
    //File carpeta = new File(directorio);
    //    String nombreArchivo = "67657"+".png";
  //   String master = System.getProperty("user.dir") +nombreArchivo;

//     portada = Image.getInstance(master);
     //D:\JAVA\hola mundo\sypossultimate_2022/1.jpg
           // portada = Image.getInstance(directorio+"/"+nombreArchivo);
            //     System.out.print(portada);
          // portada.setAlignment(Element.ALIGN_CENTER);
        //    portada.scalePercent(45f);// tamaño de imagen
    // System.out.print(portada);
      //      document.add(portada);

                document.add(Chunk.NEWLINE);
            document.add(new Paragraph("Razon social proveedor: "+jTextField_razon_social.getText()));
            document.add(new Paragraph("RUC prveedor: "+ jTextField_ruc.getText()));

            document.add(new Paragraph("Direccion proveedor: "+jTextField_direccion.getText()));
            document.add(new Paragraph("Fecha: "+ jTextFieldFecha.getText()));
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            

      
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);            
                  document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            // parte de dibujo la tabla
            PdfContentByte cb = writer.getDirectContent();
            PdfTemplate tp = cb.createTemplate(500, 500);
            
    
            Graphics2D g2 ;
          //  cb.drawTextField(TOP_ALIGNMENT, TOP_ALIGNMENT, TOP_ALIGNMENT, TOP_ALIGNMENT);
//          g2.drawString("richard", 400, 400);
            g2 = tp.createGraphicsShapes(500, 500);
   
          //  cb.drawTextField(TOP_ALIGNMENT, TOP_ALIGNMENT, TOP_ALIGNMENT, TOP_ALIGNMENT);
//          g2.drawString("richard", 400, 400);

            // g2 = tp.createGraphics(500, 500);
            //T.print(g2);
            //String s;
            
             JTableProduct.print(g2);

       
            
            g2.dispose();
        
            //posicion de la tabla de lista de compras

              cb.addTemplate(tp, 50,120);
     
///            Paragraph parrafo = new Paragraph("RIchard quispe ");
   //         parrafo.setIndentationLeft(150);
            //parrafo.setInden(150)
      //      document.add(parrafo);
      Control_Configuracion c=new Control_Configuracion();
      String simbolo=c.getsimbolo_moneda();
//                    document.add(new Paragraph(BOTTOM_ALIGNMENT, "RIchard quispe"));
                  document.add(new Paragraph(simbolo+" "+jTextFieldTotals.getText()));
            document.add(new Paragraph("            "+jTextField_son.getText()));
            //cierra el documento
            document.close();
            JOptionPane.showMessageDialog(null, "Generado PDF Exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
        
        
        
                      String file = new String("/facturas_compras/" + nro_factura + ".pdf"); 
     String master = System.getProperty("user.dir") + file;
try {
     File path = new File (master);
     Desktop.getDesktop().open(path);
}catch (IOException ex) {
     ex.printStackTrace();
} 

   dispose();
                      Compras.main(null);
    }//GEN-LAST:event_btn_boletaActionPerformed
}
    private void jButton_reg_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_reg_clienteActionPerformed
        // TODO add your handling code here:
        dispose();
          addProveedor.main(null);
          addProveedor.ventas="ventas";
    }//GEN-LAST:event_jButton_reg_clienteActionPerformed

    private void jTextField_rucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_rucKeyTyped
        // TODO add your handling code here:
                   if (jTextField_ruc.getText().length()>= 11){
                      String ruc="";
                      ruc=jTextField_ruc.getText();
             jTextField_ruc.setText(ruc.substring(0, ruc.length()-1));}
          char c=evt.getKeyChar(); 
//             String ruc=jTextField_rucKeyTyped(evt);.getText();
    
          if(Character.isLetter(c)) { 
         
                   
                   
              getToolkit().beep(); 
               
              evt.consume(); 
               }
    }//GEN-LAST:event_jTextField_rucKeyTyped

    private void jTextFieldCantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCantActionPerformed

    private void jTextFieldNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNoActionPerformed

    private void jTextFieldParametroBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldParametroBusquedaActionPerformed
        // TODO add your handling code here:
      
        
            Control_Productos cc = new Control_Productos();
        String parametroBusqueda = jTextFieldParametroBusqueda.getText();
        cc.buscarProductosparaComprascodigo(parametroBusqueda);
                                                 int[] anchos = {35,150,360, 50, 100, 40, 50};
        for (int i = 0; i < Compras.SeleccionarProductos.getColumnCount(); i++) {
            Compras.SeleccionarProductos.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);} 
    }//GEN-LAST:event_jTextFieldParametroBusquedaActionPerformed

    private void jTextFieldValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldValorActionPerformed

    private void jTextFieldCodigoClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCodigoClienteKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9') evt.consume();
    }//GEN-LAST:event_jTextFieldCodigoClienteKeyTyped

    private void jTextField_direccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_direccionKeyTyped
           char c = evt.getKeyChar();
        if((c<'a' || c>'z') && (c<'A' )|c>'Z') evt.consume();
    }//GEN-LAST:event_jTextField_direccionKeyTyped

    private void jTextField_razon_socialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_razon_socialKeyTyped
           char c = evt.getKeyChar();
        if((c<'a' || c>'z') && (c<'A' )|c>'Z') evt.consume();
    }//GEN-LAST:event_jTextField_razon_socialKeyTyped

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
            java.util.logging.Logger.getLogger(Compras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Compras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Compras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Compras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Compras dialog = new Compras(new javax.swing.JFrame(), true);
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
    public static javax.swing.JTable JTableProduct;
    public static javax.swing.JTable SeleccionarProductos;
    public static javax.swing.JTable SeleccionarProveedor;
    public static javax.swing.JTable SeleccionarVendedor;
    private javax.swing.JButton btn_boleta;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton_nuevo;
    private javax.swing.JButton jButton_reg_cliente;
    private javax.swing.JDialog jDialogProductos;
    private javax.swing.JDialog jDialogProveedor;
    private javax.swing.JDialog jDialogVendedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextArea_descripcion;
    private javax.swing.JTextField jTextFieldCant;
    private javax.swing.JTextField jTextFieldCodProducto;
    private javax.swing.JTextField jTextFieldCodigoCliente;
    private javax.swing.JTextField jTextFieldFecha;
    private javax.swing.JTextField jTextFieldImporte;
    private javax.swing.JTextField jTextFieldNo;
    private javax.swing.JTextField jTextFieldParametroBusqueda;
    private javax.swing.JTextField jTextFieldProductos;
    private javax.swing.JTextField jTextFieldTotals;
    private javax.swing.JTextField jTextFieldValor;
    private javax.swing.JTextField jTextField_direccion;
    private javax.swing.JTextField jTextField_razon_social;
    private javax.swing.JTextField jTextField_ruc;
    private javax.swing.JTextField jTextField_son;
    private javax.swing.JSpinner xcant;
    // End of variables declaration//GEN-END:variables
}
