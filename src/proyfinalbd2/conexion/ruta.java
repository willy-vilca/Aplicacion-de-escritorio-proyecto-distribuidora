
package proyfinalbd2.conexion;

import java.sql.CallableStatement;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ruta {
    
    private String nomruta;
    private String descripcion;
    private java.sql.Date fecha_registro;
    private String estado;

    public ruta(){     
    }
    
    public ruta(String nomruta, String descripcion) {
        this.nomruta = nomruta;
        this.descripcion = descripcion;
    }

    public ruta(String nomruta, String descripcion, java.sql.Date fecha_registro) {
        this.nomruta = nomruta;
        this.descripcion = descripcion;
        this.fecha_registro = fecha_registro;
    }

    public String getNomruta() {
        return nomruta;
    }

    public void setNomruta(String nomruta) {
        this.nomruta = nomruta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
      
    //insertar nueva ruta  en la bd postgresql
    public void insertarRuta(){
        conexion con=new conexion();
        String sql="insert into ruta(nombre_ruta,descripcion) values(?,?);";
        
        try(Connection conn = con.establecerConexion();
            CallableStatement cs=conn.prepareCall(sql);){
            
            cs.setString(1, this.nomruta);
            cs.setString(2, this.descripcion);
            cs.execute();
            
            //JOptionPane.showMessageDialog(null, "Ruta registrada con exito");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error: "+e.toString());
        }
    }
    
    //insertar cliente en la BD con una fecha específica
    public void insertarRutaConFecha(){
        conexion con=new conexion();
        String sql="insert into ruta(nombre_ruta,descripcion,fecha_ruta) values(?,?,?);";
        
        try(Connection conn = con.establecerConexion();
            CallableStatement cs=conn.prepareCall(sql);    ){
            
            cs.setString(1, this.nomruta);
            cs.setString(2, this.descripcion);
            cs.setDate(3, this.fecha_registro);
            cs.execute();
            
            //JOptionPane.showMessageDialog(null, "ruta registrado con exito");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error: "+e.toString());
        }
    }
    
    //mostrar tabla ruta en jtable
    public static void mostrarRutasEnTabla(JTable tablaRutas) {
        // Crear un nuevo modelo de tabla con los encabezados
        String[] titulos = {"ID", "Nombre", "Descripcion", "Fecha-Registro", "Estado"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        // Consulta SQL
        String sql = "SELECT * FROM ruta ORDER BY id_ruta ASC;";

        // Arreglo para almacenar los datos de cada fila
        String[] datos = new String[5];

        conexion c = new conexion(); 
        

        try (Connection conn = c.establecerConexion();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql)){
            

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);

                modelo.addRow(datos);
            }

            // Asignar el modelo al JTable
            tablaRutas.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar Rutas en la tabla: " + e.toString());
        }
    }
    //modificar pedido en la bd
    public static void modificarRuta(int id_pedido,int nuevoIdCliente,String nuevaFecha){
        conexion con=new conexion();
        String sql="update pedidos set id_cliente=?, fecha_pedido=? where id_pedido=?;";
        
        try(Connection conn = con.establecerConexion();
            CallableStatement cs=conn.prepareCall(sql);){
            // Parsear el String de la fecha nueva a java.util.Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date fechaUtil = sdf.parse(nuevaFecha);

            // Convertir la fecha a java.sql.Date para poder ingresarla a la bd de postgresql
            java.sql.Date fechaSQL = new java.sql.Date(fechaUtil.getTime());
            
            
            cs.setInt(1, nuevoIdCliente); 
            cs.setDate(2, fechaSQL);
            cs.setInt(3, id_pedido);
            cs.execute();
            
            //JOptionPane.showMessageDialog(null, "Pedido modificado con exito");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error: "+e.toString());
        }
    }
    
    //eliminar un pedido de la BD
    public static void eliminarRuta(int id_ruta){
        conexion con=new conexion();
        String sql="delete from ruta where id_ruta=?;";
        
        try(Connection conn = con.establecerConexion();
            CallableStatement cs=conn.prepareCall(sql);){

            //eliminacion de ruta de la bd
            
            cs.setInt(1, id_ruta);
            cs.execute();
            
            //JOptionPane.showMessageDialog(null, "ruta eliminado con exito");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error: "+e.toString());
        }
    }
    
    //obtener el id_pedido del último pedido registrado
    public static void AsignarPedidosARutas(int id_pedido, int id_ruta){
        String sql = "update pedidos set id_ruta=? where id_pedido=?";
        conexion con = new conexion();

        try (Connection conn = con.establecerConexion();
            CallableStatement cs=conn.prepareCall(sql);){
            
            cs.setInt(1, id_ruta); 
            cs.setInt(2, id_pedido);
            cs.execute();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectarse con la BD: " + e.toString());
        }
    }
      
    //mostrar pedidos y sus detalles en una tabla
    public static void MostrarPedidosYDetallesEnTabla(JTable tablaPedidos){
        // Crear un nuevo modelo de tabla con los encabezados
        String[] titulos = {"ID Pedido", "Cliente", "Fecha", "Producto", "Cantidad", "Subtotal", "Total"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        // Consulta SQL
        String sql = "select d.id_pedido,c.nombre,p.fecha_pedido,prod.nombre,d.cantidad,d.subtotal,p.total from detalle_pedido as d inner join productos as prod on d.id_producto=prod.id_producto inner join pedidos as p on d.id_pedido=p.id_pedido inner join clientes as c on p.id_cliente=c.id_cliente order by id_detalle asc;";

        // Arreglo para almacenar los datos de cada fila
        String[] datos = new String[7];

        conexion c = new conexion(); // tu clase de conexión
        

        try(Connection conn = c.establecerConexion();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);) {
            
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
            tablaPedidos.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar pedidos y detalles en la tabla: " + e.toString());
        }
    }
    
    //mostrar los datos de los pedidos
    public static void mostrarPedidos(){
        String sql = "select * from pedidos order by id_pedido asc;";

        // Arreglo para almacenar los datos de cada fila
        String[] datos = new String[4];

        conexion c = new conexion();
        

        try (Connection conn = c.establecerConexion();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql)) {
            

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                
                System.out.println("id del pedido: "+datos[0]+"-id del cliente: "+datos[1]+"-fecha del pedido: "+datos[2]+"-total del pedido: "+datos[3]);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los datos: " + e.toString());
        }
    }
    
    //mostrar los datos del pedido con el nombre del cliente
    public static void mostrarPedidosConNombreCliente(){
        String sql = "select p.id_pedido,c.nombre,p.fecha_pedido,p.total from pedidos as p inner join clientes as c on p.id_cliente=c.id_cliente;";

        // Arreglo para almacenar los datos de cada fila
        String[] datos = new String[4];

        conexion c = new conexion();
        

        try (Connection conn = c.establecerConexion();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql)) {
            

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                
                System.out.println("id del pedido: "+datos[0]+"-nombre del cliente: "+datos[1]+"-fecha del pedido: "+datos[2]+"-total del pedido: "+datos[3]);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los datos: " + e.toString());
        }
    }
    
    //obtener el id_pedido del último pedido registrado
    public static int obtenerUltimoIdRutaRegistrado(){
        String sql = "select id_ruta from ruta order by id_ruta desc limit 1;";
        int id_ruta=0;
        
        conexion c = new conexion();
        

        try (Connection conn = c.establecerConexion();
            Statement st= conn.createStatement();
            ResultSet rs = st.executeQuery(sql);) {
            

            while (rs.next()) {
                id_ruta=rs.getInt(1);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectarse con la BD: " + e.toString());
        }
        return id_ruta;
    }
    
    //mostrar detalles de un pedido en una tabla
    public static void mostrarPedidosEnRuta(int id_pedido, JTable tablaDetalleRuta){
        // Crear un nuevo modelo de tabla con los encabezados
        String[] titulos = {"ID", "ID-Cliente", "Direccion", "Total", "Fecha"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        // Consulta SQL
        String sql = 
            "SELECT \n" +
            "    p.id_pedido AS \"ID-Pedido\",\n" +
            "    COALESCE(c.id_cliente || ' - ' || c.nombre, u.id_usuario || ' - ' || u.nombre) AS \"Registrado por\",\n" +
            "    p.direccion AS \"Direccion\",\n" +
            "    p.total AS \"Total\",\n" +
            "    p.fecha_pedido AS \"Fecha\"\n" +
            "FROM pedidos p\n" +
            "LEFT JOIN clientes c ON p.id_cliente = c.id_cliente\n" +
            "LEFT JOIN usuarios u ON p.id_usuario = u.id_usuario\n" +
            "WHERE p.id_ruta = " + id_pedido + "\n" +
            "ORDER BY p.id_pedido ASC;";


        
//         = "select d.id_producto,p.nombre,d.cantidad,p.precio,d.subtotal from detalle_pedido as d inner join productos as p on d.id_producto=p.id_producto where id_pedido="+id_pedido+" order by id_detalle asc;";
//            String sql = "SELECT p.id_pedido AS \"ID-Pedido\", c.id_cliente || ' - ' || c.nombre AS \"ID-Cliente\", p.direccion AS \"Direccion\", p.total AS \"Total\", p.fecha_pedido AS \"Fecha\" FROM pedidos p INNER JOIN clientes c ON p.id_cliente = c.id_cliente WHERE p.id_ruta ="+id_pedido+";";
        // Arreglo para almacenar los datos de cada fila
        String[] datos = new String[5];

        conexion c = new conexion(); // tu clase de conexión
        

        try (Connection conn = c.establecerConexion();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql)){
            

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);

                modelo.addRow(datos);
            }

            // Asignar el modelo al JTable
            tablaDetalleRuta.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar la tabla con los detalles del pedido: " + e.getMessage());
        }
    }
    
}
