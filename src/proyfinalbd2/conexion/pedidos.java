
package proyfinalbd2.conexion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class pedidos {
    //insertar nuevo pedido en la bd postgresql
    public static void insertarPedido(int id_cliente, java.sql.Date fechaPedido, double total,String direccion){
        conexion con=new conexion();
        String sql="insert into pedidos(id_cliente,fecha_pedido,total,direccion) values(?,?,?,?);";
        
        try(Connection conn = con.establecerConexion();
            CallableStatement cs=conn.prepareCall(sql)){
            
            cs.setInt(1,id_cliente);
            cs.setDate(2,fechaPedido);
            cs.setDouble(3,total);
            cs.setString(4,direccion);
            cs.execute();
            
            //JOptionPane.showMessageDialog(null, "Pedido registrado con exito");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error: "+e.toString());
        }
    }
    
    //actualizar el total de un pedido
    public static void actualizarTotal(int id_pedido){
        conexion con=new conexion();
        //codigo sql para obtener el subtotal de los detalles_pedido del pedido que se quiere actualizar
        String sqlSubtotal="select subtotal from detalle_pedido where id_pedido="+id_pedido+";";
        
        //codigo sql para actualizar el total del pedido
        String sqlTotal="update pedidos set total=? where id_pedido=?;";
        
        double total=0;
        try(Connection conn = con.establecerConexion();
            Statement st=conn.createStatement();
            ResultSet rs = st.executeQuery(sqlSubtotal);
            CallableStatement cs=conn.prepareCall(sqlTotal);){

            while (rs.next()) {
                //el total sera igual a la suma de todos los subtotales de ese pedido
                total+=rs.getDouble(1);
            }
            
            //se actualiza el total del pedido
            
            cs.setDouble(1, total); 
            cs.setInt(2, id_pedido);
            cs.execute();
            
            //JOptionPane.showMessageDialog(null, "total del pedido actualizado con exito");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error: "+e.toString());
        }
    }
    
    //modificar pedido en la bd
    public static void modificarPedido(int id_pedido,int nuevoIdCliente,String nuevaFecha){
        conexion con=new conexion();
        String sql="update pedidos set id_cliente=?, fecha_pedido=? where id_pedido=?;";
        
        try(Connection conn = con.establecerConexion();
            CallableStatement cs=conn.prepareCall(sql)){
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
    public static void eliminarPedido(int id_pedido){
        conexion con=new conexion();
        String sql="delete from pedidos where id_pedido=?;";
        
        try(Connection conn = con.establecerConexion();
            CallableStatement cs=conn.prepareCall(sql);){
            //primero eliminamos los detalles del pedido de la bd
            eliminarDetallesPedido(id_pedido);
            
            //ahora eliminamos el pedido de la bd
            
            cs.setInt(1, id_pedido);
            cs.execute();
            
            //JOptionPane.showMessageDialog(null, "pedido eliminado con exito");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error: "+e.toString());
        }
    }
    
    //eliminar los detalle de un pedido
    public static void eliminarDetallesPedido(int id_pedido){
        conexion con=new conexion();
        String sql="delete from detalle_pedido where id_pedido=?;";
        
        try(Connection conn = con.establecerConexion();
            CallableStatement cs=conn.prepareCall(sql);){
            
            cs.setInt(1, id_pedido);
            cs.execute();
            
            //JOptionPane.showMessageDialog(null, "detalles del pedido eliminado con exito");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error: "+e.toString());
        }
    }
    
    //obtener el id_pedido del último pedido registrado
    public static int obtenerUltimoIdPedidoRegistrado(){
        String sql = "select id_pedido from pedidos order by id_pedido desc limit 1;";
        int id_pedido=0;
        
        conexion c = new conexion();
        

        try(Connection conn = c.establecerConexion();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql)) {
            

            while (rs.next()) {
                id_pedido=rs.getInt(1);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectarse con la BD: " + e.toString());
        }
        return id_pedido;
    }
    
    //mostrar datos de los pedidos en una tabla
    public static void MostrarPedidosEnTabla(JTable tablaPedidos){
        // Crear un nuevo modelo de tabla con los encabezados
        String[] titulos = {"ID Pedido", "Usuario/Cliente", "Cliente", "Fecha", "Total"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        // Consulta SQL
//        String sql = "select p.id_pedido,p.id_cliente, c.nombre,p.fecha_pedido,p.total from pedidos as p inner join clientes as c on p.id_cliente=c.id_cliente;";
        String sql="SELECT p.id_pedido AS \"ID Pedido\", CASE WHEN p.id_usuario IS NOT NULL THEN 'Usuario' WHEN p.id_cliente IS NOT NULL THEN 'Cliente' ELSE 'Sin Asignar' END AS \"Usuario / Cliente\", COALESCE(u.nombre, cl.nombre, 'Sin Nombre') AS \"Cliente\", p.fecha_pedido AS \"Fecha\", p.total AS \"Total\" FROM pedidos p LEFT JOIN usuarios u ON p.id_usuario = u.id_usuario LEFT JOIN clientes cl ON p.id_cliente = cl.id_cliente;";
        // Arreglo para almacenar los datos de cada fila
        String[] datos = new String[5];

        conexion c = new conexion(); // tu clase de conexión
        

        try(Connection conn = c.establecerConexion();
            Statement st= conn.createStatement();
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
            tablaPedidos.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar pedidos en la tabla: " + e.toString());
        }
    }
    
    
    //mostrar pedidos en PedidosTabla
    public static void MostrarTablaPedidos(JTable tablaPedidos){
        // Crear un nuevo modelo de tabla con los encabezados
        String[] titulos = {"ID Pedido", "Registro-Cliente", "Direccion", "ID-Ruta", "Total", "Fecha"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        // Consulta SQL
    //        String sql = "select p.id_pedido,p.id_cliente, c.nombre,p.fecha_pedido,p.total from pedidos as p inner join clientes as c on p.id_cliente=c.id_cliente;";
        // 1       String sql = "SELECT p.id_pedido AS \"ID Pedido\", c.id_cliente || ' - ' || c.nombre AS \"ID-Cliente\", p.direccion AS \"Direccion\", r.id_ruta || ' - ' || r.nombre_ruta AS \"ID-Ruta\", p.total AS \"Total\", p.fecha_pedido AS \"Fecha\" FROM pedidos p INNER JOIN clientes c ON p.id_cliente = c.id_cliente INNER JOIN ruta r ON p.id_ruta = r.id_ruta;";
//        String sql = "SELECT p.id_pedido AS \"ID Pedido\", c.id_usuario || ' - ' || c.nombre AS \"ID-Cliente\", p.direccion AS \"Direccion\", COALESCE(r.id_ruta || ' - ' || r.nombre_ruta, 'Sin Asignar') AS \"ID-Ruta\", p.total AS \"Total\", p.fecha_pedido AS \"Fecha\" FROM pedidos p INNER JOIN usuarios c ON p.id_usuario = c.id_usuario LEFT JOIN ruta r ON p.id_ruta = r.id_ruta;";
        // Arreglo para almacenar los datos de cada fila
        String sql = "SELECT p.id_pedido AS \"ID Pedido\", CASE WHEN p.id_usuario IS NOT NULL THEN 'WEB-' || p.id_usuario || ' ' || u.nombre WHEN p.id_cliente IS NOT NULL THEN 'APP-' || p.id_cliente || ' ' || cl.nombre ELSE 'Sin Asignar' END AS \"Origen-Cliente\", p.direccion AS \"Direccion\", COALESCE(r.id_ruta || ' - ' || r.nombre_ruta, 'Sin Asignar') AS \"ID-Ruta\", p.total AS \"Total\", p.fecha_pedido AS \"Fecha\" FROM pedidos p LEFT JOIN usuarios u ON p.id_usuario = u.id_usuario LEFT JOIN clientes cl ON p.id_cliente = cl.id_cliente LEFT JOIN ruta r ON p.id_ruta = r.id_ruta;";
        String[] datos = new String[6];

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
                modelo.addRow(datos);
            }

            // Asignar el modelo al JTable
            tablaPedidos.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar pedidos en la tabla: " + e.toString());
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
        

        try(Connection conn = c.establecerConexion();
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
        

        try(Connection conn = c.establecerConexion();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);) {
            

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
    
    
    //mostrar pedidos de un cliente en específico
    public static void mostrarPedidosDeUnCliente(int id_cliente){
        String sql = "select p.id_pedido,c.nombre,p.fecha_pedido,p.total from pedidos as p inner join clientes as c on p.id_cliente=c.id_cliente where p.id_cliente="+id_cliente+";";

        // Arreglo para almacenar los datos de cada fila
        String[] datos = new String[4];

        conexion c = new conexion();
        

        try(Connection conn = c.establecerConexion();
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

    public static void obtenerTablaPedidosParaRuta(JTable jTablePedidos,String id_ruta) {
                // Crear un nuevo modelo de tabla con los encabezados
        String[] titulos = {"ID-Pedido", "Direccion", "Cliente", "Fecha"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        // Consulta SQL
        String sql =    "SELECT \n" +
                        "    p.id_pedido AS \"ID-Pedido\",\n" +
                        "    p.direccion AS \"Direccion\",\n" +
                        "    COALESCE(c.nombre, u.nombre) AS \"Registrado por\",\n" +
                        "    p.fecha_pedido AS \"Fecha\"\n" +
                        "FROM pedidos p\n" +
                        "LEFT JOIN clientes c ON p.id_cliente = c.id_cliente\n" +
                        "LEFT JOIN usuarios u ON p.id_usuario = u.id_usuario\n" +
                        "WHERE p.id_ruta  " + id_ruta + "\n" +
                        "ORDER BY p.id_pedido ASC;";
        
        
//        String sql =        "SELECT \n" +
//                            "    p.id_pedido AS \"ID-Pedido\",\n" +
//                            "    p.direccion AS \"Direccion\",\n" +
//                            "    c.nombre AS \"Cliente\",\n" +
//                            "    p.fecha_pedido AS \"Fecha\"\n" +
//                            "FROM pedidos p\n" +
//                            "INNER JOIN clientes c ON p.id_cliente = c.id_cliente\n" +
//                            "WHERE p.id_ruta "+id_ruta+"\n" +
//                            "ORDER BY p.id_pedido ASC;";

        // Arreglo para almacenar los datos de cada fila
        String[] datos = new String[4];

        conexion c = new conexion(); // tu clase de conexión
        

        try (Connection conn = c.establecerConexion();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);){

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                modelo.addRow(datos);
            }

            // Asignar el modelo al JTable
            jTablePedidos.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar pedidos para escoger en la tabla: " + e.toString());
        }
    }
    
    public static void obtenerTablaPedidosParaNuevaRuta(JTable jTablePedidos) {
                // Crear un nuevo modelo de tabla con los encabezados
        String[] titulos = {"ID-Pedido", "Direccion", "Cliente", "Fecha"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);


        
                    String sql =    "SELECT \n" +
                            "    p.id_pedido AS \"ID-Pedido\",\n" +
                            "    c.nombre AS \"Cliente\",\n" +
                            "    p.direccion AS \"Direccion\",\n" +
                            "    p.fecha_pedido AS \"Fecha\"\n" +
                            "FROM pedidos p\n" +
                            "INNER JOIN clientes c ON p.id_cliente = c.id_cliente\n" +
                            "INNER JOIN ruta r ON p.id_ruta = r.id_ruta\n" +
                            "WHERE p.id_ruta is NULL\n" +
                            "ORDER BY p.id_pedido;";

        // Arreglo para almacenar los datos de cada fila
        String[] datos = new String[4];

        conexion c = new conexion(); // tu clase de conexión
        

        try (Connection conn = c.establecerConexion();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);){
            

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                modelo.addRow(datos);
            }

            // Asignar el modelo al JTable
            jTablePedidos.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar pedidos para escoger en la tabla: " + e.toString());
        }
    }
    
    
        public static void obtenerPedidosEnRuta(JTable jTablePedidos,int id_ruta) {
                // Crear un nuevo modelo de tabla con los encabezados
        String[] titulos = {"ID-Pedido", "Cliente", "Direccion", "Fecha"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        // Consulta SQL
        
        String sql ="SELECT \n" +
                    "    p.id_pedido AS \"ID-Pedido\",\n" +
                    "    COALESCE(c.nombre, u.nombre) AS \"Registrado por\",\n" +
                    "    p.direccion AS \"Direccion\",\n" +
                    "    p.fecha_pedido AS \"Fecha\"\n" +
                    "FROM pedidos p\n" +
                    "LEFT JOIN clientes c ON p.id_cliente = c.id_cliente\n" +
                    "LEFT JOIN usuarios u ON p.id_usuario = u.id_usuario\n" +
                    "INNER JOIN ruta r ON p.id_ruta = r.id_ruta\n" +
                    "WHERE r.id_ruta = " + id_ruta + "\n" +
                    "ORDER BY p.id_pedido ASC;";

        
//            String sql =    "SELECT \n" +
//                            "    p.id_pedido AS \"ID-Pedido\",\n" +
//                            "    c.nombre AS \"Cliente\",\n" +
//                            "    p.direccion AS \"Direccion\",\n" +
//                            "    p.fecha_pedido AS \"Fecha\"\n" +
//                            "FROM pedidos p\n" +
//                            "INNER JOIN clientes c ON p.id_cliente = c.id_cliente\n" +
//                            "INNER JOIN ruta r ON p.id_ruta = r.id_ruta\n" +
//                            "WHERE r.id_ruta ="+id_ruta+"\n" +
//                            "ORDER BY p.id_pedido;";

        // Arreglo para almacenar los datos de cada fila
        String[] datos = new String[4];

        conexion c = new conexion(); // tu clase de conexión
        

        try(Connection conn = c.establecerConexion();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);) {
           

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                modelo.addRow(datos);
            }

            // Asignar el modelo al JTable
            jTablePedidos.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los pedidos de la ruta en la tabla: " + e.toString());
        }
    }
    
    public static void QuitarPedidoRuta(int idpedido){
        conexion con=new conexion();
        String sql="UPDATE pedidos SET id_ruta = NULL WHERE id_pedido = "+idpedido+";";
        
        try(Connection conn = con.establecerConexion();
            CallableStatement cs=conn.prepareCall(sql)){
            
            cs.execute();
            
            //JOptionPane.showMessageDialog(null, "Pedido registrado con exito");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error: "+e.toString());
        }
    }
    
    
    public static boolean comprobarStock(int idProd, int cantidad) {
        conexion con=new conexion();
        String sql = "SELECT stock FROM productos WHERE id_producto = ?";
        try (   Connection conn = con.establecerConexion();
                CallableStatement cs=conn.prepareCall(sql);) {
            cs.setInt(1, idProd);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    int stockActual = rs.getInt("stock");
                    return stockActual >= cantidad;
                } else {
                    System.out.println("Producto no encontrado.");
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    
    
}
