/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

/**
 *
 * @author Kevin
 */
import Modelos.Vendedor;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;

public class cVendedor {

    public static final String SEPARADOR = ";";
    public static final String QUOTE = "\"";
    public String path = Global.getPath() + "\\Data\\dataVendedores.csv";

    private ArrayList<Vendedor> listaVendedores = new ArrayList<>();

    public int Cantidad() {
        return listaVendedores.size();
    }

    public void Limpiar() {
        listaVendedores.clear();
    }

    public Vendedor getVendedor(int posicion) {
        Vendedor v = null;
        if (posicion >= 0 && posicion < listaVendedores.size()) {
            v = listaVendedores.get(posicion);
        }
        return v;
    }

    public int localizar(String cedulav) {
        int pos = -1; //se retorna -1 si no se encuentra en el arreglo
        for (int i = 0; i < Cantidad(); i++) {
            Vendedor ob = listaVendedores.get(i);
            if (cedulav.equals(ob.cedulav)) {// REVISAR
                pos = i; //posicion encontrada
                break; //finaliza el ciclo for
            }
        }
        return pos;
    }

    public void nuevo(Vendedor v) throws IOException {
        int pos = localizar(v.cedulav);
        if (pos == -1) {
            listaVendedores.add(v);
        } else {
            throw new RuntimeException("** Vendedor ya registrado con cédula: " + v.cedulav + " **");
        }
    }

    public void modificar(Vendedor v, String cedula) throws IOException {
        int pos = localizar(cedula);
        if (pos > -1) {
            listaVendedores.set(pos, v);
        } else {
            throw new RuntimeException("** No existe vendedor con cédula: " + cedula + " **");
        }
    }

    public void eliminar(String cedula) throws IOException {
        int pos = localizar(cedula);
        if (pos > -1) {
            listaVendedores.remove(pos);
        } else {
            throw new RuntimeException("** No existe vendedor con cédula: " + cedula + " **");
        }
    }

    public DefaultTableModel getTabla() {
        String[] columnName = {"Cédula", "Nombre", "Apellido", "Celular", "Ciudad"};
        DefaultTableModel tabla = new DefaultTableModel(columnName, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (int i = 0; i < Cantidad(); i++) {
            Vendedor v = listaVendedores.get(i);
            Object[] row = {
                v.cedulav, v.nombrev, v.apellidov,
                v.celularv, v.ciudadv
            };
            tabla.addRow(row);
        }
        return tabla;
    }

    public void leer() throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(path));
            System.out.println("Datos del archivo de vendedores:");
            String line = br.readLine(); // encabezado
            Limpiar();
            line = br.readLine();
            while (line != null) {
                String[] row = line.split(SEPARADOR);
                removeTrailingQuotes(row);
                Vendedor v = new Vendedor(
                        row[0], row[1], row[2], row[3], row[4]
                );
                nuevo(v);
                System.out.println(Arrays.toString(row));
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("** Error al leer archivo de vendedores **");
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    private static String[] removeTrailingQuotes(String[] fields) {
        String result[] = new String[fields.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = fields[i].replaceAll("^" + QUOTE, "").replaceAll(QUOTE + "$", "");
        }
        return result;
    }
// Buscar por nombre (parcial o completo)

    public cVendedor buscar_nombre(String nombre) throws IOException {
        cVendedor resultado = new cVendedor();
        for (int i = 0; i < Cantidad(); i++) {
            Vendedor v = getVendedor(i);
            if (v.nombrev.toLowerCase().contains(nombre.toLowerCase())) {
                resultado.nuevo(v);
            }
        }
        return resultado;
    }
public String obtenerVendedorPorNombre(String nombreBuscado, String apellidoBuscado) {
    String nombreCompleto = "";
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
        String linea;
        br.readLine(); // Saltar encabezado
        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(";");
            String nombre = datos[1].trim();
            String apellido = datos[2].trim();
            if (nombre.equalsIgnoreCase(nombreBuscado) && apellido.equalsIgnoreCase(apellidoBuscado)) {
                nombreCompleto = nombre + " " + apellido;
                break;
            }
        }
    } catch (IOException e) {
        System.out.println("Error al leer archivo de vendedores: " + e.getMessage());
    }
    return nombreCompleto;
}

// Buscar por cédula (parcial o completa)
    public cVendedor buscar_cedula(String cedula) throws IOException {
        cVendedor resultado = new cVendedor();
        cedula = cedula.toLowerCase();

        for (int i = 0; i < Cantidad(); i++) {
            Vendedor v = getVendedor(i);
            if (v.cedulav.toLowerCase().contains(cedula)) {
                resultado.nuevo(v);
            }
        }
        return resultado;
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
            file.append("Ciudad").append(NEXT_LINE);

            for (int i = 0; i < Cantidad(); i++) {
                Vendedor v = listaVendedores.get(i);
                file.append(v.cedulav).append(SEPARADOR);
                file.append(v.nombrev).append(SEPARADOR);
                file.append(v.apellidov).append(SEPARADOR);
                file.append(v.celularv).append(SEPARADOR);
                file.append(v.ciudadv).append(NEXT_LINE);
            }

            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("** Error al guardar archivo de vendedores **");
        }
    }
}
