/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notaCredito;

import ferreteria.*;
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
import com.sun.corba.se.impl.orbutil.CorbaResourceUtil;
import com.toedter.calendar.JCalendar;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tusolutionweb tutos
 */
public class NotaCreditoInicio extends javax.swing.JDialog {

    int j = 0;
    int k = 0;
    int n = 0;
    float totals = 0;
    String son = "";
    DefaultTableModel modelo;
    DefaultTableModel modelo2;
    int w = 0;
    // llamada clase de conexion
    ConexionConBaseDatos conectar = new ConexionConBaseDatos();
    String ProveedorExistente = "no";
    Control_Configuracion config = new Control_Configuracion();
    String nro_factura = "";

    /**
     * Creates new form Ventas
     */
    public NotaCreditoInicio(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        cargarMetodosInicial();

    }
    
       /**
     *  este método se encarga de inicializar algunos componentes
     * y obtener información de alguna fuente de datos
     * externa para su posterior uso en la aplicación.
     */
    public void cargarMetodosInicial() {

        JCalendar jCalendar1 = new JCalendar();
        fechaSelecionada.setVisible(false);
        btnEliminar.setVisible(false);
        txtTotal.setVisible(false);
        labelFecha.setVisible(false);
        SimpleDateFormat sdf = new SimpleDateFormat("d/MM/yyyy");
        Date fechaSelecionada = jCalendar1.getDate();
        txtFecha.setText(sdf.format(jCalendar1.getDate()));
        radioGrupo.add(radio1);
        radioGrupo.add(radio2);
        radioGrupo.add(radio3);
        try {
            Control_Consultas cc = new Control_Consultas();
            txtNotaCredito.setText(cc.obtenerUltimaNotaCredito());
        } catch (Exception e) {
        }
    }
    /**
     * Este método carga los datos de una factura en la interfaz gráfica del usuario.
     * Primero, se valida que el campo "N°DOC" no esté vacío ni sea igual a cero.
     * Si se cumplen las condiciones, se realiza una consulta a la base de datos
     * a través del objeto Control_Consultas y se obtienen dos listas de objetos Factura
     * y Productos. Luego, se verifica si la lista de productos está vacía o no,
     * si no está vacía se muestra la tabla de productos en la interfaz gráfica
     * y se asignan los anchos de las columnas, se crea el modelo de la tabla y
     * se agregan los datos de la lista de productos al modelo de la tabla.
     * Si la lista de productos está vacía, se oculta la tabla y se muestra
     * un mensaje indicando que la factura no se encontró. En resumen,
     * el método carga los datos de una factura en la interfaz gráfica 
     * y los muestra al usuario.
     * @throws SQLException 
     */
    private void datos() throws SQLException {
        if (txtNumeroDocumento.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "el campo N°DOC no puede estar vacio");
        } else if (txtNumeroDocumento.getText().equals("0")) {
            JOptionPane.showMessageDialog(this, "el valor del campo N°DOC no puede ser CERO");

        } else {

            Control_Consultas cc = new Control_Consultas();
            List<Factura> listaFactura = cc.facturaCredito(txtNumeroDocumento.getText());
            List<Productos> listaProducto = cc.obtenerProductosPorId(Integer.parseInt(txtNumeroDocumento.getText()));

            if (!listaProducto.isEmpty()) {
                btnEliminar.setVisible(true);
                txtTotal.setVisible(true);
                labelFecha.setVisible(true);
                txtTotal.setEditable(true);
                txtTotal.setText(listaProducto.get(0).getTotal());
                txtIGV.setText(listaProducto.get(0).getLgv());
                int[] anchos = {100, 150, 200, 150, 100, 100, 100, 100};

// Asignar los anchos a cada columna de la tabla
                for (int i = 0; i < jTableListarFacturas.getColumnCount(); i++) {
                    jTableListarFacturas.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
                }

// Crear el modelo de la tabla
                DefaultTableModel modeloTabla = new DefaultTableModel();

// Agregar las columnas al modelo de la tabla
                modeloTabla.addColumn("ID");
                modeloTabla.addColumn("Cantidad");
                modeloTabla.addColumn("Productos");
                modeloTabla.addColumn("Descripcion");
                modeloTabla.addColumn("Precio Unitario");
 
                
                //modeloTabla.addColumn("IGV");
                // modeloTabla.addColumn("Total");
                int i = 1;
// Agregar los datos de la lista de productos al modelo de la tabla
                for (Productos producto : listaProducto) {

                    Object[] fila = new Object[6];
                    fila[0] = i;
                    fila[1] = producto.getCantidad();
                    fila[2] = producto.getProductos();
                    fila[3] = producto.getDescripcion();
                    fila[4] = producto.getPrecioUnitario();
                    fila[5] = producto.getImporte();
                    modeloTabla.addRow(fila);
                    i++;
                }

// Asignar el modelo de la tabla a la tabla
                jTableListarFacturas.setModel(modeloTabla);

            } else {
                btnEliminar.setVisible(false);
                txtTotal.setVisible(false);
                labelFecha.setVisible(false);
                txtIGV.setText("");
                DefaultTableModel modeloVacio = new DefaultTableModel();
                jTableListarFacturas.setModel(modeloVacio);
                JOptionPane.showMessageDialog(this, "factura no encontrada");

            }

        }

    }
    
    /**
     * Este método muestra una ventana de confirmación y,
     * si el usuario confirma, genera una nota de crédito a 
     * partir de los datos de una factura y guarda la información 
     * en la base de datos. La nota de crédito se genera en función
     * de la opción seleccionada en un conjunto de botones de radio
     * y cambia el signo del total de la factura para indicar que 
     * se trata de una nota de crédito. Si la operación es exitosa,
     * se muestra un mensaje de éxito y se vacían los campos de 
     * entrada de datos.
     */

    private void eliminarGuardarNotaCredito() {
        int opcion = JOptionPane.showOptionDialog(null, "¿Estás seguro?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Sí", "No"}, "No");

        if (opcion == JOptionPane.YES_OPTION) {

            double num = Double.parseDouble(txtTotal.getText());
            num = -num;
            String total = String.valueOf(num);

            Factura factura = new Factura();
            String fecha = txtFecha.getText();
            String[] partes = fecha.split("/");
            String dia = partes[0];
            String mes = partes[1];
            mes = factura.obtenerMes(Integer.parseInt(mes));
            String año = partes[2];
            Control_Consultas cc = new Control_Consultas();
            String opcionSeleccionada = "";
            if (radio1.isSelected()) {
                opcionSeleccionada = "Por devolución de items";
            } else if (radio2.isSelected()) {
                opcionSeleccionada = "Por anulación de factura";
            } else if (radio3.isSelected()) {
                opcionSeleccionada = "Por descuento de factura";
            }
            try {
                cc.eliminarGuardarNotaCredito(dia, mes, año, txtNotaCredito.getText(), txtNumeroDocumento.getText(), opcionSeleccionada, total);
                JOptionPane.showMessageDialog(this, "Se Genero con existo la nota de credito");
                txtNumeroDocumento.setText("");
                txtIGV.setText("");
                DefaultTableModel modeloVacio = new DefaultTableModel();
                jTableListarFacturas.setModel(modeloVacio);
            } catch (Exception e) {
            }
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
        radioGrupo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtNotaCredito = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        comboMoneda = new javax.swing.JComboBox<>();
        txtFecha = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtNumeroDocumento = new javax.swing.JTextField();
        txtIGV = new javax.swing.JTextField();
        radio1 = new javax.swing.JRadioButton();
        radio2 = new javax.swing.JRadioButton();
        radio3 = new javax.swing.JRadioButton();
        jLabel24 = new javax.swing.JLabel();
        comboFctura = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        fechaSelecionada = new com.toedter.calendar.JCalendar();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableListarFacturas = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        labelFecha = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();

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

        jLabel1.setFont(new java.awt.Font("Constantia", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nota de Credito");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 360, 40));

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Datos Creditos", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        txtNotaCredito.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNotaCredito.setEnabled(false);
        txtNotaCredito.setFocusable(false);

        jLabel6.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel6.setText("NOTA DE CREDITO");

        jLabel23.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel23.setText("Tipo Factura");

        jLabel25.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel25.setText("Fecha");

        jLabel26.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel26.setText("I.G.V");

        comboMoneda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Guarani", "USD", "pesos" }));

        txtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel27.setText("N°DOC");

        txtNumeroDocumento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNumeroDocumento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumeroDocumentoKeyTyped(evt);
            }
        });

        txtIGV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtIGV.setEnabled(false);
        txtIGV.setFocusable(false);

        radio1.setText("Por devolucion de items");
        radio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio1ActionPerformed(evt);
            }
        });

        radio2.setText("Por anulación de factura");

        radio3.setText("Por descuento de factura");

        jLabel24.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel24.setText("Moneda");

        comboFctura.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Factura", "Boleta" }));

        jButton2.setBackground(new java.awt.Color(51, 51, 255));
        jButton2.setText("BUSCAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(radio1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(radio2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radio3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addGap(32, 32, 32)
                                .addComponent(txtIGV, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtNotaCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel27)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtNumeroDocumento)))))
                        .addGap(65, 65, 65)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel25)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addGap(18, 18, 18)
                                .addComponent(comboMoneda, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addGap(18, 18, 18)
                                .addComponent(comboFctura, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(161, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNotaCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel25)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(txtNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(comboFctura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtIGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(comboMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radio1)
                    .addComponent(radio2)
                    .addComponent(radio3)
                    .addComponent(jButton2))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 880, 240));
        jPanel2.getAccessibleContext().setAccessibleName("Datos del reporte");
        jPanel2.getAccessibleContext().setAccessibleDescription("Datos del reporte");

        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 490, 10));
        jPanel1.add(fechaSelecionada, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, 30));

        jTableListarFacturas.setBackground(new java.awt.Color(204, 255, 255));
        jTableListarFacturas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTableListarFacturas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableListarFacturas.setEnabled(false);
        jTableListarFacturas.setGridColor(new java.awt.Color(204, 204, 255));
        jTableListarFacturas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableListarFacturasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableListarFacturas);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 860, 210));

        btnEliminar.setBackground(new java.awt.Color(51, 51, 255));
        btnEliminar.setText("Generar Reporte ");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 550, -1, -1));

        labelFecha.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        labelFecha.setText("TOTAL");
        jPanel1.add(labelFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 550, -1, -1));

        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });
        jPanel1.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 550, 80, -1));

        jButton3.setBackground(new java.awt.Color(255, 0, 0));
        jButton3.setText("X");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 10, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 930, 640));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 860, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 860, 210));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

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


    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed


    }//GEN-LAST:event_jButton10ActionPerformed

    private void jTextFieldParametroBusquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldParametroBusquedaKeyPressed


    }//GEN-LAST:event_jTextFieldParametroBusquedaKeyPressed

    private void jTextFieldParametroBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldParametroBusquedaActionPerformed
        // TODO add your handling code here:

        Control_Productos cc = new Control_Productos();
        String parametroBusqueda = jTextFieldParametroBusqueda.getText();
        cc.buscarProductosparaComprascodigo(parametroBusqueda);
        int[] anchos = {35, 150, 360, 50, 100, 40, 50};
        for (int i = 0; i < NotaCreditoInicio.SeleccionarProductos.getColumnCount(); i++) {
            NotaCreditoInicio.SeleccionarProductos.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
    }//GEN-LAST:event_jTextFieldParametroBusquedaActionPerformed

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaActionPerformed

    private void radio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radio1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            datos();
        } catch (SQLException ex) {
            Logger.getLogger(NotaCreditoInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTableListarFacturasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListarFacturasMouseClicked
        // TODO add your handling code here:
        int fila = jTableListarFacturas.rowAtPoint(evt.getPoint());

        nro_factura = (jTableListarFacturas.getValueAt(fila, 0).toString());
    }//GEN-LAST:event_jTableListarFacturasMouseClicked

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminarGuardarNotaCredito();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtNumeroDocumentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroDocumentoKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9') evt.consume();
    }//GEN-LAST:event_txtNumeroDocumentoKeyTyped

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
            java.util.logging.Logger.getLogger(NotaCreditoInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NotaCreditoInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NotaCreditoInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NotaCreditoInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NotaCreditoInicio dialog = new NotaCreditoInicio(new javax.swing.JFrame(), true);
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
    public static javax.swing.JTable SeleccionarProductos;
    public static javax.swing.JTable SeleccionarProveedor;
    public static javax.swing.JTable SeleccionarVendedor;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JComboBox<String> comboFctura;
    private javax.swing.JComboBox<String> comboMoneda;
    private com.toedter.calendar.JCalendar fechaSelecionada;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton7;
    private javax.swing.JDialog jDialogProductos;
    private javax.swing.JDialog jDialogProveedor;
    private javax.swing.JDialog jDialogVendedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    public static javax.swing.JTable jTableListarFacturas;
    private javax.swing.JTextField jTextFieldParametroBusqueda;
    private javax.swing.JLabel labelFecha;
    private javax.swing.JRadioButton radio1;
    private javax.swing.JRadioButton radio2;
    private javax.swing.JRadioButton radio3;
    private javax.swing.ButtonGroup radioGrupo;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtIGV;
    private javax.swing.JTextField txtNotaCredito;
    private javax.swing.JTextField txtNumeroDocumento;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
