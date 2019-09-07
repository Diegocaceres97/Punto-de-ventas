/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;


import java.sql.Connection;
import java.sql.DriverManager;

public class conexion{
    
    
    
    public Connection conexion(){
        Connection conectar=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conectar=DriverManager.getConnection("jdbc:mysql://localhost/bd_business?zeroDateTimeBehavior=convertToNull","root","");
            if(conectar != null){
                System.err.println("Conexion exitosa");
            }           
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return conectar;
    }
    
}
