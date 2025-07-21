/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vistas;

import Controladores.cCliente;
import Controladores.cProductos;
import Controladores.cVendedor;
import Modelos.DetalleFactura;
import Modelos.Productos;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.nio.file.Paths;

/**
 *
 * @author mjair
 */
public class panelFactura extends javax.swing.JPanel {

    /**
     * Creates new form panelFactura
     */
    public panelFactura() {
        initComponents();

        jComboBoxFormaPago.addItem("Efectivo");
        jComboBoxFormaPago.addItem("Transferencia");
        jComboBoxFormaPago.addItem("Tarjeta");

        cVendedor controladorVendedor = new cVendedor();
        String vendedorNombre = controladorVendedor.obtenerVendedorPorNombre("Kevin", "Zapata");
        jTextFieldVendedor.setText(vendedorNombre);

// Paso 2: Cargar clientes al combo
        cargarClientesEnComboBox();
        cargarCodigosProductosEnComboBox();
        cargarFacturasEnTabla();

    }

    private ArrayList<DetalleFactura> listaDetalle = new ArrayList<>();

    private void cargarClientesEnComboBox() {
        cCliente controladorCliente = new cCliente();
        ArrayList<String> clientesCombo = controladorCliente.obtenerClientesFormatoCombo();
        for (String cliente : clientesCombo) {
            jComboBoxClientes.addItem(cliente);
        }
    }
    
    private void cargarCodigosProductosEnComboBox() {
        cProductos controlador = new cProductos();
    try {
        controlador.leer(); // Cargar productos del CSV
        for (int i = 0; i < controlador.Cantidad(); i++) {
            Productos p = controlador.getProducto(i);
            jComboBoxCodigos.addItem(p.Codigo); // Agrega solo el código
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar productos: " + e.getMessage());
    }
    }

    private void actualizarTotales() {
        double subtotal = 0;
        for (DetalleFactura d : listaDetalle) {
            subtotal += d.subtotal;
        }
        double iva = subtotal * 0.12;
        double total = subtotal + iva;

        jTextFieldSubtotalGeneral.setText(String.format("%.2f", subtotal));
        jTextFieldIVA.setText(String.format("%.2f", iva));
        jTextFieldTotalFactura.setText(String.format("%.2f", total));
    }

    private void limpiarCamposProducto() {
        //jTextFieldCodigoProd.setText("");
        jTextFieldNombreProd.setText("");
        jTextFieldPrecio.setText("");
        jTextFieldCantidad.setText("");
        jTextFieldSubtotal.setText("");
    }

    private void mostrarDetalleFacturaSeleccionada(String numeroFactura) {
        DefaultTableModel modeloDetalle = (DefaultTableModel) jTableDetalleFactura.getModel();
        modeloDetalle.setRowCount(0); // Limpia la tabla

        double subtotal = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(Paths.get("src", "Data", "dataDetalleFactura.csv").toString()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length >= 6 && datos[0].equals(numeroFactura)) {
                    String codigo = datos[1];
                    String nombre = datos[2];
                    int cantidad = Integer.parseInt(datos[3]);
                    double precio = Double.parseDouble(datos[4]);
                    double sub = Double.parseDouble(datos[5]);
                    subtotal += sub;

                    modeloDetalle.addRow(new Object[]{codigo, nombre, cantidad, precio, sub});
                }
            }
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar el detalle: " + e.getMessage());
            return;
        }

        double iva = subtotal * 0.12;
        double total = subtotal + iva;

        jTextFieldSubtotalGeneral.setText(String.format("%.2f", subtotal));
        jTextFieldIVA.setText(String.format("%.2f", iva));
        jTextFieldTotalFactura.setText(String.format("%.2f", total));
    }
private void cargarFacturasEnTabla() {
    DefaultTableModel modelo = (DefaultTableModel) jTableFacturas.getModel();
    modelo.setRowCount(0); // Limpia la tabla

    try (BufferedReader br = new BufferedReader(new FileReader(Paths.get("src", "Data", "dataFacturas.csv").toString()))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(";");
            if (datos.length >= 5) {
                String numeroFactura = datos[0];
                String cedulaCliente = datos[2];
                String fecha = datos[3];
                String total = datos[4];

                // Obtener nombre del cliente por cédula
                cCliente controlador = new cCliente();
                String nombreCompleto = controlador.obtenerNombreClientePorCedula(cedulaCliente);
                
                 // AGREGAR FILA A LA TABLA
                modelo.addRow(new Object[]{numeroFactura, fecha, nombreCompleto, total});
            }
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar facturas: " + e.getMessage());
    }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldNumFactura = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldFecha = new javax.swing.JTextField();
        jComboBoxClientes = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldVendedor = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldNombreProd = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldPrecio = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldCantidad = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldSubtotal = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDetalleFactura = new javax.swing.JTable();
        jButtonAgregarProducto = new javax.swing.JButton();
        jButtonEliminarProducto = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldSubtotalGeneral = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldIVA = new javax.swing.JTextField();
        jTextFieldTotalFactura = new javax.swing.JTextField();
        jButtonGuardarFactura = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableFacturas = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jButtonVerDetalle = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jComboBoxFormaPago = new javax.swing.JComboBox<>();
        jButtonLimpiar = new javax.swing.JButton();
        jComboBoxCodigos = new javax.swing.JComboBox<>();

        jLabel1.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel1.setText("Panel Factura");

        jLabel2.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel2.setText("Num. Factura");

        jTextFieldNumFactura.setToolTipText("");
        jTextFieldNumFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNumFacturaActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel3.setText("Fecha");

        jTextFieldFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFechaActionPerformed(evt);
            }
        });

        jComboBoxClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxClientesActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel4.setText("Cliente:");

        jLabel5.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel5.setText("Vendedor:");

        jTextFieldVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldVendedorActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel6.setText("Codigo Producto");

        jLabel7.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel7.setText("Nombre Producto");

        jTextFieldNombreProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNombreProdActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel8.setText("Precio Unitario");

        jTextFieldPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPrecioActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel9.setText("Cantidad");

        jTextFieldCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCantidadActionPerformed(evt);
            }
        });
        jTextFieldCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldCantidadKeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel10.setText("SubTotal");

        jTextFieldSubtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSubtotalActionPerformed(evt);
            }
        });

        jTableDetalleFactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo Producto", "Nombre", "Cantidad", "Precio Unitario", "SubTotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableDetalleFactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableDetalleFacturaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTableDetalleFactura);

        jButtonAgregarProducto.setText("Agregar");
        jButtonAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarProductoActionPerformed(evt);
            }
        });

        jButtonEliminarProducto.setText("Eliminar");
        jButtonEliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarProductoActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel11.setText("Subtotal total");

        jTextFieldSubtotalGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSubtotalGeneralActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel12.setText("IVA 15%");

        jLabel13.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel13.setText("Total Final");

        jTextFieldIVA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIVAActionPerformed(evt);
            }
        });

        jTextFieldTotalFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTotalFacturaActionPerformed(evt);
            }
        });

        jButtonGuardarFactura.setText("Guardar");
        jButtonGuardarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarFacturaActionPerformed(evt);
            }
        });

        jTableFacturas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numero de Factura", "Fecha", "Nombre Cliente", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableFacturas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableFacturasKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTableFacturas);

        jLabel14.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel14.setText("Tabla de facturas");

        jButtonVerDetalle.setText("Detalle");
        jButtonVerDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerDetalleActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel15.setText("Forma Pago");

        jComboBoxFormaPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxFormaPagoActionPerformed(evt);
            }
        });

        jButtonLimpiar.setText("Limpiar");
        jButtonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarActionPerformed(evt);
            }
        });

        jComboBoxCodigos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCodigosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(36, 36, 36)
                        .addComponent(jTextFieldNumFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(35, 35, 35)
                        .addComponent(jComboBoxClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldFecha)))
                .addGap(298, 298, 298))
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldPrecio)
                            .addComponent(jTextFieldNombreProd)
                            .addComponent(jTextFieldCantidad)
                            .addComponent(jTextFieldSubtotal)
                            .addComponent(jComboBoxFormaPago, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jComboBoxCodigos, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))))
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(74, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonVerDetalle, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonLimpiar, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(183, 183, 183))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(215, 215, 215)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel13))
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextFieldSubtotalGeneral, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldIVA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldTotalFactura, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButtonAgregarProducto)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jButtonGuardarFactura)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonEliminarProducto))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(341, 341, 341)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(396, 396, 396)
                                .addComponent(jLabel14)))
                        .addGap(0, 437, Short.MAX_VALUE)))
                .addContainerGap(3, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldNumFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jTextFieldVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jComboBoxClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonAgregarProducto)
                            .addComponent(jButtonEliminarProducto))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jTextFieldSubtotalGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldIVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jTextFieldTotalFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(jButtonGuardarFactura)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(239, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jComboBoxCodigos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextFieldNombreProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jTextFieldPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jTextFieldCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jTextFieldSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jComboBoxFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(284, 284, 284)
                        .addComponent(jButtonVerDetalle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonLimpiar)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldNumFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNumFacturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNumFacturaActionPerformed

    private void jTextFieldFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFechaActionPerformed

    private void jComboBoxClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxClientesActionPerformed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_jComboBoxClientesActionPerformed

    private void jTextFieldVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldVendedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldVendedorActionPerformed

    private void jTextFieldNombreProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNombreProdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNombreProdActionPerformed

    private void jTextFieldPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPrecioActionPerformed

    private void jTextFieldCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCantidadActionPerformed

    private void jTextFieldSubtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSubtotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSubtotalActionPerformed

    private void jTableDetalleFacturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableDetalleFacturaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableDetalleFacturaKeyReleased

    private void jTextFieldSubtotalGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSubtotalGeneralActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSubtotalGeneralActionPerformed

    private void jTextFieldIVAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIVAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIVAActionPerformed

    private void jTextFieldTotalFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTotalFacturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTotalFacturaActionPerformed

    private void jButtonAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarProductoActionPerformed
        // TODO add your handling code here:
        String numFactura = jTextFieldNumFactura.getText().trim();
        String codigo = jComboBoxCodigos.getSelectedItem().toString();
        String nombre = jTextFieldNombreProd.getText().trim();
        String precioStr = jTextFieldPrecio.getText().trim();
        String cantidadStr = jTextFieldCantidad.getText().trim();

        if (codigo.isEmpty() || nombre.isEmpty() || precioStr.isEmpty() || cantidadStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos antes de agregar.");
            return;
        }

        try {
            int cantidad = Integer.parseInt(cantidadStr);
            double precio = Double.parseDouble(precioStr);

            DetalleFactura detalle = new DetalleFactura(numFactura, codigo, nombre, cantidad, precio);
            listaDetalle.add(detalle);

            DefaultTableModel modelo = (DefaultTableModel) jTableDetalleFactura.getModel();
            Object[] fila = {codigo, nombre, cantidad, precio, detalle.subtotal};
            modelo.addRow(fila);

            limpiarCamposProducto();
            actualizarTotales();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Cantidad y Precio deben ser numéricos.");
        }
    }//GEN-LAST:event_jButtonAgregarProductoActionPerformed

    private void jButtonEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarProductoActionPerformed
        // TODO add your handling code here:
        int fila = jTableDetalleFactura.getSelectedRow();
        if (fila >= 0) {
            listaDetalle.remove(fila);
            DefaultTableModel modelo = (DefaultTableModel) jTableDetalleFactura.getModel();
            modelo.removeRow(fila);
            actualizarTotales();
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un producto para eliminar.");
        }
    }//GEN-LAST:event_jButtonEliminarProductoActionPerformed

    private void jButtonGuardarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarFacturaActionPerformed
        // TODO add your handling code here:
        String numFactura = jTextFieldNumFactura.getText().trim();
        String fecha = jTextFieldFecha.getText().trim();
        String totalStr = jTextFieldTotalFactura.getText().trim().replace(",", ".");
        String clienteSeleccionado = (String) jComboBoxClientes.getSelectedItem();
        String formaPago = (String) jComboBoxFormaPago.getSelectedItem();

        if (numFactura.isEmpty() || fecha.isEmpty() || clienteSeleccionado == null || formaPago == null || listaDetalle.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos y agrega al menos un producto.");
            return;
        }

        try {
            String cedulaCliente = clienteSeleccionado.split(" - ")[0];
            double totalFactura = Double.parseDouble(totalStr);

            // Guarda la cabecera de la factura
            String rutaFactura = Paths.get("src", "Data", "dataFacturas.csv").toString();
            FileWriter fw = new FileWriter(rutaFactura, true);
            fw.write(numFactura + ";" + "0998765432000" + ";" + cedulaCliente + ";" + fecha + ";" + totalFactura + ";" + formaPago + "\n");
            fw.close();

            // Guarda los detalles de la factura
            String DetalleFactura = Paths.get("src", "Data", "dataDetalleFactura.csv").toString();
            FileWriter fwDetalle = new FileWriter(DetalleFactura, true);
            for (DetalleFactura d : listaDetalle) {
                fwDetalle.write(numFactura + ";" + d.codigoProducto + ";" + d.nombreProducto + ";" + d.cantidad + ";" + d.precioUnitario + ";" + d.subtotal + "\n");
            }
            fwDetalle.close();

            JOptionPane.showMessageDialog(this, "Factura guardada correctamente.");

            // Limpia todo después de guardar
            ((DefaultTableModel) jTableDetalleFactura.getModel()).setRowCount(0);
            listaDetalle.clear();
            actualizarTotales();
            limpiarCamposProducto();
            jTextFieldNumFactura.setText("");
            jTextFieldFecha.setText("");
            jComboBoxClientes.setSelectedIndex(0);
            jComboBoxFormaPago.setSelectedIndex(0);
            
            cargarFacturasEnTabla();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar factura: " + e.getMessage());
        }
    }//GEN-LAST:event_jButtonGuardarFacturaActionPerformed

    private void jTableFacturasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableFacturasKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableFacturasKeyReleased

    private void jButtonVerDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerDetalleActionPerformed
        // TODO add your handling code here:
        int filaSeleccionada = jTableFacturas.getSelectedRow();
        if (filaSeleccionada >= 0) {
            String numeroFactura = jTableFacturas.getValueAt(filaSeleccionada, 0).toString(); // Columna 0 = NumFactura
            mostrarDetalleFacturaSeleccionada(numeroFactura);
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona una factura de la tabla para ver su detalle.");
        }
    }//GEN-LAST:event_jButtonVerDetalleActionPerformed

    private void jTextFieldCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCantidadKeyReleased
        // TODO add your handling code here:
        try {
            int cantidad = Integer.parseInt(jTextFieldCantidad.getText().trim());
            double precio = Double.parseDouble(jTextFieldPrecio.getText().trim());
            double subtotal = cantidad * precio;
            jTextFieldSubtotal.setText(String.valueOf(subtotal));
        } catch (NumberFormatException e) {
            // Ignora si aún no han ingresado bien los datos
            jTextFieldSubtotal.setText("");
        }
    }//GEN-LAST:event_jTextFieldCantidadKeyReleased

    private void jComboBoxFormaPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxFormaPagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxFormaPagoActionPerformed

    private void jButtonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarActionPerformed
        // TODO add your handling code here:
        // Limpiar tabla de facturas
    // Limpiar tabla de detalle si quieres
    DefaultTableModel modeloDetalle = (DefaultTableModel) jTableDetalleFactura.getModel();
    modeloDetalle.setRowCount(0);

    // Limpiar campos de totales
    jTextFieldSubtotalGeneral.setText("");
    jTextFieldIVA.setText("");
    jTextFieldTotalFactura.setText("");
        
    }//GEN-LAST:event_jButtonLimpiarActionPerformed

    private void jComboBoxCodigosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCodigosActionPerformed
        // TODO add your handling code here:
        String codigoSeleccionado = (String) jComboBoxCodigos.getSelectedItem();
    if (codigoSeleccionado != null) {
        cProductos controlador = new cProductos();
        try {
            controlador.leer();
            for (int i = 0; i < controlador.Cantidad(); i++) {
                Productos p = controlador.getProducto(i);
                if (p.Codigo.equalsIgnoreCase(codigoSeleccionado)) {
                    jTextFieldNombreProd.setText(p.Nombre);
                    jTextFieldPrecio.setText(String.valueOf(p.Precio));
                    break;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al buscar producto: " + e.getMessage());
        }
    }
    }//GEN-LAST:event_jComboBoxCodigosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAgregarProducto;
    private javax.swing.JButton jButtonEliminarProducto;
    private javax.swing.JButton jButtonGuardarFactura;
    private javax.swing.JButton jButtonLimpiar;
    private javax.swing.JButton jButtonVerDetalle;
    private javax.swing.JComboBox<String> jComboBoxClientes;
    private javax.swing.JComboBox<String> jComboBoxCodigos;
    private javax.swing.JComboBox<String> jComboBoxFormaPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableDetalleFactura;
    private javax.swing.JTable jTableFacturas;
    private javax.swing.JTextField jTextFieldCantidad;
    private javax.swing.JTextField jTextFieldFecha;
    private javax.swing.JTextField jTextFieldIVA;
    private javax.swing.JTextField jTextFieldNombreProd;
    private javax.swing.JTextField jTextFieldNumFactura;
    private javax.swing.JTextField jTextFieldPrecio;
    private javax.swing.JTextField jTextFieldSubtotal;
    private javax.swing.JTextField jTextFieldSubtotalGeneral;
    private javax.swing.JTextField jTextFieldTotalFactura;
    private javax.swing.JTextField jTextFieldVendedor;
    // End of variables declaration//GEN-END:variables
}
