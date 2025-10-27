
package proyfinalbd2.conexion;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class cliente {
    private int dni;
    private String nombre;
    private String email;
    private int telefono;
    private String direccion;
    private java.sql.Date fecha_registro;

    public cliente() {
    }

    public cliente(int dni, String nombre, String email, int telefono,String direccion) {
        this.dni=dni;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
    }
    
    public cliente(int dni,String nombre, String email, int telefono , String direccion, java.sql.Date fecha) {
        this.dni = dni;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fecha_registro = fecha;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    
    
    public java.sql.Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(java.sql.Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
    
    
    //mostrar cliente en una tabla
    public static void mostrarClientesEnTabla(JTable tablaClientes) {
        // Crear un nuevo modelo de tabla con los encabezados
        String[] titulos = {"ID","DNI", "Nombre", "Correo", "Telefono", "Direccion","Registro"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        // Consulta SQL
        
                String sql =
                    "SELECT \n" +
                    "    c.id_cliente AS id,\n" +
                    "    c.dni AS dni,\n" +
                    "    'APP - ' || c.nombre AS nombre,\n" +
                    "    c.email AS correo,\n" +
                    "    c.telefono AS telefono,\n" +
                    "    c.direccion AS direccion,\n" +
                    "    c.fecha_registro AS fecha_registro\n" +
                    "FROM clientes c\n" +
                    "\n" +
                    "UNION ALL\n" +
                    "\n" +
                    "SELECT \n" +
                    "    u.id_usuario AS id,\n" +
                    "    u.dni AS dni,\n" +
                    "    'WEB - ' || u.nombre AS nombre,\n" +
                    "    u.correo AS correo,\n" +
                    "    u.telefono AS telefono,\n" +
                    "    p.direccion AS direccion,\n" +
                    "    p.fecha_pedido AS fecha_registro\n" +
                    "FROM usuarios u\n" +
                    "JOIN LATERAL (\n" +
                    "    SELECT p2.direccion, p2.fecha_pedido\n" +
                    "    FROM pedidos p2\n" +
                    "    WHERE p2.id_usuario = u.id_usuario\n" +
                    "    ORDER BY p2.fecha_pedido DESC\n" +  // 🔹 último pedido
                    "    LIMIT 1\n" +
                    ") p ON TRUE\n" +
                    "ORDER BY id ASC;";

        
//        String sql = "SELECT id_cliente, dni, nombre, email, telefono, direccion, fecha_registro FROM clientes ORDER BY id_cliente ASC;";

        // Arreglo para almacenar los datos de cada fila
        String[] datos = new String[7];

        conexion c = new conexion(); 
        
        try (   Connection conn = c.establecerConexion();
                Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql)){
            

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
            tablaClientes.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar clientes en la tabla: " + e.toString());
        }
    }
    
    //mostrar los clientes registrados y su id en JComboBox para que puedan ser seleccionados
    public static void mostrarClientesEnComboBox(JComboBox<String> comboBox){
        // Consulta SQL
        String sql = "select id_cliente,nombre from clientes order by id_cliente asc;";
        comboBox.removeAllItems();
        
        conexion c = new conexion(); 
        
        try (   Connection conn = c.establecerConexion();
                Statement st= conn.createStatement();
            ResultSet rs = st.executeQuery(sql)){
            

            while (rs.next()) {
                String id = rs.getString(1);
                String cliente = rs.getString(2);
                String opcion = id+"-"+cliente;
                comboBox.addItem(opcion);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los clientes en el comboBox: " + e.toString());
        }
    }
    
    //obtener el nombre de un cliente por su id
    public static String obtenerNombreCliente(int id_cliente){
        // Consulta SQL
        String sql = "select nombre from clientes where id_cliente="+id_cliente+";";

        String nombre = "";

        conexion c = new conexion(); 
        

        try(    Connection conn = c.establecerConexion();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            

            while (rs.next()) {
                nombre = rs.getString(1);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el nombre del cliente: " + e.toString());
        }
        return nombre;
    }
    
    
    public static void obtenerDireccionCliente(int id_cliente,javax.swing.JTextField jDireccion){
        // Consulta SQL
        String sql = "select direccion from clientes where id_cliente="+id_cliente+";";

        String direccion = "";

        conexion c = new conexion(); 
        
        try (   Connection conn = c.establecerConexion();
                Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql)){
            

            while (rs.next()) {
                direccion = rs.getString(1);
            }
        jDireccion.setText(direccion);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el nombre del cliente: " + e.toString());
        }
    }
    
    //mostrar solo el id y el nombre del cliente en una tabla
    public static void mostrarIdYNombresEnTabla(JTable tablaClientes) {
        // Crear un nuevo modelo de tabla con los encabezados
        String[] titulos = {"ID", "Cliente", "Direccion"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        // Consulta SQL

                String sql = """
                SELECT 
                    c.id_cliente AS ID,
                    CONCAT('APP - ', c.nombre) AS Cliente,
                    c.direccion AS Direccion
                FROM clientes c
                WHERE EXISTS (
                    SELECT 1 FROM pedidos p WHERE p.id_cliente = c.id_cliente
                )

                UNION ALL

                SELECT 
                    u.id_usuario AS ID,
                    CONCAT('WEB - ', u.nombre) AS Cliente,
                    (
                        SELECT p.direccion
                        FROM pedidos p
                        WHERE p.id_usuario = u.id_usuario
                        ORDER BY p.fecha_pedido DESC
                        LIMIT 1
                    ) AS Direccion
                FROM usuarios u
                WHERE EXISTS (
                    SELECT 1 FROM pedidos p WHERE p.id_usuario = u.id_usuario
                )
                ORDER BY ID ASC;
                """;


        
//        String sql = "select id_cliente,nombre,direccion from clientes order by id_cliente asc;";

        // Arreglo para almacenar los datos de cada fila
        String[] datos = new String[3];

        conexion c = new conexion(); 

        try (Connection conn = c.establecerConexion();
                Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql)) {
            

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);

                modelo.addRow(datos);
            }

            // Asignar el modelo al JTable
            tablaClientes.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar clientes en la tabla: " + e.toString());
        }
    }
    
    //insertar cliente a la BD con la fecha actual por defecto
    public void insertarCliente(){
        conexion con=new conexion();
        String sql="insert into clientes(dni,nombre,email,telefono,direccion) values(?,?,?,?,?);";
        
        try(    Connection conn = con.establecerConexion();   
                CallableStatement cs=conn.prepareCall(sql)){
            
            cs.setInt(1, this.dni);
            cs.setString(2, this.nombre);
            cs.setString(3, this.email);
            cs.setInt(4, this.telefono);
            cs.setString(5, this.direccion);
            cs.execute();
            
            //JOptionPane.showMessageDialog(null, "cliente registrado con exito");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error: "+e.toString());
        }
    }
    
    
    //insertar cliente en la BD con una fecha específica
    public void insertarClienteConFecha(){
        conexion con=new conexion();
        String sql="insert into clientes(dni,nombre,email,telefono,direccion,fecha_registro) values(?,?,?,?,?,?);";
        
        try(    Connection conn = con.establecerConexion();
                CallableStatement cs=conn.prepareCall(sql)){
            cs.setInt(1, this.dni);
            cs.setString(2, this.nombre);
            cs.setString(3, this.email);
            cs.setInt(4, this.telefono);
            cs.setString(5, this.direccion);
            cs.setDate(6, this.fecha_registro);
            cs.execute();
            
            //JOptionPane.showMessageDialog(null, "cliente registrado con exito");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error: "+e.toString());
        }
    }
    
    
    //modificar cliente por id en la BD
    public void modificarCliente(int id){
        conexion con=new conexion();
        String sql="update clientes set dni=?,nombre=?, email=?, telefono=?, direccion=?, fecha_registro=? where id_cliente=?;";
        
        try(    Connection conn = con.establecerConexion();
                CallableStatement cs=conn.prepareCall(sql)){
            
            cs.setInt(1, this.dni);
            cs.setString(2, this.nombre);
            cs.setString(3, this.email);
            cs.setInt(4, this.telefono);
            cs.setString(5,this.direccion);
            cs.setDate(6, this.fecha_registro);
            cs.setInt(7, id);
            cs.execute();
            
            //JOptionPane.showMessageDialog(null, "cliente modificado con exito");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error: "+e.toString());
        }
    }
    
    //mostrar datos del cliente seleccionado en la ventana modificar
    public void mostrarDatosClienteAModificar(String id,JTextField inputNombre,JTextField inputEmail,JTextField inputTelefono){
        conexion c=new conexion();
        String sql="";
        
        sql="select * from clientes where id_cliente="+id+";";
        
        String[] datos=new String[5];
          
        try(    Connection conn = c.establecerConexion();
                Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(sql)){
            
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);
                
                inputNombre.setText(datos[1]);
                inputEmail.setText(datos[2]);
                inputTelefono.setText(datos[3]);
                
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error: "+e.toString());
        }
    }
    
    
    //eliminar un cliente de la BD
    public void eliminarCliente(int id){
        conexion con=new conexion();
        String sql="delete from clientes where id_cliente=?;";
        
        try(    Connection conn = con.establecerConexion();
                CallableStatement cs=conn.prepareCall(sql)){
            
            cs.setInt(1, id);
            cs.execute();
            
            //JOptionPane.showMessageDialog(null, "cliente eliminado con exito");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error: "+e.toString());
        }
    }
}
