package Controladores;

import Entidades.Cliente;
import Entidades.Usuario;
import P_Conexion.Conexion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USUARIO
 */
@WebServlet(name = "ClienteControlador", urlPatterns = {"/ClienteControlador"})
public class ClienteControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String Op = request.getParameter("Op");
        ArrayList<Cliente> listaCliente = new ArrayList<Cliente>();

        Conexion conect = new Conexion();
        Connection con = conect.establecerConexion();

        PreparedStatement ps;
        ResultSet rs;

        switch (Op) {
            case "Listar":
            try {
                String sql = "Select * from cliente";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Cliente cli = new Cliente();
                    cli.setIdCliente(rs.getString("idCliente"));
                    cli.setApellidos(rs.getString("apellidos"));
                    cli.setNombres(rs.getString("nombres"));
                    cli.setDireccion(rs.getString("direccion"));
                    cli.setDNI(rs.getString("DNI"));
                    cli.setTelefono(rs.getString("telefono"));
                    cli.setMovil(rs.getString("movil"));

                    listaCliente.add(cli);

                }
                request.setAttribute("Lista", listaCliente);
                request.getRequestDispatcher("listarClientes.jsp").forward(request, response);
            } catch (SQLException ex) {
                System.out.println("Error de SQL +" + ex.getMessage());
            } finally {
                conect.disconnect();
            }

            break;

            case "Consultar":
                String id = request.getParameter("idCliente");
                String jsp = request.getParameter("pagina");
                Cliente cliente= new Cliente();
                try {
                    String sql = "SELECT * FROM cliente WHERE idCliente = ?";
                    ps = con.prepareStatement(sql);
                    ps.setString(1, id);
                    rs = ps.executeQuery();
                    
                    if (rs.next()) {
                        
                        cliente.setIdCliente(rs.getString("idCliente"));
                        cliente.setApellidos(rs.getString("apellidos"));
                        cliente.setNombres(rs.getString("nombres"));
                        cliente.setDireccion(rs.getString("direccion"));
                        cliente.setDNI(rs.getString("DNI"));
                        cliente.setTelefono(rs.getString("telefono"));
                        cliente.setMovil(rs.getString("movil"));
                    }

                } catch (SQLException ex) {
                    System.out.println("Error de SQL +" + ex.getMessage());
                } finally {
                    conect.disconnect();
                }

                request.setAttribute("cliente", cliente);
                request.getRequestDispatcher(jsp).forward(request, response);
                break;

            case "Nuevo":
                break;

            case "Modificar":

                try {
                    String idCliente = request.getParameter("idCliente");
                    String apellidos = request.getParameter("apellidos");
                    String nombres = request.getParameter("nombres");
                    String direccion = request.getParameter("direccion");
                    String DNI = request.getParameter("DNI");
                    String telefono = request.getParameter("telefono");
                    String movil = request.getParameter("movil");

                    String updateSQL = "UPDATE cliente SET apellidos=?, nombres=?, direccion=?, DNI=?, telefono=?, movil=? WHERE idCliente=?";
                    ps = con.prepareStatement(updateSQL);
                    ps.setString(1, apellidos);
                    ps.setString(2, nombres);
                    ps.setString(3, direccion);
                    ps.setString(4, DNI);
                    ps.setString(5, telefono);
                    ps.setString(6, movil);
                    ps.setString(7, idCliente);

                    int filas = ps.executeUpdate();

                    if (filas > 0) {
                        request.setAttribute("mensaje", "Cliente modificado correctamente.");
                    } else {
                        request.setAttribute("error", "No se pudo modificar el cliente.");
                    }

                    // volver a cargar los datos del cliente actualizado
                    response.sendRedirect("ClienteControlador?Op=Consultar&idCliente=" + idCliente+"&pagina=modificarCliente.jsp");

                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("error", "Error al modificar cliente: " + e.getMessage());
                    request.getRequestDispatcher("cliente.jsp").forward(request, response);
                }
                break;

            case "Eliminar":
                break;

            default:
                System.out.println("MALA ");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
