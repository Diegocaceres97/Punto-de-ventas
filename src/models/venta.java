/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

/**
 *
 * @author Diego
 */
public class venta {

    public venta() {
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getNit_cliente() {
        return nit_cliente;
    }

    public void setNit_cliente(int nit_cliente) {
        this.nit_cliente = nit_cliente;
    }

    public String getId_producto() {
        return id_producto;
    }

    public void setId_producto(String id_producto) {
        this.id_producto = id_producto;
    }

    public int getTotal_cant() {
        return total_cant;
    }

    public void setTotal_cant(int total_cant) {
        this.total_cant = total_cant;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    private int id_venta;
    private int nit_cliente;
    private String id_producto;
    private int total_cant;
    private int total;
    private Date fecha;
}
