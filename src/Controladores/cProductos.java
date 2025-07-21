/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Modelos.Productos;// importamos la clase Productos que esta en el paquete modelos
import java.util.ArrayList;
import java.io.*;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kevin
 */
public class cProductos {

    public ArrayList<Productos> listaProductos = new ArrayList();// coleccion o arreglo dinamico de objetos.
//devuelve la cantidad de objetos de la lista

    public int Cantidad() {
        return listaProductos.size();
    }
//borrar los objetos del arreglo

    public void Limpiar() {
        listaProductos.clear();
    }

    //Realiza una busqueda secuencial del campo clave entonces codigo del producto
    public int localizar(String codigo_prod) {
        int pos = -1; //se retorna -1 si no se encuentra en el arreglo
        for (int i = 0; i < Cantidad(); i++) {
            Productos ob = listaProductos.get(i);
            if (codigo_prod.equals(ob.Codigo)) {// REVISAR
                pos = i; //posicion encontrada
                break; //finaliza el ciclo for
            }
        }
        return pos;
    }

    public void nuevo(Productos p) throws IOException {
        int pos = localizar(p.Codigo);
        if (pos == -1) {//si el producto no esta registrado, entonces se agrega un nuevo Producto
            listaProductos.add(p);
        } else {
            throw new RuntimeException("Producto ya agregado previamente." + p.Codigo);
        }
    }

    public void modificar(Productos p, String codigo_prod) throws IOException {
        int pos = localizar(codigo_prod);
        if (pos > -1) {//si producto esta registrado se modifica
            listaProductos.set(pos, p);
        } else {
            throw new RuntimeException("**No existe un producto  registrado con el codigo ingresado**" + codigo_prod);
        }
    }

    public void eliminar(String codigo_prod) throws IOException {
        int pos = localizar(codigo_prod);
        if (pos > -1) {//si encontro el objeto entonces  entra al if de condicion y elimina el objeto de la listaProductos
            listaProductos.remove(pos);
        } else {
            throw new RuntimeException("**No existe un producto  registrado con el codigo ingresado" + codigo_prod + "**");
        }
    }

    public DefaultTableModel getTabla() {
        //encabezados de columnas de la tabla
        String[] columnName = {"Codigo", "Nombre del Producto", "Categoria", "Marca", "Precio Unitario", "Stock", "Fecha Caducidad"};
        DefaultTableModel tabla = new DefaultTableModel(columnName, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {// hace que celda no sea editable
                return false;
            }
        };
        for (int i = 0; i < Cantidad(); i++) {
            Productos p = listaProductos.get(i);//retorna una posicion del arreglo segun i.
            Object[] row = {
                p.Codigo, p.Nombre, p.Categoria, p.Marca,
                p.Precio, p.Stock, p.Fecha_caducidad};
            tabla.addRow(row);
        }
        return tabla;
    }

    //busqueda parcial por codigo
    public cProductos buscar_codigo_prod(String codigo_prod) throws IOException {
        cProductos p = new cProductos();
        for (int i = 0; i < Cantidad(); i++) {
            Productos z = getProducto(i);
            if (z.Codigo.toLowerCase().contains(codigo_prod.toLowerCase())) {
                p.nuevo(z);
            }
        }
        return p;
    }
    //busqueda parcial por nombre del producto

    public cProductos buscar_nombre_prod(String nombre_prod) throws IOException {
        cProductos p = new cProductos();
        for (int i = 0; i < Cantidad(); i++) {
            Productos z = getProducto(i);
            if (z.Nombre.toLowerCase().contains(nombre_prod.toLowerCase())) {
                p.nuevo(z);
            }
        }
        return p;

    }

    public cProductos buscar_marca(String marca_prod) throws IOException {
        cProductos p = new cProductos();
        for (int i = 0; i < Cantidad(); i++) {
            Productos z = getProducto(i);
            if (z.Marca.toLowerCase().contains(marca_prod.toLowerCase())) {
                p.nuevo(z);
            }
        }
        return p;
    }

    public cProductos buscar_categoria(String categoria_prod) throws IOException {
        cProductos p = new cProductos();
        for (int i = 0; i < Cantidad(); i++) {
            Productos z = getProducto(i);
            if (z.Categoria.toLowerCase().contains(categoria_prod.toLowerCase())) {
                p.nuevo(z);
            }
        }
        return p;
    }

    public Productos getProducto(int posicion) {
        Productos p = null;
        if (posicion >= 0 && posicion < listaProductos.size()) {
            p = listaProductos.get(posicion);
        }
        return p;
    }
    //trabajamos con archivos CSV separados por coma o ;
    public static final String SEPARADOR = ";";
    public static final String QUOTE = "\"";
    //nombre del archivo csv
    public String path = Global.getPath() + "\\Data\\dataProductos.csv";//ruta
    //El metodo leer lee los datos del archivo

    public void leer() throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(path));
            System.out.println("Datos del archivo: ");
            String line = br.readLine();
            System.out.println(line);
            Limpiar(); //limpiar lista de objetos del arreglo
            line = br.readLine();
            while (line != null) {
                String[] row = line.split(SEPARADOR);
                removeTrailingQuotes(row);
                Productos p = new Productos();
                p.Codigo = row[0];
                p.Nombre = row[1];
                p.Categoria = row[2];
                p.Marca = row[3];
                p.Precio = Double.parseDouble(row[4]);// conversion a double
                p.Stock = Double.parseDouble(row[5]);// conversion a int
                p.Fecha_caducidad = row[6];
                nuevo(p);//agregar a la lista	           
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

    //eliminar comillas
    private static String[] removeTrailingQuotes(String[] fields) {
        String result[] = new String[fields.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = fields[i].replaceAll("^" + QUOTE, "").replaceAll(QUOTE + "$", "");
        }
        return result;
    }
// metodo que guarda el arreglo en un archivo CSV

    public void guardar() throws IOException {
        FileWriter file;
        try {
            file = new FileWriter(path);
            final String NEXT_LINE = "\n";
            file.append("Codigo").append(SEPARADOR);
            file.append("Nombre").append(SEPARADOR);
            file.append("Categoria").append(SEPARADOR);
            file.append("Marca").append(SEPARADOR);
            file.append("Precio").append(SEPARADOR);
            file.append("Stock").append(SEPARADOR);
            file.append("Fecha_caducidad").append(NEXT_LINE);

            for (int i = 0; i < Cantidad(); i++) {
                Productos p = (Productos) listaProductos.get(i);
                file.append(p.Codigo).append(SEPARADOR);
                file.append(p.Nombre).append(SEPARADOR);
                file.append(p.Categoria).append(SEPARADOR);
                file.append(p.Marca).append(SEPARADOR);
                file.append(p.Precio + "").append(SEPARADOR);
                file.append(p.Stock + "").append(SEPARADOR);
                file.append(p.Fecha_caducidad).append(NEXT_LINE);
            }
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.print(e.getMessage());
            throw new RuntimeException("**Error al guardar datos** ");
        }
    }

    public ArrayList<String> obtenerProductosFormatoCombo() {
        ArrayList<String> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linea;
            br.readLine(); // Saltar encabezado
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length >= 2) {
                    String Codigo = datos[1].trim();
                    lista.add(Codigo);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar productos para el combo: " + e.getMessage());
        }
        return lista;
    }
}
