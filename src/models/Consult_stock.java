/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

/**
 *
 * @author Diego
 */
public class Consult_stock extends conexion{
    private QueryRunner QR = new QueryRunner();
    private List<stock>Stockk,Stocck;
    //private List<Clientes> cliente; //Creación de Objeto y pasamos como parametro a clase clientes
    public List<stock>STOCK(){ //Este metodo retornara un objeto
        try{
         Stocck = (List<stock>)QR.query((conexion()),"SELECT * FROM stock",
                new BeanListHandler(stock.class));//Convierte resulset en una lista, devuelve la columna con sus datos
          conexion().close();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "error");
            
        }
        return Stocck;
    }
    public void insert(String sql, Object[] data){
        final QueryRunner qr = new QueryRunner(true); //Objeto
        try {
            qr.insert(conexion(),sql,new ColumnListHandler(),data);
             conexion().close();
        } catch (SQLException ex) {
            Logger.getLogger(Consult_stock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    public List<stock> reporteStock(int valor){
//   String condicion = "clientes.nit = reporte_clientes.NitC";
//   String campos =  "clientes.nit,clientes.nombre,clientes.apellido,clientes.direccion,clientes.numero,"
//           +"reporte_clientes.NitC,reporte_clientes.saldo_actual, reporte_clientes.fecha_actual,reporte_clientes.ultimo_pago,reporte_clientes.fecha_pago";
//        try {
//            Stockk= (List<stock>)QR.query(conexion(),"SELECT "+campos+" FROM reporte_clientes inner join clientes on "
//                    + condicion + " where reporte_clientes.NitC = "+valor, new BeanListHandler(reporte_clientes.class));
//            //Me devuelve datos de dos tablas
//        } catch (SQLException ex) {
//            System.err.println("Error"+ex);
//        }
//        return Stockk;
//}
    public void update(String sql,Object [] data){ //Metodo para actualizar los registros
        final QueryRunner qr = new QueryRunner(true);
        try {
            qr.update(conexion(),sql,data);
             conexion().close();
        } catch (SQLException ex) {
            System.err.println("Error"+ex);
        }
        
    }
     public void updated(String sql){ //Metodo para actualizar los registros para sumar 1
        final QueryRunner qr = new QueryRunner(true);
        try {
            qr.update(conexion(),sql);
             conexion().close();
        } catch (SQLException ex) {
            System.err.println("Error"+ex);
        }
        
    }
    public void delete(String sql,int id){
        final QueryRunner qr = new QueryRunner(true); 
        try {
            qr.update(conexion(),sql,id);
             conexion().close();
        } catch (SQLException ex) {
           System.err.println("Error"+ex);
        }
    }
  
}
