
package proyfinalbd2.conexion;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class detalle_pedido {
    //insertar detalle_pedido indicando el id del pedido
    public static void insertarDetalle_pedido(int id_pedido,int id_producto, int cantidad){
        conexion con=new conexion();
        String sql="insert into detalle_pedido(id_pedido,id_producto,cantidad,subtotal) values(?,?,?,?);";
        
        //se obtiene el precio del producto buscando en la tabla productos a traves de su id
        double precioProducto = obtenerPrecioProducto(id_producto);
        double subtotal = precioProducto*cantidad;
        
        try(    Connection conn = con.establecerConexion();
                CallableStatement cs=conn.prepareCall(sql)){
            
            cs.setInt(1,id_pedido);
            cs.setInt(2,id_producto);
            cs.setInt(3,cantidad);
            cs.setDouble(4,subtotal);
            cs.execute();
            
            //JOptionPane.showMessageDialog(null, "detalle_pedido registrado con exito");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error: "+e.toString());
        }
    }
    
    //obtener el precio de un producto por su id
    public static double obtenerPrecioProducto(int id_producto){
        String sql = "select precio from productos where id_producto="+id_producto+";";

        // Arreglo para almacenar el precio
        double precio=0;

        conexion c = new conexion();
        

        try (   Connection conn = c.establecerConexion();
                Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql)){
            

            while (rs.next()) {
                precio=rs.getDouble(1);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los datos: " + e.toString());
        }
        return precio;
    }
    
    //modificar un detalle_pedido en la bd
    public static void modificarDetallePedido(int id_detalle,int id_pedido,int id_producto,int cantidad){
        conexion con=new conexion();
        String sql="update detalle_pedido set id_pedido=?, id_producto=?, cantidad=?, subtotal=? where id_detalle=?;";
        
        //se obtiene el precio del producto buscando en la tabla productos a traves de su id
        double precioProducto = obtenerPrecioProducto(id_producto);
        double subtotal = precioProducto*cantidad;
        
        try(Connection conn = con.establecerConexion();
            CallableStatement cs=conn.prepareCall(sql)){            
            
            cs.setInt(1, id_pedido); 
            cs.setInt(2, id_producto);
            cs.setInt(3, cantidad);
            cs.setDouble(4, subtotal);
            cs.setInt(5, id_detalle);
            cs.execute();
            
            //JOptionPane.showMessageDialog(null, "DetallePedido modificado con exito");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error: "+e.toString());
        }
    }
    
    //eliminar un detalle_pedido de la bd
    public static void eliminarDetallePedido(int id_detalle){
        conexion con=new conexion();
        String sql="delete from detalle_pedido where id_detalle=?;";
        
        try(Connection conn = con.establecerConexion();
            CallableStatement cs=conn.prepareCall(sql)){   
            
            cs.setInt(1, id_detalle);
            cs.execute();
            
            //JOptionPane.showMessageDialog(null, "DetallePedido eliminado con exito");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error: "+e.toString());
        }
    }
    
    //mostrar detalles de un pedido en una tabla
    public static void mostrarDetallesPedidoTabla(int id_pedido, JTable tablaDetallesPedido){
        // Crear un nuevo modelo de tabla con los encabezados
        String[] titulos = {"ID", "Nombre", "Cantidad", "P.Unitario", "Importe"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        // Consulta SQL
//        String sql = "select d.id_producto,p.nombre,d.cantidad,p.precio,d.subtotal from detalle_pedido as d inner join productos as p on d.id_producto=p.id_producto where id_pedido="+id_pedido+" order by id_detalle asc;";
        String sql="SELECT d.id_producto, p.nombre, d.cantidad, p.precio, d.subtotal FROM detalle_pedido d INNER JOIN productos p ON d.id_producto = p.id_producto WHERE d.id_pedido ="+id_pedido+" ORDER BY d.id_detalle ASC;";
        // Arreglo para almacenar los datos de cada fila
        String[] datos = new String[5];

        conexion c = new conexion(); // tu clase de conexión
        

        try (Connection conn = c.establecerConexion();
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
            tablaDetallesPedido.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar la tabla con los detalles del pedido: " + e.getMessage());
        }
    }
    
    //mostrar detalles de un pedido en específico
    public static void mostrarDetallesPedido(int id_pedido){
        conexion con=new conexion();
        //codigo sql para obtener los datos del pedido
        String sqlPedido="select p.id_pedido,p.id_cliente,c.nombre,p.fecha_pedido,p.total from pedidos as p inner join clientes as c on p.id_cliente=c.id_cliente where p.id_pedido="+id_pedido+";";
        
        //codigo sql para obtener los datos de los detalles del pedido
        String sqlDetalles="update pedidos set total=? where id_pedido=?;";
        
        String[] datosPedido = new String[5];
        String[] datosDetalle = new String[5];
        
        try(Connection conn = con.establecerConexion();
            Statement st=conn.createStatement();
            ResultSet rs = st.executeQuery(sqlPedido)){
            
            while (rs.next()) {
                datosPedido[0]=rs.getString(1);
                datosPedido[1]=rs.getString(2);
                datosPedido[2]=rs.getString(3);
                datosPedido[3]=rs.getString(4);
                datosPedido[4]=rs.getString(5);
                
                System.out.println("id del pedido: "+datosPedido[0]+"- id del cliente: "+datosPedido[1]+"- nombre del cliente: "+datosPedido[2]+"- fecha: "+datosPedido[3]+"- monto total: "+datosPedido[4]);
                System.out.println("-------------------detalle del pedido---------------------");
            }

            
            //JOptionPane.showMessageDialog(null, "total del pedido actualizado con exito");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error: "+e.toString());
        }
    }
}
