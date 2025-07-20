/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import Modelos.Factura;
import Controladores.cCliente;
import Controladores.cFacturas;
import java.io.IOException;

public class testFactura {

    public static void main(String[] args) {
        // Primero creamos el controlador de clientes para pasarle a cFacturas
        cCliente controladorClientes = new cCliente();

        try {
            // Agregamos algunos clientes que serán referenciados en las facturas
            controladorClientes.nuevo(new Modelos.Cliente("1723456789", "Kevin", "Flores", "0999999999", "Av. Siempre Viva", "Quito", "kevin@mail.com"));
            controladorClientes.nuevo(new Modelos.Cliente("1723456790", "Kerly", "Zambrano", "0988888888", "Calle Falsa 123", "Guayaquil", "kerly@mail.com"));

            // Creamos el controlador de facturas con el controlador de clientes
            cFacturas controladorFacturas = new cFacturas(controladorClientes);

            // Creamos algunas facturas
            Factura f1 = new Factura("F001", "0998765432001", "1723456789", "15/07/2025", 150.50, "Efectivo");
            Factura f2 = new Factura("F002", "0998765432002", "1723456790", "16/07/2025", 250.00, "Transferencia");

            // Agregamos las facturas
            controladorFacturas.nuevo(f1);
            controladorFacturas.nuevo(f2);

            // Guardamos las facturas en archivo CSV
            controladorFacturas.guardar();

            // Leemos para verificar que todo está bien
            controladorFacturas.leer();

            System.out.println("Cantidad de facturas cargadas: " + controladorFacturas.Cantidad());

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Error de lógica: " + e.getMessage());
        }
    }
}