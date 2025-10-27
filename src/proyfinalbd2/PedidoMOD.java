/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyfinalbd2;

import java.awt.Color;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import static proyfinalbd2.PedidoEscogerCliente.pFecha;
import proyfinalbd2.conexion.detalle_pedido;
import proyfinalbd2.conexion.pedidos;
import proyfinalbd2.conexion.producto;

/**
 *
 * @author ASUS
 */
public class PedidoMOD extends javax.swing.JFrame {
    private DefaultTableModel modelo;
    private DefaultTableModel modelo2;
    TableRowSorter<DefaultTableModel> sorter;
    TableRowSorter<DefaultTableModel> sorter2;
    public JTable tablaPedidosMenuPrincipal;
    TableRowSorter<DefaultTableModel> SORTER;
    JTextField BUSCADOR;
    JComboBox COMBO;
    JLabel CONTADOR;
    
    

    private void filtrar(TableRowSorter<DefaultTableModel> sorter) {
        String texto = jTextFieldBuscarProducto.getText();
        
        int columna = jComboColumnasProductos.getSelectedIndex();
        
        if (texto.trim().length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto, columna));
        }
        
        
        
    }
    private void filtrar2(TableRowSorter<DefaultTableModel> sorter2) {
        String texto = jTextFieldBuscarPPedido.getText();
        int columna = jComboColumnasPedidos.getSelectedIndex();
        
        if (texto.trim().length() == 0) {
            sorter2.setRowFilter(null);
        } else {
            sorter2.setRowFilter(RowFilter.regexFilter("(?i)" + texto, columna));
        }
    }
    
    private void calcularTotalImporte() {
    double total = 0.0;
    //definimos una tabla modelo la cual no se verá afectada por los filtros al momento de calcular el total
    DefaultTableModel tablaPedidosModelo = (DefaultTableModel) jTablePedido.getModel();
    for (int i = 0; i < tablaPedidosModelo.getRowCount(); i++) {
        Object valor = tablaPedidosModelo.getValueAt(i, 4); // Columna 5 (índice 4)

        if (valor != null && !valor.toString().isEmpty()) {
            try {
                double importe = Double.parseDouble(valor.toString());
                total += importe;
            } catch (NumberFormatException e) {
                // Manejo si el valor no es un número
                System.out.println("Valor no válido en fila " + i + ": " + valor);
            }
        }
    }

    // Mostrar el total en el JLabel
    jLabelImrpoteTotal.setText("Total: S/ " + String.format("%.2f", total));
}
    
    
        private static boolean existeId(DefaultTableModel modelo, String id) {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            if (modelo.getValueAt(i, 0).toString().equals(id)) { // columna 0 es ID
                return true;
            }
        }
        return false;
    }
    
    
        // Método para buscar el índice de fila por ID
    private static int buscarFilaPorId(DefaultTableModel modelo, String id) {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            if (modelo.getValueAt(i, 0).toString().equals(id)) { // columna 0 = ID
                return i;
            }
        }
        return -1; // no encontrado
    }
    
        //Spinner se coloca en rojo si se intenta dismuir y queda en negativo 
    private void resaltarSpinner(JSpinner spinner) {

    JComponent editor = spinner.getEditor();
    JFormattedTextField txt = ((JSpinner.DefaultEditor) editor).getTextField();

        spinner.setBackground(Color.PINK);   // Fondo rojo/rosado
        spinner.setForeground(Color.BLACK);  // Texto negro

        
        Timer timer = new Timer(300, e -> spinner.setBackground(Color.WHITE)); //vuelve al color originar despues de 1.5 segundos
        timer.setRepeats(false); // Solo una vez
        timer.start();
}
    
    

    /**
     * Creates new form PedidoEscogerCliente
     */
    public PedidoMOD(JTable tablaPedidosMenuPrincipal,TableRowSorter<DefaultTableModel> SORTER,JTextField BUSCADOR,JComboBox COMBO,JLabel CONTADOR) {
        initComponents();
        this.tablaPedidosMenuPrincipal=tablaPedidosMenuPrincipal;
        this.BUSCADOR=BUSCADOR;
        this.SORTER=SORTER;
        this.COMBO=COMBO;
        this.CONTADOR=CONTADOR;
        setLocationRelativeTo(null);
        setDefaultCloseOperation(PedidosTabla.DISPOSE_ON_CLOSE);
        detalle_pedido.mostrarDetallesPedidoTabla(PedidoEscogerPedido.id_pedido, jTablePedido);
        producto.obtenerTablaProductosParaPedidos(jTableProductos);

        
        getContentPane().setBackground(Color.WHITE);
        
        //        Spinner no permite numeros negativos
        SpinnerNumberModel modelos = new SpinnerNumberModel(0, 0, null, 1);
        jSpinnerCantidad.setModel(modelos);
        
        // centrar datos y ajustar columnas de la tabla productos
        TableColumnModel columnModel = jTableProductos.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(20);//id
        columnModel.getColumn(1).setPreferredWidth(50);//cod
        columnModel.getColumn(2).setPreferredWidth(100);////nombre
        columnModel.getColumn(3).setPreferredWidth(50);//precio
        columnModel.getColumn(4).setPreferredWidth(50);//stock
        
        DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
        centrado.setHorizontalAlignment(SwingConstants.CENTER);

        //  Aplicar el centrado a la columna "id" (índice 0)
        jTableProductos.getColumnModel().getColumn(0).setCellRenderer(centrado);
        jTableProductos.getColumnModel().getColumn(1).setCellRenderer(centrado);
        jTableProductos.getColumnModel().getColumn(3).setCellRenderer(centrado);
        jTableProductos.getColumnModel().getColumn(4).setCellRenderer(centrado);
        
        // centrar datos y ajustar columnas de la tabla pedido
        TableColumnModel columnModel1 = jTablePedido.getColumnModel();
        columnModel1.getColumn(0).setPreferredWidth(20);//id
        columnModel1.getColumn(1).setPreferredWidth(150);//nombre
        columnModel1.getColumn(2).setPreferredWidth(50);////cant
        columnModel1.getColumn(3).setPreferredWidth(50);//precio
        columnModel1.getColumn(4).setPreferredWidth(50);//importe
        

        //  Aplicar el centrado a la columna "id" (índice 0)
        jTablePedido.getColumnModel().getColumn(0).setCellRenderer(centrado);
        jTablePedido.getColumnModel().getColumn(2).setCellRenderer(centrado);
        jTablePedido.getColumnModel().getColumn(3).setCellRenderer(centrado);
        jTablePedido.getColumnModel().getColumn(4).setCellRenderer(centrado);
        
        calcularTotalImporte();
        jLabelCLIENTES.setText(PedidoEscogerPedido.id_cliente + " - " + PedidoEscogerPedido.nombre_cliente);
        
        modelo = (DefaultTableModel) jTableProductos.getModel(); // Asignas el modelo
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
        jTableProductos.setRowSorter(sorter); // Conectas el sorter a la tabla
            
        modelo2 = (DefaultTableModel) jTablePedido.getModel(); // Asignas el modelo
        TableRowSorter<DefaultTableModel> sorter2 = new TableRowSorter<>(modelo2);
        jTablePedido.setRowSorter(sorter2); // Conectas el sorter a la tabla

    // Vinculas el filtro con el JTextField
    jTextFieldBuscarProducto.getDocument().addDocumentListener(new DocumentListener() {
        public void insertUpdate(DocumentEvent e) { filtrar(sorter); }
        public void changedUpdate(DocumentEvent e) { filtrar(sorter); }
        public void removeUpdate(DocumentEvent e) { filtrar(sorter); }
    });
       // Vinculas el filtro con el JTextField
    jTextFieldBuscarPPedido.getDocument().addDocumentListener(new DocumentListener() {
        public void insertUpdate(DocumentEvent e) { filtrar2(sorter2); }
        public void changedUpdate(DocumentEvent e) { filtrar2(sorter2); }
        public void removeUpdate(DocumentEvent e) { filtrar2(sorter2); }
    });
    
    

    }
    /**
     * Creates new form DatosCliente
     */


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButtonPedido = new javax.swing.JButton();
        jTextFieldBuscarProducto = new javax.swing.JTextField();
        jButtonAgregarProducto = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePedido = new javax.swing.JTable();
        jSpinnerCantidad = new javax.swing.JSpinner();
        jLabelCLIENTES = new javax.swing.JLabel();
        jButtonQuitarProducto = new javax.swing.JButton();
        jButtonActualizarCant = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldBuscarPPedido = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableProductos = new javax.swing.JTable();
        jComboColumnasProductos = new javax.swing.JComboBox<>();
        jComboColumnasPedidos = new javax.swing.JComboBox<>();
        jLabelImrpoteTotal = new javax.swing.JLabel();
        Cabecera = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel2.setText("Cliente : ");

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel4.setText("Cantidad :");

        jButtonPedido.setBackground(new java.awt.Color(238, 0, 0));
        jButtonPedido.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jButtonPedido.setForeground(new java.awt.Color(255, 255, 255));
        jButtonPedido.setText("Actualizar");
        jButtonPedido.setBorder(null);
        jButtonPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPedidoActionPerformed(evt);
            }
        });

        jTextFieldBuscarProducto.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jTextFieldBuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBuscarProductoActionPerformed(evt);
            }
        });

        jButtonAgregarProducto.setBackground(new java.awt.Color(238, 0, 0));
        jButtonAgregarProducto.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jButtonAgregarProducto.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAgregarProducto.setText("Agregar producto");
        jButtonAgregarProducto.setBorder(null);
        jButtonAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarProductoActionPerformed(evt);
            }
        });

        jTablePedido.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jTablePedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Cantidad", "P.Unitario", "Importe"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Double.class
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
        jScrollPane1.setViewportView(jTablePedido);

        jLabelCLIENTES.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabelCLIENTES.setText("CLIENTE");

        jButtonQuitarProducto.setBackground(new java.awt.Color(238, 0, 0));
        jButtonQuitarProducto.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jButtonQuitarProducto.setForeground(new java.awt.Color(255, 255, 255));
        jButtonQuitarProducto.setText("Quitar producto");
        jButtonQuitarProducto.setBorder(null);
        jButtonQuitarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQuitarProductoActionPerformed(evt);
            }
        });

        jButtonActualizarCant.setBackground(new java.awt.Color(238, 0, 0));
        jButtonActualizarCant.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jButtonActualizarCant.setForeground(new java.awt.Color(255, 255, 255));
        jButtonActualizarCant.setText("Actualizar cantidad");
        jButtonActualizarCant.setBorder(null);
        jButtonActualizarCant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarCantActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel6.setText("Buscar Producto");

        jTextFieldBuscarPPedido.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jTextFieldBuscarPPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBuscarPPedidoActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel7.setText("Producto");

        jButton1.setText("+1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("+5");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("+10");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("+25");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("-1");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton8.setText("-5");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("-10");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("-25");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jTableProductos.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jTableProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
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
                "ID", "Codigo", "Nombre", "P.Unitario", "Stock"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
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
        jScrollPane5.setViewportView(jTableProductos);

        jComboColumnasProductos.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jComboColumnasProductos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Nombre", "Precio" }));

        jComboColumnasPedidos.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jComboColumnasPedidos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Nombre", "Cantidad", "P.Unitario", "Importe" }));

        jLabelImrpoteTotal.setFont(new java.awt.Font("Roboto", 1, 48)); // NOI18N
        jLabelImrpoteTotal.setText("Total: S/0,00");

        Cabecera.setBackground(new java.awt.Color(174, 0, 0));
        Cabecera.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("MODIFICAR PEDIDO");
        Cabecera.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1160, 70));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelCLIENTES, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton4))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton6)
                                .addGap(18, 18, 18)
                                .addComponent(jButton8)
                                .addGap(18, 18, 18)
                                .addComponent(jButton9)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton10)
                            .addComponent(jButton5))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(18, 18, 18)
                                    .addComponent(jTextFieldBuscarProducto)
                                    .addGap(18, 18, 18)
                                    .addComponent(jComboColumnasProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                                .addComponent(jButtonAgregarProducto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonQuitarProducto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonActualizarCant, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(18, 18, 18)
                                    .addComponent(jTextFieldBuscarPPedido)
                                    .addGap(18, 18, 18)
                                    .addComponent(jComboColumnasPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(120, 120, 120)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSpinnerCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButtonPedido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 45, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelImrpoteTotal)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addComponent(Cabecera, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Cabecera, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboColumnasProductos)
                            .addComponent(jTextFieldBuscarProducto))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jSpinnerCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton4)
                                .addComponent(jButton5)
                                .addComponent(jButton3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton6)
                            .addComponent(jButton8)
                            .addComponent(jButton9)
                            .addComponent(jButton10))
                        .addGap(18, 18, 18)
                        .addComponent(jButtonAgregarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboColumnasPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                            .addComponent(jTextFieldBuscarPPedido)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonActualizarCant, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonQuitarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelImrpoteTotal)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelCLIENTES, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane1)))
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPedidoActionPerformed
    DefaultTableModel modeloPedido = (DefaultTableModel) jTablePedido.getModel();
    
    //long fecha = pFecha.getTime();
    //java.sql.Date fecha_sql = new java.sql.Date(fecha);
    
    
    String idCliente = PedidoEscogerPedido.id_cliente;      
    String nombreCliente = PedidoEscogerPedido.nombre_cliente;
    //java.sql.Date fechaPedido = fecha_sql;          
    String total = jLabelImrpoteTotal.getText();     

        if (modeloPedido.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this,
                "No hay productos en el pedido. Agregue al menos uno.",
                "Pedido vacío", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // eliminamos los detalles del pedido para cambiarlos por los nuevos
        pedidos.eliminarDetallesPedido(PedidoEscogerPedido.id_pedido);
        //obtenemos los datos de la tabla de detalles del pedido para guardarlos y reemplazar los anteriores
        for (int i = 0; i < modeloPedido.getRowCount(); i++) {
            int id_producto = Integer.parseInt(modeloPedido.getValueAt(i, 0).toString());
            int cantidad = Integer.parseInt(modeloPedido.getValueAt(i, 2).toString());
            
            detalle_pedido.insertarDetalle_pedido(PedidoEscogerPedido.id_pedido, id_producto, cantidad);
        }
        
        //actualizamos el total del pedido
        pedidos.actualizarTotal(PedidoEscogerPedido.id_pedido);
        
        
        BUSCADOR.setText("");      // limpia buscador
        SORTER.setRowFilter(null);
        
        //actualizamos la tabla de pedidos del menu principal de pedidos
        pedidos.MostrarTablaPedidos(this.tablaPedidosMenuPrincipal);
        TableColumnModel columnModel = this.tablaPedidosMenuPrincipal.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(20);//id pedido
        columnModel.getColumn(1).setPreferredWidth(200);//id-cliente
        columnModel.getColumn(2).setPreferredWidth(150);//direccion
        columnModel.getColumn(3).setPreferredWidth(100);//id-ruta
        columnModel.getColumn(4).setPreferredWidth(70);//total
        columnModel.getColumn(5).setPreferredWidth(70);//fecha

        
        DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
        centrado.setHorizontalAlignment(SwingConstants.CENTER);

        //  Aplicar el centrado a la columna "id" (índice 0)
        this.tablaPedidosMenuPrincipal.getColumnModel().getColumn(0).setCellRenderer(centrado);
        this.tablaPedidosMenuPrincipal.getColumnModel().getColumn(4).setCellRenderer(centrado);
        this.tablaPedidosMenuPrincipal.getColumnModel().getColumn(5).setCellRenderer(centrado);
        
        SORTER = new TableRowSorter<DefaultTableModel>((DefaultTableModel) this.tablaPedidosMenuPrincipal.getModel());
        this.tablaPedidosMenuPrincipal.setRowSorter(SORTER);
        BUSCADOR.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { filtrar(); }
            @Override
            public void removeUpdate(DocumentEvent e) { filtrar(); }
            @Override
            public void changedUpdate(DocumentEvent e) { filtrar(); }

            private void filtrar() {
                String texto = BUSCADOR.getText();
                if (texto.trim().length() == 0) {
                    SORTER.setRowFilter(null);
                } else {
                    SORTER.setRowFilter(RowFilter.regexFilter("(?i)" + texto, COMBO.getSelectedIndex()));
                }
            }
        });

        
        JOptionPane.showMessageDialog(this,
            "Pedido registrado exitosamente.\n\n" +
            "ID Cliente: " + idCliente + "\n" +
            "Nombre: " + nombreCliente + "\n" +
            //"Fecha del Pedido: " + fechaPedido + "\n" +
            total,
            "Éxito", JOptionPane.INFORMATION_MESSAGE);

        // (Opcional) Limpieza de tabla después del registro
        modeloPedido.setRowCount(0);
        // Cerrar solo esta ventana
        dispose();

        
    }//GEN-LAST:event_jButtonPedidoActionPerformed

    private void jTextFieldBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBuscarProductoActionPerformed
        // TODO add your handling code here:
        
        jTableProductos = new JTable(modelo);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
        jTableProductos.setRowSorter(sorter);
        // Campo de búsqueda

        jTextFieldBuscarProducto.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { filtrar(sorter); }
            public void removeUpdate(DocumentEvent e) { filtrar(sorter); }
            public void changedUpdate(DocumentEvent e) { filtrar(sorter); }
        });
    }//GEN-LAST:event_jTextFieldBuscarProductoActionPerformed

    private void jButtonQuitarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQuitarProductoActionPerformed
        // TODO add your handling code here:
            int fila = jTablePedido.getSelectedRow();

        if (fila >= 0) {
            int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Está seguro que desea eliminar este producto del pedido?\nEsta acción no se puede deshacer.",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

            if (confirmacion == JOptionPane.YES_OPTION) {
                DefaultTableModel modeloPedido = (DefaultTableModel) jTablePedido.getModel();
                int filaReal = jTablePedido.convertRowIndexToModel(fila);
                int idproducto = Integer.parseInt(modeloPedido.getValueAt(fila, 0).toString());
                int cant = Integer.parseInt(modeloPedido.getValueAt(fila, 2).toString());
                producto.devolverStock(idproducto,cant);
                modeloPedido.removeRow(filaReal);
                producto.obtenerTablaProductosParaPedidos(jTableProductos);

                JOptionPane.showMessageDialog(this,
                    "Producto eliminado correctamente.",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this,
                "Debe seleccionar un producto del pedido para eliminar.",
                "Error", JOptionPane.ERROR_MESSAGE);
        }
        calcularTotalImporte();
    }//GEN-LAST:event_jButtonQuitarProductoActionPerformed

    private void jTextFieldBuscarPPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBuscarPPedidoActionPerformed
        // TODO add your handling code here:
        jTablePedido = new JTable(modelo2);
        TableRowSorter<DefaultTableModel> sorter2 = new TableRowSorter<>(modelo2);
        jTablePedido.setRowSorter(sorter2);
        // Campo de búsqueda

        jTextFieldBuscarPPedido.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { filtrar2(sorter2); }
            public void removeUpdate(DocumentEvent e) { filtrar2(sorter2); }
            public void changedUpdate(DocumentEvent e) { filtrar2(sorter2); }
        });

    }//GEN-LAST:event_jTextFieldBuscarPPedidoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Object n = jSpinnerCantidad.getValue();
        int numero = (int) n; //
        int nvalor = numero+1;
        Object obj = nvalor;
        jSpinnerCantidad.setValue(obj);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonActualizarCantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarCantActionPerformed
        // TODO add your handling code here:
        int fila = jTablePedido.getSelectedRow();
           if (fila >= 0) {
               
                DefaultTableModel modeloPedido = (DefaultTableModel) jTablePedido.getModel();

               int filaReal = jTablePedido.convertRowIndexToModel(fila); // Corregido
               int cantantigua = Integer.parseInt(modeloPedido.getValueAt(filaReal, 2).toString());

               String input = JOptionPane.showInputDialog(this, "Ingrese la nueva cantidad:");
               int idproducto = Integer.parseInt(modeloPedido.getValueAt(filaReal, 0).toString());


               if (input != null) {
                   try {
                       int nuevaCant = Integer.parseInt(input);
                       
                       int cantidadagregada= nuevaCant-cantantigua;

                       
                       if (nuevaCant <= 0) {
                           JOptionPane.showMessageDialog(this,
                               "La cantidad debe ser mayor que cero.",
                               "Cantidad inválida", JOptionPane.WARNING_MESSAGE);
                           return;
                       }

                       

                       if (pedidos.comprobarStock(idproducto, nuevaCant)) {
                       double precio = Double.parseDouble(modeloPedido.getValueAt(filaReal, 3).toString());
                       double nuevoSubtotal = nuevaCant * precio;
                       
                       
                       if (cantidadagregada>0) {
                           producto.reducirStock(idproducto, cantidadagregada);
                       }else{
                           cantidadagregada=cantidadagregada*-1;
                           producto.devolverStock(idproducto, cantidadagregada);
                       }
                       producto.obtenerTablaProductosParaPedidos(jTableProductos);

                       // Actualizar cantidad y subtotal
                       modeloPedido.setValueAt(nuevaCant, filaReal, 2); // Cantidad
                       modeloPedido.setValueAt(nuevoSubtotal, filaReal, 4); // Subtotal

                       JOptionPane.showMessageDialog(this,
                           "Cantidad y subtotal actualizados correctamente.",
                           "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(this,
                               "STOCK INSUFICIENTE",
                               "STOCK INSUFICIENTE", JOptionPane.WARNING_MESSAGE);
                        }
                   } catch (NumberFormatException e) {
                       JOptionPane.showMessageDialog(this,
                           "Debe ingresar un número entero válido.",
                           "Error de formato", JOptionPane.ERROR_MESSAGE);
                   }
               }

           } else {
               JOptionPane.showMessageDialog(this,
                   "Seleccione un producto del pedido.",
                   "ERROR", JOptionPane.ERROR_MESSAGE);
           }
           calcularTotalImporte();
    }//GEN-LAST:event_jButtonActualizarCantActionPerformed

    private void jButtonAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarProductoActionPerformed
        // TODO add your handling code here:
        int fila = jTableProductos.getSelectedRow();
        if (fila >= 0) {
            int filaReal = jTableProductos.convertRowIndexToModel(fila);
            String id = modelo.getValueAt(filaReal, 0).toString();
            String nombre = modelo.getValueAt(filaReal, 1).toString();
            Double precio = Double.parseDouble(modelo.getValueAt(filaReal, 3).toString());
                        int id_producto = Integer.parseInt(modelo.getValueAt(filaReal, 0).toString());

            try {
                int valor = Integer.parseInt(jSpinnerCantidad.getValue().toString());

                if (valor <= 0) {
                    resaltarSpinner(jSpinnerCantidad);
                    return; // 🔹 corta la acción del botón
                }
                // Si llega aquí, significa que el valor es válido (>0)

            } catch (NumberFormatException ex) {
                resaltarSpinner(jSpinnerCantidad);
            }
            
                        //Validad cantidad ingresada menor o igual pero no mayor al stock           
            try {
                int valor = Integer.parseInt(jSpinnerCantidad.getValue().toString());
                int stockd = Integer.parseInt(modelo.getValueAt(filaReal,4).toString());
                if (valor > stockd) {
                    JOptionPane.showMessageDialog(
                        null, 
                        "Stock no disponible", 
                        "Alerta", 
                        JOptionPane.WARNING_MESSAGE
                    );
                    return; // 🔹 corta la acción del botón
                }
                // Si llega aquí, significa que el valor es válido (>0)

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        null, 
                        "Stock no disponible", 
                        "Alerta", 
                        JOptionPane.WARNING_MESSAGE
                    );
            }
            
            int cant = Integer.parseInt(String.valueOf(jSpinnerCantidad.getValue()));
            double subtotal = cant*precio;
            
            DefaultTableModel modeloPedido = (DefaultTableModel) jTablePedido.getModel();
        
        int filapedido = buscarFilaPorId(modeloPedido, id);

        
        if (!existeId(modeloPedido, id)) {
                    modeloPedido.addRow(new Object[]{id, nombre, cant, precio, subtotal});
                    JOptionPane.showMessageDialog(this,
            "Producto agregado exitosamente:\n" +
            "ID Producto: " + id + " - " + nombre + "\n" +
            "Precio: " + precio + "\n" +
            "Cantidad: " + cant + "\n" +
            "Subtotal: " +  subtotal,
            "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    //redurcir stock
            producto.reducirStock(id_producto,cant);
            
            producto.obtenerTablaProductosParaPedidos(jTableProductos);        
                    
                } else {
                    // Ya existe -> seleccionar fila
                    jTablePedido.setRowSelectionInterval(filapedido, filapedido);
                    jTablePedido.scrollRectToVisible(jTablePedido.getCellRect(filapedido, 0, true));
                    
                    JOptionPane.showMessageDialog(null,
                            "El Producto " + nombre + " ya existe en el pedido",
                            "Duplicado",
                            JOptionPane.WARNING_MESSAGE);
                }
        
        
//        modeloPedido.addRow(new Object[]{id, nombre, cant, precio, subtotal});
        }
        calcularTotalImporte();
    }//GEN-LAST:event_jButtonAgregarProductoActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
                // TODO add your handling code here:
        Object n = jSpinnerCantidad.getValue();
        int numero = (int) n; //
        int nvalor = numero-1;
        Object obj = nvalor;
        if(nvalor<0){
        resaltarSpinner(jSpinnerCantidad);   
        }else{
        jSpinnerCantidad.setValue(obj);    
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
                // TODO add your handling code here:
        Object n = jSpinnerCantidad.getValue();
        int numero = (int) n; //
        int nvalor = numero+5;
        Object obj = nvalor;
        jSpinnerCantidad.setValue(obj);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
                // TODO add your handling code here:
        Object n = jSpinnerCantidad.getValue();
        int numero = (int) n; //
        int nvalor = numero-5;
        Object obj = nvalor;
                if(nvalor<0){
        resaltarSpinner(jSpinnerCantidad);   
        }else{
        jSpinnerCantidad.setValue(obj);    
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
                // TODO add your handling code here:
        Object n = jSpinnerCantidad.getValue();
        int numero = (int) n; //
        int nvalor = numero+10;
        Object obj = nvalor;
        jSpinnerCantidad.setValue(obj);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
                // TODO add your handling code here:
        Object n = jSpinnerCantidad.getValue();
        int numero = (int) n; //
        int nvalor = numero-10;
        Object obj = nvalor;
                if(nvalor<0){
        resaltarSpinner(jSpinnerCantidad);   
        }else{
        jSpinnerCantidad.setValue(obj);    
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
                // TODO add your handling code here:
        Object n = jSpinnerCantidad.getValue();
        int numero = (int) n; //
        int nvalor = numero+25;
        Object obj = nvalor;
        jSpinnerCantidad.setValue(obj);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
                // TODO add your handling code here:
        Object n = jSpinnerCantidad.getValue();
        int numero = (int) n; //
        int nvalor = numero-25;
        Object obj = nvalor;
                if(nvalor<0){
        resaltarSpinner(jSpinnerCantidad);   
        }else{
        jSpinnerCantidad.setValue(obj);    
        }
    }//GEN-LAST:event_jButton10ActionPerformed

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
            java.util.logging.Logger.getLogger(PedidoMOD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PedidoMOD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PedidoMOD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PedidoMOD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PedidoMOD().setVisible(true);
            }
        });*/
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Cabecera;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButtonActualizarCant;
    private javax.swing.JButton jButtonAgregarProducto;
    private javax.swing.JButton jButtonPedido;
    private javax.swing.JButton jButtonQuitarProducto;
    private javax.swing.JComboBox<String> jComboColumnasPedidos;
    private javax.swing.JComboBox<String> jComboColumnasProductos;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelCLIENTES;
    private javax.swing.JLabel jLabelImrpoteTotal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSpinner jSpinnerCantidad;
    private javax.swing.JTable jTablePedido;
    private javax.swing.JTable jTableProductos;
    private javax.swing.JTextField jTextFieldBuscarPPedido;
    private javax.swing.JTextField jTextFieldBuscarProducto;
    // End of variables declaration//GEN-END:variables
}
