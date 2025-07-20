/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Modelos.Login;

/**
 *
 * @author kevin
 */
public class cLogin {

    public boolean validar(String claveIngresada, String rolSeleccionado) {
        if (rolSeleccionado.equals("GERENTE") && claveIngresada.equals("Emperador1996#")) {
            return true;
        }
        if (rolSeleccionado.equals("VENDEDOR") && claveIngresada.equals("Vendor555")) {
            return true;
        }
        return false;
    }
}
