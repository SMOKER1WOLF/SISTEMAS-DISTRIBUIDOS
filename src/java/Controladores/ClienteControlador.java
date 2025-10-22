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
                Cliente cliente = new Cliente();
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
                String idCliente1 = request.getParameter("idCliente");
                String nombres1 = request.getParameter("nombres");
                String apellidos1 = request.getParameter("apellidos");
                String direccion1 = request.getParameter("direccion");
                String DNI1 = request.getParameter("DNI");
                String telefono1 = request.getParameter("telefono");
                String movil1 = request.getParameter("movil");

                try {
                    String sql = "INSERT INTO cliente (idCliente, apellidos, nombres, direccion, DNI, telefono, movil) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?)";
                    ps = con.prepareStatement(sql);
                    rs = ps.executeQuery();
                    ps.setString(1, idCliente1);
                    ps.setString(2, nombres1);
                    ps.setString(3, apellidos1);
                    ps.setString(4, direccion1);
                    ps.setString(5, telefono1);
                    ps.setString(6, movil1);

                } catch (Exception ex) {
                    System.out.println("Error de SQL +" + ex.getMessage());
                } finally {
                    conect.disconnect();
                }
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
                    // volver a consultar los datos actualizados
                    String sql = "SELECT * FROM cliente WHERE idCliente = ?";
                    ps = con.prepareStatement(sql);
                    ps.setString(1, idCliente);
                    rs = ps.executeQuery();

                    Cliente clienteActualizado = new Cliente();
                    if (rs.next()) {
                        clienteActualizado.setIdCliente(rs.getString("idCliente"));
                        clienteActualizado.setApellidos(rs.getString("apellidos"));
                        clienteActualizado.setNombres(rs.getString("nombres"));
                        clienteActualizado.setDireccion(rs.getString("direccion"));
                        clienteActualizado.setDNI(rs.getString("DNI"));
                        clienteActualizado.setTelefono(rs.getString("telefono"));
                        clienteActualizado.setMovil(rs.getString("movil"));
                    }

                    // Enviamos mensaje de éxito
                    request.setAttribute("cliente", clienteActualizado);
                    request.setAttribute("mensaje", "✅ Cliente modificado correctamente.");
                    request.getRequestDispatcher("modificarCliente.jsp").forward(request, response);
                } else {
                    // Error de actualización
                    request.setAttribute("error", "❌ No se pudo modificar el cliente. Verifique los datos.");
                    request.getRequestDispatcher("modificarCliente.jsp").forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "⚠️ Error al modificar cliente: " + e.getMessage());
                request.getRequestDispatcher("modificarCliente.jsp").forward(request, response);
            } finally {
                conect.disconnect();
            }
            break;

            case "Eliminar":
    try {
                String idCliente = request.getParameter("idCliente");

                String sql = "DELETE FROM cliente WHERE idCliente = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, idCliente);

                int filas = ps.executeUpdate();

                if (filas > 0) {
                    request.setAttribute("mensaje", "✅ Cliente eliminado correctamente.");
                } else {
                    request.setAttribute("error", "❌ No se encontró el cliente a eliminar.");
                }

                // Volver a listar los clientes actualizados
                String sqlListar = "SELECT * FROM cliente";
                ps = con.prepareStatement(sqlListar);
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

            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "⚠️ Error al eliminar cliente: " + e.getMessage());
                request.getRequestDispatcher("listarClientes.jsp").forward(request, response);
            } finally {
                conect.disconnect();
            }
            break;

            default:
                System.out.println("MALA ");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String Op = request.getParameter("Op");

        if ("Nuevo".equals(Op)) {
            Conexion conect = new Conexion();
            Connection con = conect.establecerConexion();

// --- Generar nuevo ID automáticamente ---
            String nuevoId = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                // Buscar el último idCliente numéricamente
                String queryId = "SELECT idCliente FROM cliente ORDER BY CAST(SUBSTRING(idCliente, 4) AS UNSIGNED) DESC LIMIT 1";
                ps = con.prepareStatement(queryId);
                rs = ps.executeQuery();

                if (rs.next()) {
                    String ultimoId = rs.getString("idCliente");

                    if (ultimoId != null && ultimoId.startsWith("CLI")) {
                        int numero = Integer.parseInt(ultimoId.substring(3));
                        nuevoId = String.format("CLI%03d", numero + 1);
                    } else {
                        nuevoId = "CLI001";
                    }
                } else {
                    nuevoId = "CLI001";
                }

                // --- Insertar cliente ---
                String apellidos = request.getParameter("apellidos");
                String nombres = request.getParameter("nombres");
                String direccion = request.getParameter("direccion");
                String DNI = request.getParameter("DNI");
                String telefono = request.getParameter("telefono");
                String movil = request.getParameter("movil");

                String sql = "INSERT INTO cliente (idCliente, apellidos, nombres, direccion, DNI, telefono, movil) VALUES (?, ?, ?, ?, ?, ?, ?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, nuevoId);
                ps.setString(2, apellidos);
                ps.setString(3, nombres);
                ps.setString(4, direccion);
                ps.setString(5, DNI);
                ps.setString(6, telefono);
                ps.setString(7, movil);

                int filas = ps.executeUpdate();

                if (filas > 0) {
                    request.setAttribute("mensaje", "✅ Cliente registrado correctamente con ID: " + nuevoId);
                } else {
                    request.setAttribute("error", "❌ No se pudo registrar el cliente.");
                }

            } catch (Exception e) {
                request.setAttribute("error", "⚠️ Error al registrar cliente: " + e.getMessage());
            } finally {
                conect.disconnect();
            }

            request.getRequestDispatcher("nuevoCliente.jsp").forward(request, response);

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
