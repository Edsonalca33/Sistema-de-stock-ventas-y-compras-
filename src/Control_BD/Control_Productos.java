package Control_BD;

import BD_Productos.Consulta_por_categoria;
import BD_Productos.ConsultarProductos;
import static BD_Productos.ConsultarProductos.jTableListarCliente;
import BD_Productos.EliminarProductos;

import BD_Productos.ModificarProductos;
import minimarket.Compras;
import minimarket.Guia_de_remision;
import minimarket.Ventas;
import static minimarket.Ventas.JTableProduct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * En esta clase se exponen todos los métodos para ejercer control sobre los
 * datos que van desde y hacia la tabla clientes. En esta clase se hace la
 * validación y organizacion de los datos.
 *
 * @author Sergio
 */


public class Control_Productos {

    //modelo para la tabla
    DefaultTableModel modelo;
       private String sSQL = "";
    //vector con los titulos de cada columna
    String[] titulosColumnas = {"ID","CODIGO", "NOMBRE", " PRECIO VENTA","DESCRIPCION","CANTIDAD","PRECIO COMPRA"};
    //matriz donde se almacena los datos de cada celda de la tabla
    String info[][] = {};
    
    
    
    

     public void agregarProductos(String cedula, String nombre, String apellido, String direccion, String telefono) {


         
    }//cierra metodo agregarCliente
     
     
     public void CargarProductos(){
         
         modelo = new DefaultTableModel(info, titulosColumnas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
      //le asigna el modelo al jtable
        Ventas.SeleccionarProductos.setModel(modelo);

        //ejecuta una consulta a la BD
        ejecutarConsultaTodaTabla();
        
                               /*              int[] anchos = {35, 300, 40, 200, 40};
        for (int i = 0; i < Ventas.SeleccionarProductos.getColumnCount(); i++) {
            Ventas.SeleccionarProductos.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);}
         */
     }
          public void CargarProductosVentas(){
         
         modelo = new DefaultTableModel(info, titulosColumnas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
      //le asigna el modelo al jtable
              Ventas.SeleccionarProductos.setModel(modelo);

        //ejecuta una consulta a la BD
        ejecutarConsultaTodaTablaventas();
        
                               /*              int[] anchos = {35, 300, 40, 200, 40};
        for (int i = 0; i < Ventas.SeleccionarProductos.getColumnCount(); i++) {
            Ventas.SeleccionarProductos.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);}
         */
     }
      
                    public void CargarProductosCompras(){
         
         modelo = new DefaultTableModel(info, titulosColumnas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
      //le asigna el modelo al jtable
                        Compras.SeleccionarProductos.setModel(modelo);

        //ejecuta una consulta a la BD
        ejecutarConsultaTodaTablaventas();
        
                               /*              int[] anchos = {35, 300, 40, 200, 40};
        for (int i = 0; i < Ventas.SeleccionarProductos.getColumnCount(); i++) {
            Ventas.SeleccionarProductos.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);}
         */
     }
     public void CargarProductosGuiaReimsion(){
         
         modelo = new DefaultTableModel(info, titulosColumnas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
      //le asigna el modelo al jtable
         Guia_de_remision.SeleccionarProductos.setModel(modelo);

        //ejecuta una consulta a la BD
        ejecutarConsultaTodaTablaventas();
        
                               /*              int[] anchos = {35, 300, 40, 200, 40};
        for (int i = 0; i < Ventas.SeleccionarProductos.getColumnCount(); i++) {
            Ventas.SeleccionarProductos.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);}
         */
     }
     
     
         public void CargarProductostodoscategoria(){
         
         modelo = new DefaultTableModel(info, titulosColumnas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
      //le asigna el modelo al jtable
             Consulta_por_categoria.jTable1.setModel(modelo);

        //ejecuta una consulta a la BD
        ejecutarConsultaTodaTabla();
         
     }
          public void CargarProductos2(){
         
         modelo = new DefaultTableModel(info, titulosColumnas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
      //le asigna el modelo al jtable
        ModificarProductos.jTable_productos.setModel(modelo);

        //ejecuta una consulta a la BD
        ejecutarConsultaTodaTabla();
         
     }
    
          public void CargarProductos_eliminar(){
         
         modelo = new DefaultTableModel(info, titulosColumnas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
      //le asigna el modelo al jtable
              EliminarProductos.jTable_productos.setModel(modelo);

        //ejecuta una consulta a la BD
        ejecutarConsultaTodaTabla();
         
     }    
     
      public DefaultTableModel mostrar_inicial() {
        //cargar tabla plan sin parametros
            // conexion = ConexionConBaseDatos.getConexion();
          
          int totalregistros;
     //   DefaultTableModel modelo1;

        String[] titulos = {"id", "nombres", "precio", "dias"};

        String[] registro = new String[14];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

            sSQL = "SELECT idProductos,codigo,nombreProductos,preciosProductos,descripcionProductos,cantidadProductos,preciocompraProductos,codigo FROM table_Productos ORDER BY idProductos ASC";

        try {
            conexion = ConexionConBaseDatos.getConexion();

//              conexion = DriverManager.getConnection("jdbc:mysql://localhost/Ultimate_FerreteriaAndres", "root", "");
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
            
                 registro[0]  = resultado.getString("idProductos");
                  registro[1]  = resultado.getString("codigo");
                registro[2] = resultado.getString("nombreProductos");
                registro[3] = resultado.getString("preciosProductos");
                registro[4] = resultado.getString("descripcionProductos");
                registro[5] = resultado.getString("cantidadProductos");
                registro[6] = resultado.getString("preciocompraProductos");
                //crea un vector donde los está la informacion (se crea una fila)
               // Object[] info = {codigo, nombre, precioventa,descripcion,cantidad,preciocompra};
                

                
                totalregistros = totalregistros + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }
    
    
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
    /**
     * Metodo para listar todos los registros de la tabla
     * de clientes, los muestra en un jtable.
     */
      
      public void listarTodosProductos_por_agotar() {

        modelo = new DefaultTableModel(info, titulosColumnas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
      //le asigna el modelo al jtable
        ConsultarProductos.jTableListarCliente.setModel(modelo);


        //ejecuta una consulta a la BD
        ejecutarConsultaTodaTabla_por_agotar();
        

    }
      
      
    public void listarTodosProductos() {

        modelo = new DefaultTableModel(info, titulosColumnas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
      //le asigna el modelo al jtable
        ConsultarProductos.jTableListarCliente.setModel(modelo);


        //ejecuta una consulta a la BD
        ejecutarConsultaTodaTabla();
        

    }//cierra metodo listarTodosClientes
    
     /**
     * Metodo para consultar todos los regsitros de la base de datos de clientes
     * y luego ser mostrados en una tabla.
     */
    Connection conexion = null;
    Statement sentencia = null;
    ResultSet resultado = null;
    PreparedStatement ps = null;

        public void ejecutarConsultaTodaTabla_por_agotar() {

        try {
            conexion = ConexionConBaseDatos.getConexion();

            sentencia = conexion.createStatement();
            //ORDER BY idProductos
            String consultaSQL = "select * from table_productos where cantidadProductos<=4";
                          Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(consultaSQL);


            //mientras haya datos en la BD ejecutar eso...
            while (rs.next()) {


                String codigo = rs.getString("idProductos");
                String nombre = rs.getString("nombreProductos");
                String precioventa = rs.getString("preciosProductos");
                String descripcion = rs.getString("descripcionProductos");
                String cantidad = rs.getString("cantidadProductos");
                   String preciocompraProductos = rs.getString("preciocompraProductos");

                //crea un vector donde los está la informacion (se crea una fila)
                Object[] info = {codigo, nombre, precioventa,descripcion,cantidad,preciocompraProductos};

                //al modelo de la tabla le agrega una fila
                //con los datos que están en info
                modelo.addRow(info);

            }//cierra while (porque no hay mas datos en la BD)


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error SQL:\n" + e);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
            conexion = null;
        } finally {
            CerrarConexiones.metodoCerrarConexiones(conexion, sentencia, resultado, ps);
        }

    }//cierra metodo ejecutarConsulta
        
        
            public void ejecutarConsultaTodaTablaventas() {

        try {
            conexion = ConexionConBaseDatos.getConexion();

            sentencia = conexion.createStatement();
            String consultaSQL = "SELECT * FROM table_Productos WHERE cantidadProductos>0 ORDER BY idProductos ASC ";
            resultado = sentencia.executeQuery(consultaSQL);


            //mientras haya datos en la BD ejecutar eso...
            while (resultado.next()) {


                String codigo = resultado.getString("idProductos");
                String codigob = resultado.getString("codigo");
                String nombre = resultado.getString("nombreProductos");
                String precioventa = resultado.getString("preciosProductos");
                String descripcion = resultado.getString("descripcionProductos");
                String cantidad = resultado.getString("cantidadProductos");
                   String preciocompraProductos = resultado.getString("preciocompraProductos");

                //crea un vector donde los está la informacion (se crea una fila)
                Object[] info = {codigo,codigob, nombre, precioventa,descripcion,cantidad,preciocompraProductos};

                //al modelo de la tabla le agrega una fila
                //con los datos que están en info
                modelo.addRow(info);

            }//cierra while (porque no hay mas datos en la BD)


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error SQL:\n" + e);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
            conexion = null;
        } finally {
            CerrarConexiones.metodoCerrarConexiones(conexion, sentencia, resultado, ps);
        }

    }//cierra metodo ejecutarConsulta
        
    public void ejecutarConsultaTodaTabla() {

        try {
            conexion = ConexionConBaseDatos.getConexion();

            sentencia = conexion.createStatement();
            String consultaSQL = "SELECT * FROM table_Productos ORDER BY idProductos ASC";
            resultado = sentencia.executeQuery(consultaSQL);


            //mientras haya datos en la BD ejecutar eso...
            while (resultado.next()) {


                String codigo = resultado.getString("idProductos");
                   String codigobarra = resultado.getString("codigo");
                String nombre = resultado.getString("nombreProductos");
                String precioventa = resultado.getString("preciosProductos");
                String descripcion = resultado.getString("descripcionProductos");
                String cantidad = resultado.getString("cantidadProductos");
                   String preciocompraProductos = resultado.getString("preciocompraProductos");

                //crea un vector donde los está la informacion (se crea una fila)
                Object[] info = {codigo,codigobarra, nombre, precioventa,descripcion,cantidad,preciocompraProductos};
                
                //al modelo de la tabla le agrega una fila
                //con los datos que están en info
                modelo.addRow(info);

            }//cierra while (porque no hay mas datos en la BD)


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error SQL:\n" + e);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
            conexion = null;
        } finally {
            CerrarConexiones.metodoCerrarConexiones(conexion, sentencia, resultado, ps);
        }

    }//cierra metodo ejecutarConsulta
    
  
    public void buscarProductos(String parametroBusqueda) {

        

            modelo = new DefaultTableModel(info, titulosColumnas) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            ;
            //le asigna el modelo al jtable
            ConsultarProductos.jTableListarCliente.setModel(modelo);
            //ejecuta una consulta a la BD
            buscarRegistroProductos(parametroBusqueda);

    }
        public void buscarProductoscodigo(String parametroBusqueda) {

        

            modelo = new DefaultTableModel(info, titulosColumnas) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            ;
            //le asigna el modelo al jtable
            ConsultarProductos.jTableListarCliente.setModel(modelo);
            //ejecuta una consulta a la BD
            buscarRegistroProductoscodigo(parametroBusqueda);

    }
        
        
               public void buscarProductosporcategoria(String parametroBusqueda) {

        

            modelo = new DefaultTableModel(info, titulosColumnas) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            ;
            //le asigna el modelo al jtable
            Consulta_por_categoria.jTable1.setModel(modelo);
            //ejecuta una consulta a la BD
            buscarRegistroProductosporcategoria(parametroBusqueda);

    }
        
//    
                       public void buscarRegistroProductosporcategoria(String parametroBusqueda) {

        try {        
       conexion = ConexionConBaseDatos.getConexion();
            String selectSQL;
            resultado = null;
            String id_categoria="";
            
            
            
        ///
                selectSQL = "SELECT * FROM table_categoria WHERE nombre_categoria LIKE ? ORDER BY id_categoria ASC";
                ps = conexion.prepareStatement(selectSQL);
                ps.setString(1, "%" + parametroBusqueda + "%");
    
            resultado = ps.executeQuery();

            while (resultado.next()) {
                       id_categoria = resultado.getString("id_categoria");
            
   
            }
        ///
           //// 
            
            
            
            
            
            
            
            
                            
                selectSQL = "SELECT * FROM table_Productos WHERE categoria LIKE ? ORDER BY idProductos ASC";
                ps = conexion.prepareStatement(selectSQL);
                ps.setString(1, "%" + id_categoria + "%");
    
            resultado = ps.executeQuery();

            while (resultado.next()) {
                      String codigop = resultado.getString("codigo");
                String codigo = resultado.getString("idProductos");
                String nombre = resultado.getString("nombreProductos");
                String precioventa = resultado.getString("preciosProductos");
                String descripcion = resultado.getString("descripcionProductos");
                String cantidad = resultado.getString("cantidadProductos");
                String preciocompra = resultado.getString("preciosProductos");
                //crea un vector donde los está la informacion (se crea una fila)
                Object[] info = {codigo,codigop, nombre, precioventa,descripcion,cantidad,preciocompra};
                //al modelo de la tabla le agrega una fila
                //con los datos que están en info
                modelo.addRow(info);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error\n Por la Causa" + e);
        } finally {
            CerrarConexiones.metodoCerrarConexiones(conexion, sentencia, resultado, ps);
        }


    }
               
               
               
               
        public void buscarRegistroProductoscodigo(String parametroBusqueda) {

        try {

            conexion = ConexionConBaseDatos.getConexion();
            String selectSQL;
            resultado = null;
                            
                selectSQL = "SELECT * FROM table_Productos WHERE codigo LIKE ? ORDER BY idProductos ASC";
                ps = conexion.prepareStatement(selectSQL);
                ps.setString(1, "%" + parametroBusqueda + "%");
    
            resultado = ps.executeQuery();

            while (resultado.next()) {
                      String codigop = resultado.getString("codigo");
                String codigo = resultado.getString("idProductos");
                String nombre = resultado.getString("nombreProductos");
                String precioventa = resultado.getString("preciosProductos");
                String descripcion = resultado.getString("descripcionProductos");
                String cantidad = resultado.getString("cantidadProductos");
                String preciocompra = resultado.getString("preciosProductos");
                //crea un vector donde los está la informacion (se crea una fila)
                Object[] info = {codigo,codigop, nombre, precioventa,descripcion,cantidad,preciocompra};
                //al modelo de la tabla le agrega una fila
                //con los datos que están en info
                modelo.addRow(info);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error\n Por la Causa" + e);
        } finally {
            CerrarConexiones.metodoCerrarConexiones(conexion, sentencia, resultado, ps);
        }


    }
    public void buscarRegistroProductos(String parametroBusqueda) {

        try {

            conexion = ConexionConBaseDatos.getConexion();
            String selectSQL;
            resultado = null;
                            
                selectSQL = "SELECT * FROM table_Productos WHERE nombreProductos LIKE ? ORDER BY idProductos ASC";
                ps = conexion.prepareStatement(selectSQL);
                ps.setString(1, "%" + parametroBusqueda + "%");
    
            resultado = ps.executeQuery();

            while (resultado.next()) {
                      String codigop = resultado.getString("codigo");
                String codigo = resultado.getString("idProductos");
                String nombre = resultado.getString("nombreProductos");
                String precioventa = resultado.getString("preciosProductos");
                String descripcion = resultado.getString("descripcionProductos");
                String cantidad = resultado.getString("cantidadProductos");
                String preciocompra = resultado.getString("preciosProductos");
                //crea un vector donde los está la informacion (se crea una fila)
                Object[] info = {codigo,codigop, nombre, precioventa,descripcion,cantidad,preciocompra};
                //al modelo de la tabla le agrega una fila
                //con los datos que están en info
                modelo.addRow(info);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error\n Por la Causa" + e);
        } finally {
            CerrarConexiones.metodoCerrarConexiones(conexion, sentencia, resultado, ps);
        }


    }//cierra metodo buscarRegistro
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    
    /**
     * Método para validar la entrada del usuario
     * que ingresa para eliminar un cliente
     */
    public void EliminarProductos(String code) {

        try {            
             conexion = ConexionConBaseDatos.getConexion();
            Statement comando = conexion.createStatement();
            int cantidad = comando.executeUpdate("delete from table_Productos where idProductos=" + code);
            if (cantidad == 1) {
   
                JOptionPane.showMessageDialog(null,"Eliminado");
            } else {
                JOptionPane.showMessageDialog(null,"No existe Producto de Codigo "+code);
            }
            conexion.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error "+ex);
        }

    }//cierra metodo eliminarCliente

    
    /**
     * Método para validar y modificar la 
     * información de un cliente.
     */
    public void ModificarProductos(String code,String nombre,float precios,String descripcion,int cantidad_productos, float preciocompra,float dif) {

    
        try {
             conexion = ConexionConBaseDatos.getConexion();
            Statement comando = conexion.createStatement();
            
            // linea de codigo de mysql que actualiza regristos que va modificar
            int cantidad = comando.executeUpdate("update table_Productos set nombreProductos ='" + nombre + "', "
                + " preciosProductos ='" + precios + "' "+ ", descripcionProductos ='" + descripcion + "'"+ ", cantidadProductos ='" + cantidad_productos + "'"+ " , preciocompraProductos ='" + preciocompra + "' , Difererencia =" + dif + "  where idProductos=" + code);
            if (cantidad == 1) {
                JOptionPane.showMessageDialog(null," Modifico con Exito");
            } else {
                JOptionPane.showMessageDialog(null,"No existe Vendedor de un codigo "+code);
            }
            conexion.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null," Error -->"+ex);
        }
    }//cierra metodo modificarCliente
    
    
    
    
    //es para buscar productos de compras en ventas- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
    public void buscarProductosparaGuia(String parametroBusqueda) {

        

            modelo = new DefaultTableModel(info, titulosColumnas) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            ;
            //le asigna el modelo al jtable
            Guia_de_remision.SeleccionarProductos.setModel(modelo);
            //ejecuta una consulta a la BD
            buscarRegistroProductoss(parametroBusqueda);

    }   
    public void buscarProductosparaVentas(String parametroBusqueda) {

        

            modelo = new DefaultTableModel(info, titulosColumnas) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            ;
            //le asigna el modelo al jtable
            Ventas.SeleccionarProductos.setModel(modelo);
            //ejecuta una consulta a la BD
            buscarRegistroProductoss(parametroBusqueda);

    }
            public void buscarProductosparaComprascodigo(String parametroBusqueda) {

        

            modelo = new DefaultTableModel(info, titulosColumnas) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            ;
            //le asigna el modelo al jtable
            Compras.SeleccionarProductos.setModel(modelo);
            //ejecuta una consulta a la BD
            buscarRegistroProductosscodigo(parametroBusqueda);

    }
        public void buscarProductosparaVentascodigo(String parametroBusqueda) {

        

            modelo = new DefaultTableModel(info, titulosColumnas) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            ;
            //le asigna el modelo al jtable
            Ventas.SeleccionarProductos.setModel(modelo);
            //ejecuta una consulta a la BD
            buscarRegistroProductosscodigo(parametroBusqueda);

    }
        public void buscarProductosparaCompras(String parametroBusqueda) {

        

            modelo = new DefaultTableModel(info, titulosColumnas) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            ;
            //le asigna el modelo al jtable
            Compras.SeleccionarProductos.setModel(modelo);
            //ejecuta una consulta a la BD
            buscarRegistroProductoss(parametroBusqueda);

    }
        public void buscarProductosparaProductos(String parametroBusqueda) {

        

            modelo = new DefaultTableModel(info, titulosColumnas) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            ;
            //le asigna el modelo al jtable
            ModificarProductos.jTable_productos.setModel(modelo);
            //ejecuta una consulta a la BD
            buscarRegistroProductoss(parametroBusqueda);

    }
        
             public void buscarProductosparaEliminarProductos(String parametroBusqueda) {

        

            modelo = new DefaultTableModel(info, titulosColumnas) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            ;
            //le asigna el modelo al jtable
                 EliminarProductos.jTable_productos.setModel(modelo);
            //ejecuta una consulta a la BD
            buscarRegistroProductoss(parametroBusqueda);

    } 
    
    public void buscarRegistroProductosscodigo(String parametroBusqueda) {

        try {

            conexion = ConexionConBaseDatos.getConexion();
            String selectSQL;
            resultado = null;
//  + " nombres like '%"
  //              + buscar + "%'                            
                selectSQL = "SELECT * FROM table_Productos WHERE codigo = ? AND cantidadProductos>0 ORDER BY idProductos ASC";
                ps = conexion.prepareStatement(selectSQL);
                ps.setString(1, "" + parametroBusqueda + "");
    
            resultado = ps.executeQuery();

            while (resultado.next()) {
                String codigo = resultado.getString("idProductos");
                String codigob = resultado.getString("codigo");
                String nombre = resultado.getString("nombreProductos");
                String precioventa = resultado.getString("preciosProductos");
                String descripcion = resultado.getString("descripcionProductos");
                String cantidad = resultado.getString("cantidadProductos");
                String preciocompra = resultado.getString("preciosProductos");
                //crea un vector donde los está la informacion (se crea una fila)
                Object[] info = {codigo,codigob, nombre, precioventa,descripcion,cantidad,preciocompra};
                //al modelo de la tabla le agrega una fila
                //con los datos que están en info
                modelo.addRow(info);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error\n Por la Causa" + e);
        } finally {
            CerrarConexiones.metodoCerrarConexiones(conexion, sentencia, resultado, ps);
        }


    }
             
             
    public void buscarRegistroProductoss(String parametroBusqueda) {

        try {

            conexion = ConexionConBaseDatos.getConexion();
            String selectSQL;
            resultado = null;
//  + " nombres like '%"
  //              + buscar + "%'                            
                selectSQL = "SELECT * FROM table_Productos WHERE nombreProductos LIKE ? AND cantidadProductos>0 ORDER BY idProductos ASC";
                ps = conexion.prepareStatement(selectSQL);
                ps.setString(1, "%" + parametroBusqueda + "%");
    
            resultado = ps.executeQuery();

            while (resultado.next()) {
                String codigo = resultado.getString("idProductos");
                String codigob = resultado.getString("codigo");
                String nombre = resultado.getString("nombreProductos");
                String precioventa = resultado.getString("preciosProductos");
                String descripcion = resultado.getString("descripcionProductos");
                String cantidad = resultado.getString("cantidadProductos");
                String preciocompra = resultado.getString("preciosProductos");
                //crea un vector donde los está la informacion (se crea una fila)
                Object[] info = {codigo,codigob, nombre, precioventa,descripcion,cantidad,preciocompra};
                //al modelo de la tabla le agrega una fila
                //con los datos que están en info
                modelo.addRow(info);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error\n Por la Causa" + e);
        } finally {
            CerrarConexiones.metodoCerrarConexiones(conexion, sentencia, resultado, ps);
        }


    }


}//cierra class
