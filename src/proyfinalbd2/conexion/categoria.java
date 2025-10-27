
package proyfinalbd2.conexion;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class categoria {
    private String nombre;

    public categoria() {
    }

    public categoria(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    
    //mostrar productos en una tabla
    public static void mostrarCategoriasEnTabla(JTable tablaCategorias) {
        // Crear un nuevo modelo de tabla con los encabezados
        String[] titulos = {"ID", "Nombre"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);


        // Consulta SQL
        String sql = "SELECT * FROM categoria ORDER BY id_categoria ASC";

        // Arreglo para almacenar los datos de cada fila
        String[] datos = new String[2];

        conexion c = new conexion(); // tu clase de conexión
        

        try ( 
            Connection conn = c.establecerConexion();    
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql)
        ){
           
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);

                modelo.addRow(datos);
            }

            // Asignar el modelo al JTable
            tablaCategorias.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar categorias en la tabla: " + e.toString());
        }
    }
     
    //insertar categorias a la BD
    public void insertarCategoria(){
        conexion con=new conexion();
        String sql="insert into categoria(nombre) values(?);";
        
        try(    Connection conn = con.establecerConexion();
                CallableStatement cs=conn.prepareCall(sql)){
            
            cs.setString(1, this.nombre);
            cs.execute();
            
            //JOptionPane.showMessageDialog(null, "Categoria registrado con exito");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error: "+e.toString());
        }
    }
    
    //modificar categorias en la BD
    public void modificarCategoria(int id){
        conexion con=new conexion();
        String sql="update categoria set nombre=? where id_categoria=?;";
        
        try(    Connection conn = con.establecerConexion();
                CallableStatement cs=conn.prepareCall(sql)){
            
            cs.setString(1, this.nombre);
            cs.setInt(2, id);
            cs.execute();
            
            //JOptionPane.showMessageDialog(null, "Categoria modificada con exito");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error: "+e.toString());
        }
    }
    
    //mostrar datos de la categoria seleccionado en la ventana modificar
    public void mostrarDatosCategoriaAModificar(String id,JTextField inputNombre){
        conexion c=new conexion();
        String sql="";
        
        sql="select * from categoria where id_categoria="+id+";";
        
        String[] datos=new String[3];
        
        try(    Connection conn = c.establecerConexion();
                Statement st =conn.createStatement();
            ResultSet rs=st.executeQuery(sql)    ){
            
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                
                inputNombre.setText(datos[1]);
                
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error: "+e.toString());
        }
    }
    
    //eliminar una categoria de la BD
    public void eliminarCategoria(int id){
        conexion con=new conexion();
        String sql="delete from categoria where id_categoria=?;";
        
        try(    Connection conn = con.establecerConexion();
                CallableStatement cs=conn.prepareCall(sql)){
            
            cs.setInt(1, id);
            cs.execute();
            
            //JOptionPane.showMessageDialog(null, "categoria eliminada con exito");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error: "+e.toString());
        }
    }
    
        //mostrar solo el id y el nombre de una categoria en una tabla
    public static void mostrarIdYNombresEnTabla(JTable tablaCategorias) {
        // Crear un nuevo modelo de tabla con los encabezados
        String[] titulos = {"ID", "Nombre"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        // Consulta SQL
        String sql = "select id_categoria,nombre from categoria order by id_categoria asc;";

        // Arreglo para almacenar los datos de cada fila
        String[] datos = new String[2];

        conexion c = new conexion(); 
        

        try (   Connection conn = c.establecerConexion();
                Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql)){
            

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);

                modelo.addRow(datos);
            }

            // Asignar el modelo al JTable
            tablaCategorias.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar categoria en la tabla: " + e.toString());
        }
    }
}
