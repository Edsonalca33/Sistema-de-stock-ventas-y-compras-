package Control_BD;

import BD_Cliente.ListarCliente;
import BD_Usuario.addUsuario;
import consultas.ConsultarCompras;
import consultas.ConsultarFacturas;
import static consultas.ConsultarFacturas.jTableListarFacturas;
import static consultas.ConsultarFacturas.listadecompras;
import consultas.ConsultarFacturasCompras;
import consultas.ConsultarVentas;
import static consultas.ConsultarVentas.jTableListarVentas;
import minimarket.Lista_cajas;
import minimarket.Ventas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import notaCredito.Factura;
import notaCredito.Productos;
import notaCredito.ReportesAvanzadosDTO;

/**
 * En esta clase se exponen todos los métodos para ejercer control sobre los
 * datos que van desde y hacia la tabla clientes. En esta clase se hace la
 * validación y organizacion de los datos.
 *
 * @author Sergio
 */
public class Control_Consultas {

    //modelo para la tabla
    DefaultTableModel modelo;
    //vector con los titulos de cada columna

    //matriz donde se almacena los datos de cada celda de la tabla
    String info[][] = {};
    // Conectar Base de Datos
    ConexionConBaseDatos conectar = new ConexionConBaseDatos();

    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
    public void listarTodosFacturascompras() {

        String[] titulosColumnas = {"No_Facturas", "PROVEEDOR", "FECHA", "TOTAL"};

        modelo = new DefaultTableModel(info, titulosColumnas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        //le asigna el modelo al jtable
        ConsultarFacturasCompras.jTableListarFacturas.setModel(modelo);

        //ejecuta una consulta a la BD
        ejecutarConsultaTodaTabladeFacturasCompras();

    }

    public void listarcajas() {

        String[] titulosColumnas = {"FECHA", "MONTO", "ESTADO", "CIERRE","SALDO"};

        modelo = new DefaultTableModel(info, titulosColumnas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        //le asigna el modelo al jtable
        Lista_cajas.jTablecajas.setModel(modelo);

        //ejecuta una consulta a la BD
        ejecutarConsultaTodaTablacajas();

    }

    public void listarTodosFacturas() {

        String[] titulosColumnas = {"No_Facturas", "CLIENTE", "RAZÓN SOCIAL", "FECHA APERTURA", "TOTAL"};

        modelo = new DefaultTableModel(info, titulosColumnas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        //le asigna el modelo al jtable
        ConsultarFacturas.jTableListarFacturas.setModel(modelo);

        //ejecuta una consulta a la BD
        ejecutarConsultaTodaTabladeFacturas();

    }//cierra metodo listarTodosFacturas

    /**
     * Metodo para consultar todos los regsitros de la base de datos de clientes
     * y luego ser mostrados en una tabla.
     */
    Connection conexion = null;
    Statement sentencia = null;
    ResultSet resultado = null;
    PreparedStatement ps = null;

     public void ejecutarConsultaTodaTablacajas() {

        try {
            conexion = ConexionConBaseDatos.getConexion();

            sentencia = conexion.createStatement();
            String consultaSQL = "SELECT * FROM table_cajas  ORDER BY fecha  ASC";
            resultado = sentencia.executeQuery(consultaSQL);

            //mientras haya datos en la BD ejecutar eso...
            while (resultado.next()) {

                String fecha = resultado.getString("fecha");

                String monto = resultado.getString("monto");
                String estado = resultado.getString("estado");
                String guardar = resultado.getString("guardar");
                String saldo = resultado.getString("saldo");
                

                //crea un vector donde los está la informacion (se crea una fila)
                Object[] info = {fecha, monto, estado,guardar,saldo};

                //al modelo de la tabla le agrega una fila
                //con los datos que están en info
                modelo.addRow(info);

            }//cierra while (porque no hay mas datos en la BD)

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL:\n" + e);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            conexion = null;
        } finally {
            CerrarConexiones.metodoCerrarConexiones(conexion, sentencia, resultado, ps);
        }

    }


    public void ejecutarConsultaTodaTabladeFacturasCompras() {

        try {
            conexion = ConexionConBaseDatos.getConexion();

            sentencia = conexion.createStatement();
            String consultaSQL = "SELECT * FROM table_facturas_compras f inner join  table_proveedor c on f.proveedor=c.id_proveedor ORDER BY fecha_compras  ASC";
            resultado = sentencia.executeQuery(consultaSQL);

            //mientras haya datos en la BD ejecutar eso...
            while (resultado.next()) {

                int fact = resultado.getInt("No_Facturas_Compras");
                String Proveedor = resultado.getString("c.razon_social_proveedor");

                String fecha = resultado.getString("fecha_compras");
                String total = resultado.getString("totals_compras");

                //crea un vector donde los está la informacion (se crea una fila)
                Object[] info = {fact, Proveedor, fecha, total};

                //al modelo de la tabla le agrega una fila
                //con los datos que están en info
                modelo.addRow(info);

            }//cierra while (porque no hay mas datos en la BD)

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL:\n" + e);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            conexion = null;
        } finally {
            CerrarConexiones.metodoCerrarConexiones(conexion, sentencia, resultado, ps);
        }

    }

    public void ejecutarConsultaTodaTabladeFacturas() {

        try {
            conexion = ConexionConBaseDatos.getConexion();

            sentencia = conexion.createStatement();
            String consultaSQL = "SELECT * FROM table_Facturas f inner join  table_Cliente c on f.cliente=c.idCliente ORDER BY fecha ASC";
            resultado = sentencia.executeQuery(consultaSQL);

            //mientras haya datos en la BD ejecutar eso...
            while (resultado.next()) {

                int fact = resultado.getInt("No_Facturas");
                String cliente = resultado.getString("c.Nombre_Cliente") + resultado.getString("c.Apellido_Cliente");
                String razons = resultado.getString("c.razon_s_Cliente");
                String fecha = resultado.getString("fecha");
                String total = resultado.getString("totals");

                //crea un vector donde los está la informacion (se crea una fila)
                Object[] info = {fact, cliente, razons, fecha, total};

                //al modelo de la tabla le agrega una fila
                //con los datos que están en info
                modelo.addRow(info);

            }//cierra while (porque no hay mas datos en la BD)

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL:\n" + e);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            conexion = null;
        } finally {
            CerrarConexiones.metodoCerrarConexiones(conexion, sentencia, resultado, ps);
        }

    }//cierra metodo ejecutarConsulta

    public void buscarFacturas(String parametroBusqueda, boolean buscarPorFacturas, boolean buscarPorCliente, boolean buscarPorFecha) {

        String[] titulosColumnas = {"No_Facturas", "CLIENTE", "RAZÓN SOCIAL", "FECHA", "TOTAL"};
        if ((parametroBusqueda.trim().length() == 0)) {
            JOptionPane.showMessageDialog(null, "Error, datos incorrectos");
        } else {

            modelo = new DefaultTableModel(info, titulosColumnas) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            //le asigna el modelo al jtable
            ConsultarFacturas.jTableListarFacturas.setModel(modelo);
            //ejecuta una consulta a la BD
            buscarFacturasporBusqueda(parametroBusqueda, buscarPorFacturas, buscarPorCliente, buscarPorFecha);

        }

    }//cierra metodo buscarCliente

    public void buscarFacturasCompras(String parametroBusqueda, boolean buscarPorFacturas, boolean buscarPorCliente, boolean buscarPorFecha) {

        String[] titulosColumnas = {"No_Facturas", "PROVEEDOR", "FECHA", "TOTAL"};
        if ((parametroBusqueda.trim().length() == 0)) {
            JOptionPane.showMessageDialog(null, "Error, datos incorrectos");
        } else {

            modelo = new DefaultTableModel(info, titulosColumnas) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            //le asigna el modelo al jtable
            ConsultarFacturasCompras.jTableListarFacturas.setModel(modelo);
            //ejecuta una consulta a la BD
            buscarFacturasporBusquedaCompras(parametroBusqueda, buscarPorFacturas, buscarPorCliente, buscarPorFecha);

        }

    }

    /**
     * Método para buscar un registro en la base de datos dentro de la tabla
     * clientes, se puede buscar por la cedula o por el nombre.
     */
    public void abrircaja(String monto) {
        String estado = "abierto";

        Calendar fecha = Calendar.getInstance();
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);

        String fechaactual = anio + "/" + mes + "/" + dia;
        Connection reg = ConexionConBaseDatos.getConexion();

        String sql = "INSERT INTO table_cajas ( id_table_cajas, fecha, monto,estado)VALUES (?,?,?,?)";
        try {

            PreparedStatement pst = reg.prepareStatement(sql);
            pst.setString(1, "");
            pst.setString(2, fechaactual);
            pst.setString(3, monto);
            pst.setString(4, estado);

            int n = pst.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Regristado Exitosamente de caja");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error - " + ex);
            Logger.getLogger(addUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String setmontocaja() {
        String monto = "";
        try {

            String parametroBusqueda = "abierto";

            conexion = ConexionConBaseDatos.getConexion();
            String selectSQL;
            resultado = null;

            System.out.print("buscando por facturas");
            selectSQL = "SELECT * FROM table_cajas WHERE estado LIKE ? ORDER BY id_table_cajas ASC";
            ps = conexion.prepareStatement(selectSQL);
            ps.setString(1, "%" + parametroBusqueda + "%");

            resultado = ps.executeQuery();

            while (resultado.next()) {
                int id_table_cajas = resultado.getInt("id_table_cajas");

                monto = resultado.getString("monto");

                //   
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error\n Por la Causa" + e);
        } finally {
            CerrarConexiones.metodoCerrarConexiones(conexion, sentencia, resultado, ps);
        }
        return monto;

    }

    public void actualizarcaja(float monto_anterior, float montosumar) {

        try {
            float monto = monto_anterior + montosumar;
            conexion = ConexionConBaseDatos.getConexion();
            Statement comando = conexion.createStatement();
            String estado = "abierto";
            // linea de codigo de mysql que actualiza regristos que va modificar
            int cantidad = comando.executeUpdate("update table_cajas set monto ='" + monto + "'" + "  where estado='" + estado + "'");
            if (cantidad == 1) {
                JOptionPane.showMessageDialog(null, " caja actualizada con Exito");

            } else {
                JOptionPane.showMessageDialog(null, "error estado ");
            }
            conexion.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Error -->" + ex);
        }
    }

    public void cerrarcaja(String estadoGuardado, String saldo) {
                 try {
            conexion = ConexionConBaseDatos.getConexion();
            Statement comando = conexion.createStatement();
            String estado = "cerrado";
            // linea de codigo de mysql que actualiza regristos que va modificar
            int cantidad = comando.executeUpdate("update table_cajas set estado ='" + estado + "'");
            String sql = "UPDATE table_cajas SET guardar = ? WHERE id_table_cajas = (SELECT MAX(id_table_cajas) FROM table_cajas)";
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, estadoGuardado);
            pstmt.executeUpdate();
            String sql1 = "UPDATE table_cajas SET saldo = ? WHERE id_table_cajas = (SELECT MAX(id_table_cajas) FROM table_cajas)";
            PreparedStatement pstmt1 = conexion.prepareStatement(sql1);
            pstmt1.setString(1, saldo);
            pstmt1.executeUpdate();
             if (cantidad == 1) {
                JOptionPane.showMessageDialog(null, " Modifico con Exito");
            } else {
                //     JOptionPane.showMessageDialog(null,"error estado ");
            }

            conexion.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Error -->" + ex);
        }
       

     
    }

    public int buscarestadocaja() {
        int c = 0;
        try {

            String parametroBusqueda = "abierto";

            conexion = ConexionConBaseDatos.getConexion();
            String selectSQL;
            resultado = null;

            System.out.print("buscando por facturas");
            selectSQL = "SELECT * FROM table_cajas WHERE estado LIKE ? ORDER BY id_table_cajas ASC";
            ps = conexion.prepareStatement(selectSQL);
            ps.setString(1, "%" + parametroBusqueda + "%");

            resultado = ps.executeQuery();

            while (resultado.next()) {
                int id_table_cajas = resultado.getInt("id_table_cajas");

                String monto = resultado.getString("monto");
                String fecha = resultado.getString("fecha");

                c++;

                //   
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error\n Por la Causa" + e);
        } finally {
            CerrarConexiones.metodoCerrarConexiones(conexion, sentencia, resultado, ps);
        }
        return c;

    }

    public void buscarFacturasporBusquedaCompras(String parametroBusqueda, boolean buscarPorFacturas, boolean buscarPorCliente, boolean buscarPorFecha) {

        try {

            conexion = ConexionConBaseDatos.getConexion();
            String selectSQL;
            resultado = null;
            if (buscarPorFacturas == true) {
                System.out.print("buscando por facturas");
                selectSQL = "SELECT * FROM table_facturas_compras WHERE No_facturas_compras LIKE ? ORDER BY fecha_compras ASC";
                ps = conexion.prepareStatement(selectSQL);
                ps.setString(1, "%" + parametroBusqueda + "%");
            } else if (buscarPorCliente == true) {
                System.out.print("buscando por proveedor");
                selectSQL = "SELECT * FROM table_facturas_compras f inner join  table_proveedor c on f.proveedor=c.id_proveedor WHERE   c.ruc_proveedor LIKE ? or c.razon_social_proveedor LIKE ? ORDER BY fecha_compras ASC";
                ps = conexion.prepareStatement(selectSQL);

                ps.setString(1, "%" + parametroBusqueda + "%");
                ps.setString(2, "%" + parametroBusqueda + "%");

            } else if (buscarPorFecha == true) {

                System.out.print("buscando por fecha -->" + parametroBusqueda);
                selectSQL = "SELECT * FROM table_facturas_compras f inner join  table_proveedor c on f.proveedor=c.id_proveedor WHERE f.fecha_compras LIKE ?  ORDER BY fecha_compras ASC";
                ps = conexion.prepareStatement(selectSQL);
                ps.setString(1, "%" + parametroBusqueda + "%");
            }
            resultado = ps.executeQuery();

            while (resultado.next()) {
                int fact = resultado.getInt("No_facturas_compras");

                String fecha = resultado.getString("fecha_compras");
                String total = resultado.getString("totals_compras");
                String razon_social_proveedor = resultado.getString("razon_social_proveedor");

                //crea un vector donde los está la informacion (se crea una fila)
                Object[] info = {fact, razon_social_proveedor, fecha, total};
                //al modelo de la tabla le agrega una fila
                //con los datos que están en info
                modelo.addRow(info);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error\n Por la Causa" + e);
        } finally {
            CerrarConexiones.metodoCerrarConexiones(conexion, sentencia, resultado, ps);
        }

    }

    public void buscarFacturasporBusqueda(String parametroBusqueda, boolean buscarPorFacturas, boolean buscarPorCliente, boolean buscarPorFecha) {

        try {

            conexion = ConexionConBaseDatos.getConexion();
            String selectSQL;
            resultado = null;
            if (buscarPorFacturas == true) {
                System.out.print("buscando por facturas");
                selectSQL = "SELECT * FROM table_Facturas WHERE No_Facturas LIKE ? ORDER BY fecha ASC";
                ps = conexion.prepareStatement(selectSQL);
                ps.setString(1, "%" + parametroBusqueda + "%");
            } else if (buscarPorCliente == true) {
                System.out.print("buscando por cliente");
                selectSQL = "SELECT * FROM table_Facturas f inner join  table_Cliente c on f.cliente=c.idCliente WHERE   c.ruc_Cliente LIKE ? or  c.razon_s_Cliente LIKE ? or c.Nombre_Cliente LIKE ? ORDER BY fecha ASC";
                ps = conexion.prepareStatement(selectSQL);

                ps.setString(1, "%" + parametroBusqueda + "%");
                ps.setString(2, "%" + parametroBusqueda + "%");
                ps.setString(3, "%" + parametroBusqueda + "%");
            } else if (buscarPorFecha == true) {

                System.out.print("buscando por fecha -->" + parametroBusqueda);
                selectSQL = "SELECT * FROM table_Facturas f inner join  table_Cliente c on f.cliente=c.idCliente WHERE f.fecha LIKE ?  ORDER BY fecha ASC";
                ps = conexion.prepareStatement(selectSQL);
                ps.setString(1, "%" + parametroBusqueda + "%");
            }
            resultado = ps.executeQuery();

            while (resultado.next()) {
                int fact = resultado.getInt("No_Facturas");
                String cliente = resultado.getString("c.Nombre_Cliente") + resultado.getString("c.Apellido_Cliente");
                String fecha = resultado.getString("fecha");
                String total = resultado.getString("totals");
                String razons = resultado.getString("razon_s_Cliente");

                //crea un vector donde los está la informacion (se crea una fila)
                Object[] info = {fact, cliente, razons, fecha, total};
                //al modelo de la tabla le agrega una fila
                //con los datos que están en info
                modelo.addRow(info);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error\n Por la Causa" + e);
        } finally {
            CerrarConexiones.metodoCerrarConexiones(conexion, sentencia, resultado, ps);
        }

    }//cierra metodo buscarRegistro
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    //esta es la parte de mostar detalle de facturas por la tabla venta
    //metodo para buscar un producto
    public void buscarFacturas(String number) {

        String[] titulosColumnas = {"ID-VENTAS", "PRODUCTOS", "CANTIDAD", "IMPORTE"};
        if ((number.trim().length() == 0)) {
            JOptionPane.showMessageDialog(null, "Error, Seleccione la Facturas");
        } else {

            modelo = new DefaultTableModel(info, titulosColumnas) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }

            };

            //le asigna el modelo al jtable
            ConsultarFacturas.listadecompras.setModel(modelo);

            int[] anchos = {80, 200, 50, 145};
            for (int i = 0; i < listadecompras.getColumnCount(); i++) {
                listadecompras.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
            //ejecuta una consulta a la BD   
            buscarFacturasDetall(number);
        }

    }//cierra metodo buscarCliente

    public void buscarFacturasDetall(String number) {

        try {
            conexion = ConexionConBaseDatos.getConexion();
            String selectSQL;
            resultado = null;

            selectSQL = "SELECT * FROM table_ventas WHERE No_Facturas LIKE ? ORDER BY idVentas ASC";
            ps = conexion.prepareStatement(selectSQL);
            ps.setString(1, "%" + number + "%");

            resultado = ps.executeQuery();

            while (resultado.next()) {
                String id = resultado.getString("idVentas");
                String product = resultado.getString("productos");
                String cant = resultado.getString("cantidad");
                String imp = resultado.getString("importe");
                //crea un vector donde los está la informacion (se crea una fila)

                //buscar producto
                String name = "";
                Statement comando = conexion.createStatement();
                ResultSet registro = comando.executeQuery("select idProductos,nombreProductos from table_productos where idProductos=" + product);

                if (registro.next() == true) {
                    name = registro.getString("nombreProductos");
                }
                // cierdda de buscar productos
                Object[] info = {id, name, cant, imp};
                //al modelo de la tabla le agrega una fila
                //con los datos que están en info
                modelo.addRow(info);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error\n " + e);
        } finally {
            CerrarConexiones.metodoCerrarConexiones(conexion, sentencia, resultado, ps);
        }

    }//cierra metodo buscarRegistro

    // parte de todo consultas en ventas--------------------------------------------------------------
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
    public void listarTodosVentas() {

        String[] titulosColumnas = {"id-Ventas", "Fecha", "Producto", "cantidad", "Importe"};

        modelo = new DefaultTableModel(info, titulosColumnas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        //le asigna el modelo al jtable
        ConsultarVentas.jTableListarVentas.setModel(modelo);

        //ejecuta una consulta a la BD
        ejecutarConsultaTodaTabladeVentas();

    }

    public void listarTodosCompras() {

        String[] titulosColumnas = {"id-Compras", "Fecha", "Producto", "cantidad", "Importe"};

        modelo = new DefaultTableModel(info, titulosColumnas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        //le asigna el modelo al jtable
        ConsultarCompras.jTableListarVentas.setModel(modelo);

        //ejecuta una consulta a la BD
        ejecutarConsultaTodaTabladeCompras();

    }
//cierra metodo listarTodosFacturas

    /**
     * Metodo para consultar todos los regsitros de la base de datos de clientes
     * y luego ser mostrados en una tabla.
     */
    public void ejecutarConsultaTodaTabladeCompras() {

        try {
            conexion = ConexionConBaseDatos.getConexion();

            sentencia = conexion.createStatement();
            String consultaSQL = "SELECT * FROM table_facturas_compras f inner join  table_compras v on f.No_facturas_compras=v.No_facturas_compras inner join  table_Productos p on v.Productos_Compras=p.idProductos ORDER BY v.idCompras ASC";
            resultado = sentencia.executeQuery(consultaSQL);

            //mientras haya datos en la BD ejecutar eso...
            while (resultado.next()) {

                int num = resultado.getInt("idCompras");
                String fact = resultado.getString("f.fecha_compras");
                String prod = resultado.getString("p.nombreProductos");
                String cant = resultado.getString("cantidad");
                String importe = resultado.getString("importe");

                //crea un vector donde los está la informacion (se crea una fila)
                Object[] info = {num, fact, prod, cant, importe};

                //al modelo de la tabla le agrega una fila
                //con los datos que están en info
                modelo.addRow(info);

            }//cierra while (porque no hay mas datos en la BD)

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL:\n" + e);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            conexion = null;
        } finally {
            CerrarConexiones.metodoCerrarConexiones(conexion, sentencia, resultado, ps);
        }

    }

    public void ejecutarConsultaTodaTabladeVentas() {

        try {
            conexion = ConexionConBaseDatos.getConexion();

            sentencia = conexion.createStatement();
            String consultaSQL = "SELECT * FROM table_Facturas f inner join  table_Ventas v on f.No_Facturas=v.No_Facturas inner join  table_Productos p on v.Productos=p.idProductos ORDER BY v.idVentas ASC";
            resultado = sentencia.executeQuery(consultaSQL);

            //mientras haya datos en la BD ejecutar eso...
            while (resultado.next()) {

                int num = resultado.getInt("idVentas");
                String fact = resultado.getString("f.fecha");
                String prod = resultado.getString("p.nombreProductos");
                String cant = resultado.getString("cantidad");
                String importe = resultado.getString("importe");

                //crea un vector donde los está la informacion (se crea una fila)
                Object[] info = {num, fact, prod, cant, importe};

                //al modelo de la tabla le agrega una fila
                //con los datos que están en info
                modelo.addRow(info);

            }//cierra while (porque no hay mas datos en la BD)

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL:\n" + e);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            conexion = null;
        } finally {
            CerrarConexiones.metodoCerrarConexiones(conexion, sentencia, resultado, ps);
        }

    }//cierra metodo ejecutarConsulta

    public void buscarFacturasdeComprasFecha(String parametroBusqueda, String parametroBusqued2) {

        String[] titulosColumnas = {"id-Compras", "Fecha", "Producto", "cantidad", "Importe"};
        if ((parametroBusqueda.trim().length() == 0)) {
            JOptionPane.showMessageDialog(null, "Error, datos incorrectos");
        } else {

            modelo = new DefaultTableModel(info, titulosColumnas) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            //le asigna el modelo al jtable

            ConsultarCompras.jTableListarVentas.setModel(modelo);
            //ejecuta una consulta a la BD
            buscarFacturasporBusquedaenComprasFecha(parametroBusqueda, parametroBusqued2);

        }

    }

    public void buscarFacturasdeVentasFecha(String parametroBusqueda, String parametroBusqued2) {

        String[] titulosColumnas = {"id-Ventas", "Fecha", "Producto", "cantidad", "Importe"};
        if ((parametroBusqueda.trim().length() == 0)) {
            JOptionPane.showMessageDialog(null, "Error, datos incorrectos");
        } else {

            modelo = new DefaultTableModel(info, titulosColumnas) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            //le asigna el modelo al jtable

            ConsultarVentas.jTableListarVentas.setModel(modelo);
            //ejecuta una consulta a la BD
            buscarFacturasporBusquedaenVentasFecha(parametroBusqueda, parametroBusqued2);

        }

    }//cierra metodo buscarCliente

    public void buscarFacturasdeCompras(String parametroBusqueda) {

        String[] titulosColumnas = {"id-Compras", "Fecha", "Producto", "cantidad", "Importe"};
        if ((parametroBusqueda.trim().length() == 0)) {
            JOptionPane.showMessageDialog(null, "Error, datos incorrectos");
        } else {

            modelo = new DefaultTableModel(info, titulosColumnas) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            //le asigna el modelo al jtable
            ConsultarCompras.jTableListarVentas.setModel(modelo);
            //ejecuta una consulta a la BD
            buscarFacturasporBusquedaenVentas(parametroBusqueda);

        }

    }

    public void buscarFacturasdeVentas(String parametroBusqueda) {

        String[] titulosColumnas = {"id-Ventas", "Fecha", "Producto", "cantidad", "Importe"};
        if ((parametroBusqueda.trim().length() == 0)) {
            JOptionPane.showMessageDialog(null, "Error, datos incorrectos");
        } else {

            modelo = new DefaultTableModel(info, titulosColumnas) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            //le asigna el modelo al jtable
            ConsultarVentas.jTableListarVentas.setModel(modelo);
            //ejecuta una consulta a la BD
            buscarFacturasporBusquedaenVentas(parametroBusqueda);

        }

    }

    public void buscarFacturasdeCompras2(String parametroBusqueda) {

        String[] titulosColumnas = {"id-Compras", "Fecha", "Producto", "cantidad", "Importe"};
        if ((parametroBusqueda.trim().length() == 0)) {
            JOptionPane.showMessageDialog(null, "Error, datos incorrectos");
        } else {

            modelo = new DefaultTableModel(info, titulosColumnas) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            //le asigna el modelo al jtable
            ConsultarCompras.jTableListarVentas.setModel(modelo);
            //ejecuta una consulta a la BD
            buscarFacturasporBusquedaenCompras2(parametroBusqueda);

        }

    }
//cierra metodo buscarCliente

    /**
     * Método para buscar un registro en la base de datos dentro de la tabla
     * clientes, se puede buscar por la cedula o por el nombre.
     */
    public void buscarFacturasporBusquedaenComprasFecha(String parametroBusqueda, String parametroBusqueda2) {

        try {

            conexion = ConexionConBaseDatos.getConexion();
            String selectSQL;
            resultado = null;
            System.err.println(parametroBusqueda2 + "    " + parametroBusqueda);
            System.out.print("buscando por facturas en Compras");
            selectSQL = "SELECT * FROM table_facturas_compras f inner join  table_compras v on f.No_facturas_compras=v.No_facturas_compras inner join  table_Productos p on v.Productos_Compras=p.idProductos WHERE " + "  fecha_compras<= '" + parametroBusqueda2 + "' AND  fecha_compras>= '" + parametroBusqueda + "'";

            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(selectSQL);

            float total = 0;
            float ganancia = 0;
//Difererencia
            while (rs.next()) {
                int num = rs.getInt("idCompras");
                String fact = rs.getString("f.fecha_compras");
                String prod = rs.getString("p.nombreProductos");
                String cant = rs.getString("v.cantidad");
                String importe = rs.getString("v.importe");
                total = total + rs.getFloat("v.importe");
                //ganancia=ganancia+rs.getFloat("p.Difererencia");

                //crea un vector donde los está la informacion (se crea una fila)
                Object[] info = {num, fact, prod, cant, importe};
                //al modelo de la tabla le agrega una fila
                //con los datos que están en info
                modelo.addRow(info);

            }
            ConsultarCompras.label_total.setText(total + "  Soles");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error\n Por la Causa" + e);
        } finally {
            CerrarConexiones.metodoCerrarConexiones(conexion, sentencia, resultado, ps);
        }

    }

    public void buscarFacturasporBusquedaenVentasFecha(String parametroBusqueda, String parametroBusqueda2) {

        try {

            conexion = ConexionConBaseDatos.getConexion();
            String selectSQL;
            resultado = null;
            System.err.println(parametroBusqueda2 + "    " + parametroBusqueda);
            System.out.print("buscando por facturas en Ventas");
            selectSQL = "SELECT * FROM table_Facturas f inner join  table_Ventas v on f.No_Facturas=v.No_Facturas inner join  table_Productos p on v.Productos=p.idProductos WHERE " + "  fecha<= '" + parametroBusqueda2 + "' AND  fecha>= '" + parametroBusqueda + "'";

            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(selectSQL);

            float total = 0;
            float ganancia = 0;
//Difererencia
            while (rs.next()) {
                int num = rs.getInt("idVentas");
                String fact = rs.getString("f.fecha");
                String prod = rs.getString("p.nombreProductos");
                String cant = rs.getString("v.cantidad");
                String importe = rs.getString("v.importe");
                total = total + rs.getFloat("v.importe");
                //ganancia=ganancia+rs.getFloat("p.Difererencia");
                ganancia = ganancia + (rs.getInt("p.Difererencia")) * (rs.getInt("v.cantidad"));

                //crea un vector donde los está la informacion (se crea una fila)
                Object[] info = {num, fact, prod, cant, importe};
                //al modelo de la tabla le agrega una fila
                //con los datos que están en info
                modelo.addRow(info);

            }
            ConsultarVentas.label_total.setText(total + "  Soles");
            ConsultarVentas.label_ganancia.setText(ganancia + "  Soles");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error\n Por la Causa" + e);
        } finally {
            CerrarConexiones.metodoCerrarConexiones(conexion, sentencia, resultado, ps);
        }

    }

    public void buscarFacturasporBusquedaenCompras(String parametroBusqueda) {

        try {

            conexion = ConexionConBaseDatos.getConexion();
            String selectSQL;
            resultado = null;

            System.out.print("buscando por facturas en Compras");
            selectSQL = "SELECT * FROM table_facturas_compras f inner join  table_compras v on f.No_facturas_compras=v.No_facturas_compras inner join  table_Productos p on v.Productos=p.idProductos WHERE p.nombreProductos LIKE ? ORDER BY v.idCompras ASC";
            ps = conexion.prepareStatement(selectSQL);
            ps.setString(1, "%" + parametroBusqueda + "%");

            resultado = ps.executeQuery();
            float total = 0;
            float ganancia = 0;
            while (resultado.next()) {
                int num = resultado.getInt("idCompras");
                String fact = resultado.getString("f.fecha_compras");
                String prod = resultado.getString("p.nombreProductos");
                String cant = resultado.getString("v.cantidad");
                String importe = resultado.getString("v.importe");

                total = total + resultado.getInt("v.importe");
//ganancia=ganancia+(resultado.getInt("p.Difererencia"))*(resultado.getInt("v.cantidad"));
                //crea un vector donde los está la informacion (se crea una fila)
                Object[] info = {num, fact, prod, cant, importe};
                //al modelo de la tabla le agrega una fila
                //con los datos que están en info
                modelo.addRow(info);

            }
            ConsultarVentas.label_total.setText(total + "  Soles");
//ConsultarVentas.label_ganancia.setText(ganancia+"  Soles");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error\n Por la Causa" + e);
        } finally {
            CerrarConexiones.metodoCerrarConexiones(conexion, sentencia, resultado, ps);
        }

    }

    public void buscarFacturasporBusquedaenCompras2(String parametroBusqueda) {

        try {

            conexion = ConexionConBaseDatos.getConexion();
            String selectSQL;
            resultado = null;

            System.out.print("buscando por facturas en Compras");
            selectSQL = "SELECT * FROM table_facturas_compras f inner join  table_compras v on f.No_facturas_compras=v.No_facturas_compras inner join  table_Productos p on v.Productos_Compras=p.idProductos WHERE p.nombreProductos LIKE ? ORDER BY v.idCompras ASC";
            ps = conexion.prepareStatement(selectSQL);
            ps.setString(1, "%" + parametroBusqueda + "%");

            resultado = ps.executeQuery();
            float total = 0;
            float ganancia = 0;
            while (resultado.next()) {
                int num = resultado.getInt("idCompras");
                String fact = resultado.getString("f.fecha_compras");
                String prod = resultado.getString("p.nombreProductos");
                String cant = resultado.getString("v.cantidad");
                String importe = resultado.getString("v.importe");

                total = total + resultado.getInt("v.importe");
//ganancia=ganancia+(resultado.getInt("p.Difererencia"))*(resultado.getInt("v.cantidad"));
                //crea un vector donde los está la informacion (se crea una fila)
                Object[] info = {num, fact, prod, cant, importe};
                //al modelo de la tabla le agrega una fila
                //con los datos que están en info
                modelo.addRow(info);

            }
            ConsultarCompras.label_total.setText(total + "  Soles");
//ConsultarCompras.label_ganancia.setText(ganancia+"  Soles");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error\n Por la Causa" + e);
        } finally {
            CerrarConexiones.metodoCerrarConexiones(conexion, sentencia, resultado, ps);
        }

    }

    public void buscarFacturasporBusquedaenVentas(String parametroBusqueda) {

        try {

            conexion = ConexionConBaseDatos.getConexion();
            String selectSQL;
            resultado = null;

            System.out.print("buscando por facturas en Ventas");
            selectSQL = "SELECT * FROM table_Facturas f inner join  table_Ventas v on f.No_Facturas=v.No_Facturas inner join  table_Productos p on v.Productos=p.idProductos WHERE p.nombreProductos LIKE ? ORDER BY v.idVentas ASC";
            ps = conexion.prepareStatement(selectSQL);
            ps.setString(1, "%" + parametroBusqueda + "%");

            resultado = ps.executeQuery();
            float total = 0;
            float ganancia = 0;
            while (resultado.next()) {
                int num = resultado.getInt("idVentas");
                String fact = resultado.getString("f.fecha");
                String prod = resultado.getString("p.nombreProductos");
                String cant = resultado.getString("v.cantidad");
                String importe = resultado.getString("v.importe");

                total = total + resultado.getInt("v.importe");
                ganancia = ganancia + (resultado.getInt("p.Difererencia")) * (resultado.getInt("v.cantidad"));
                //crea un vector donde los está la informacion (se crea una fila)
                Object[] info = {num, fact, prod, cant, importe};
                //al modelo de la tabla le agrega una fila
                //con los datos que están en info
                modelo.addRow(info);

            }
            ConsultarVentas.label_total.setText(total + "  Soles");
            ConsultarVentas.label_ganancia.setText(ganancia + "  Soles");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error\n Por la Causa" + e);
        } finally {
            CerrarConexiones.metodoCerrarConexiones(conexion, sentencia, resultado, ps);
        }

    }//cierra metodo buscarRegistro
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    ////////////
    public List<Factura> facturaCredito(String factur) {
        List<Factura> facturas = new ArrayList<>();
        try {

            conexion = ConexionConBaseDatos.getConexion();
            String selectSQL;
            resultado = null;

            selectSQL = "SELECT * FROM table_facturas WHERE No_facturas = ?";
            ps = conexion.prepareStatement(selectSQL);
            ps.setInt(1, Integer.parseInt(factur));

            resultado = ps.executeQuery();

            // Verificar si se encontraron resultados
            // Crear una lista para almacenar las facturas encontradas
            // Recorrer los resultados de la consulta
            while (resultado.next()) {
                // Crear una nueva factura y asignarle los datos de la fila actual
                Factura factura = new Factura();
                factura.setNoFacturas(resultado.getInt("No_Facturas"));
                factura.setCliente(resultado.getInt("cliente"));
                factura.setFecha(resultado.getDate("fecha"));
                factura.setTotal(resultado.getInt("totals"));
                factura.setNroBoleta(resultado.getInt("nro_boleta"));
                factura.setFacturaBoleta(resultado.getString("factura_boleta"));
                // Agregar la factura a la lista
                facturas.add(factura);
                return facturas;
            }

            return facturas;

        } catch (SQLException ex) {
            System.out.println("Error al buscar las facturas: " + ex.getMessage());
        }
        return facturas;
    }

    public void guardarTablaProducto(List<Productos> listaProductos) throws SQLException {

        Connection conexion = ConexionConBaseDatos.getConexion();
        String sql = "INSERT INTO tabla_productos (factura, cantidad, productos, descripcion, precioUnitario, importe, igv, total) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conexion.prepareStatement(sql);

        for (Productos producto : listaProductos) {
            ps.setInt(1, producto.getNum_factura());
            ps.setString(2, producto.getCantidad());
            ps.setString(3, producto.getProductos());
            ps.setString(4, producto.getDescripcion());
            ps.setString(5, producto.getPrecioUnitario());
            ps.setString(6, producto.getImporte());
            ps.setString(7, producto.getLgv());
            ps.setString(8, producto.getTotal());
            ps.executeUpdate();
        }
        ps.close();
        conexion.close();

    }

    public List<Productos> obtenerProductosPorId(int id) throws SQLException {
        List<Productos> listaProductos = new ArrayList<>();
        Connection conexion = ConexionConBaseDatos.getConexion();
        String sql = "SELECT * FROM tabla_productos WHERE factura=?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Productos producto = new Productos();
            producto.setNum_factura(rs.getInt("id"));
            producto.setCantidad(rs.getString("cantidad"));
            producto.setProductos(rs.getString("productos"));
            producto.setDescripcion(rs.getString("descripcion"));
            producto.setPrecioUnitario(rs.getString("precioUnitario"));
            producto.setImporte(rs.getString("importe"));
            producto.setLgv(rs.getString("igv"));
            producto.setTotal(rs.getString("total"));
            listaProductos.add(producto);
        }
        rs.close();
        ps.close();
        conexion.close();
        return listaProductos;
    }

    public String obtenerUltimaNotaCredito() throws SQLException {
        List<String> notaCredito = new ArrayList<>();
        notaCredito.clear();
        Connection conexion = ConexionConBaseDatos.getConexion();
        String sql = "SELECT * FROM nota_credito";
        PreparedStatement ps = conexion.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {

            notaCredito.add((rs.getString("nota_credito")));

        }
        String nuevoValor = "";
        if (!notaCredito.isEmpty()) {
            String[] partes = notaCredito.get(notaCredito.size() - 1).split("-");
            int numero = Integer.parseInt(partes[1]) + 1;
            String formato = String.format("%06d", numero);
            nuevoValor = partes[0] + "-" + formato;
        } else {
            nuevoValor = "0001-000001";
        }

        rs.close();
        ps.close();
        conexion.close();
        return nuevoValor;

    }

    public void eliminarGuardarNotaCredito(String dia, String mes, String anio, String notaCredito, String numeroFactura, String check, String dinero) throws SQLException {
        Connection conexion = ConexionConBaseDatos.getConexion();
        String sql = "DELETE FROM table_ventas WHERE No_Facturas = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);

// Asigna el valor de la factura a eliminar al primer parámetro de la consulta
        ps.setString(1, numeroFactura);

        ps.executeUpdate();

        ps.close();
        conexion.close();

        Connection conexion1 = ConexionConBaseDatos.getConexion();
        String sql1 = "DELETE FROM table_facturas WHERE No_Facturas = ?";
        PreparedStatement ps1 = conexion1.prepareStatement(sql1);

        ps1.setString(1, numeroFactura);

        ps1.executeUpdate();

        ps1.close();
        conexion1.close();

        Connection conexion2 = ConexionConBaseDatos.getConexion();
        String sql2 = "DELETE FROM tabla_productos WHERE factura = ?";
        PreparedStatement ps2 = conexion2.prepareStatement(sql2);

        ps2.setString(1, numeroFactura);

        ps2.executeUpdate();

        ps2.close();
        conexion2.close();

        Connection conexion3 = ConexionConBaseDatos.getConexion();
        String sql3 = "INSERT INTO tabla_nota_credito (dia, mes, anio, notaCredito, numeroFactura, razon,dinero) VALUES (?, ?, ?, ?, ?, ?,?)";
        PreparedStatement ps3 = conexion3.prepareStatement(sql3);
        ps3.setString(1, dia);
        ps3.setString(2, mes);
        ps3.setString(3, anio);
        ps3.setString(4, notaCredito);
        ps3.setString(5, numeroFactura);
        ps3.setString(6, check);
        ps3.setString(7, dinero);
        int filasAfectadas = ps3.executeUpdate(); // Ejecutar la consulta y obtener el número de filas afectadas
        ps3.close();
        conexion3.close();
        
        
        Connection conexion4 = ConexionConBaseDatos.getConexion();
        String sql4 = "INSERT INTO nota_credito (nota_credito) VALUES (?)";
        PreparedStatement ps4 = conexion4.prepareStatement(sql4);
        ps4.setString(1, notaCredito);

        int filasAfectadas1 = ps4.executeUpdate(); // Ejecutar la consulta y obtener el número de filas afectadas// Imprimir el número de filas insertadas
        ps4.close();
        conexion4.close();

    }
    
    public List<ReportesAvanzadosDTO> buscarReportesAvanzados(String anios, String mes) throws SQLException{
         List<ReportesAvanzadosDTO> reporte = new ArrayList<>();
        Connection conexion = ConexionConBaseDatos.getConexion();
        String sql = "SELECT * FROM tabla_nota_credito WHERE anio=? AND mes=?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, anios);
        ps.setString(2, mes);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ReportesAvanzadosDTO producto = new ReportesAvanzadosDTO();
            producto.setId(rs.getInt("id"));
            producto.setDia(rs.getString("dia"));
            producto.setMes(rs.getString("mes"));
            producto.setAnio(rs.getString("anio"));
            producto.setNotaCredito(rs.getString("notaCredito"));
            producto.setNumeroFactura(rs.getString("numeroFactura"));
            producto.setRazo(rs.getString("razon"));
            producto.setDinero(rs.getString("dinero"));
            reporte .add(producto);
        }
        rs.close();
        ps.close();
        conexion.close();
        return reporte ;
    }
    
     public void guardarNotaDebito(String dia, String mes, String anio, String notaCredito, String numeroFactura, String check, String dinero) throws SQLException {
 

        Connection conexion3 = ConexionConBaseDatos.getConexion();
        String sql3 = "INSERT INTO tabla_nota_credito (dia, mes, anio, notaCredito, numeroFactura, razon,dinero) VALUES (?, ?, ?, ?, ?, ?,?)";
        PreparedStatement ps3 = conexion3.prepareStatement(sql3);
        ps3.setString(1, dia);
        ps3.setString(2, mes);
        ps3.setString(3, anio);
        ps3.setString(4, notaCredito);
        ps3.setString(5, numeroFactura);
        ps3.setString(6, check);
        ps3.setString(7, dinero);
        int filasAfectadas = ps3.executeUpdate(); // Ejecutar la consulta y obtener el número de filas afectadas
        ps3.close();
        conexion3.close();
        
        
        Connection conexion4 = ConexionConBaseDatos.getConexion();
        String sql4 = "INSERT INTO nota_credito (nota_credito) VALUES (?)";
        PreparedStatement ps4 = conexion4.prepareStatement(sql4);
        ps4.setString(1, notaCredito);

        int filasAfectadas1 = ps4.executeUpdate(); // Ejecutar la consulta y obtener el número de filas afectadas// Imprimir el número de filas insertadas
        ps4.close();
        conexion4.close();

    }
     
     public void montonApertura(String monton) throws SQLException{

       Connection conexion4 = ConexionConBaseDatos.getConexion();
        String sql4 = "INSERT INTO arqueo (monton) VALUES (?)";
        PreparedStatement ps4 = conexion4.prepareStatement(sql4);
        ps4.setString(1, monton);

        int filasAfectadas1 = ps4.executeUpdate(); // Ejecutar la consulta y obtener el número de filas afectadas// Imprimir el número de filas insertadas
        ps4.close();
        conexion4.close();
     }
     
     
      public List<String> montonAperturaBuscar() throws SQLException{
         List<String> reporte = new ArrayList<>();
        Connection conexion = ConexionConBaseDatos.getConexion();
        String sql = "SELECT * FROM arqueo";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            reporte.add(rs.getString("monton"));
        }
        rs.close();
        ps.close();
        conexion.close();
        return reporte ;
    }
      
      
    public void montonAperturaEliminar(String monton) throws SQLException{
         Connection conexion2 = ConexionConBaseDatos.getConexion();
        String sql2 = "DELETE FROM arqueo WHERE monton = ?";
        PreparedStatement ps2 = conexion2.prepareStatement(sql2);

        ps2.setString(1, monton);

        ps2.executeUpdate();

        ps2.close();
        conexion2.close();
    }
}
