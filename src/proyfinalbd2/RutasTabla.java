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
import proyfinalbd2.conexion.ruta;

/**
 *
 * @author ASUS
 */
public class RutasTabla extends javax.swing.JFrame {
    

    /**
     * Creates new form TablaPedidos
     */
        public static int id_ruta;
        public static String rutaId;
        public static int id_pedido;
        public static int id_cliente;
        public static String direccion;
        public static String nomruta;
        public static String estado;
        
                    private DefaultTableModel modelo;
            TableRowSorter<DefaultTableModel> sorter;
            
            
                    private void filtrar(TableRowSorter<DefaultTableModel> sorter) {
        String texto = BuscadorRutas.getText();
        
        int columna = jComboRutasColumnas.getSelectedIndex();
        
        if (texto.trim().length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto, columna));
        }    
        }
        
        
    
    public RutasTabla() {
        initComponents();
        setLocationRelativeTo(null);
        //cargar rutas en la tabla
        ruta.mostrarRutasEnTabla(jTableRutas);
        TableColumnModel columnModel = jTableRutas.getColumnModel();
                        columnModel.getColumn(0).setPreferredWidth(30);//id
                        columnModel.getColumn(1).setPreferredWidth(270);//nombre
                        columnModel.getColumn(2).setPreferredWidth(270);////descripcion
                        columnModel.getColumn(3).setPreferredWidth(50);//fecha
                        columnModel.getColumn(4).setPreferredWidth(50);//estado
                        

                        DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
                        centrado.setHorizontalAlignment(SwingConstants.CENTER);
                        //  Aplicar el centrado a la columna "id" (índice 0)
                        jTableRutas.getColumnModel().getColumn(0).setCellRenderer(centrado);// id estado y fecha centrados
                        jTableRutas.getColumnModel().getColumn(3).setCellRenderer(centrado);
                        jTableRutas.getColumnModel().getColumn(4).setCellRenderer(centrado);
        
        getContentPane().setBackground(Color.WHITE);
        nRutas();
        
        modelo = (DefaultTableModel) jTableRutas.getModel(); // Asignas el modelo
        sorter = new TableRowSorter<>(modelo);
        jTableRutas.setRowSorter(sorter); // Conectas el sorter a la tabla
        
        // Vinculas el filtro con el JTextField
        BuscadorRutas.getDocument().addDocumentListener(new DocumentListener() {
        public void insertUpdate(DocumentEvent e) { filtrar(sorter); }
        public void changedUpdate(DocumentEvent e) { filtrar(sorter); }
        public void removeUpdate(DocumentEvent e) { filtrar(sorter); }
        
        });
        
        //detecta si se selecciono una fila de la tabla ruta y muestra su detalle en la otra tabla
        jTableRutas.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) { // evita duplicados
                int fila = jTableRutas.getSelectedRow(); // fila seleccionada
                if (fila != -1) { // si hay una fila seleccionada
                    int idPedido = Integer.parseInt(jTableRutas.getValueAt(fila, 0).toString()); // columna 0
                    ruta.mostrarPedidosEnRuta(idPedido, jTablePedidosEnRuta);
                    TableColumnModel columnModelR = jTablePedidosEnRuta.getColumnModel();
                        columnModelR.getColumn(0).setPreferredWidth(35);//id PEDIDO
                        columnModelR.getColumn(1).setPreferredWidth(180);//ID CLIENTE
                        columnModelR.getColumn(2).setPreferredWidth(230);////direccion
                        columnModelR.getColumn(3).setPreferredWidth(70);//total
                        columnModelR.getColumn(4).setPreferredWidth(90);//fecha
                        
                        //  Aplicar el centrado a la columna "id" (índice 0)
                        jTablePedidosEnRuta.getColumnModel().getColumn(0).setCellRenderer(centrado);// id estado y fecha centrados
                        jTablePedidosEnRuta.getColumnModel().getColumn(3).setCellRenderer(centrado);
                        jTablePedidosEnRuta.getColumnModel().getColumn(4).setCellRenderer(centrado);
                    
                    
                }
            }
        });
        
                // Agregar un listener al modelo
        modelo.addTableModelListener(new javax.swing.event.TableModelListener() {
            @Override
            public void tableChanged(javax.swing.event.TableModelEvent e) {
                int totalFilas = modelo.getRowCount();
                JRutasRegistrados.setText(totalFilas + " RUTAS REGISTRADAS");
            }
        });
        
    }
        public void nRutas(){
        int n = jTableRutas.getRowCount();
        JRutasRegistrados.setText(n+" RUTAS REGISTRADAS");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Cabecera = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        JRutasRegistrados = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableRutas = new javax.swing.JTable();
        BuscadorRutas = new javax.swing.JTextField();
        jComboRutasColumnas = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jButtonSalir = new javax.swing.JButton();
        jButtonVolver = new javax.swing.JButton();
        jButtonAddRuta = new javax.swing.JButton();
        jButtonModRuta = new javax.swing.JButton();
        jButtonEliminarRuta = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablePedidosEnRuta = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        Cabecera.setBackground(new java.awt.Color(209, 0, 0));
        Cabecera.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Rutas");
        Cabecera.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 10, 1510, -1));

        JRutasRegistrados.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        JRutasRegistrados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JRutasRegistrados.setText("n Rutas Registrados");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Buscador");

        jTableRutas.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jTableRutas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Descripcion", "Fecha_Ruta", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableRutas);

        BuscadorRutas.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        BuscadorRutas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscadorRutasActionPerformed(evt);
            }
        });

        jComboRutasColumnas.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jComboRutasColumnas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Nombre", "Descripcion", "Fecha", "Estado" }));
        jComboRutasColumnas.setSelectedIndex(1);

        jPanel1.setBackground(new java.awt.Color(238, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonSalir.setBackground(new java.awt.Color(238, 0, 0));
        jButtonSalir.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jButtonSalir.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSalir.setText("Salir");
        jButtonSalir.setBorder(null);
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 90, 220, 100));

        jButtonVolver.setBackground(new java.awt.Color(238, 0, 0));
        jButtonVolver.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jButtonVolver.setForeground(new java.awt.Color(255, 255, 255));
        jButtonVolver.setText("Volver al menú");
        jButtonVolver.setBorder(null);
        jButtonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVolverActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 710, 100));

        jButtonAddRuta.setBackground(new java.awt.Color(238, 0, 0));
        jButtonAddRuta.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jButtonAddRuta.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAddRuta.setText("Nueva Ruta");
        jButtonAddRuta.setBorder(null);
        jButtonAddRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddRutaActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonAddRuta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, 90));

        jButtonModRuta.setBackground(new java.awt.Color(238, 0, 0));
        jButtonModRuta.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jButtonModRuta.setForeground(new java.awt.Color(255, 255, 255));
        jButtonModRuta.setText("Modificar Ruta");
        jButtonModRuta.setBorder(null);
        jButtonModRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModRutaActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonModRuta, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, 310, 90));

        jButtonEliminarRuta.setBackground(new java.awt.Color(238, 0, 0));
        jButtonEliminarRuta.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jButtonEliminarRuta.setForeground(new java.awt.Color(255, 255, 255));
        jButtonEliminarRuta.setText("Eliminar Ruta");
        jButtonEliminarRuta.setBorder(null);
        jButtonEliminarRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarRutaActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonEliminarRuta, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 0, 310, 90));

        jLabel1.setFont(new java.awt.Font("Roboto", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(238, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Lista de pedidos asignadas en la ruta");

        jTablePedidosEnRuta.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jTablePedidosEnRuta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID-Pedido", "ID-Cliente", "Direccion", "Total", "Fecha-Pedido"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTablePedidosEnRuta);

        jButton2.setBackground(new java.awt.Color(238, 0, 0));
        jButton2.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("LIMPIAR");
        jButton2.setBorder(null);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Cabecera, javax.swing.GroupLayout.DEFAULT_SIZE, 1524, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 908, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BuscadorRutas, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboRutasColumnas, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(JRutasRegistrados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 933, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Cabecera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JRutasRegistrados)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BuscadorRutas)
                            .addComponent(jComboRutasColumnas, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddRutaActionPerformed
        //validamos permisos según el rol
        if("Supervisor".equals(Login.rol)){
            JOptionPane.showMessageDialog(null, "Usted no cuenta con los permisos suficientes para acceder a este apartado",
                "Acceso Denegado", JOptionPane.WARNING_MESSAGE);
            return;
        }
        new RutasCREAR(jTableRutas).setVisible(true);
    }//GEN-LAST:event_jButtonAddRutaActionPerformed

    private void jButtonModRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModRutaActionPerformed
        //validamos permisos según el rol
        if("Supervisor".equals(Login.rol)){
            JOptionPane.showMessageDialog(null, "Usted no cuenta con los permisos suficientes para acceder a este apartado",
                "Acceso Denegado", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int fila = jTableRutas.getSelectedRow(); // Obtenemos la fila seleccionada

        
        // Validamos que se haya seleccionado un cliente
        if (fila < 0) {
            JOptionPane.showMessageDialog(this,
                "Debe seleccionar una ruta.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return; // detenemos el flujo
        }
        
        //guardamos los datos de la fila seleccionada
        id_ruta = Integer.parseInt(jTableRutas.getValueAt(fila, 0).toString()); // ID de la ruta seleccionado
        nomruta= jTableRutas.getValueAt(fila, 1).toString(); // nombre de la ruta seleccionado
        estado = jTableRutas.getValueAt(fila, 4).toString(); // estado de la ruta seleccionado

        new RutasMOD(jTableRutas).setVisible(true);
    }//GEN-LAST:event_jButtonModRutaActionPerformed

    private void jButtonEliminarRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarRutaActionPerformed
        //validamos permisos según el rol
        if("Supervisor".equals(Login.rol)){
            JOptionPane.showMessageDialog(null, "Usted no cuenta con los permisos suficientes para acceder a este apartado",
                "Acceso Denegado", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int fila = jTableRutas.getSelectedRow(); // Obtenemos la fila seleccionada

        // Validamos que se haya seleccionado un cliente
        if (fila < 0) {
            JOptionPane.showMessageDialog(this,
                "Debe seleccionar una fila.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return; // detenemos el flujo
        }
        
        //guardamos los datos de la fila seleccionada
        rutaId = jTableRutas.getValueAt(fila, 0).toString(); // ID de la ruta seleccionada
            
            try{    
                id_ruta = Integer.parseInt(rutaId);
                
                int opcion = JOptionPane.showConfirmDialog(
                        null,
                        "¿Está seguro de que desea eliminar la ruta?\n¡Este será borrado permanentemente de la base de datos!",
                        "Confirmar Eliminación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (opcion == JOptionPane.YES_OPTION) {
                        // eliminamos el pedido de la BD
                        ruta.eliminarRuta(id_ruta);
                        //actualizar tabla de pedidos
                        BuscadorRutas.setText("");      // limpia buscador
                        sorter.setRowFilter(null);
                        
                        ruta.mostrarRutasEnTabla(jTableRutas);
                        
                       sorter = new TableRowSorter<DefaultTableModel>((DefaultTableModel) jTableRutas.getModel());
                        jTableRutas.setRowSorter(sorter); 
                        
                        TableColumnModel columnModel = jTableRutas.getColumnModel();
                        columnModel.getColumn(0).setPreferredWidth(30);//id
                        columnModel.getColumn(1).setPreferredWidth(270);//nombre
                        columnModel.getColumn(2).setPreferredWidth(270);////descripcion
                        columnModel.getColumn(3).setPreferredWidth(50);//fecha
                        columnModel.getColumn(4).setPreferredWidth(50);//estado
                        

                        DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
                        centrado.setHorizontalAlignment(SwingConstants.CENTER);
                        //  Aplicar el centrado a la columna "id" (índice 0)
                        jTableRutas.getColumnModel().getColumn(0).setCellRenderer(centrado);// id estado y fecha centrados
                        jTableRutas.getColumnModel().getColumn(3).setCellRenderer(centrado);
                        jTableRutas.getColumnModel().getColumn(4).setCellRenderer(centrado);
                        
                         nRutas();
                        
                        JOptionPane.showMessageDialog(
                            null,
                            "La ruta de ID: "+id_ruta+" ha sido eliminado correctamente.",
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
    }//GEN-LAST:event_jButtonEliminarRutaActionPerformed

    private void jButtonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVolverActionPerformed
        new MenuPrincipal().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonVolverActionPerformed

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButtonSalirActionPerformed

    private void BuscadorRutasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscadorRutasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BuscadorRutasActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
                BuscadorRutas.setText("");

    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(RutasTabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RutasTabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RutasTabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RutasTabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RutasTabla().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BuscadorRutas;
    private javax.swing.JPanel Cabecera;
    private javax.swing.JLabel JRutasRegistrados;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonAddRuta;
    private javax.swing.JButton jButtonEliminarRuta;
    private javax.swing.JButton jButtonModRuta;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JButton jButtonVolver;
    private javax.swing.JComboBox<String> jComboRutasColumnas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTablePedidosEnRuta;
    private javax.swing.JTable jTableRutas;
    // End of variables declaration//GEN-END:variables
}
