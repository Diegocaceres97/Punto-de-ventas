/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.awt.Color;
import java.awt.Component;
import java.sql.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Diego
 */
public class ClassRender extends DefaultTableCellRenderer {
DefaultTableModel tablamodeloclt;
//movimiento c=new movimiento();
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//tablamodeloclt = c.getModelo();
       //int filas = table.getSelectedRow();        
        if(Double.parseDouble((String)table.getValueAt(row, 4))==0){
         setBackground(Color.RED);
           setForeground(Color.BLACK);
       }else{
           setBackground(Color.WHITE);
           setForeground(Color.BLACK);
           
       }
        
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
    }
    
}
