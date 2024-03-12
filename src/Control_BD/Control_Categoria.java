/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control_BD;
import BD_Categoria_p.ModificarCategoria;
import BD_Categoria_p.addCategorias;
import BD_Categoria_p.ModificarCategoria;


import Control_BD.CerrarConexiones;
import Control_BD.ConexionConBaseDatos;

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

public class Control_Categoria {
        DefaultTableModel modelo;
    //vector con los titulos de cada columna
    String[] titulosColumnas = {"ID","CATEGORIA"};
    //matriz donde se almacena los datos de cada celda de la tabla
    String info[][] = {};
    // Conectar Base de Datos
    ConexionConBaseDatos conectar = new ConexionConBaseDatos();
    
    
       Connection conexion = null;
    Statement sentencia = null;
    ResultSet resultado = null;
    PreparedStatement ps = null;

    public void ejecutarConsultaTodaTabla() {

        try {
            conexion = ConexionConBaseDatos.getConexion();

            sentencia = conexion.createStatement();
            String consultaSQL = "SELECT * FROM table_categoria ORDER BY nombre_categoria ASC";
            resultado = sentencia.executeQuery(consultaSQL);


            //mientras haya datos en la BD ejecutar eso...
            while (resultado.next()) {


                int codigo = resultado.getInt("id_categoria");
                String nombre = resultado.getString("nombre_categoria");
    
                //crea un vector donde los está la informacion (se crea una fila)
                Object[] info = {codigo, nombre};

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
     public void agregarCategoria(String categoria) {

         Connection reg = ConexionConBaseDatos.getConexion();
        
         String sql = "INSERT INTO table_categoria ( id_categoria, nombre_categoria)VALUES (?,?)";
            try {
            
            PreparedStatement pst= reg.prepareStatement(sql);
            pst.setString(1,"");
            pst.setString(2,categoria);
  
            int n = pst.executeUpdate();
            if (n>0){
                JOptionPane.showMessageDialog(null,"Regristado Exitosamente de Categoria");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error - "+ex);
            Logger.getLogger(addCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     
     
         public void ModificarCategoria(String code,String nombre_categoria) {

    
        try {
               Connection reg = ConexionConBaseDatos.getConexion();
            //Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/sysultimate_2022", "root", "");
      
            Statement comando = reg.createStatement();

            // linea de codigo de mysql que actualiza regristos que va modificar
            int cantidad = comando.executeUpdate("update table_categoria set nombre_categoria ='" + nombre_categoria + "' " +"where id_categoria =" + code);
            if (cantidad == 1) {
                JOptionPane.showMessageDialog(null," Modifico con Exito");
            } else {
                JOptionPane.showMessageDialog(null,"No existe categoria de un codigo "+code);
            }
            reg.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null," Error -->"+ex);
        }
    }//cierra metodo modificarCliente
         
                 public void CargarModificarcategoria() {

        modelo = new DefaultTableModel(info, titulosColumnas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
      //le asigna el modelo al jtable
            ModificarCategoria.jTable_categoria.setModel(modelo);

        //ejecuta una consulta a la BD
        ejecutarConsultaTodaTabla();

    }  
                 
                     public void buscarModificarCategoria(String parametroBusqueda) {

        

            modelo = new DefaultTableModel(info, titulosColumnas) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            ;

            //le asigna el modelo al jtable
            ModificarCategoria.jTable_categoria.setModel(modelo);
            //ejecuta una consulta a la BD
            buscarRegistronombre(parametroBusqueda);

        

    }
                     
                             public void buscarRegistronombre(String parametroBusqueda) {

        try {

            conexion = ConexionConBaseDatos.getConexion();
            String selectSQL;
            resultado = null;

                selectSQL = "SELECT * FROM table_categoria WHERE  nombre_categoria LIKE ?  ORDER BY id_categoria ASC";
                ps = conexion.prepareStatement(selectSQL);
                ps.setString(1, "%" + parametroBusqueda + "%");

            resultado = ps.executeQuery();

            while (resultado.next()) {
           
          int codigo = resultado.getInt("id_categoria");
                String nombre = resultado.getString("nombre_categoria");
            
                //crea un vector donde los está la informacion (se crea una fila)
                Object[] info = {codigo, nombre};                
 
             
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
}
