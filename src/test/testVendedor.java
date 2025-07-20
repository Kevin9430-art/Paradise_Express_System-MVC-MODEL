/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import Modelos.Vendedor;
import Controladores.cVendedor;
import java.io.IOException;

public class testVendedor {

    public static void main(String[] args) {
        cVendedor lista = new cVendedor();

        try {
            Vendedor v1 = new Vendedor("1754321001", "Kevin", "Zapata", "0991112222", "Quito");
            Vendedor v2 = new Vendedor("1754321002", "Andrea", "Morales", "0993334444", "Guayaquil");
            Vendedor v3 = new Vendedor("1754321003", "Luis", "Carvajal", "0995556666", "Cuenca");

            lista.nuevo(v1);
            lista.nuevo(v2);
            lista.nuevo(v3);

            lista.guardar();
            lista.leer();

            System.out.println("Cantidad de vendedores cargados: " + lista.Cantidad());

        } catch (IOException e) {
            System.out.println("Error de archivo: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Error lógico: " + e.getMessage());
        }
    }
}
