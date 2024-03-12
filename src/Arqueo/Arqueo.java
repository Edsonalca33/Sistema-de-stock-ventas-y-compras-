/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arqueo;

import notaDebito.*;
import notaCredito.*;
import ferreteria.*;
import BD_Proveedor.addProveedor;
import BD_Usuario.addUsuario;
import Control_BD.*;
import GenerarRep.GenerarReporte;
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
public class Arqueo extends javax.swing.JDialog {

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
    public Arqueo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);

        cargarMetodosInicial();

    }

    /**
     * este método se encarga de inicializar algunos componentes y obtener
     * información de alguna fuente de datos externa para su posterior uso en la
     * aplicación.
     */
    public void cargarMetodosInicial() {
        Control_Consultas consul = new Control_Consultas();
        JCalendar jCalendar1 = new JCalendar();
        fechaSelecionada.setVisible(false);
        SimpleDateFormat sdf = new SimpleDateFormat("d/MM/yyyy");
        Date fechaSelecionada = jCalendar1.getDate();
        txtFecha.setText(sdf.format(jCalendar1.getDate()));

        List<String> listaApertura;
        try {
            listaApertura = consul.montonAperturaBuscar();
            txtApertura.setText(listaApertura.get(0));
            String caja = consul.setmontocaja();
            int caja1 = Integer.parseInt(caja) - Integer.valueOf(listaApertura.get(0));
            txtCaja.setText(String.valueOf(caja1));
            txtTotal.setText(caja);
        } catch (SQLException ex) {
            Logger.getLogger(Arqueo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void guardar() {
        for (int i = 0; i < 11; i++) {
            camposVacio();
        }

        comvertirNumeroSunar();

    }

    public void camposVacio() {
        if (txt50.getText().isEmpty()) {
            txt50.setText("0");
        } else if (txt100.getText().isEmpty()) {
            txt100.setText("0");
        } else if (txt500.getText().isEmpty()) {
            txt500.setText("0");
        } else if (txt1000G.getText().isEmpty()) {
            txt1000G.setText("0");
        } else if (txt1000b.getText().isEmpty()) {
            txt1000b.setText("0");
        } else if (txt2000b.getText().isEmpty()) {
            txt2000b.setText("0");
        } else if (txt5000b.getText().isEmpty()) {
            txt5000b.setText("0");
        } else if (txt10000b.getText().isEmpty()) {
            txt10000b.setText("0");
        } else if (txt20000b.getText().isEmpty()) {
            txt20000b.setText("0");
        } else if (txt50000b.getText().isEmpty()) {
            txt50000b.setText("0");
        } else if (txt100000b.getText().isEmpty()) {
            txt100000b.setText("0");
        }

    }

    public void comvertirNumeroSunar() {
        int saldo = 0, ciencuentaG = 50, cienG = 100, quinientoG = 500, milG = 1000, milB = 1000, dosMilB = 2000, cincoMilB = 5000, diezMilB = 10000, venteMilB = 20000, cincuentaMilB = 50000, cienMilB = 100000;
        ciencuentaG = ciencuentaG * Integer.parseInt(txt50.getText());
        cienG = cienG * Integer.parseInt(txt100.getText());
        quinientoG = quinientoG * Integer.parseInt(txt500.getText());
        milG = milG * Integer.parseInt(txt1000G.getText());
        milB = milB * Integer.parseInt(txt1000b.getText());
        dosMilB = dosMilB * Integer.parseInt(txt2000b.getText());
        cincoMilB = cincoMilB * Integer.parseInt(txt5000b.getText());
        diezMilB = diezMilB * Integer.parseInt(txt10000b.getText());
        venteMilB = venteMilB * Integer.parseInt(txt20000b.getText());
        cincuentaMilB = cincuentaMilB * Integer.parseInt(txt50000b.getText());
        cienMilB = cienMilB * Integer.parseInt(txt100000b.getText());
        saldo = ciencuentaG + cienG + quinientoG + milG + milB + dosMilB + cincoMilB + diezMilB + venteMilB + cincuentaMilB + cienMilB;
        String reporte = "<html><head><style>body {background-color: #FFFFFF;}</style></head><body>"
                + "<h2>Reporte</h2>"
                + "<table>"
                + "<tr><th>Moneda</th><th>Cantidad</th><th>Total</th></tr>"
                + "<tr><td>50G:</td><td>" + txt50.getText() + "</td><td>" + ciencuentaG + "</td></tr>"
                + "<tr><td>100G:</td><td>" + txt100.getText() + "</td><td>" + cienG + "</td></tr>"
                + "<tr><td>500G:</td><td>" + txt500.getText() + "</td><td>" + quinientoG + "</td></tr>"
                + "<tr><td>1000G:</td><td>" + txt1000G.getText() + "</td><td>" + milB + "</td></tr>"
                + "</table>"
                + "<table>"
                + "<tr><th>Billetes</th><th>Cantidad</th><th>Total</th></tr>"
                + "<tr><td>1000G:</td><td>" + txt1000b.getText() + "</td><td>" + milG + "</td></tr>"
                + "<tr><td>2000G:</td><td>" + txt2000b.getText() + "</td><td>" + dosMilB + "</td></tr>"
                + "<tr><td>5000G:</td><td>" + txt5000b.getText() + "</td><td>" + cincoMilB + "</td></tr>"
                + "<tr><td>10000G:</td><td>" + txt10000b.getText() + "</td><td>" + diezMilB + "</td></tr>"
                + "<tr><td>20000G:</td><td>" + txt20000b.getText() + "</td><td>" + venteMilB + "</td></tr>"
                + "<tr><td>50000G:</td><td>" + txt50000b.getText() + "</td><td>" + cincuentaMilB + "</td></tr>"
                + "<tr><td>100000G:</td><td>" + txt100000b.getText() + "</td><td>" + cienMilB + "</td></tr>"
                + "</table>"
                + "<h3>TOTAL CAJA:</h3>"
                + "<p>" + saldo + "</p>"
                + "</body></html>";

        JOptionPane.showMessageDialog(this, reporte);

        if (saldo == Integer.parseInt(txtTotal.getText())) {
            JOptionPane.showMessageDialog(this, "Se guardo Correctamente");

            Control_Consultas c = new Control_Consultas();
            try {
                c.montonAperturaEliminar(txtApertura.getText());
            } catch (SQLException ex) {
                Logger.getLogger(Arqueo.class.getName()).log(Level.SEVERE, null, ex);
            }
            c.cerrarcaja("Correcto", "0");
            dispose();
        } else if (saldo < Integer.parseInt(txtTotal.getText())) {
            int respuesta = JOptionPane.showConfirmDialog(this, "Tienes un faltante de: " + (saldo - Integer.parseInt(txtTotal.getText()))
                    + "\n" + "Quieres guardar asi?", "Confirmación", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                Control_Consultas c = new Control_Consultas();
                try {
                    c.montonAperturaEliminar(txtApertura.getText());
                } catch (SQLException ex) {
                    Logger.getLogger(Arqueo.class.getName()).log(Level.SEVERE, null, ex);
                }
                c.cerrarcaja("Faltante", String.valueOf(saldo - Integer.parseInt(txtTotal.getText())));
                dispose();
            }
        } else {
            int respuesta = JOptionPane.showConfirmDialog(this, "Te sobran: " + (saldo - Integer.parseInt(txtTotal.getText()))
                    + "\n" + "Quieres guardar asi?", "Confirmación", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                Control_Consultas c = new Control_Consultas();
                try {
                    c.montonAperturaEliminar(txtApertura.getText());
                } catch (SQLException ex) {
                    Logger.getLogger(Arqueo.class.getName()).log(Level.SEVERE, null, ex);
                }
                c.cerrarcaja("sobrante", String.valueOf(saldo - Integer.parseInt(txtTotal.getText())));
                dispose();
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
        jButton3 = new javax.swing.JButton();
        fechaSelecionada = new com.toedter.calendar.JCalendar();
        jLabel25 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt50 = new javax.swing.JTextField();
        txt100 = new javax.swing.JTextField();
        txt1000G = new javax.swing.JTextField();
        txt500 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt1000b = new javax.swing.JTextField();
        txt2000b = new javax.swing.JTextField();
        txt5000b = new javax.swing.JTextField();
        txt10000b = new javax.swing.JTextField();
        txt20000b = new javax.swing.JTextField();
        txt100000b = new javax.swing.JTextField();
        txt50000b = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtCaja = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        txtApertura = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

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

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Constantia", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Arqueo");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 360, 40));

        jButton3.setBackground(new java.awt.Color(255, 0, 0));
        jButton3.setText("X");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, -1, -1));
        jPanel1.add(fechaSelecionada, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 30));

        jLabel25.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel25.setText("Fecha");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, -1, -1));

        txtFecha.setEnabled(false);
        txtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaActionPerformed(evt);
            }
        });
        jPanel1.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, 130, -1));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 490, 10));

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel3.setText("50G");

        jLabel4.setText("100G");

        jLabel5.setText("500G");

        jLabel6.setText("1000G");

        txt50.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt50KeyTyped(evt);
            }
        });

        txt100.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt100KeyTyped(evt);
            }
        });

        txt1000G.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt1000GKeyTyped(evt);
            }
        });

        txt500.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt500KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel3))
                            .addComponent(jLabel5)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt50, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt1000G, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt500, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt100, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt100, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt500, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt1000G, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 190, 170));

        jLabel2.setText("Monedas");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, -1, -1));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 190, 10));

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel7.setText("1000G");

        jLabel8.setText("2000G");

        jLabel9.setText("5000G");

        jLabel10.setText("100000G");

        jLabel11.setText("10000G");

        jLabel12.setText("20000G");

        jLabel13.setText("50000G");

        txt1000b.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt1000bKeyTyped(evt);
            }
        });

        txt2000b.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt2000bKeyTyped(evt);
            }
        });

        txt5000b.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt5000bKeyTyped(evt);
            }
        });

        txt10000b.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt10000bKeyTyped(evt);
            }
        });

        txt20000b.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt20000bKeyTyped(evt);
            }
        });

        txt100000b.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt100000bKeyTyped(evt);
            }
        });

        txt50000b.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt50000bKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txt1000b, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(txt2000b, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(txt5000b, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(txt20000b, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt100000b, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(txt10000b, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt50000b, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13)
                    .addComponent(txt1000b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt10000b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt50000b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel8)
                    .addComponent(txt2000b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt20000b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txt100000b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt5000b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 230, 520, 170));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 200, 510, 10));

        jLabel14.setText("Billetes");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 210, -1, -1));

        jLabel26.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel26.setText("Total Caja");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 150, 90, -1));

        jLabel27.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel27.setText("Caja");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, -1, -1));

        txtCaja.setEnabled(false);
        txtCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCajaActionPerformed(evt);
            }
        });
        jPanel1.add(txtCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 130, -1));

        jLabel28.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel28.setText("Apertura");
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 110, -1, -1));

        txtTotal.setEnabled(false);
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });
        jPanel1.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 150, 130, -1));

        txtApertura.setEnabled(false);
        txtApertura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAperturaActionPerformed(evt);
            }
        });
        jPanel1.add(txtApertura, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 110, 130, -1));

        jButton2.setBackground(new java.awt.Color(51, 102, 255));
        jButton2.setText("Guardar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 420, -1, -1));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/caja_registradora.png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 225, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 800, 490));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

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
        for (int i = 0; i < Arqueo.SeleccionarProductos.getColumnCount(); i++) {
            Arqueo.SeleccionarProductos.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
    }//GEN-LAST:event_jTextFieldParametroBusquedaActionPerformed

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCajaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCajaActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void txtAperturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAperturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAperturaActionPerformed

    private void txt50KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt50KeyTyped
        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
            evt.consume();
        }

        if (txt50.getText().trim().length() == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txt50KeyTyped

    private void txt100KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt100KeyTyped
        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
            evt.consume();
        }

        if (txt100.getText().trim().length() == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txt100KeyTyped

    private void txt500KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt500KeyTyped
        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
            evt.consume();
        }

        if (txt500.getText().trim().length() == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txt500KeyTyped

    private void txt1000GKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt1000GKeyTyped
        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
            evt.consume();
        }

        if (txt1000G.getText().trim().length() == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txt1000GKeyTyped

    private void txt1000bKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt1000bKeyTyped
        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
            evt.consume();
        }

        if (txt1000b.getText().trim().length() == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txt1000bKeyTyped

    private void txt2000bKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt2000bKeyTyped
        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
            evt.consume();
        }

        if (txt2000b.getText().trim().length() == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txt2000bKeyTyped

    private void txt5000bKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt5000bKeyTyped
        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
            evt.consume();
        }

        if (txt5000b.getText().trim().length() == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txt5000bKeyTyped

    private void txt10000bKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt10000bKeyTyped
        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
            evt.consume();
        }

        if (txt10000b.getText().trim().length() == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txt10000bKeyTyped

    private void txt20000bKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt20000bKeyTyped
        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
            evt.consume();
        }

        if (txt20000b.getText().trim().length() == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txt20000bKeyTyped

    private void txt50000bKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt50000bKeyTyped
        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
            evt.consume();
        }

        if (txt50000b.getText().trim().length() == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txt50000bKeyTyped

    private void txt100000bKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt100000bKeyTyped
        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
            evt.consume();
        }

        if (txt100000b.getText().trim().length() == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txt100000bKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        guardar();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Arqueo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Arqueo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Arqueo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Arqueo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                Arqueo dialog = new Arqueo(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextField jTextFieldParametroBusqueda;
    private javax.swing.ButtonGroup radioGrupo;
    private javax.swing.JTextField txt100;
    private javax.swing.JTextField txt100000b;
    private javax.swing.JTextField txt10000b;
    private javax.swing.JTextField txt1000G;
    private javax.swing.JTextField txt1000b;
    private javax.swing.JTextField txt20000b;
    private javax.swing.JTextField txt2000b;
    private javax.swing.JTextField txt50;
    private javax.swing.JTextField txt500;
    private javax.swing.JTextField txt50000b;
    private javax.swing.JTextField txt5000b;
    private javax.swing.JTextField txtApertura;
    private javax.swing.JTextField txtCaja;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables

}
