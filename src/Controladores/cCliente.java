/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Modelos.Cliente;
import java.util.ArrayList;
import java.io.*;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kevin
 */
public class cCliente {

    private ArrayList<Cliente> listaClientes = new ArrayList<>();

    public int Cantidad() {
        return listaClientes.size();
    }

    public void Limpiar() {
        listaClientes.clear();
    }

    public int localizar(String cedula) {
        int pos = -1;
        for (int i = 0; i < Cantidad(); i++) {
            Cliente c = listaClientes.get(i);
            if (cedula.equals(c.cedula)) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    public void nuevo(Cliente c) throws IOException {
        int pos = localizar(c.cedula);
        if (pos == -1) {
            listaClientes.add(c);
        } else {
            throw new RuntimeException("Cliente ya registrado con cédula: " + c.cedula);
        }
    }

    public void modificar(Cliente c, String cedula) throws IOException {
        int pos = localizar(cedula);
        if (pos > -1) {
            listaClientes.set(pos, c);
        } else {
            throw new RuntimeException("**No existe cliente con cédula: " + cedula + "**");
        }
    }

    public void eliminar(String cedula) throws IOException {
        int pos = localizar(cedula);
        if (pos > -1) {
            listaClientes.remove(pos);
        } else {
            throw new RuntimeException("**No existe cliente con cédula: " + cedula + "**");
        }
    }

    public Cliente getCliente(int posicion) {
        Cliente c = null;
        if (posicion >= 0 && posicion < listaClientes.size()) {
            c = listaClientes.get(posicion);
        }
        return c;
    }

    public ArrayList<Cliente> buscarClientes(String texto) {
        ArrayList<Cliente> resultado = new ArrayList<>();
        texto = texto.toLowerCase();

        for (Cliente c : listaClientes) {
            if (c.cedula.toLowerCase().contains(texto)
                    || c.nombre.toLowerCase().contains(texto)
                    || c.apellido.toLowerCase().contains(texto)
                    || c.celular.toLowerCase().contains(texto)
                    || c.direccion.toLowerCase().contains(texto)
                    || c.ciudad.toLowerCase().contains(texto)
                    || c.email.toLowerCase().contains(texto)) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    public DefaultTableModel getTabla() {
        String[] columnName = {"Cédula", "Nombre", "Apellido", "Celular", "Dirección", "Ciudad", "Email"};
        DefaultTableModel tabla = new DefaultTableModel(columnName, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (int i = 0; i < Cantidad(); i++) {
            Cliente c = listaClientes.get(i);
            Object[] row = {
                c.cedula, c.nombre, c.apellido, c.celular, c.direccion, c.ciudad, c.email
            };
            tabla.addRow(row);
        }
        return tabla;
    }

    // Búsquedas parciales
    public cCliente buscar_cedula(String cedula) throws IOException {
        cCliente resultado = new cCliente();
        for (int i = 0; i < Cantidad(); i++) {
            Cliente c = getCliente(i);
            if (c.cedula.toLowerCase().contains(cedula.toLowerCase())) {
                resultado.nuevo(c);
            }
        }
        return resultado;
    }

    public cCliente buscar_nombre(String nombre) throws IOException {
        cCliente resultado = new cCliente();
        for (int i = 0; i < Cantidad(); i++) {
            Cliente c = getCliente(i);
            if (c.nombre.toLowerCase().contains(nombre.toLowerCase())) {
                resultado.nuevo(c);
            }
        }
        return resultado;
    }

    public cCliente buscar_ciudad(String ciudad) throws IOException {
        cCliente resultado = new cCliente();
        for (int i = 0; i < Cantidad(); i++) {
            Cliente c = getCliente(i);
            if (c.ciudad.toLowerCase().contains(ciudad.toLowerCase())) {
                resultado.nuevo(c);
            }
        }
        return resultado;
    }

    // Archivo CSV
    public static final String SEPARADOR = ";";
    public static final String QUOTE = "\"";
    public String path = Global.getPath() + "\\Data\\dataClientes.csv";

    public void leer() throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(path));
            System.out.println("Datos del archivo:");
            String line = br.readLine();
            System.out.println(line);
            Limpiar();
            line = br.readLine();
            while (line != null) {
                String[] row = line.split(SEPARADOR);
                removeTrailingQuotes(row);
                Cliente c = new Cliente();
                c.cedula = row[0];
                c.nombre = row[1];
                c.apellido = row[2];
                c.celular = row[3];
                c.direccion = row[4];
                c.ciudad = row[5];
                c.email = row[6];
                nuevo(c);
                System.out.println(Arrays.toString(row));
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.print(e.getMessage());
            throw new RuntimeException("**Error en la carga de datos del archivo**");
        } finally {
            if (null != br) {
                br.close();
            }
        }
    }

    private static String[] removeTrailingQuotes(String[] fields) {
        String[] result = new String[fields.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = fields[i].replaceAll("^" + QUOTE, "").replaceAll(QUOTE + "$", "");
        }
        return result;
    }

    public void guardar() throws IOException {
        FileWriter file;
        try {
            file = new FileWriter(path);
            final String NEXT_LINE = "\n";
            file.append("Cedula").append(SEPARADOR);
            file.append("Nombre").append(SEPARADOR);
            file.append("Apellido").append(SEPARADOR);
            file.append("Celular").append(SEPARADOR);
            file.append("Direccion").append(SEPARADOR);
            file.append("Ciudad").append(SEPARADOR);
            file.append("Email").append(NEXT_LINE);

            for (int i = 0; i < Cantidad(); i++) {
                Cliente c = listaClientes.get(i);
                file.append(c.cedula).append(SEPARADOR);
                file.append(c.nombre).append(SEPARADOR);
                file.append(c.apellido).append(SEPARADOR);
                file.append(c.celular).append(SEPARADOR);
                file.append(c.direccion).append(SEPARADOR);
                file.append(c.ciudad).append(SEPARADOR);
                file.append(c.email).append(NEXT_LINE);
            }
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.print(e.getMessage());
            throw new RuntimeException("**Error al guardar datos del cliente**");
        }
    }

}
