
package proyfinalbd2.conexion;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class subcategoria {
    private String nombre;
    private int id_categoria_padre;

    public subcategoria() {
    }

    public subcategoria(String nombre,int id_categoria_padre) {
        this.nombre = nombre;
        this.id_categoria_padre=id_categoria_padre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_categoria_padre() {
        return id_categoria_padre;
    }

    public void setId_categoria_padre(int id_categoria_padre) {
        this.id_categoria_padre = id_categoria_padre;
    }

    
    
    //mostrar subcategoria en una tabla
    public static void mostrarSubCategoriasEnTabla(JTable tablaSubCategorias) {
        // Crear un nuevo modelo de tabla con los encabezados
        String[] titulos = {"ID", "Nombre","ID-Categoria Padre"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        // Consulta SQL
//        String sql = "SELECT * FROM subcategoria ORDER BY id_subcategoria ASC";
                // Consulta SQL
        String sql = "SELECT \n" +
        "    s.id_subcategoria,\n" +
        "    s.nombre AS nombre_subcategoria,\n" +
        "    c.id_categoria || '-' || c.nombre AS categoria_padre\n" +
        "FROM subcategoria s\n" +
        "INNER JOIN categoria c \n" +
        "    ON s.id_categoria_padre = c.id_categoria\n" +
        "ORDER BY s.id_subcategoria ASC;";

        // Arreglo para almacenar los datos de cada fila
        String[] datos = new String[3];

        conexion c = new conexion(); // tu clase de conexión
        

        try(Connection conn = c.establecerConexion();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);    ) {
            

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);

                modelo.addRow(datos);
            }

            // Asignar el modelo al JTable
            tablaSubCategorias.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar SUBcategorias en la tabla: " + e.toString());
        }
    }
     
    //insertar subcategorias a la BD
    public void insertarSubCategoria(){
        conexion con=new conexion();
        String sql="insert into subcategoria(nombre,id_categoria_padre) values(?,?);";
        
        try(Connection conn = con.establecerConexion();
            CallableStatement cs=conn.prepareCall(sql)){
            
            cs.setString(1, this.nombre);
            cs.setInt(2, this.id_categoria_padre);
            cs.execute();
            
            //JOptionPane.showMessageDialog(null, "Categoria registrado con exito");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error: "+e.toString());
        }
    }
    
    //modificar Subcategorias en la BD
    public void modificarSubCategoria(String nom,int idcat,int idsubcat){
        conexion con=new conexion();
        String sql="update subcategoria set nombre=?, id_categoria_padre=? where id_subcategoria=?;";
        
        try(Connection conn = con.establecerConexion();
            CallableStatement cs=conn.prepareCall(sql)){
            
            cs.setString(1,nom);
            cs.setInt(2,idcat);
            cs.setInt(3, idsubcat);
            cs.execute();
            
            //JOptionPane.showMessageDialog(null, "Categoria modificada con exito");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error: "+e.toString());
        }
    }
    
    //mostrar datos de la subcategoria seleccionado en la ventana modificar
    public void mostrarDatosSubCategoriaAModificar(String id,JTextField inputNombre,JTextField inputid_categoria_padre){
        conexion c=new conexion();
        String sql="";
        
        sql="select * from subcategoria where id_subcategoria="+id+";";
        
        String[] datos=new String[3];
        
        
        
        try(Connection conn = c.establecerConexion();
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(sql)){
            
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                
                inputNombre.setText(datos[1]);
                inputid_categoria_padre.setText(datos[2]);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error: "+e.toString());
        }
    }
    
    //eliminar una subcategoria de la BD
    public void eliminarSubCategoria(int id){
        conexion con=new conexion();
        String sql="delete from subcategoria where id_subcategoria=?;";
        
        try(Connection conn = con.establecerConexion();
            CallableStatement cs=conn.prepareCall(sql);){
            
            cs.setInt(1, id);
            cs.execute();
            
            //JOptionPane.showMessageDialog(null, "categoria eliminada con exito");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error: "+e.toString());
        }
    }
}
