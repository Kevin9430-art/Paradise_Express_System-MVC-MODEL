/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author kevin
 */
public class Factura {

    public String numFactura;
    public String RUC;
    public String cedulaCliente;
    public String fechaEmision;
    public double totalFactura;
    public String formaPago; // "Efectivo" o "Transferencia"

    public Factura() {
    }//Constructor vacio

    public Factura(String numFactura, String RUC, String cedulaCliente, String fechaEmision, double totalFactura, String formaPago) {
        this.numFactura = numFactura;
        this.RUC = RUC;
        this.cedulaCliente = cedulaCliente;
        this.fechaEmision = fechaEmision;
        this.totalFactura = totalFactura;
        this.formaPago = formaPago;
    }

    public String getFactura() {
        String datos = this.numFactura + ";";
        datos += this.RUC + ";";
        datos += this.cedulaCliente + ";";
        datos += this.fechaEmision + ";";
        datos += this.totalFactura + ";";
        datos += this.formaPago + ";";
        return datos;
    }
}
