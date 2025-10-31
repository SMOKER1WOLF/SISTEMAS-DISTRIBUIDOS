package Controladores;

import Entidades.Cliente;
import Entidades.Producto;
import Modelos.ProductoDAO;
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
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "PedidoControlador", urlPatterns = {"/PedidoControlador"})
public class PedidoControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String op = request.getParameter("Op");

        if (op == null) {
            op = "MostrarFormulario";
        }

        switch (op) {
            case "MostrarFormulario":
                mostrarFormulario(request, response);
                break;
            case "Registrar":
                registrarPedido(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Conexion conect = new Conexion();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        ProductoDAO PRODAO =new ProductoDAO();
        List<Producto> listaProductos = new ArrayList<>();
        listaProductos = PRODAO.listar();
        
        List<Cliente> listaClientes = new ArrayList<>();

        try {
            con = conect.establecerConexion();
            String sql = "SELECT * FROM cliente";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setIdCliente(rs.getString("idCliente"));
                cli.setNombres(rs.getString("nombres"));
                cli.setApellidos(rs.getString("apellidos"));
                cli.setDireccion(rs.getString("direccion"));
                cli.setTelefono(rs.getString("telefono"));
                listaClientes.add(cli);
            }
            request.setAttribute("listaClientes", listaClientes);
            request.setAttribute("listaProductos", listaProductos);
            request.setAttribute("fechaVisible", Herramientas.EntidadesGlobales.getFecha());

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al cargar la lista de clientes: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) conect.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("registroPedido.jsp").forward(request, response);
    }


    private void registrarPedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Conexion conect = new Conexion();
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = conect.establecerConexion();

            String idCliente = request.getParameter("idCliente");
            String fechaStr = request.getParameter("fechaPedido");
            double montoTotal = Double.parseDouble(request.getParameter("montoTotal"));

            // SimpleDateFormat to parse the date from the form
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaPedido = sdf.parse(fechaStr);
            java.sql.Date sqlFechaPedido = new java.sql.Date(fechaPedido.getTime());

            String sql = "INSERT INTO pedido (idCliente, fechaPedido, montoTotal, estado) VALUES (?, ?, ?, 'Pendiente')";
            ps = con.prepareStatement(sql);
            ps.setString(1, idCliente);
            ps.setDate(2, sqlFechaPedido);
            ps.setDouble(3, montoTotal);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                request.setAttribute("mensaje", "Pedido registrado correctamente.");
            } else {
                request.setAttribute("error", "No se pudo registrar el pedido.");
            }

        } catch (SQLException | ParseException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al registrar el pedido: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) conect.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // After processing, always show the form again, reloading the client list
        // and displaying any success/error message.
        mostrarFormulario(request, response);
    }
}
