/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author mjair
 */
public class Vendedor {

    public String cedulav;
    public String nombrev;
    public String apellidov;
    public String celularv;
    public String ciudadv;

    public Vendedor() {
    }

    public Vendedor(String cedulav, String nombrev, String apellidov, String celularv, String ciudadv) {
        this.cedulav = cedulav;
        this.nombrev = nombrev;
        this.apellidov = apellidov;
        this.celularv = celularv;
        this.ciudadv = ciudadv;
    }

    public String getVendedor() {
        String datos = this.cedulav + ";";
        datos += this.nombrev + ";";
        datos += this.apellidov + ";";
        datos += this.celularv + ";";
        datos += this.ciudadv + ";";
        return datos;
    }

}
