/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyfinalbd2;

import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import proyfinalbd2.conexion.categoria;
import proyfinalbd2.conexion.subcategoria;

/**
 *
 * @author ASUS
 */
public class CategoriasTabla extends javax.swing.JFrame {

    /**
     * Creates new form Productos
     */
            int id_categoria;
            public static String categoriaId;
            public static String nombre;
            //atributos de la tabla subcategorias
            int id_subcategoria;
            public static String subcatid;
            public static String id_categoria_padre;
            public static String subcatnombre;
            
            private DefaultTableModel modelo;
            TableRowSorter<DefaultTableModel> sorter;
            private DefaultTableModel modelo2;
            TableRowSorter<DefaultTableModel> sorter2;
            
            private void filtrar(TableRowSorter<DefaultTableModel> sorter) {
            String texto = jTextFieldBuscarCategoria.getText();

            int columna = jComboCategorias.getSelectedIndex();

            if (texto.trim().length() == 0) {
                sorter.setRowFilter(null);
            } else {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto, columna));
            }    
            }
            
            private void filtrar2(TableRowSorter<DefaultTableModel> sorter2) {
            String texto = jTextFieldBuscarSubCategoria.getText();

            int columna = jComboSubCat.getSelectedIndex();

            if (texto.trim().length() == 0) {
                sorter2.setRowFilter(null);
            } else {
                sorter2.setRowFilter(RowFilter.regexFilter("(?i)" + texto, columna));
            }    
            }
            
            public void limpiarBuscador(javax.swing.JTextField Buscador) {
                Buscador.setText(null);
                
            }
            
            
            public void resetearTabla(javax.swing.JTable tabla) {
                javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) tabla.getModel();
                modelo2 = modelo;
                sorter2.setRowFilter(null);
                tabla.setRowSorter(null);
                sorter2 = new TableRowSorter<>(modelo); // re asigna el sorter

            }
            

    
    public CategoriasTabla() {
        initComponents();
        setLocationRelativeTo(null);
        categoria.mostrarCategoriasEnTabla(jTableCategorias);
        
        DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
        centrado.setHorizontalAlignment(SwingConstants.CENTER);
        
        TableColumnModel columnModel = jTableCategorias.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(20);//id
        columnModel.getColumn(1).setPreferredWidth(250);//nombre

        //  Aplicar el centrado a la columna "id" (índice 0)
        jTableCategorias.getColumnModel().getColumn(0).setCellRenderer(centrado);

        
        
        subcategoria.mostrarSubCategoriasEnTabla(jTableSubCategorias);
        
        
        
        TableColumnModel columnModel2 = jTableSubCategorias.getColumnModel();
        columnModel2.getColumn(0).setPreferredWidth(5);//id
        columnModel2.getColumn(1).setPreferredWidth(150);//nombre
        columnModel2.getColumn(2).setPreferredWidth(150);////subcatepadre

        //  Aplicar el centrado a la columna "id" (índice 0)
        jTableSubCategorias.getColumnModel().getColumn(0).setCellRenderer(centrado);

        
//    //INICIALIZAR SORTER
//    DefaultTableModel modelo = (DefaultTableModel) jTableCategorias.getModel();
//        sorter = new TableRowSorter<>(modelo); // ¡aquí sí lo llenas!
//        jTableCategorias.setRowSorter(sorter);
//
//    //INICIALIZAR SORTER    
//    DefaultTableModel modelo2 = (DefaultTableModel) jTableSubCategorias.getModel();
//        sorter2 = new TableRowSorter<>(modelo2); // ¡aquí sí lo llenas!
//        jTableSubCategorias.setRowSorter(sorter2);
            
        //fondo blanco
        getContentPane().setBackground(new Color(255, 255, 255)); // blanco
        
        //modelo = categorias
        //modelo1 = subcategorias
        
        
        
        modelo = (DefaultTableModel) jTableCategorias.getModel(); // Asignas el modelo
        sorter = new TableRowSorter<>(modelo);
        jTableCategorias.setRowSorter(sorter); // Conectas el sorter a la tabla
        // Vinculas el filtro con el JTextField
         jTextFieldBuscarCategoria.getDocument().addDocumentListener(new DocumentListener() {
        public void insertUpdate(DocumentEvent e) { filtrar(sorter); }
        public void changedUpdate(DocumentEvent e) { filtrar(sorter); }
        public void removeUpdate(DocumentEvent e) { filtrar(sorter); }
    });
        
         ///Subcategorias buscador
        modelo2 = (DefaultTableModel) jTableSubCategorias.getModel(); // Asignas el modelo
        sorter2 = new TableRowSorter<>(modelo2);
        jTableSubCategorias.setRowSorter(sorter2); // Conectas el sorter a la tabla
        // Vinculas el filtro con el JTextField
        jTextFieldBuscarSubCategoria.getDocument().addDocumentListener(new DocumentListener() {
        public void insertUpdate(DocumentEvent e) { filtrar2(sorter2); }
        public void changedUpdate(DocumentEvent e) { filtrar2(sorter2); }
        public void removeUpdate(DocumentEvent e) { filtrar2(sorter2); }
    });
         
         
         
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCategorias = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableSubCategorias = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Cabecera = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        Lateral = new javax.swing.JPanel();
        jButtonAddCat = new javax.swing.JButton();
        jButtonModCat = new javax.swing.JButton();
        jButtonEliminarCat = new javax.swing.JButton();
        jButtonAddSUBCat = new javax.swing.JButton();
        jButtonModSUBCat = new javax.swing.JButton();
        jButtonEliminarSUBcat = new javax.swing.JButton();
        jButtonVolver = new javax.swing.JButton();
        jButtonSalir = new javax.swing.JButton();
        jTextFieldBuscarCategoria = new javax.swing.JTextField();
        jTextFieldBuscarSubCategoria = new javax.swing.JTextField();
        jComboCategorias = new javax.swing.JComboBox<>();
        jComboSubCat = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Categorias");

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel2.setText("Buscador");

        jTableCategorias.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jTableCategorias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID_Categorias", "nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableCategorias);

        jTableSubCategorias.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jTableSubCategorias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID_SubCategorias", "nombre", "CategoriaPadre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableSubCategorias);

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 36)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Sub Categorias");

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel4.setText("Buscador");

        Cabecera.setBackground(new java.awt.Color(171, 0, 0));
        Cabecera.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Categorias y Subcategorias");
        Cabecera.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 0, 1040, 60));

        Lateral.setBackground(new java.awt.Color(238, 0, 0));
        Lateral.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonAddCat.setBackground(new java.awt.Color(238, 0, 0));
        jButtonAddCat.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jButtonAddCat.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAddCat.setText("Agregar Categoria");
        jButtonAddCat.setBorder(null);
        jButtonAddCat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonAddCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddCatActionPerformed(evt);
            }
        });
        Lateral.add(jButtonAddCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 100));

        jButtonModCat.setBackground(new java.awt.Color(238, 0, 0));
        jButtonModCat.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jButtonModCat.setForeground(new java.awt.Color(255, 255, 255));
        jButtonModCat.setText("Modificar Categoria");
        jButtonModCat.setBorder(null);
        jButtonModCat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonModCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModCatActionPerformed(evt);
            }
        });
        Lateral.add(jButtonModCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 440, 90));

        jButtonEliminarCat.setBackground(new java.awt.Color(238, 0, 0));
        jButtonEliminarCat.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jButtonEliminarCat.setForeground(new java.awt.Color(255, 255, 255));
        jButtonEliminarCat.setText("Eliminar Categoria");
        jButtonEliminarCat.setBorder(null);
        jButtonEliminarCat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonEliminarCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarCatActionPerformed(evt);
            }
        });
        Lateral.add(jButtonEliminarCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 440, 80));

        jButtonAddSUBCat.setBackground(new java.awt.Color(238, 0, 0));
        jButtonAddSUBCat.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jButtonAddSUBCat.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAddSUBCat.setText("Agregar Sub Categoria");
        jButtonAddSUBCat.setBorder(null);
        jButtonAddSUBCat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonAddSUBCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddSUBCatActionPerformed(evt);
            }
        });
        Lateral.add(jButtonAddSUBCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 440, 90));

        jButtonModSUBCat.setBackground(new java.awt.Color(238, 0, 0));
        jButtonModSUBCat.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jButtonModSUBCat.setForeground(new java.awt.Color(255, 255, 255));
        jButtonModSUBCat.setText("Modificar Sub Categoria");
        jButtonModSUBCat.setBorder(null);
        jButtonModSUBCat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonModSUBCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModSUBCatActionPerformed(evt);
            }
        });
        Lateral.add(jButtonModSUBCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 440, 90));

        jButtonEliminarSUBcat.setBackground(new java.awt.Color(238, 0, 0));
        jButtonEliminarSUBcat.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jButtonEliminarSUBcat.setForeground(new java.awt.Color(255, 255, 255));
        jButtonEliminarSUBcat.setText("Eliminar Sub Categoria");
        jButtonEliminarSUBcat.setBorder(null);
        jButtonEliminarSUBcat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonEliminarSUBcat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarSUBcatActionPerformed(evt);
            }
        });
        Lateral.add(jButtonEliminarSUBcat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, 440, 70));

        jButtonVolver.setBackground(new java.awt.Color(238, 0, 0));
        jButtonVolver.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jButtonVolver.setForeground(new java.awt.Color(255, 255, 255));
        jButtonVolver.setText("Volver al menú");
        jButtonVolver.setBorder(null);
        jButtonVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVolverActionPerformed(evt);
            }
        });
        Lateral.add(jButtonVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 590, 440, 70));

        jButtonSalir.setBackground(new java.awt.Color(238, 0, 0));
        jButtonSalir.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jButtonSalir.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSalir.setText("Salir");
        jButtonSalir.setBorder(null);
        jButtonSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });
        Lateral.add(jButtonSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 660, 440, 60));

        jTextFieldBuscarCategoria.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jTextFieldBuscarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBuscarCategoriaActionPerformed(evt);
            }
        });

        jTextFieldBuscarSubCategoria.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jTextFieldBuscarSubCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBuscarSubCategoriaActionPerformed(evt);
            }
        });

        jComboCategorias.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jComboCategorias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Nombre" }));
        jComboCategorias.setSelectedIndex(1);
        jComboCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboCategoriasActionPerformed(evt);
            }
        });

        jComboSubCat.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jComboSubCat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Nombre", "Categoria Padre" }));
        jComboSubCat.setSelectedIndex(1);

        jButton4.setBackground(new java.awt.Color(238, 0, 0));
        jButton4.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("LIMPIAR");
        jButton4.setBorder(null);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(238, 0, 0));
        jButton5.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("LIMPIAR");
        jButton5.setBorder(null);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Cabecera, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 35, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldBuscarSubCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboSubCat, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldBuscarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(33, 33, 33)
                .addComponent(Lateral, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Cabecera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextFieldBuscarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextFieldBuscarSubCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboSubCat, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(Lateral, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddCatActionPerformed
        //validamos permisos según el rol
        if("Supervisor".equals(Login.rol) || "Vendedor".equals(Login.rol)){
            JOptionPane.showMessageDialog(null, "Usted no cuenta con los permisos suficientes para acceder a este apartado",
                "Acceso Denegado", JOptionPane.WARNING_MESSAGE);
            return;
        }
        new CategoriaAGREGAR(jTableCategorias,sorter,jTextFieldBuscarCategoria,jComboCategorias).setVisible(true);
    }//GEN-LAST:event_jButtonAddCatActionPerformed

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButtonSalirActionPerformed

    private void jButtonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVolverActionPerformed
        new MenuPrincipal().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonVolverActionPerformed

    private void jButtonModCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModCatActionPerformed
        //validamos permisos según el rol
        if("Supervisor".equals(Login.rol) || "Vendedor".equals(Login.rol)){
            JOptionPane.showMessageDialog(null, "Usted no cuenta con los permisos suficientes para acceder a este apartado",
                "Acceso Denegado", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int fila = jTableCategorias.getSelectedRow(); // Obtenemos la fila seleccionada

        // Validamos que se haya seleccionado un cliente
        if (fila < 0) {
            JOptionPane.showMessageDialog(this,
                "Debe seleccionar un producto.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return; // detenemos el flujo
        }
        
        //guardamos los datos de la fila seleccionada
        categoriaId = jTableCategorias.getValueAt(fila, 0).toString(); // ID de la categoria seleccionada
        nombre = jTableCategorias.getValueAt(fila, 1).toString(); // nombre de la categoria seleccionada
        new CategoriaMODIFICAR(jTableCategorias,sorter,jTextFieldBuscarCategoria,jComboCategorias).setVisible(true);
    }//GEN-LAST:event_jButtonModCatActionPerformed

    private void jButtonEliminarCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarCatActionPerformed
       //validamos permisos según el rol
        if("Supervisor".equals(Login.rol) || "Vendedor".equals(Login.rol)){
            JOptionPane.showMessageDialog(null, "Usted no cuenta con los permisos suficientes para acceder a este apartado",
                "Acceso Denegado", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int fila = jTableCategorias.getSelectedRow(); // Obtenemos la fila seleccionada

        // Validamos que se haya seleccionado un cliente
        if (fila < 0) {
            JOptionPane.showMessageDialog(this,
                "Debe seleccionar una fila.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return; // detenemos el flujo
        }
        
        //guardamos los datos de la fila seleccionada
        categoriaId = jTableCategorias.getValueAt(fila, 0).toString(); // ID del producto seleccionado
        nombre = jTableCategorias.getValueAt(fila, 1).toString(); // nombre del producto seleccionado
            
            
                try{    
                    id_categoria = Integer.parseInt(categoriaId);

                    int opcion = JOptionPane.showConfirmDialog(
                            null,
                            "¿Está seguro de que desea eliminar a la categoria?\n¡Este será borrado permanentemente de la base de datos!",
                            "Confirmar Eliminación",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

                    if (opcion == JOptionPane.YES_OPTION) {
                            categoria cat = new categoria();
                            cat.eliminarCategoria(id_categoria);
                            JOptionPane.showMessageDialog(
                                null,
                                "La categoria de ID: "+id_categoria+" ha sido eliminado correctamente.",
                                "Eliminado",
                                JOptionPane.INFORMATION_MESSAGE
                            );
                            
                            jTextFieldBuscarCategoria.setText("");      // limpia buscador
                            sorter.setRowFilter(null);
                          
                            categoria.mostrarCategoriasEnTabla(jTableCategorias);
                                
                            // Si sabes que el modelo es DefaultTableModel
                            sorter = new TableRowSorter<DefaultTableModel>((DefaultTableModel) jTableCategorias.getModel());
                            jTableCategorias.setRowSorter(sorter);

                            
                    } else {
                            JOptionPane.showMessageDialog(
                                null,
                                "Operación cancelada. La categoria no fue eliminado.",
                                "Cancelado",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }

                }catch(NumberFormatException e){
                            JOptionPane.showMessageDialog(null,"¡El ID de la categoria tiene que ser un entero!","Alerta",JOptionPane.WARNING_MESSAGE);
                }            
    
    }//GEN-LAST:event_jButtonEliminarCatActionPerformed

    private void jButtonAddSUBCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddSUBCatActionPerformed
        //validamos permisos según el rol
        if("Supervisor".equals(Login.rol) || "Vendedor".equals(Login.rol)){
            JOptionPane.showMessageDialog(null, "Usted no cuenta con los permisos suficientes para acceder a este apartado",
                "Acceso Denegado", JOptionPane.WARNING_MESSAGE);
            return;
        }
        new CategoriaHijaEscogerCategoriaPadre(jTableSubCategorias,sorter2,jTextFieldBuscarSubCategoria,jComboSubCat).setVisible(true);
        
    }//GEN-LAST:event_jButtonAddSUBCatActionPerformed

    private void jButtonModSUBCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModSUBCatActionPerformed
        //validamos permisos según el rol
        if("Supervisor".equals(Login.rol) || "Vendedor".equals(Login.rol)){
            JOptionPane.showMessageDialog(null, "Usted no cuenta con los permisos suficientes para acceder a este apartado",
                "Acceso Denegado", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int fila = jTableSubCategorias.getSelectedRow(); // Obtenemos la fila seleccionada

        // Validamos que se haya seleccionado una categoria
        if (fila < 0) {
            JOptionPane.showMessageDialog(this,
                "Debe seleccionar una categoria.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return; // detenemos el flujo
        }
        
        //guardamos los datos de la fila seleccionada
        subcatid = jTableSubCategorias.getValueAt(fila, 0).toString(); // ID de la subcategoria seleccionada
        subcatnombre = jTableSubCategorias.getValueAt(fila, 1).toString(); // nombre de la subcategoria seleccionada
        id_categoria_padre = jTableSubCategorias.getValueAt(fila, 2).toString(); // id_categoria_padre de la subcategoria seleccionada
        new CategoriaHijaMODIFICAR(jTableSubCategorias,sorter2,jTextFieldBuscarSubCategoria,jComboSubCat).setVisible(true);
    }//GEN-LAST:event_jButtonModSUBCatActionPerformed

    private void jButtonEliminarSUBcatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarSUBcatActionPerformed
        //validamos permisos según el rol
        if("Supervisor".equals(Login.rol) || "Vendedor".equals(Login.rol)){
            JOptionPane.showMessageDialog(null, "Usted no cuenta con los permisos suficientes para acceder a este apartado",
                "Acceso Denegado", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int fila = jTableSubCategorias.getSelectedRow(); // Obtenemos la fila seleccionada

        // Validamos que se haya seleccionado un cliente
        if (fila < 0) {
            JOptionPane.showMessageDialog(this,
                "Debe seleccionar una fila.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return; // detenemos el flujo
        }
        
        //guardamos los datos de la fila seleccionada
        subcatid = jTableSubCategorias.getValueAt(fila, 0).toString(); // ID del categoria seleccionado
        subcatnombre = jTableSubCategorias.getValueAt(fila, 1).toString(); // nombre del cateogira seleccionado
        id_categoria_padre = jTableSubCategorias.getValueAt(fila, 0).toString(); // ID de la categoria padre seleccionado

            
            
                try{    
                    id_subcategoria = Integer.parseInt(subcatid);

                    int opcion = JOptionPane.showConfirmDialog(
                            null,
                            "¿Está seguro de que desea eliminar al producto?\n¡Este será borrado permanentemente de la base de datos!",
                            "Confirmar Eliminación",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

                    if (opcion == JOptionPane.YES_OPTION) {
                            subcategoria subcat = new subcategoria();
                            subcat.eliminarSubCategoria(id_subcategoria);
                            JOptionPane.showMessageDialog(
                                null,
                                "La Subcategoria de ID: "+id_subcategoria+" ha sido eliminado correctamente.",
                                "Eliminado",
                                JOptionPane.INFORMATION_MESSAGE
                            );
                            
                            jTextFieldBuscarSubCategoria.setText("");      // limpia buscador
                            sorter2.setRowFilter(null);
                            subcategoria.mostrarSubCategoriasEnTabla(jTableSubCategorias);
                            sorter2 = new TableRowSorter<DefaultTableModel>((DefaultTableModel) jTableSubCategorias.getModel());
                            jTableSubCategorias.setRowSorter(sorter2);
                            
                            
                            
                    } else {
                            JOptionPane.showMessageDialog(
                                null,
                                "Operación cancelada. La Subcategoria no fue eliminado.",
                                "Cancelado",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }

                }catch(NumberFormatException e){
                            JOptionPane.showMessageDialog(null,"¡El ID de la Subcategoria tiene que ser un entero!","Alerta",JOptionPane.WARNING_MESSAGE);
                }
    
    }//GEN-LAST:event_jButtonEliminarSUBcatActionPerformed

    private void jTextFieldBuscarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBuscarCategoriaActionPerformed
//        jTableCategorias = new JTable(modelo);
//        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
//        jTableCategorias.setRowSorter(sorter);
//        // Campo de búsqueda
//
//        jTextFieldBuscarCategoria.getDocument().addDocumentListener(new DocumentListener() {
//            public void insertUpdate(DocumentEvent e) { filtrar(sorter); }
//            public void removeUpdate(DocumentEvent e) { filtrar(sorter); }
//            public void changedUpdate(DocumentEvent e) { filtrar(sorter); }
//        });
    }//GEN-LAST:event_jTextFieldBuscarCategoriaActionPerformed

    private void jTextFieldBuscarSubCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBuscarSubCategoriaActionPerformed
//        jTableSubCategorias = new JTable(modelo2);
//        TableRowSorter<DefaultTableModel> sorter2 = new TableRowSorter<>(modelo2);
//        jTableSubCategorias.setRowSorter(sorter2);
//        // Campo de búsqueda
//
//        jTextFieldBuscarSubCategoria.getDocument().addDocumentListener(new DocumentListener() {
//            public void insertUpdate(DocumentEvent e) { filtrar2(sorter2); }
//            public void removeUpdate(DocumentEvent e) { filtrar2(sorter2); }
//            public void changedUpdate(DocumentEvent e) { filtrar2(sorter2); }
//        });
    }//GEN-LAST:event_jTextFieldBuscarSubCategoriaActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        jTextFieldBuscarCategoria.setText("");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        jTextFieldBuscarSubCategoria.setText("");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jComboCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboCategoriasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboCategoriasActionPerformed

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
            java.util.logging.Logger.getLogger(CategoriasTabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CategoriasTabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CategoriasTabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CategoriasTabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CategoriasTabla().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Cabecera;
    private javax.swing.JPanel Lateral;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButtonAddCat;
    private javax.swing.JButton jButtonAddSUBCat;
    private javax.swing.JButton jButtonEliminarCat;
    private javax.swing.JButton jButtonEliminarSUBcat;
    private javax.swing.JButton jButtonModCat;
    private javax.swing.JButton jButtonModSUBCat;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JButton jButtonVolver;
    private javax.swing.JComboBox<String> jComboCategorias;
    private javax.swing.JComboBox<String> jComboSubCat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableCategorias;
    private javax.swing.JTable jTableSubCategorias;
    private javax.swing.JTextField jTextFieldBuscarCategoria;
    private javax.swing.JTextField jTextFieldBuscarSubCategoria;
    // End of variables declaration//GEN-END:variables
}
