/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Modelos.DetalleFactura;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mjair
 */
public class cDetalleFactura {

    public static final String SEPARADOR = ";";
    public static final String QUOTE = "\"";
    public String path = Global.getPath() + "\\Data\\dataDetalleFacturas.csv";

    private ArrayList<DetalleFactura> listaDetalles = new ArrayList<>();

    public int Cantidad() {
        return listaDetalles.size();
    }

    public void Limpiar() {
        listaDetalles.clear();
    }

    public DetalleFactura getDetalle(int posicion) {
        if (posicion >= 0 && posicion < listaDetalles.size()) {
            return listaDetalles.get(posicion);
        }
        return null;
    }

    public int localizar(String numFactura, String codigoProducto) {
        for (int i = 0; i < Cantidad(); i++) {
            DetalleFactura d = listaDetalles.get(i);
            if (d.numFactura.equals(numFactura) && d.codigoProducto.equals(codigoProducto)) {
                return i;
            }
        }
        return -1;
    }

    public void nuevo(DetalleFactura d) throws IOException {
        int pos = localizar(d.numFactura, d.codigoProducto);
        if (pos == -1) {
            listaDetalles.add(d);
        } else {
            throw new RuntimeException("* Ya existe un detalle con ese producto en esta factura *");
        }
    }

    public void modificar(DetalleFactura d, String numFactura, String codigoProducto) throws IOException {
        int pos = localizar(numFactura, codigoProducto);
        if (pos > -1) {
            listaDetalles.set(pos, d);
        } else {
            throw new RuntimeException("* No existe detalle con ese producto en esta factura *");
        }
    }

    public void eliminar(String numFactura, String codigoProducto) throws IOException {
        int pos = localizar(numFactura, codigoProducto);
        if (pos > -1) {
            listaDetalles.remove(pos);
        } else {
            throw new RuntimeException("* No existe detalle con ese producto en esta factura *");
        }
    }

    public DefaultTableModel getTabla() {
        String[] columnName = {"Nro Factura", "Cod Producto", "Nombre Producto", "Cantidad", "Precio Unitario", "Subtotal"};
        DefaultTableModel tabla = new DefaultTableModel(columnName, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (DetalleFactura d : listaDetalles) {
            Object[] row = {
                d.numFactura, d.codigoProducto, d.nombreProducto,
                d.cantidad, d.precioUnitario, d.subtotal
            };
            tabla.addRow(row);
        }
        return tabla;
    }

    public ArrayList<DetalleFactura> obtenerPorFactura(String numFactura) {
        ArrayList<DetalleFactura> detalles = new ArrayList<>();
        for (DetalleFactura d : listaDetalles) {
            if (d.numFactura.equals(numFactura)) {
                detalles.add(d);
            }
        }
        return detalles;
    }

    public double calcularTotalFactura(String numFactura) {
        double total = 0;
        for (DetalleFactura d : listaDetalles) {
            if (d.numFactura.equals(numFactura)) {
                total += d.subtotal;
            }
        }
        return total;
    }

    public void leer() throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(path));
            String line = br.readLine(); // encabezado
            Limpiar();
            line = br.readLine();
            while (line != null) {
                String[] row = line.split(SEPARADOR);
                removeTrailingQuotes(row);
                DetalleFactura d = new DetalleFactura();
                d.numFactura = row[0];
                d.codigoProducto = row[1];
                d.nombreProducto = row[2];
                d.cantidad = Integer.parseInt(row[3]);
                d.precioUnitario = Double.parseDouble(row[4]);
                d.subtotal = Double.parseDouble(row[5]);
                listaDetalles.add(d);
                line = br.readLine();
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("* Error en la carga de detalles desde archivo *");
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    private static String[] removeTrailingQuotes(String[] fields) {
        String[] result = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            result[i] = fields[i].replaceAll("^" + QUOTE, "").replaceAll(QUOTE + "$", "");
        }
        return result;
    }
// Calcula solo el subtotal sin IVA

    public double calcularSubtotal(String numFactura) {
        double subtotal = 0;
        for (DetalleFactura d : listaDetalles) {
            if (d.numFactura.equals(numFactura)) {
                subtotal += d.subtotal;
            }
        }
        return subtotal;
    }

// Calcula el IVA (15% del subtotal)
    public double calcularIVA(String numFactura) {
        double subtotal = calcularSubtotal(numFactura);
        return subtotal * 0.15; // 15% IVA
    }

// Calcula el total final (subtotal + IVA)
    public double calcularTotalConIVA(String numFactura) {
        double subtotal = calcularSubtotal(numFactura);
        double iva = subtotal * 0.15;
        return subtotal + iva;
    }

    public void guardar() throws IOException {
        FileWriter file = null;
        try {
            file = new FileWriter(path);
            final String NEXT_LINE = "\n";
            file.append("NumFactura").append(SEPARADOR)
                    .append("CodProducto").append(SEPARADOR)
                    .append("NombreProducto").append(SEPARADOR)
                    .append("Cantidad").append(SEPARADOR)
                    .append("PrecioUnitario").append(SEPARADOR)
                    .append("Subtotal").append(NEXT_LINE);
            for (DetalleFactura d : listaDetalles) {
                file.append(d.numFactura).append(SEPARADOR)
                        .append(d.codigoProducto).append(SEPARADOR)
                        .append(d.nombreProducto).append(SEPARADOR)
                        .append(String.valueOf(d.cantidad)).append(SEPARADOR)
                        .append(String.valueOf(d.precioUnitario)).append(SEPARADOR)
                        .append(String.valueOf(d.subtotal)).append(NEXT_LINE);
            }
            file.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("* Error al guardar archivo de detalles *");
        } finally {
            if (file != null) {
                file.close();
            }
        }
    }
}
