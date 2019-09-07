/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelClass;


import static View.Sistema.coloc2;
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
import models.Clientes;
import models.Consult;
import models.reporte_clientes;


/**
 *
 * @author Diego
 */
public class cliente extends Consult implements Printable{
java.util.Date date;
java.util.Date sqldate;
java.util.Date fecha = new java.util.Date();
   
   // private Consult consult = new Consult();
    private DefaultTableModel modelo,modelo2;
    private List<Clientes>clientee, ClienteFilter;
    private List<reporte_clientes>Reportes;
    private int Nit;
    public int res;
   private String sql;
   private Object[ ] object;
   private JPanel jpanel;
   public void insertCliente(int nit, String nombre,String apellido, String direccion, String numero){
       String sqld;
       sqldate = new java.sql.Date(fecha.getTime());
       sql = "INSERT INTO  clientes (nit,nombre,apellido,direccion,numero)" + "values (?,?,?,?,?)";
       object = new Object[]{nit,nombre,apellido,direccion,numero};//Datos que vamos a enviar a consult en el array
       insert (sql,object);
       clientee = clientes();//Guarda la informacion obtenida para poder ver/manipular
  clientee.forEach(item ->{//Este objeto esta conteniendo todo los datos obtenidos de clientes
      Nit = item.getNit();
  });
       //System.out.println("esta ahora aca");
  sqld = "INSERT INTO reporte_clientes(NitC,saldo_actual,fecha_actual,ultimo_pago,fecha_pago)" + "values(?,?,?,?,?)";
  object = new Object[]{nit,"0",sqldate,"0",sqldate};//Mantenimiento el dia 04
  insert(sqld,object);
   }
   public List<Clientes>getClientes(){
       return clientes();
   }
   public void SearchCliente(JTable table, String campo, int num_registro,int reg_por_pag){
       String[] registros = new String[5];  //Creacion tanto del modelo como de la maqueta tabla
     String[] titulos = {"Nit","Nombre","Apellido","Direccion","Telefono"};
//int t= Integer.parseInt(campo);
     modelo = new DefaultTableModel(null,titulos);   
  clientee = clientes();
  if(campo.equals("")){
      ClienteFilter = clientee.stream()//Obtiene el metodo y filtra
  .skip(num_registro).limit(reg_por_pag)//Omite los registros dependiedo al dato que venga por parametro 
    .collect(Collectors.toList());
              //El limit limita los registros a visualizar
              }else{
      ClienteFilter = clientee.stream()
              .filter(c -> c.getNombre().toLowerCase().startsWith(campo)
             ||c.getApellido().toLowerCase().startsWith(campo))
  .skip(num_registro).limit(reg_por_pag)//Omite los registros dependiedo al dato que venga por parametro 
    .collect(Collectors.toList());              
// startsWith: Busca informacion con respecto a lo que se ingresa
              //y lo almacena en el objeto
  }ClienteFilter.forEach(item-> {
  registros[0] = String.valueOf(item.getNit());
  registros[1] = item.getNombre();
  registros[2] = item.getApellido();
  registros[3] = item.getDireccion();
  registros[4] = item.getNumero();
  modelo.addRow(registros);
  });
  table.setModel(modelo);
  table.setRowHeight(27);
  coloc2.setText(String.valueOf(table.getRowCount()));
   }

    public DefaultTableModel getModelo() {
        return modelo;//Creado especialmente para devolver el modelo para coger datos de la tabla
    }
   public DefaultTableModel ReportesClientes(JTable table,int NIT){
       System.out.println(NIT);
        String[] registros = new String[7];  //Creacion tanto del modelo como de la maqueta tabla
     String[] titulos = {"Nombre","Apellido","Nit","Saldo_Actual","Fecha_Actual","Ultimo_Pago","Fecha_Pago"};
     modelo2 = new DefaultTableModel(null,titulos);
     List<reporte_clientes>rportes = reporteClientes(NIT);
     rportes.forEach(item-> {
  registros[0] = item.getNombre();
  registros[1] = item.getApellido();
  registros[2] = String.valueOf(item.getNitC());
  registros[3] = String.valueOf(item.getSaldo_Actual());
  registros[4] = String.valueOf(item.getFecha_Actual());
  registros[5] = String.valueOf(item.getUltimo_pago());
  registros[6] = String.valueOf(item.getFecha_pago());
  modelo2.addRow(registros);
  res=item.getSaldo_Actual();
        // System.out.println("devuelve numero: "+res);
  });
     table.setModel(modelo2);
  table.setRowHeight(27);
//  table.getColumnModel().getColumn(0).setMaxWidth(0);
//  table.getColumnModel().getColumn(0).setMinWidth(0);  Fragmento de codigo que sirve para ocultar columnas
//  table.getColumnModel().getColumn(0).setPreferredWidth(0);
  return modelo2;
   }
   public void UpdateCliente(int Nit, String nombre,String apellido, String direccion, String numero){
       sql = "UPDATE clientes  SET nombre=?,apellido=?,direccion=?,numero=? where nit = "+Nit;
       Object[] cli = new Object[]{nombre,apellido,direccion,numero};
       update(sql,cli);
//       List<reporte_clientes>rportes = reporteClientes(Nit);
//       int NitCc=rportes.get(0).getNitC();//Accedemos a los datos y los almacenamos
//       int saldo_actual=rportes.get(0).getSaldo_Actual();
//       Date fecha_actual=rportes.get(0).getFecha_Actual();
//       int ultimo_pago=rportes.get(0).getUltimo_pago();
//       Date fecha_pago=rportes.get(0).getFecha_pago();
//       int nit=Nit;
//      sql = "UPDATE reporte_clientes  saldo_actual=?,fecha_actual=?,ultimo_pago=?,fecha_pago=? where NitC = "+nit;
//      Object[] rp = new Object[]{saldo_actual,fecha_actual,ultimo_pago,fecha_pago};
//      update(sql,rp);
   }
   public void deleteClientes(int nit){
       sql = "DELETE FROM clientes WHERE nit = ?";
       delete(sql,nit);
   }
   public void UpdateDeuda(int Nit,int deuda){
       System.out.println("entra aca");
       sqldate = new java.sql.Date(fecha.getTime());
      sql = "UPDATE reporte_clientes  SET saldo_actual=?,fecha_actual=? where NitC = "+Nit;
      Object[] rp = new Object[]{deuda,sqldate};
      update(sql,rp);
   }
    public  void updatereportcliente(int deudaactual, int pago, int nit) {
         sqldate = new java.sql.Date(fecha.getTime());
        List<reporte_clientes>rportes = reporteClientes(nit);
  int NitCc=rportes.get(0).getNitC();
  int nn=rportes.get(0).getNit();
  sql = "UPDATE reporte_clientes  SET NitC=?,saldo_actual=?,fecha_actual=?,ultimo_pago=?,fecha_pago=? where NitC = "+nit;
  Object[] rp = new Object[]{nit,deudaactual,sqldate,pago,sqldate};
      update(sql,rp);
    }

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
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
 public DefaultTableModel getModelodos() {
        return modelo2;//Creado especialmente para devolver el modelo para coger datos de la tabla
    }
}
