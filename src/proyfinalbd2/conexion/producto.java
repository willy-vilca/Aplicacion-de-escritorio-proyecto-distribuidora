
package proyfinalbd2.conexion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class producto {
    private String cod;
    private String nombre;
    private String descripcion;
    private double precio;
    private int id_subcat;
    private int stock;

    public producto() {
    }
    public producto(String cod, String nombre, String descripcion, double precio, int id_subcat, int stock) {
        this.cod = cod;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.id_subcat = id_subcat;
        this.stock = stock;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getId_subcat() {
        return id_subcat;
    }

    public void setId_subcat(int id_subcat) {
        this.id_subcat = id_subcat;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    


    
    //mostrar productos en una tabla
    public static void mostrarProductosEnTabla(JTable tablaProductos) {
        // Crear un nuevo modelo de tabla con los encabezados
        String[] titulos = {"ID","COD", "Nombre","Descripcion","ID-Subcategoria", "P.Unitario","Stock"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        // Consulta SQL
                String sql =    "SELECT \n" +
                                "    p.id_producto AS \"ID\",\n" +
                                "    p.cod AS \"COD\",\n" +
                                "    p.nombre AS \"Nombre\",\n" +
                                "    p.descripcion AS \"Descripcion\",\n" +
                                "    (s.id_subcategoria || '-' || s.nombre) AS \"ID-Subcategoria\",\n" +
                                "    p.precio AS \"P.Unitario\",\n" +
                                "    p.stock AS \"Stock\"\n" +
                                "FROM productos p\n" +
                                "INNER JOIN subcategoria s \n" +
                                "    ON p.id_subcategoria = s.id_subcategoria\n" +
                                "ORDER BY p.id_producto ASC;";

        // Arreglo para almacenar los datos de cada fila
        String[] datos = new String[7];

        conexion c = new conexion(); // tu clase de conexión
        

        try (Connection conn = c.establecerConexion();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);){
            

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                modelo.addRow(datos);
            }

            // Asignar el modelo al JTable
            tablaProductos.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar productos en la tabla: " + e.toString());
        }
    }
    
    //mostrar productos en una tabla para registrarlos en un pedido
    public static void obtenerTablaProductosParaPedidos(JTable tablaProductos) {
        // Crear un nuevo modelo de tabla con los encabezados
        String[] titulos = {"ID","Código", "Nombre", "Precio","Stock"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        // Consulta SQL
        String sql = "SELECT * FROM productos ORDER BY id_producto ASC";

        // Arreglo para almacenar los datos de cada fila
        String[] datos = new String[5];

        conexion c = new conexion(); // tu clase de conexión
        

        try(Connection conn = c.establecerConexion();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql)) {
            

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(6);
                datos[4] = rs.getString(7);

                modelo.addRow(datos);
            }

            // Asignar el modelo al JTable
            tablaProductos.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar clientes en la tabla: " + e.toString());
        }
    }
    
    //insertar producto a la BD
    public void insertarProducto(){
        conexion con=new conexion();
        String sql="insert into productos(cod,nombre,descripcion,precio,id_subcategoria,stock) values(?,?,?,?,?,?);";
        
        try(Connection conn = con.establecerConexion();
            CallableStatement cs=conn.prepareCall(sql);){
            
            cs.setString(1, this.cod);
            cs.setString(2, this.nombre);
            cs.setString(3, this.descripcion);
            cs.setDouble(4, this.precio);
            cs.setInt(5, this.id_subcat);
            cs.setInt(6, this.stock);
            cs.execute();
            
            //JOptionPane.showMessageDialog(null, "Producto registrado con exito");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error: "+e.toString());
        }
    }
    
    //modificar productos en la BD
    public void modificarProducto(int id){
        conexion con=new conexion();
        String sql="update productos set cod=?, nombre=?, descripcion=?, precio=?, id_subcategoria=?, stock=? where id_producto=?;";
        
        try(Connection conn = con.establecerConexion();
            CallableStatement cs=conn.prepareCall(sql)){
            
            cs.setString(1, this.cod);
            cs.setString(2, this.nombre);
            cs.setString(3, this.descripcion);
            cs.setDouble(4, this.precio);
            cs.setInt(5, this.id_subcat);
            cs.setInt(6, this.stock);
            cs.setInt(7, id);
            cs.execute();
            
            //JOptionPane.showMessageDialog(null, "producto modificado con exito");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error: "+e.toString());
        }
    }
    
    //mostrar datos del producto seleccionado en la ventana modificar
    public void mostrarDatosProductoAModificar(String id,JTextField inputCodigo,JTextField inputNombre,JTextArea inputDescripcion,JTextField inputPrecio,JTextField inputID_subcategoria,JTextField inputStock){
        conexion c=new conexion();
        String sql="";
        
        sql="select * from productos where id_producto="+id+";";
        
        String[] datos=new String[3];
        
        
        
        try(Connection conn = c.establecerConexion();
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(sql);){
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);
                datos[5]=rs.getString(6);
                datos[6]=rs.getString(7);
                
                inputCodigo.setText(datos[1]);
                inputNombre.setText(datos[2]);
                inputDescripcion.setText(datos[3]);
                inputPrecio.setText(datos[4]);
                inputID_subcategoria.setText(datos[5]);
                inputStock.setText(datos[6]);
                
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error: "+e.toString());
        }
    }
    
    //eliminar un producto de la BD
    public void eliminarProducto(int id){
        conexion con=new conexion();
        String sql="delete from productos where id_producto=?;";
        
        try(Connection conn = con.establecerConexion();
           CallableStatement cs=conn.prepareCall(sql);){
            
            cs.setInt(1, id);
            cs.execute();
            
            //JOptionPane.showMessageDialog(null, "producto eliminado con exito");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error: "+e.toString());
        }
    }
    
    public static void reducirStock(int idProducto, int cantidad){
    conexion con = new conexion();
    String sql = "UPDATE productos SET stock = stock - ? WHERE id_producto = ? AND stock >= ?;";

    try(Connection conn = con.establecerConexion();
        CallableStatement cs = conn.prepareCall(sql)) {
        
        cs.setInt(1, cantidad);
        cs.setInt(2, idProducto);
        cs.setInt(3, cantidad); // asegura que no baje de 0
        int filas = cs.executeUpdate();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, " Error: " + e.toString());
    }
}
    
    public static void devolverStock(int idProducto, int cantidad){
    conexion con = new conexion();
    String sql = "UPDATE productos SET stock = stock + ? WHERE id_producto = ?;";

    try (Connection conn = con.establecerConexion();
        CallableStatement cs = conn.prepareCall(sql)){
        
        cs.setInt(1, cantidad);
        cs.setInt(2, idProducto);
        int filas = cs.executeUpdate();

       
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "❌ Error: " + e.toString());
    }
}
    
    
}
