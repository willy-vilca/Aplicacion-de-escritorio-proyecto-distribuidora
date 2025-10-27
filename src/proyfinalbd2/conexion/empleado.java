
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

public class empleado {
    private int dni;
    private String nombre;
    private String rol;
    private String usuario;
    private String clave;
    private java.sql.Date fecha_registro;

    public empleado() {
    }

    public empleado(int dni, String nombre, String rol, String usuario,String clave) {
        this.dni=dni;
        this.nombre = nombre;
        this.rol = rol;
        this.usuario = usuario;
        this.clave = clave;
    }
    
    public empleado(int dni, String nombre, String rol, String usuario,String clave, java.sql.Date fecha) {
        this.dni=dni;
        this.nombre = nombre;
        this.rol = rol;
        this.usuario = usuario;
        this.clave = clave;
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
   
    
    public java.sql.Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(java.sql.Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
    
    
    //mostrar cliente en una tabla
    public static void mostrarEmpleadosEnTabla(JTable tablaClientes) {
        // Crear un nuevo modelo de tabla con los encabezados
        String[] titulos = {"ID","DNI","Nombre", "Rol", "Usuario", "Clave","FechaRegistro"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        // Consulta SQL
        
                String sql =
                    "SELECT * FROM empleados;";

        
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
            JOptionPane.showMessageDialog(null, "Error al mostrar empleados en la tabla: " + e.toString());
        }
    }
    
    
    //insertar cliente a la BD con la fecha actual por defecto
    public void insertarEmpleado(){
        conexion con=new conexion();
        String sql="insert into empleados(dni,nombre,rol,usuario,clave) values(?,?,?,?,?);";
        
        try(    Connection conn = con.establecerConexion();   
                CallableStatement cs=conn.prepareCall(sql)){
            
            cs.setInt(1, this.dni);
            cs.setString(2, this.nombre);
            cs.setString(3, this.rol);
            cs.setString(4, this.usuario);
            cs.setString(5, this.clave);
            cs.execute();
            
            //JOptionPane.showMessageDialog(null, "cliente registrado con exito");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error: "+e.toString());
        }
    }
    
    
    //insertar cliente en la BD con una fecha específica
    public void insertarEmpleadoConFecha(){
        conexion con=new conexion();
        String sql="insert into empleados(dni,nombre,rol,usuario,clave,fecha_registro) values(?,?,?,?,?,?);";
        
        try(    Connection conn = con.establecerConexion();
                CallableStatement cs=conn.prepareCall(sql)){
            cs.setInt(1, this.dni);
            cs.setString(2, this.nombre);
            cs.setString(3, this.rol);
            cs.setString(4, this.usuario);
            cs.setString(5, this.clave);
            cs.setDate(6, this.fecha_registro);
            cs.execute();
            
            //JOptionPane.showMessageDialog(null, "cliente registrado con exito");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error: "+e.toString());
        }
    }
    
    
    //modificar cliente por id en la BD
    public void modificarEmpleado(int id){
        conexion con=new conexion();
        String sql="update empleados set dni=?,nombre=?, rol=?, usuario=?, clave=?, fecha_registro=? where id_empleado=?;";
        
        try(    Connection conn = con.establecerConexion();
                CallableStatement cs=conn.prepareCall(sql)){
            
            cs.setInt(1, this.dni);
            cs.setString(2, this.nombre);
            cs.setString(3, this.rol);
            cs.setString(4, this.usuario);
            cs.setString(5,this.clave);
            cs.setDate(6, this.fecha_registro);
            cs.setInt(7, id);
            cs.execute();
            
            //JOptionPane.showMessageDialog(null, "cliente modificado con exito");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error: "+e.toString());
        }
    }
    
    //mostrar datos del cliente seleccionado en la ventana modificar
    public void mostrarDatosEmpleadoAModificar(String id,JTextField inputNombre,JTextField inputEmail,JTextField inputTelefono){
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
    public void eliminarEmpleado(int id){
        conexion con=new conexion();
        String sql="delete from clientes where id_empleado=?;";
        
        try(    Connection conn = con.establecerConexion();
                CallableStatement cs=conn.prepareCall(sql)){
            
            cs.setInt(1, id);
            cs.execute();
            
            //JOptionPane.showMessageDialog(null, "cliente eliminado con exito");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error: "+e.toString());
        }
    }
    
    
// validar usuario y clave en la BD, devolviendo el rol si existe
public static String validarEmpleado(String usuario, String clave) {
    conexion con = new conexion();
    String sql = "SELECT rol FROM empleados WHERE usuario = ? AND clave = ?;";
    String rol = null;

    try (Connection conn = con.establecerConexion();
         CallableStatement cs = conn.prepareCall(sql)) {

        cs.setString(1, usuario);
        cs.setString(2, clave);

        try (ResultSet rs = cs.executeQuery()) {
            if (rs.next()) {
                rol = rs.getString("rol");
            }
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al validar usuario: " + e.toString());
    }

    return rol; // si es null, el acceso falló
}


    
}
