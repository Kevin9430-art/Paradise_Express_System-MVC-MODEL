/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author kevin, kerly
 */
public class Productos {

    public String Codigo;
    public String Nombre;
    public String Categoria;
    public String Marca;
    public double Precio;
    public double Stock;
    public String Fecha_caducidad;

    public Productos() {
    }//constructor vacio

    public Productos(String codigo, String nombre, String categoria, String marca, Double precio, Double stock, String fecha_cad) {
        this.Codigo = codigo;
        this.Nombre = nombre;
        this.Categoria = categoria;
        this.Marca = marca;
        this.Precio = precio;
        this.Stock = stock;
        this.Fecha_caducidad = fecha_cad;
    }
//getters y setters

    public String getProducto() {
        String datos = this.Nombre + ";";
        datos += this.Marca + ";";
        datos += this.Codigo + ";";
        datos += this.Precio + ";";
        datos += this.Categoria + ";";
        datos += this.Stock + ";";
        return datos;
    }
}
