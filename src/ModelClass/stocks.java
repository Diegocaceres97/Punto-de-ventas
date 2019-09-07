/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelClass;


import static View.Sistema.Table_Clientes1;
import static View.Sistema.coloc;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
//import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import models.Consult_stock;
import models.reporte_clientes;
import models.stock;


/**
 *
 * @author Diego
 */
public class stocks extends Consult_stock implements Printable{
java.util.Date date;
java.util.Date sqldate;
java.util.Date fecha = new java.util.Date();
   
   // private Consult consult = new Consult();
    private DefaultTableModel modelo,modelo2;
    private List<stock>stk, stkFilter;
    private int Nit;
    public int Nitt;
   private String sql;
   private Object[ ] object;
   private JPanel jpanel;
   public void insertStock(String ref, String des,int pre,int cant,String mar,String cat){
       sqldate = new java.sql.Date(fecha.getTime());
       sql = "INSERT INTO  stock (referencia,descripcion,precio_unidad,cantidad,marca,categoria)" + "values (?,?,?,?,?,?)";
       object = new Object[]{ref,des,pre,cant,mar,cat};//Datos que vamos a enviar la consult en el array
       insert (sql,object);
       stk = STOCK();//Guarda la informacion obtenida para poder ver/manipular
  stk.forEach(item ->{//Este objeto esta conteniendo todo los datos obtenidos de clientes
      Nit = item.getId_stock();
  });
   }
   public List<stock>getStock(){
       return STOCK();
   }
   public void SearchCliente(JTable table, String campo, int num_registro,int reg_por_pag){
     String[] registros = new String[7];  //Creacion tanto del modelo como de la maqueta tabla
     String[] titulos = {"ID","Referencia","Descripcion","Precio_unidad","cantidad","Marca","Categoria"};
//int t= Integer.parseInt(campo);
     modelo = new DefaultTableModel(null,titulos);   
  stk = STOCK();
  if(campo.equals("")){
      stkFilter = stk.stream()//Obtiene el metodo y filtra
  .skip(num_registro).limit(reg_por_pag)//Omite los registros dependiedo al dato que venga por parametro 
    .collect(Collectors.toList());
              //El limit limita los registros a visualizar
              }else{
      stkFilter = stk.stream()
              .filter(c -> c.getMarca().toLowerCase().startsWith(campo)||c.getReferencia().toLowerCase().startsWith(campo)
             ||c.getCategoria().toLowerCase().startsWith(campo))
  .skip(num_registro).limit(reg_por_pag)//Omite los registros dependiedo al dato que venga por parametro 
    .collect(Collectors.toList());              
// startsWith: Busca informacion con respecto a lo que se ingresa
              //y lo almacena en el objeto List
  }
  stkFilter.forEach(item-> {
  registros[0] = String.valueOf(item.getId_stock());
  registros[1] = item.getReferencia();
  registros[2] = item.getDescripcion();
  registros[3] = String.valueOf(item.getPrecio_unidad());
  registros[4] = String.valueOf(item.getCantidad());
  registros[5] = item.getMarca();
  registros[6] = item.getCategoria();
  modelo.addRow(registros);
  });
  table.setModel(modelo);
  table.setRowHeight(33);
  table.getColumnModel().getColumn(0).setMaxWidth(0);
  table.getColumnModel().getColumn(0).setMinWidth(0);
  table.getColumnModel().getColumn(0).setPreferredWidth(0);
  coloc.setText(String.valueOf(table.getRowCount()));
       for (int i = 0; i < Table_Clientes1.getRowCount(); i++) { //Datos obtenidos Act 3.0
if(Table_Clientes1.getValueAt(i, 4).toString().equals("0")){//Con este metodo eliminara o sacara todo producto
      ((DefaultTableModel)Table_Clientes1.getModel()).removeRow(i);
}
 }
   }

    public DefaultTableModel getModelo() {
        return modelo;//Creado especialmente para devolver el modelo para coger datos de la tabla
    }
//   public DefaultTableModel ReportesClientes(JTable table,int NIT){
//        String[] registros = new String[7];  //Creacion tanto del modelo como de la maqueta tabla
//     String[] titulos = {"Nombre","Apellido","Nit","Saldo_Actual","Fecha_Actual","Ultimo_Pago","Fecha_Pago"};
//     modelo2 = new DefaultTableModel(null,titulos);
//     List<reporte_clientes>rportes = reporteClientes(NIT);
//     rportes.forEach(item-> {
//  registros[0] = item.getNombre();
//  registros[1] = item.getApellido();
//  registros[2] = String.valueOf(item.getNitC());
//  registros[3] = String.valueOf(item.getSaldo_Actual());
//  registros[4] = String.valueOf(item.getFecha_Actual());
//  registros[5] = String.valueOf(item.getUltimo_pago());
//  registros[6] = String.valueOf(item.getFecha_pago());
//  modelo2.addRow(registros);
//  });
//     table.setModel(modelo2);
//  table.setRowHeight(27);
////  table.getColumnModel().getColumn(0).setMaxWidth(0);
////  table.getColumnModel().getColumn(0).setMinWidth(0);  Fragmento de codigo que sirve para ocultar columnas
////  table.getColumnModel().getColumn(0).setPreferredWidth(0);
//  return modelo2;
//   }
   public void UpdateCliente(int Nit,String ref, String des,int pre,int cant,String mar,String cat,int sab){
      if(sab==0){
       sql = "UPDATE stock  SET referencia=?,descripcion=?,precio_unidad=?,cantidad=?,marca=?,categoria=? where id_stock = "+Nit;
       Object[] st = new Object[]{ref,des,pre,cant,mar,cat};
       update(sql,st);}else{
           sql = "UPDATE stock  SET cantidad=? where id_stock = "+Nit;
       Object[] st = new Object[]{cant};
       update(sql,st);
      }
   }
   public void deleteClientes(int nit){
       sql = "DELETE FROM stock WHERE id_stock = ?";
       delete(sql,nit);
   }
//    public  void updatereportcliente(int deudaactual, int pago, int nit) {
//         sqldate = new java.sql.Date(fecha.getTime());
//        List<reporte_clientes>rportes = reporteClientes(nit);
//  int NitCc=rportes.get(0).getNitC();
//  int nn=rportes.get(0).getNit();
//  sql = "UPDATE reporte_clientes  SET NitC=?,saldo_actual=?,fecha_actual=?,ultimo_pago=?,fecha_pago=? where NitC = "+nit;
//  Object[] rp = new Object[]{nit,deudaactual,sqldate,pago,sqldate};
//      update(sql,rp);
//    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
       if(pageIndex==0){
           Graphics2D g2D = (Graphics2D) graphics;
           g2D.translate(pageFormat.getImageableX(), pageFormat.getImageableY());//Obtenemos las coordenadas
           g2D.scale(0.70, 0.70);//Reduccion de la impresion al 70%
           this.jpanel.printAll(graphics);//Imprimemos al objeto
           return PAGE_EXISTS;
       }else{
           return NO_SUCH_PAGE;
       }
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
public void imprimirrecibo(JPanel jpanel){
    this.jpanel = jpanel;//Este objeto contiene nueestro control que hace falta hacer que tiene el recibo
    PrinterJob printerjob=PrinterJob.getPrinterJob();
    //Se pasa la instancia del JFRAME al PrinterJob
    printerjob.setPrintable(this);
    //Muestra ventana de dialogo para imprimir
    if(printerjob.printDialog()){
        try {
            printerjob.print();
        } catch (PrinterException ex) {
            Logger.getLogger(stocks.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
public void buscar(String palabra, String ot){   
       sql = "select * from stock WHERE "+palabra+" = "+ot+"";
       String h="";
       String y="";      
        try (Statement st = conexion().createStatement(); ResultSet rs = st.executeQuery(sql);){ 
            while(rs.next()){
                h = rs.getString(2)+" "+rs.getString(3);                
               y+="\n"+h; 
            }
            if(palabra.equalsIgnoreCase("cantidad")){
            JOptionPane.showMessageDialog(null,y,"Productos Faltantes",JOptionPane.INFORMATION_MESSAGE);}else{
                JOptionPane.showMessageDialog(null,y,"Productos vendidos",JOptionPane.INFORMATION_MESSAGE);
            }
//            else{
//                JOptionPane.showMessageDialog(null,"No hay productos escasos","ERROR",JOptionPane.ERROR_MESSAGE);               
//            }
        } catch (SQLException ex) {
            Logger.getLogger(stocks.class.getName()).log(Level.SEVERE, null, ex);
        }
       
   }
}
