/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control_BD;

import BD_Cliente.ListarCliente;
import BD_Usuario.addUsuario;
import BD_Proveedor.EliminarProveedor;
import BD_Proveedor.ListarProveedor;
import BD_Proveedor.ModificarProveedor;
import minimarket.Compras;
import minimarket.Guia_de_remision;
import minimarket.Ventas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RICHARD
 */
public class Control_Proveedor {
    
    //modelo para la tabla
    DefaultTableModel modelo;
    //vector con los titulos de cada columna
    String[] titulosColumnas = {"CÓDIGO", "RAZÓN SOCIAL", "RUC", "DIRECCIÓN", "TELÉFONO", "CORREO"};
    //matriz donde se almacena los datos de cada celda de la tabla
    String info[][] = {};
    // Conectar Base de Datos
    ConexionConBaseDatos conectar = new ConexionConBaseDatos();
    
    
    

     public void agregarProveedor(String razon_social,String ruc,String direccion,String telefono,String correo) {

         Connection reg = ConexionConBaseDatos.getConexion();
        
         String sql = "INSERT INTO table_proveedor ( id_proveedor,razon_social_proveedor,ruc_proveedor,direccion_proveedor,telefono_proveedor,correo_proveedor)VALUES (?,?,?,?,?,?)";
            try {
            
            PreparedStatement pst= reg.prepareStatement(sql);
            pst.setString(1,"");
   
            pst.setString(2, razon_social);
            pst.setString(3, ruc);
             pst.setString(4, direccion);
              pst.setString(5, telefono);
               pst.setString(6, correo);
            int n = pst.executeUpdate();
            if (n>0){
                JOptionPane.showMessageDialog(null,"Regristado Exitosamente de Proveedor");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error - "+ex);
            Logger.getLogger(addUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//cierra metodo agregarCliente

    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
    /**
     * Metodo para listar todos los registros de la tabla
     * de clientes, los muestra en un jtable.
     */
    public void listarTodosProveedores() {

        modelo = new DefaultTableModel(info, titulosColumnas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
      //le asigna el modelo al jtable
        ListarProveedor.jTableListarProveedor.setModel(modelo);

        //ejecuta una consulta a la BD
        ejecutarConsultaTodaTabla();

    }//cierra metodo listarTodosClientes
        public void CargarModificarProveedores() {

        modelo = new DefaultTableModel(info, titulosColumnas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
      //le asigna el modelo al jtable
            ModificarProveedor.jTable_proveedor.setModel(modelo);

        //ejecuta una consulta a la BD
        ejecutarConsultaTodaTabla();

    }    
              public void CargarEliminarProveedores() {

        modelo = new DefaultTableModel(info, titulosColumnas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
      //le asigna el modelo al jtable
                  EliminarProveedor.jTable_proveedor.setModel(modelo);

        //ejecuta una consulta a la BD
        ejecutarConsultaTodaTabla();

    } 
        public void CargarProveedores() {

        modelo = new DefaultTableModel(info, titulosColumnas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
      //le asigna el modelo al jtable
            Guia_de_remision.SeleccionarCliente.setModel(modelo);

        //ejecuta una consulta a la BD
        ejecutarConsultaTodaTabla();

    }//cierra metodo cargarTodosClientes
    public void CargarProveedoresVentas() {

        modelo = new DefaultTableModel(info, titulosColumnas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
      //le asigna el modelo al jtable
            Compras.SeleccionarProveedor.setModel(modelo);

        //ejecuta una consulta a la BD
        ejecutarConsultaTodaTabla();

    }//cierra metodo cargarTodosClientes
     /**
     * Metodo para consultar todos los regsitros de la base de datos de clientes
     * y luego ser mostrados en una tabla.
     */
    Connection conexion = null;
    Statement sentencia = null;
    ResultSet resultado = null;
    PreparedStatement ps = null;

    public void ejecutarConsultaTodaTabla() {

        try {
            conexion = ConexionConBaseDatos.getConexion();

            sentencia = conexion.createStatement();
            String consultaSQL = "SELECT * FROM table_proveedor ORDER BY razon_social_proveedor ASC";
            resultado = sentencia.executeQuery(consultaSQL);


            //mientras haya datos en la BD ejecutar eso...
            while (resultado.next()) {


                int codigo = resultado.getInt("id_proveedor");
         
                String razon_s = resultado.getString("razon_social_proveedor");
                String ruc = resultado.getString("ruc_proveedor");
                String direccion = resultado.getString("direccion_proveedor");
                String telefono = resultado.getString("telefono_proveedor");
                String correo = resultado.getString("correo_proveedor");
                //crea un vector donde los está la informacion (se crea una fila)
                Object[] info = {codigo, razon_s,ruc,direccion,telefono,correo};

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
    
  
    public void buscarProveedor(String parametroBusqueda, boolean buscarPorCedula, boolean buscarPorNombre, boolean buscarPorApellido) {

        

            modelo = new DefaultTableModel(info, titulosColumnas) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            ;

            //le asigna el modelo al jtable
            ListarCliente.jTableListarCliente.setModel(modelo);
            //ejecuta una consulta a la BD
            buscarRegistroCedulaONombreOapellido(parametroBusqueda, buscarPorCedula, buscarPorNombre, buscarPorApellido);

        

    }//cierra metodo buscarCliente
    public void buscarListaProveedor(String parametroBusqueda) {

        

            modelo = new DefaultTableModel(info, titulosColumnas) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            ;

            //le asigna el modelo al jtable
            ListarProveedor.jTableListarProveedor.setModel(modelo);
            //ejecuta una consulta a la BD
            buscarRegistrorucrazonsocial(parametroBusqueda);

        

    }//cierra metodo buscarCliente  
    public void buscarModificarproveedor(String parametroBusqueda) {

        

            modelo = new DefaultTableModel(info, titulosColumnas) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            ;

            //le asigna el modelo al jtable
            ModificarProveedor.jTable_proveedor.setModel(modelo);
            //ejecuta una consulta a la BD
            buscarRegistrorucrazonsocial(parametroBusqueda);

        

    }//cierra metodo buscarCliente    
    public void buscarEliminarProveedor(String parametroBusqueda) {

        

            modelo = new DefaultTableModel(info, titulosColumnas) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            ;

            //le asigna el modelo al jtable
            EliminarProveedor.jTable_proveedor.setModel(modelo);
            //ejecuta una consulta a la BD
            buscarRegistrorucrazonsocial(parametroBusqueda);

        

    }//cierra metodo buscarCliente
    /**
     * Método para buscar un registro en la base de datos dentro de la tabla
     * clientes, se puede buscar por la cedula o por el nombre.
     */
        public void buscarRegistrorucrazonsocial(String parametroBusqueda) {

        try {

            conexion = ConexionConBaseDatos.getConexion();
            String selectSQL;
            resultado = null;

                selectSQL = "SELECT * FROM table_proveedor WHERE  razon_social_proveedor LIKE ?  ORDER BY id_proveedor ASC";
                ps = conexion.prepareStatement(selectSQL);
                ps.setString(1, "%" + parametroBusqueda + "%");
               

            resultado = ps.executeQuery();

            while (resultado.next()) {
           
          int codigo = resultado.getInt("id_proveedor");
      
                String razon_social_proveedor = resultado.getString("razon_social_proveedor");
                String ruc_proveedor = resultado.getString("ruc_proveedor");
                String direccion_proveedor = resultado.getString("direccion_proveedor");
                String telefono_proveedor = resultado.getString("telefono_proveedor");
                String correo_proveedor = resultado.getString("correo_proveedor");
                //crea un vector donde los está la informacion (se crea una fila)
                Object[] info = {codigo, razon_social_proveedor,ruc_proveedor,direccion_proveedor,telefono_proveedor,correo_proveedor};;                

             
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
    public void buscarRegistroCedulaONombreOapellido(String parametroBusqueda, boolean buscarPordni, boolean buscarPorNombre, boolean buscarPorApellido) {

        try {

            conexion = ConexionConBaseDatos.getConexion();
            String selectSQL;
            resultado = null;
       
             if(buscarPorNombre== true){
                selectSQL = "SELECT * FROM table_Cliente WHERE Nombre_Cliente LIKE ? ORDER BY idCliente ASC";
                ps = conexion.prepareStatement(selectSQL);
                ps.setString(1, "%" + parametroBusqueda + "%");
            }
            else if(buscarPorApellido== true){

                selectSQL = "SELECT * FROM table_Cliente WHERE Apellido_Cliente LIKE ? ORDER BY idCliente ASC";
                ps = conexion.prepareStatement(selectSQL);
                ps.setString(1, "%" + parametroBusqueda + "%");
            }
            resultado = ps.executeQuery();

            while (resultado.next()) {
                int codigo = resultado.getInt("idCliente");
                String nombre = resultado.getString("Nombre_Cliente");
                String apellido = resultado.getString("Apellido_Cliente");
                 String direccion = resultado.getString("direccion_Cliente");
                String razon_s_cliente = resultado.getString("razon_s_Cliente");
                String ruc_cliente = resultado.getString("razon_s_Cliente");                

                //crea un vector donde los está la informacion (se crea una fila)
                Object[] info = {codigo,nombre, apellido, direccion,razon_s_cliente,ruc_cliente};
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
    public void EliminarProveedor(String code) {

        try {            
     Connection conexion = ConexionConBaseDatos.getConexion();
            Statement comando = conexion.createStatement();
            int cantidad = comando.executeUpdate("delete from table_proveedor where id_proveedor=" + code);
            if (cantidad == 1) {
   
                JOptionPane.showMessageDialog(null,"Eliminado");
            } else {
                JOptionPane.showMessageDialog(null,"No existe Proveedor de Codigo "+code);
            }
            conexion.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error "+ex);
        }

    }//cierra metodo eliminarCliente

    
    
    public void ModificarProveedor(String code, String razon_social,String ruc,String direccion,String telefono,String correo) {

    
        try {
                 Connection conexion = ConexionConBaseDatos.getConexion();

      
            Statement comando = conexion.createStatement();

            // linea de codigo de mysql que actualiza regristos que va modificar
            int cantidad = comando.executeUpdate("update table_proveedor set  razon_social_proveedor ='" + razon_social + "'"+  ", direccion_proveedor ='" + direccion + "', telefono_proveedor ='" + telefono + "', correo_proveedor ='" + correo + "' , ruc_proveedor ='"+ruc+"'  where id_proveedor=" + code);
            if (cantidad == 1) {
                JOptionPane.showMessageDialog(null," Modifico con Exito");
            } else {
                JOptionPane.showMessageDialog(null,"No existe proveedor de un codigo "+code);
            }
            conexion.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null," Error -->"+ex);
        }
    }//cierra metodo modificarCliente
    
    
    
    
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 


}
