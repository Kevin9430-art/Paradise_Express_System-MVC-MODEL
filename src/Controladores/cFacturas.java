/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Modelos.Factura;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;

public class cFacturas {

    public static final String SEPARADOR = ";";
    public static final String QUOTE = "\"";
    public String path = Global.getPath() + "\\Data\\dataFacturas.csv";
    private static final String DATE_FORMAT = "dd/MM/yyyy";

    private ArrayList<Factura> listaFacturas = new ArrayList<>();
    private cCliente controladorClientes;

    public cFacturas(cCliente controladorClientes) {
        this.controladorClientes = controladorClientes;
    }

    private boolean isValidDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public int Cantidad() {
        return listaFacturas.size();
    }

    public void Limpiar() {
        listaFacturas.clear();
    }

    public Factura getFactura(int posicion) {
        if (posicion >= 0 && posicion < listaFacturas.size()) {
            return listaFacturas.get(posicion);
        }
        return null;
    }

    public int localizar(String numFactura) {
        for (int i = 0; i < Cantidad(); i++) {
            Factura ob = listaFacturas.get(i);
            if (numFactura.equals(ob.numFactura)) {
                return i;
            }
        }
        return -1;
    }

    public void nuevo(Factura f) throws IOException {
        int pos = localizar(f.numFactura);
        if (pos == -1) {
            if (controladorClientes.localizar(f.cedulaCliente) == -1) {
                throw new RuntimeException("** El cliente con cédula " + f.cedulaCliente + " no está registrado **");
            }
            if (!f.formaPago.equals("Efectivo") && !f.formaPago.equals("Transferencia")) {
                throw new RuntimeException("** Forma de pago inválida: debe ser 'Efectivo' o 'Transferencia' **");
            }
            if (!isValidDate(f.fechaEmision)) {
                throw new RuntimeException("** Fecha de emisión inválida: debe ser en formato dd/MM/yyyy, ej. 15/07/2025 **");
            }
            listaFacturas.add(f);
        } else {
            throw new RuntimeException("** La factura con número " + f.numFactura + " ya existe **");
        }
    }

    public void modificar(Factura f, String numFactura) throws IOException {
        int pos = localizar(numFactura);
        if (pos > -1) {
            if (controladorClientes.localizar(f.cedulaCliente) == -1) {
                throw new RuntimeException("** El cliente con cédula " + f.cedulaCliente + " no está registrado **");
            }
            if (!f.formaPago.equals("Efectivo") && !f.formaPago.equals("Transferencia")) {
                throw new RuntimeException("** Forma de pago inválida: debe ser 'Efectivo' o 'Transferencia' **");
            }
            if (!isValidDate(f.fechaEmision)) {
                throw new RuntimeException("** Fecha de emisión inválida: debe ser en formato dd/MM/yyyy, ej. 15/07/2025 **");
            }
            listaFacturas.set(pos, f);
        } else {
            throw new RuntimeException("** No existe factura con número: " + numFactura + " **");
        }
    }

    public void eliminar(String numFactura) throws IOException {
        int pos = localizar(numFactura);
        if (pos > -1) {
            listaFacturas.remove(pos);
        } else {
            throw new RuntimeException("** No existe factura con número: " + numFactura + " **");
        }
    }

    public DefaultTableModel getTabla() {
        String[] columnName = {"Nro Factura", "RUC", "Cedula Cliente", "Fecha Emision", "Total Factura", "Forma de Pago"};
        DefaultTableModel tabla = new DefaultTableModel(columnName, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (Factura f : listaFacturas) {
            Object[] row = {
                f.numFactura, f.RUC, f.cedulaCliente, f.fechaEmision,
                f.totalFactura, f.formaPago
            };
            tabla.addRow(row);
        }
        return tabla;
    }

    public cFacturas buscar_numFactura(String numFactura) throws IOException {
        cFacturas facturas = new cFacturas(controladorClientes);
        for (Factura f : listaFacturas) {
            if (f.numFactura.toLowerCase().contains(numFactura.toLowerCase())) {
                facturas.nuevo(f);
            }
        }
        return facturas;
    }

    public cFacturas buscar_RUC(String ruc) throws IOException {
        cFacturas facturas = new cFacturas(controladorClientes);
        for (Factura f : listaFacturas) {
            if (f.RUC.toLowerCase().contains(ruc.toLowerCase())) {
                facturas.nuevo(f);
            }
        }
        return facturas;
    }

    public cFacturas buscar_cedulaCliente(String cedula) throws IOException {
        cFacturas facturas = new cFacturas(controladorClientes);
        for (Factura f : listaFacturas) {
            if (f.cedulaCliente.toLowerCase().contains(cedula.toLowerCase())) {
                facturas.nuevo(f);
            }
        }
        return facturas;
    }

    public cFacturas buscar_formaPago(String formaPago) throws IOException {
        cFacturas facturas = new cFacturas(controladorClientes);
        for (Factura f : listaFacturas) {
            if (f.formaPago.toLowerCase().contains(formaPago.toLowerCase())) {
                facturas.nuevo(f);
            }
        }
        return facturas;
    }
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
                Factura f = new Factura();
                f.numFactura = row[0];
                f.RUC = row[1];
                f.cedulaCliente = row[2];
                if (controladorClientes.localizar(f.cedulaCliente) == -1) {
                    throw new RuntimeException("** El cliente con cédula " + f.cedulaCliente + " no está registrado en el archivo CSV **");
                }
                f.fechaEmision = row[3];
                if (!isValidDate(f.fechaEmision)) {
                    throw new RuntimeException("** Fecha de emisión inválida en el archivo CSV: " + f.fechaEmision + ", debe ser en formato dd/MM/yyyy **");
                }
                f.totalFactura = Double.parseDouble(row[4]);
                f.formaPago = row[5];
                nuevo(f);
                System.out.println(Arrays.toString(row));
                line = br.readLine();
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("** Error en la carga de datos del archivo **");
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

    public void guardar() throws IOException {
        FileWriter file = null;
        try {
            file = new FileWriter(path);
            final String NEXT_LINE = "\n";
            file.append("NumFactura").append(SEPARADOR)
                .append("RUC").append(SEPARADOR)
                .append("CedulaCliente").append(SEPARADOR)
                .append("FechaEmision").append(SEPARADOR)
                .append("TotalFactura").append(SEPARADOR)
                .append("FormaPago").append(NEXT_LINE);
            for (Factura f : listaFacturas) {
                file.append(f.numFactura).append(SEPARADOR)
                    .append(f.RUC).append(SEPARADOR)
                    .append(f.cedulaCliente).append(SEPARADOR)
                    .append(f.fechaEmision).append(SEPARADOR)
                    .append(String.valueOf(f.totalFactura)).append(SEPARADOR)
                    .append(f.formaPago).append(NEXT_LINE);
            }
            file.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("** Error al guardar archivo de facturas **");
        } finally {
            if (file != null) {
                file.close();
            }
        }
    }
}
