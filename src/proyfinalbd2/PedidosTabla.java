/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyfinalbd2;

import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import proyfinalbd2.conexion.detalle_pedido;
import proyfinalbd2.conexion.pedidos;

/**
 *
 * @author ASUS
 */
public class PedidosTabla extends javax.swing.JFrame {
    

    /**
     * Creates new form TablaPedidos
     */
        int id_pedido;
        public static String pedidoId;
        int id_cliente;
        int id_total;
        int id_detalle;
        int total;
        
        
                private DefaultTableModel modelo;
        TableRowSorter<DefaultTableModel> sorter;
        
        private void filtrar(TableRowSorter<DefaultTableModel> sorter) {
        String texto = BuscadorPedido.getText();
        
        int columna = jComboPedidosColumnas.getSelectedIndex();
        
        if (texto.trim().length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto, columna));
        }    
        }
        
    
    public PedidosTabla() {
        initComponents();
        setLocationRelativeTo(null);
        //cargar pedidos en la tabla
        pedidos.MostrarTablaPedidos(jTablePedidos);
        TableColumnModel columnModel = jTablePedidos.getColumnModel();
        TableColumnModel columnModel2 = jTablePedidoDetalle.getColumnModel();
        // centrar datos y ajustar columnas de la tabla productos
        
        nPedidos();
        
        
        
        //fondo blanco
        getContentPane().setBackground(new Color(255, 255, 255)); // blanco
        
        columnModel.getColumn(0).setPreferredWidth(20);//id pedido
        columnModel.getColumn(1).setPreferredWidth(200);//id-cliente
        columnModel.getColumn(2).setPreferredWidth(210);//direccion
        columnModel.getColumn(3).setPreferredWidth(40);//id-ruta
        columnModel.getColumn(4).setPreferredWidth(70);//total
        columnModel.getColumn(5).setPreferredWidth(70);//fecha

        
        DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
        centrado.setHorizontalAlignment(SwingConstants.CENTER);

        //  Aplicar el centrado a la columna "id" (índice 0)
        jTablePedidos.getColumnModel().getColumn(0).setCellRenderer(centrado);
        jTablePedidos.getColumnModel().getColumn(4).setCellRenderer(centrado);
        jTablePedidos.getColumnModel().getColumn(5).setCellRenderer(centrado);
        
        
        modelo = (DefaultTableModel) jTablePedidos.getModel(); // Asignas el modelo
        sorter = new TableRowSorter<>(modelo);
        jTablePedidos.setRowSorter(sorter); // Conectas el sorter a la tabla
        
        // Vinculas el filtro con el JTextField
        BuscadorPedido.getDocument().addDocumentListener(new DocumentListener() {
        public void insertUpdate(DocumentEvent e) { filtrar(sorter); }
        public void changedUpdate(DocumentEvent e) { filtrar(sorter); }
        public void removeUpdate(DocumentEvent e) { filtrar(sorter); }
    });
        
        
        //detecta si se selecciono una fila de la tabla pedidos y muestra su detalle en la otra tabla
        jTablePedidos.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) { // evita duplicados
                int fila = jTablePedidos.getSelectedRow(); // fila seleccionada
                if (fila != -1) { // si hay una fila seleccionada
                    int idPedido = Integer.parseInt(jTablePedidos.getValueAt(fila, 0).toString()); // columna 0
                    detalle_pedido.mostrarDetallesPedidoTabla(idPedido, jTablePedidoDetalle);
                    columnModel2.getColumn(0).setPreferredWidth(20);//id
                    columnModel2.getColumn(1).setPreferredWidth(250);//nombre
                    columnModel2.getColumn(2).setPreferredWidth(40);//cantidad
                    columnModel2.getColumn(3).setPreferredWidth(50);//precio
                    columnModel2.getColumn(4).setPreferredWidth(70);//importe
                    
                    jTablePedidoDetalle.getColumnModel().getColumn(0).setCellRenderer(centrado);
                    jTablePedidoDetalle.getColumnModel().getColumn(2).setCellRenderer(centrado);
                    jTablePedidoDetalle.getColumnModel().getColumn(3).setCellRenderer(centrado);
                    jTablePedidoDetalle.getColumnModel().getColumn(4).setCellRenderer(centrado);

                }
            }
        });
        
        
        modelo.addTableModelListener(new javax.swing.event.TableModelListener() {
            @Override
            public void tableChanged(javax.swing.event.TableModelEvent e) {
                int totalFilas = modelo.getRowCount();
                JPedidosRegistrados.setText(totalFilas + " PEDIDOS REGISTRADOS");
            }
        });
    }
    public void nPedidos(){
        int n = jTablePedidos.getRowCount();
        JPedidosRegistrados.setText(n+" PEDIDOS REGISTRADOS");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JPedidosRegistrados = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePedidos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablePedidoDetalle = new javax.swing.JTable();
        Cabecera = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        Footer = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jButtonAddPedido = new javax.swing.JButton();
        jButtonModPedido = new javax.swing.JButton();
        jButtonVolver = new javax.swing.JButton();
        jButtonEliminarPedido = new javax.swing.JButton();
        jButtonSalir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        BuscadorPedido = new javax.swing.JTextField();
        jComboPedidosColumnas = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        JPedidosRegistrados.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        JPedidosRegistrados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JPedidosRegistrados.setText("n Pedidos Registrados");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Buscador");

        jTablePedidos.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jTablePedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID-Pedido", "ID-Cliente", "Direccion", "ID-Ruta", "Total", "Fecha-Pedido"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTablePedidos);
        if (jTablePedidos.getColumnModel().getColumnCount() > 0) {
            jTablePedidos.getColumnModel().getColumn(0).setResizable(false);
            jTablePedidos.getColumnModel().getColumn(1).setResizable(false);
            jTablePedidos.getColumnModel().getColumn(2).setResizable(false);
            jTablePedidos.getColumnModel().getColumn(3).setResizable(false);
            jTablePedidos.getColumnModel().getColumn(4).setResizable(false);
            jTablePedidos.getColumnModel().getColumn(5).setResizable(false);
        }

        jTablePedidoDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Cantidad", "Precio", "Importe"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTablePedidoDetalle);

        Cabecera.setBackground(new java.awt.Color(171, 0, 0));
        Cabecera.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Pedidos");
        Cabecera.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(-3, 10, 1510, -1));

        Footer.setBackground(new java.awt.Color(209, 0, 0));
        Footer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        Footer.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, -1, -1));

        jButtonAddPedido.setBackground(new java.awt.Color(238, 0, 0));
        jButtonAddPedido.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jButtonAddPedido.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAddPedido.setText("Nuevo Pedido");
        jButtonAddPedido.setBorder(null);
        jButtonAddPedido.setBorderPainted(false);
        jButtonAddPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddPedidoActionPerformed(evt);
            }
        });
        Footer.add(jButtonAddPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 90));

        jButtonModPedido.setBackground(new java.awt.Color(238, 0, 0));
        jButtonModPedido.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jButtonModPedido.setForeground(new java.awt.Color(255, 255, 255));
        jButtonModPedido.setText("Modificar Pedido");
        jButtonModPedido.setBorder(null);
        jButtonModPedido.setBorderPainted(false);
        jButtonModPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModPedidoActionPerformed(evt);
            }
        });
        Footer.add(jButtonModPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 310, 90));

        jButtonVolver.setBackground(new java.awt.Color(238, 0, 0));
        jButtonVolver.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jButtonVolver.setForeground(new java.awt.Color(255, 255, 255));
        jButtonVolver.setText("Volver al menú");
        jButtonVolver.setBorder(null);
        jButtonVolver.setBorderPainted(false);
        jButtonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVolverActionPerformed(evt);
            }
        });
        Footer.add(jButtonVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 710, 90));

        jButtonEliminarPedido.setBackground(new java.awt.Color(238, 0, 0));
        jButtonEliminarPedido.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jButtonEliminarPedido.setForeground(new java.awt.Color(255, 255, 255));
        jButtonEliminarPedido.setText("Eliminar Pedido");
        jButtonEliminarPedido.setBorder(null);
        jButtonEliminarPedido.setBorderPainted(false);
        jButtonEliminarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarPedidoActionPerformed(evt);
            }
        });
        Footer.add(jButtonEliminarPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(605, 0, 320, 90));

        jButtonSalir.setBackground(new java.awt.Color(238, 0, 0));
        jButtonSalir.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jButtonSalir.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSalir.setText("Salir");
        jButtonSalir.setBorder(null);
        jButtonSalir.setBorderPainted(false);
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });
        Footer.add(jButtonSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 90, 220, 90));

        jLabel1.setFont(new java.awt.Font("Roboto", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(238, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Detalles del pedido");

        BuscadorPedido.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        BuscadorPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscadorPedidoActionPerformed(evt);
            }
        });

        jComboPedidosColumnas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboPedidosColumnas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "ID-Cliente", "Direccion", "ID-Ruta", "Total", "Fecha" }));

        jButton1.setBackground(new java.awt.Color(238, 0, 0));
        jButton1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("LIMPIAR");
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Footer, javax.swing.GroupLayout.PREFERRED_SIZE, 926, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BuscadorPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboPedidosColumnas, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane1)
                            .addComponent(JPedidosRegistrados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31))
            .addComponent(Cabecera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Cabecera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JPedidosRegistrados)
                    .addComponent(jLabel1))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(BuscadorPedido)
                            .addComponent(jComboPedidosColumnas)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(Footer, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddPedidoActionPerformed
        //validamos permisos según el rol
        if("Supervisor".equals(Login.rol) || "Coordinador de Rutas".equals(Login.rol)){
            JOptionPane.showMessageDialog(null, "Usted no cuenta con los permisos suficientes para acceder a este apartado",
                "Acceso Denegado", JOptionPane.WARNING_MESSAGE);
            return;
        }
        new PedidoEscogerCliente(jTablePedidos,sorter,BuscadorPedido,jComboPedidosColumnas).setVisible(true);
    }//GEN-LAST:event_jButtonAddPedidoActionPerformed

    private void jButtonModPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModPedidoActionPerformed
        //validamos permisos según el rol
        if("Supervisor".equals(Login.rol) || "Coordinador de Rutas".equals(Login.rol) || "Vendedor".equals(Login.rol)){
            JOptionPane.showMessageDialog(null, "Usted no cuenta con los permisos suficientes para acceder a este apartado",
                "Acceso Denegado", JOptionPane.WARNING_MESSAGE);
            return;
        }
        new PedidoEscogerPedido(jTablePedidos,sorter,BuscadorPedido,jComboPedidosColumnas,JPedidosRegistrados).setVisible(true);
    }//GEN-LAST:event_jButtonModPedidoActionPerformed

    private void jButtonEliminarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarPedidoActionPerformed
        //validamos permisos según el rol
        if("Supervisor".equals(Login.rol) || "Coordinador de Rutas".equals(Login.rol) || "Vendedor".equals(Login.rol)){
            JOptionPane.showMessageDialog(null, "Usted no cuenta con los permisos suficientes para acceder a este apartado",
                "Acceso Denegado", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int fila = jTablePedidos.getSelectedRow(); // Obtenemos la fila seleccionada

        // Validamos que se haya seleccionado un cliente
        if (fila < 0) {
            JOptionPane.showMessageDialog(this,
                "Debe seleccionar una fila.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return; // detenemos el flujo
        }
        
        //guardamos los datos de la fila seleccionada
        pedidoId = jTablePedidos.getValueAt(fila, 0).toString(); // ID del pedido seleccionado
            
            try{    
                id_pedido = Integer.parseInt(pedidoId);
                
                int opcion = JOptionPane.showConfirmDialog(
                        null,
                        "¿Está seguro de que desea eliminar el pedido?\n¡Este será borrado permanentemente de la base de datos!",
                        "Confirmar Eliminación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (opcion == JOptionPane.YES_OPTION) {
                        // eliminamos el pedido de la BD
                        pedidos.eliminarPedido(id_pedido);
                        
                        BuscadorPedido.setText("");      // limpia buscador
                        sorter.setRowFilter(null);
                        
                        //actualizar tabla de pedidos
                        pedidos.MostrarTablaPedidos(jTablePedidos);
                        TableColumnModel columnModel = jTablePedidos.getColumnModel();
                        columnModel.getColumn(1).setPreferredWidth(180);
                        columnModel.getColumn(3).setPreferredWidth(90);
                        
                        sorter = new TableRowSorter<DefaultTableModel>((DefaultTableModel) jTablePedidos.getModel());
                        jTablePedidos.setRowSorter(sorter);
                        
                        nPedidos();
                        
                        JOptionPane.showMessageDialog(
                            null,
                            "El pedido de ID: "+id_pedido+" ha sido eliminado correctamente.",
                            "Eliminado",
                            JOptionPane.INFORMATION_MESSAGE
                        );
                } else {
                        JOptionPane.showMessageDialog(
                            null,
                            "Operación cancelada. El pedido no fue eliminado.",
                            "Cancelado",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
                
            }catch(NumberFormatException e){
                        JOptionPane.showMessageDialog(null,"¡El ID del pedido tiene que ser un entero!","Alerta",JOptionPane.WARNING_MESSAGE);
            }            
    }//GEN-LAST:event_jButtonEliminarPedidoActionPerformed

    private void jButtonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVolverActionPerformed
        new MenuPrincipal().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonVolverActionPerformed

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButtonSalirActionPerformed

    private void BuscadorPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscadorPedidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BuscadorPedidoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        BuscadorPedido.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PedidosTabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PedidosTabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PedidosTabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PedidosTabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PedidosTabla().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BuscadorPedido;
    private javax.swing.JPanel Cabecera;
    private javax.swing.JPanel Footer;
    private javax.swing.JLabel JPedidosRegistrados;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAddPedido;
    private javax.swing.JButton jButtonEliminarPedido;
    private javax.swing.JButton jButtonModPedido;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JButton jButtonVolver;
    private javax.swing.JComboBox<String> jComboPedidosColumnas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTablePedidoDetalle;
    private javax.swing.JTable jTablePedidos;
    // End of variables declaration//GEN-END:variables
}
