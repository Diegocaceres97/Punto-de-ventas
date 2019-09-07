/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelClass;


import static View.Sistema.CIFRA;
import static View.Sistema.ver;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JPanel;
import javax.swing.JTable;
//import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import models.Consult_Ventas;
import models.Consult_stock;
import models.reporte_clientes;
import models.stock;
import models.venta;


/**
 *
 * @author Diego
 */
public class Ventas extends Consult_Ventas implements Printable{
java.util.Date date;
java.util.Date sqldate;
java.util.Date fecha = new java.util.Date();
   
   // private Consult consult = new Consult();
    private DefaultTableModel modelo,modelo2;
    private List<venta>stk, stkFilter;
    private int Nit;
    public int total,totald;//Creada para la suma de los totales de ventas
   private String sql;
   private Object[ ] object;
   private JPanel jpanel;
   public void insertVenta(int nit_cliente,String id_producto,int total_cant,int total){
        sqldate = new java.sql.Date(fecha.getTime());
       sql = "INSERT INTO  venta (nit_cliente,id_producto,total_cant,total,fecha)" + "values (?,?,?,?,?)";
       object = new Object[]{nit_cliente,id_producto,total_cant,total,sqldate};//Datos que vamos a enviar la consult en el array
       insert (sql,object);
       stk = ventas();//Guarda la informacion obtenida para poder ver/manipular
  stk.forEach(item ->{//Este objeto esta conteniendo todo los datos obtenidos de clientes
      Nit = item.getId_venta();
  });
   }
   public List<venta>getVentas(){
       return ventas();
   }
    public void SearchVentas(JTable table, String campo, int num_registro,int reg_por_pag,java.util.Date uno, java.util.Date dos){
       total=0;
        System.out.println("Manda: "+uno+" y "+dos);
        String[] registros = new String[6];  //Creacion tanto del modelo como de la maqueta tabla
     String[] titulos = {"ID_Ventas","Nit_Cliente","Id_Producto","Total_cantidad","total","fecha"};
//int t= Integer.parseInt(campo);
     modelo = new DefaultTableModel(null,titulos);   
  stk = ventas();
  if(campo.equals("")){
      stkFilter = stk.stream()//Obtiene el metodo y filtra
  .skip(num_registro).limit(reg_por_pag)//Omite los registros dependiedo al dato que venga por parametro 
    .collect(Collectors.toList());
              //El limit limita los registros a visualizar
              }else{
       System.out.println("Manda: "+uno+" y "+dos);
      stkFilter = stk.stream()
              .filter(c -> c.getFecha().after(uno)&&c.getFecha().before(dos))
  .skip(num_registro).limit(reg_por_pag)//Omite los registros dependiedo al dato que venga por parametro 
    .collect(Collectors.toList());
  }

  stkFilter.forEach(item-> {
  registros[0] = String.valueOf(item.getId_venta());
  registros[1] = String.valueOf(item.getNit_cliente());
  registros[2] = item.getId_producto();
  registros[3] = String.valueOf(item.getTotal_cant());
  registros[4] = String.valueOf(item.getTotal());
  registros[5] = String.valueOf(item.getFecha());
  modelo.addRow(registros);
  total+=item.getTotal();
  });
    if(ver==1){
      totald=0;
      if(uno!=null&&dos!=null){
          stkFilter = stk.stream()
              .filter(c -> c.getFecha().after(uno)&&c.getFecha().before(dos)).collect(Collectors.toList());
  //Omite los registros dependiedo al dato que venga por parametro 
      }else{
      stkFilter = stk.stream().collect(Collectors.toList());}
      //totald=0;
      stkFilter.forEach(item-> {
  //totald+=item.getTotal();
  //CIFRAD.setText(String.valueOf(totald));
  });
  }
  CIFRA.setText(String.valueOf(total));
  table.setModel(modelo);
  table.setRowHeight(36);
  table.getColumnModel().getColumn(0).setMaxWidth(0);
  table.getColumnModel().getColumn(0).setMinWidth(0);
  table.getColumnModel().getColumn(0).setPreferredWidth(0);
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
   public void UpdateCliente(int Nit,String ref, String des,int pre,int cant,String mar,String cat){
       sql = "UPDATE stock  SET referencia=?,descripcion=?,precio_unidad=?,cantidad=?,marca=?,categoria=? where id_stock = "+Nit;
       Object[] st = new Object[]{ref,des,pre,cant,mar,cat};
       update(sql,st);
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
            Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

    public void informe_ventas(java.util.Date uno, java.util.Date dos) {
          sql = "select * from venta where fecha between"+uno+"and"+dos;
       informeVentas(sql,uno,dos);
    }


}
