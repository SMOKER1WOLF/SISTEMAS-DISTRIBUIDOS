
package Controladores;

import Entidades.Producto;
import P_Conexion.Conexion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/ProductoControlador")
public class ProductoControlador extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ProductoControlador() {
        super();
    }

    // Método para editar un producto
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        double precio = Double.parseDouble(request.getParameter("precio"));
        String descripcion = request.getParameter("descripcion");

        Producto producto = new Producto(id, nombre, precio, descripcion);
        actualizarProducto(producto);
        response.sendRedirect("producto.jsp");
    }

    // Método para buscar un producto por su ID
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Producto producto = obtenerProducto(id);
        request.setAttribute("producto", producto);
        request.getRequestDispatcher("producto.jsp").forward(request, response);
    }

    // Método para eliminar un producto
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        eliminarProducto(id);
        response.sendRedirect("producto.jsp");
    }

    // Función para actualizar producto en la base de datos
    private void actualizarProducto(Producto producto) {
        try (Connection conn = Conexion.getConnection()) {
            String query = "UPDATE productos SET nombre=?, precio=?, descripcion=? WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, producto.getNombre());
            stmt.setDouble(2, producto.getPrecio());
            stmt.setString(3, producto.getDescripcion());
            stmt.setInt(4, producto.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Función para obtener un producto por su ID
    private Producto obtenerProducto(int id) {
        Producto producto = null;
        try (Connection conn = Conexion.getConnection()) {
            String query = "SELECT * FROM productos WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                producto = new Producto(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio"), rs.getString("descripcion"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return producto;
    }

    // Función para eliminar un producto
    private void eliminarProducto(int id) {
        try (Connection conn = Conexion.getConnection()) {
            String query = "DELETE FROM productos WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
