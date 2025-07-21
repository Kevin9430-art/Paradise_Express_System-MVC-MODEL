/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author mjair
 */
public class DetalleFactura {

    public String numFactura;        // Relaciona con Factura
    public String codigoProducto;
    public String nombreProducto;
    public int cantidad;
    public double precioUnitario;
    public double subtotal;

    public DetalleFactura() {
    } // Constructor vacío

    public DetalleFactura(String numFactura, String codigoProducto, String nombreProducto, int cantidad, double precioUnitario) {
        this.numFactura = numFactura;
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = cantidad * precioUnitario;
    }

    public String getDetalle() {
        String datos = this.numFactura + ";";
        datos += this.codigoProducto + ";";
        datos += this.nombreProducto + ";";
        datos += this.cantidad + ";";
        datos += this.precioUnitario + ";";
        datos += this.subtotal + ";";
        return datos;
    }
}