/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import Modelos.Productos;
import Controladores.cProductos;
import java.io.IOException;

/**
 *
 * @author kevin
 */
public class testProducto {

    public static void main(String[] args) {
        Productos p = new Productos("PROD001", "Bolos de fabrica", "Alimentos", "Explosion de Sabores", 1.0, 100.0, "2025/12/05");
        Productos p1 = new Productos("PROD002", "Detergente", "Limpieza", "Paradise Express", 1.0, 100.0, "2025/12/25");
        Productos p2 = new Productos("PROD003", "Suavizante de ropa", "Aseo Personal", "Paradise Express", 8.0, 60.0, "2025/12/26");
        Productos p3 = new Productos("PROD004", "Cloro", "Limpieza", "Paradise Express", 5.0, 50.0, "2025/12/31");
        cProductos Lista = new cProductos();// instancia creada de la clase cProducto (objeto Lista)
        try {
            Lista.nuevo(p);
            Lista.nuevo(p1);
            Lista.nuevo(p2);
            Lista.nuevo(p3);
            Lista.guardar();// guarda en el archivo CSV
            Lista.leer();//Lee lo que guardo en el archivo
            System.out.println("Cantidad de productos cargados: " + Lista.Cantidad());
        } catch (Exception ex) {
        }
    }
}
