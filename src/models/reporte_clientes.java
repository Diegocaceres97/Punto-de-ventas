/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;

/**
 *
 * @author Diego
 */
public class reporte_clientes extends Clientes {

    public int getNitC() {
        return NitC;
    }

    public void setNitC(int NitC) {
        this.NitC = NitC;
    }

    public int getSaldo_Actual() {
        return saldo_Actual;
    }

    public void setSaldo_Actual(int saldo_Actual) {
        this.saldo_Actual = saldo_Actual;
    }

    public Date getFecha_Actual() {
        return fecha_Actual;
    }

    public void setFecha_Actual(Date fecha_Actual) {
        this.fecha_Actual = fecha_Actual;
    }

    public int getUltimo_pago() {
        return ultimo_pago;
    }

    public void setUltimo_pago(int ultimo_pago) {
        this.ultimo_pago = ultimo_pago;
    }

    public Date getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(Date fecha_pago) {
        this.fecha_pago = fecha_pago;
    }
    private int NitC;
    private int saldo_Actual;
    private Date fecha_Actual;
    private int ultimo_pago;
    private Date fecha_pago;
}
