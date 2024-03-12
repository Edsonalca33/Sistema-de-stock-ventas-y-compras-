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
import static notaCredito.NotaCreditoInicio.jTableListarFacturas;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import javax.swing.JTable;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.JFileChooser;

/**
 *
 * @author tusolutionweb tutos
 */
public class ReportesAvanzados extends javax.swing.JDialog {

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
    public ReportesAvanzados(java.awt.Frame parent, boolean modal) {
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
        btnReporte.setVisible(false);
    }
    List<ReportesAvanzadosDTO> listaReporte = new ArrayList<>();

    /**
     * Este método se encarga de buscar y mostrar en una tabla los reportes
     * avanzados correspondientes a un período (año y mes) seleccionado en dos
     * JComboBox. Utiliza la clase Control_Consultas para buscar los reportes en
     * la base de datos y si los encuentra, los muestra en una tabla con
     * columnas para ID, N°DOCUMENTO, RAZON DE DEVOLUCION, NUMERO FACTURA y
     * PRECIO VENTA. Si no se encuentran reportes en la fecha seleccionada,
     * muestra un mensaje indicando que no se encontraron facturas y vacía la
     * tabla. Además, habilita un botón para generar un reporte a partir de los
     * datos mostrados en la tabla.
     *
     * @throws SQLException
     */
    private void datos() throws SQLException {
        String anio = (String) comboPeriodo.getSelectedItem();
        String mes = (String) comboMes.getSelectedItem();
        try {
            Control_Consultas cc = new Control_Consultas();
            listaReporte.clear();
            listaReporte = cc.buscarReportesAvanzados(anio, mes);

            if (!listaReporte.isEmpty()) {
                int[] anchos = {80, 170, 200, 150, 100, 100, 100, 100};

// Asignar los anchos a cada columna de la tabla
                for (int i = 0; i < jTableListarFacturas.getColumnCount(); i++) {
                    jTableListarFacturas.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
                }

// Crear el modelo de la tabla
                DefaultTableModel modeloTabla = new DefaultTableModel();

// Agregar las columnas al modelo de la tabla
                modeloTabla.addColumn("ID");
                modeloTabla.addColumn("N°DOCUMENTO");
                modeloTabla.addColumn("RAZON DE DEVOLUCION");
                modeloTabla.addColumn("NUMERO FACTURA");
                modeloTabla.addColumn("PRECIO VENTA");
                int i = 1;
// Agregar los datos de la lista de productos al modelo de la tabla
                for (ReportesAvanzadosDTO producto : listaReporte) {

                    Object[] fila = new Object[5];
                    fila[0] = i;
                    fila[1] = producto.getNotaCredito();
                    fila[2] = producto.getRazo();
                    fila[3] = producto.getNumeroFactura();
                    fila[4] = producto.getDinero();
                    modeloTabla.addRow(fila);
                    i++;
                }

// Asignar el modelo de la tabla a la tabla
                jTableListarFacturas.setModel(modeloTabla);
                btnReporte.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(this, "No se encontro factura en esta fecha");
                DefaultTableModel modeloVacio = new DefaultTableModel();
                jTableListarFacturas.setModel(modeloVacio);
                btnReporte.setVisible(false);
            }
        } catch (Exception e) {
        }

    }

    /**
     * El método generarPDF genera un archivo PDF con un reporte de notas de
     * crédito utilizando la librería iText y lo guarda en la carpeta del
     * proyecto. Primero se define la ruta y el nombre del archivo, luego se
     * crea el documento PDF, se agrega un título, se crea una tabla con las
     * columnas y se agregan los datos de la lista de notas de crédito a la
     * tabla. Finalmente, se agrega la tabla al documento, se cierra el
     * documento y se abre el archivo generado en el visor de PDF predeterminado
     * del sistema.
     * @param modeloTabla
     * @param nombre
     * @throws DocumentException
     */
    public void generarPDF(DefaultTableModel modeloTabla, String nombre) throws DocumentException {
        // Crear el objeto JFileChooser para seleccionar la ruta de guardado
        // Obtener la ruta del proyecto
        String rutaProyecto = System.getProperty("user.dir");

// Definir el nombre del archivo PDF a generar
// Concatenar la ruta del proyecto con el nombre del archivo
        String rutaArchivo = rutaProyecto + "/pdf" + File.separator + nombre + ".pdf";

        try {
            GenerarReporte generar = new GenerarReporte();
            generar.generandoReporte(listaReporte);
            int n = 0;
            if (n == 1) {
                // Crear el documento PDF
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(rutaArchivo));
                document.open();

                Paragraph titulo = new Paragraph("Reporte de Notas de Crédito");
                titulo.setAlignment(Element.ALIGN_CENTER);
                document.add(titulo);

                document.add(Chunk.NEWLINE);
                // Crear una tabla y agregar las columnas
                PdfPTable table = new PdfPTable(5);
                table.addCell(createCell("ID", BaseColor.BLUE));
                table.addCell(createCell("N°DOCUMENTO", BaseColor.BLUE));
                table.addCell(createCell("RAZON DE DEVOLUCION", BaseColor.BLUE));
                table.addCell(createCell("NUMERO FACTURA", BaseColor.BLUE));
                table.addCell(createCell("PRECIO VENTAS", BaseColor.BLUE));

                // Agregar los datos de la lista de productos a la tabla
                int i = 1;
                for (ReportesAvanzadosDTO producto : listaReporte) {
                    table.addCell(createCell(Integer.toString(i), BaseColor.WHITE));
                    table.addCell(createCell(producto.getNotaCredito(), BaseColor.WHITE));
                    table.addCell(createCell(producto.getRazo(), BaseColor.WHITE));
                    table.addCell(createCell(producto.getNumeroFactura(), BaseColor.WHITE));
                    table.addCell(createCell(producto.getDinero(), BaseColor.WHITE));
                    i++;
                }

                // Agregar la tabla al documento
                document.add(table);
                document.close();

                // Abrir el archivo generado en el visor de PDF predeterminado
                Desktop.getDesktop().open(new File(rutaArchivo));
            }
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    /**
     * Este método crea una celda para ser utilizada en una tabla PDF.
     * Toma dos parámetros: el contenido que se mostrará en la celda y el color
     * de fondo de la celda. El método crea una nueva instancia de PdfPCell
     * y le asigna el contenido proporcionado y el color de fondo.
     * Luego devuelve la celda creada.
     * @param content
     * @param color
     * @return 
     */
    private PdfPCell createCell(String content, BaseColor color) {
        PdfPCell cell = new PdfPCell(new Phrase(content));
        cell.setBackgroundColor(color);
        return cell;
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
        jLabel6 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        comboMes = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        comboPeriodo = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableListarFacturas = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        btnReporte = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();

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
        jLabel1.setText("Reportes Avanzados");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 360, 40));

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Reporte Avanzados", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel6.setText("Periodo");

        jLabel23.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel23.setText("Seleccionar Mes");

        comboMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));

        jButton2.setBackground(new java.awt.Color(51, 51, 255));
        jButton2.setText("BUSCAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        comboPeriodo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100" }));
        comboPeriodo.setSelectedIndex(33);
        comboPeriodo.setSelectedItem(2023);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(jLabel6))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel23)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(comboMes, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(179, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(comboPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(comboMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 490, 160));
        jPanel2.getAccessibleContext().setAccessibleName("Datos del reporte");
        jPanel2.getAccessibleContext().setAccessibleDescription("Datos del reporte");

        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 490, 10));

        jTableListarFacturas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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
        jTableListarFacturas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableListarFacturasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableListarFacturas);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 860, 290));

        jButton3.setBackground(new java.awt.Color(255, 0, 0));
        jButton3.setText("X");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 10, -1, -1));

        btnReporte.setBackground(new java.awt.Color(51, 51, 255));
        btnReporte.setText("Generar Reporte");
        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteActionPerformed(evt);
            }
        });
        jPanel1.add(btnReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 920, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 920, 620));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
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
        for (int i = 0; i < ReportesAvanzados.SeleccionarProductos.getColumnCount(); i++) {
            ReportesAvanzados.SeleccionarProductos.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
    }//GEN-LAST:event_jTextFieldParametroBusquedaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            datos();

        } catch (SQLException ex) {
            Logger.getLogger(ReportesAvanzados.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTableListarFacturasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListarFacturasMouseClicked
        // TODO add your handling code here:
        int fila = jTableListarFacturas.rowAtPoint(evt.getPoint());

        nro_factura = (jTableListarFacturas.getValueAt(fila, 0).toString());
    }//GEN-LAST:event_jTableListarFacturasMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
        try {
            String nombre = JOptionPane.showInputDialog(this, "Porfavor increse el nombre del archivo PDF");
            generarPDF((DefaultTableModel) jTableListarFacturas.getModel(), nombre);
        } catch (DocumentException ex) {
            Logger.getLogger(ReportesAvanzados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnReporteActionPerformed

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
            java.util.logging.Logger.getLogger(ReportesAvanzados.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReportesAvanzados.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReportesAvanzados.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReportesAvanzados.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ReportesAvanzados dialog = new ReportesAvanzados(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnReporte;
    private javax.swing.JComboBox<String> comboMes;
    private javax.swing.JComboBox<String> comboPeriodo;
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    public static javax.swing.JTable jTableListarFacturas;
    private javax.swing.JTextField jTextFieldParametroBusqueda;
    private javax.swing.ButtonGroup radioGrupo;
    // End of variables declaration//GEN-END:variables
}
