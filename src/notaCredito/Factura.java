/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notaCredito;

import java.util.Date;

/**
 *
 * @author User
 */
public class Factura {
     private int noFacturas;
    private int cliente;
    private Date fecha;
    private int total;
    private int nroBoleta;
    private String facturaBoleta;

    public Factura(int noFacturas, int cliente, Date fecha, int total, int nroBoleta, String facturaBoleta) {
        this.noFacturas = noFacturas;
        this.cliente = cliente;
        this.fecha = fecha;
        this.total = total;
        this.nroBoleta = nroBoleta;
        this.facturaBoleta = facturaBoleta;
    }
    
    
    

    // Constructor
    public Factura() {}

    public int getNoFacturas() {
        return noFacturas;
    }

    public void setNoFacturas(int noFacturas) {
        this.noFacturas = noFacturas;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getNroBoleta() {
        return nroBoleta;
    }

    public void setNroBoleta(int nroBoleta) {
        this.nroBoleta = nroBoleta;
    }

    public String getFacturaBoleta() {
        return facturaBoleta;
    }

    public void setFacturaBoleta(String facturaBoleta) {
        this.facturaBoleta = facturaBoleta;
    }
    
    
    public String obtenerMes(int numero) {
    String mes;
    switch (numero) {
        case 1:
            mes = "Enero";
            break;
        case 2:
            mes = "Febrero";
            break;
        case 3:
            mes = "Marzo";
            break;
        case 4:
            mes = "Abril";
            break;
        case 5:
            mes = "Mayo";
            break;
        case 6:
            mes = "Junio";
            break;
        case 7:
            mes = "Julio";
            break;
        case 8:
            mes = "Agosto";
            break;
        case 9:
            mes = "Septiembre";
            break;
        case 10:
            mes = "Octubre";
            break;
        case 11:
            mes = "Noviembre";
            break;
        case 12:
            mes = "diciembre";
            break;
        default:
            mes = "Número de mes inválido";
    }
    return mes;
}
 
    
}
