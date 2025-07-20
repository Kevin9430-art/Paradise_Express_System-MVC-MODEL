/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import Modelos.Cliente;
import Controladores.cCliente;
import java.io.IOException;

public class testCliente {

    public static void main(String[] args) {
        cCliente lista = new cCliente();

        try {
            Cliente c1 = new Cliente("1723456789", "Kevin", "Flores", "0999999999", "Av. Siempre Viva", "Quito", "kevin@mail.com");
            Cliente c2 = new Cliente("1723456790", "Kerly", "Zambrano", "0988888888", "Calle Falsa 123", "Guayaquil", "kerly@mail.com");
            Cliente c3 = new Cliente("1723456791", "Carlos", "Perez", "0977777777", "Av. Principal", "Cuenca", "carlos@mail.com");

            lista.nuevo(c1);
            lista.nuevo(c2);
            lista.nuevo(c3);
            lista.guardar();
            lista.leer();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
