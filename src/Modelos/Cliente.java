/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author kevin, Kerly
 */
public class Cliente {

    public String cedula;
    public String nombre;
    public String apellido;
    public String celular;
    public String direccion;
    public String ciudad;
    public String email;

    // Constructor vacío
    public Cliente() {
    }

    // Constructor con parámetros
    public Cliente(String cedula, String nombre, String apellido, String celular, String direccion, String ciudad, String email) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.email = email;
    }

    // Método que devuelve los datos concatenados
    public String getCliente() {
        String datos = this.cedula + ";";
        datos += this.nombre + ";";
        datos += this.apellido + ";";
        datos += this.celular + ";";
        datos += this.direccion + ";";
        datos += this.ciudad + ";";
        datos += this.email + ";";
        return datos;
    }

}
