/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Diego
 */
public class Clientes {

//    public Clientes(int nit, String nombre, String apellido, String direccion, String numero_tel) {
//        this.nit = nit;
//        this.nombre = nombre;
//        this.apellido = apellido;
//        this.direccion = direccion;
//        this.numero_tel = numero_tel;
//    }
    public Clientes() {
    }
    private int nit;
    private String nombre;
    private String apellido;
    private String direccion;
    private String numero;
 
 
    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
   
   
}
