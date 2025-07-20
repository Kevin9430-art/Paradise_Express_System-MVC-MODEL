/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author kevin,Kerly
 */
public class Login {

    public String Clave;
    public String Rol;//GERENTE, VENDEDOR
    
    public Login() {
    }
    public Login(String clave, String rol) {
        this.Clave = clave;
        this.Rol = rol;
    }
    public String getLogin(){
         String datos = this.Clave + ";";
        datos += this.Rol + ";";
        return datos;
    }
}
