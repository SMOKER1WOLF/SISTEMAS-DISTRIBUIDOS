package Controladores;

import Entidades.Cliente;
import Entidades.Pedido;
import Entidades.Producto;
import Modelos.PedidoDAO;
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

        PedidoDAO dao = new PedidoDAO();

        List<Cliente> listaClientes = dao.listarClientes();
        List<Producto> listaProductos = dao.listarProductos();

        request.setAttribute("listaClientes", listaClientes);
        request.setAttribute("listaProductos", listaProductos);
        request.setAttribute("fechaVisible", Herramientas.EntidadesGlobales.getFecha());
        
        int codigoPedido =
                Herramientas.GeneradorCode.generarCodigoPedido();
        request.setAttribute("codigoPedido",codigoPedido);

        request.getRequestDispatcher("registroPedido.jsp").forward(request, response);
        request.getRequestDispatcher("registroPedido.jsp").forward(request, response);
    }

    private void registrarPedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String idCliente = request.getParameter("idCliente");
            double montoTotal = Double.parseDouble(request.getParameter("montoTotal"));
            String fechaStr = request.getParameter("fechaPedido");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = sdf.parse(fechaStr);

            Pedido p = new Pedido();
            p.setIdCliente(idCliente);
            p.setFechaPedido(fecha);
            p.setMontoTotal(montoTotal);
            p.setEstado("Pendiente");

            PedidoDAO dao = new PedidoDAO();

            if (dao.insertarPedido(p)) {
                request.setAttribute("mensaje", "Pedido registrado correctamente.");
            } else {
                request.setAttribute("error", "No se pudo registrar el pedido.");
            }

        } catch (Exception e) {
            request.setAttribute("error", "Error al registrar pedido: " + e.getMessage());
        }

        mostrarFormulario(request, response);
    }
}
