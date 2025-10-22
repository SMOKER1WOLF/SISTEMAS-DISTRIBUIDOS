package Controladores;

import Entidades.Producto;
import P_Conexion.ProductoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ProductoControlador", urlPatterns = {"/ProductoControlador"})
public class ProductoControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String op = request.getParameter("op");
        ProductoDAO dao = new ProductoDAO();

        try {
            switch (op) {
                case "listar" -> {
                    ArrayList<Producto> lista = dao.listar();
                    request.setAttribute("listaProductos", lista);
                    request.getRequestDispatcher("listarProductos.jsp").forward(request, response);
                }

                case "nuevo" -> 
                    request.getRequestDispatcher("nuevoProducto.jsp").forward(request, response);

                case "obtener" -> { // 👈 para abrir el formulario de edición
                    String id = request.getParameter("id");
                    Producto producto = dao.obtenerPorId(id);
                    request.setAttribute("producto", producto);
                    request.getRequestDispatcher("modificarProducto.jsp").forward(request, response);
                }

                case "eliminar" -> {
                    String idEliminar = request.getParameter("id");
                    dao.eliminar(idEliminar);
                    response.sendRedirect("ProductoControlador?op=listar");
                }

                // 🔍 NUEVO: BÚSQUEDA DE PRODUCTOS
                case "buscar" -> {
                    String texto = request.getParameter("texto");
                    ArrayList<Producto> lista = dao.buscar(texto);
                    request.setAttribute("listaProductos", lista);
                    request.getRequestDispatcher("listarProductos.jsp").forward(request, response);
                }

                default -> 
                    response.getWriter().println("Operación GET no reconocida: " + op);
            }

        } catch (ServletException | IOException e) {
            response.getWriter().println("⚠️ Error en ProductoControlador (GET): " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String op = request.getParameter("op");
        ProductoDAO dao = new ProductoDAO();

        try {
            switch (op) {
                case "insertar" -> {
                    Producto nuevo = new Producto();
                    nuevo.setIdArticulo(request.getParameter("idArticulo"));
                    nuevo.setNombre(request.getParameter("nombre"));
                    nuevo.setCantidad(request.getParameter("cantidad"));
                    nuevo.setPrecio(request.getParameter("precio"));
                    dao.insertar(nuevo);
                    response.sendRedirect("ProductoControlador?op=listar");
                }

                case "actualizar" -> { // 👈 para guardar los cambios de edición
                    Producto actualizado = new Producto();
                    actualizado.setIdArticulo(request.getParameter("idArticulo"));
                    actualizado.setNombre(request.getParameter("nombre"));
                    actualizado.setCantidad(request.getParameter("cantidad"));
                    actualizado.setPrecio(request.getParameter("precio"));
                    dao.actualizar(actualizado);
                    response.sendRedirect("ProductoControlador?op=listar");
                }

                default -> 
                    response.getWriter().println("Operación POST no reconocida: " + op);
            }

        } catch (IOException e) {
            response.getWriter().println("⚠️ Error en ProductoControlador (POST): " + e.getMessage());
        }
    }
}
