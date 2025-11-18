package Modelos;

import Entidades.Cliente;
import Entidades.Pedido;
import Entidades.Producto;
import P_Conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    private final Conexion cn = new Conexion();
    private Connection con;

    // ------------------------------------------------------------
    // LISTAR CLIENTES
    // ------------------------------------------------------------
    public List<Cliente> listarClientes() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try {
            con = cn.establecerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setIdCliente(rs.getString("idCliente"));
                c.setNombres(rs.getString("nombres"));
                c.setApellidos(rs.getString("apellidos"));
                c.setDireccion(rs.getString("direccion"));
                c.setTelefono(rs.getString("telefono"));
                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Error listando clientes: " + e.getMessage());
        } finally {
            cerrarConexion();
        }

        return lista;
    }

    // ------------------------------------------------------------
    // LISTAR PRODUCTOS
    // ------------------------------------------------------------
    public List<Producto> listarProductos() {
        ProductoDAO pdao = new ProductoDAO();
        return pdao.listar(); // reutilizamos tu DAO existente
    }

    // ------------------------------------------------------------
    // INSERTAR PEDIDO
    // ------------------------------------------------------------
    public boolean insertarPedido(Pedido p) {
        String sql = "INSERT INTO pedido(idCliente, fechaPedido, montoTotal, estado) VALUES (?, ?, ?, ?)";

        try {
            con = cn.establecerConexion();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, p.getIdCliente());
            ps.setDate(2, new java.sql.Date(p.getFechaPedido().getTime()));
            ps.setDouble(3, p.getMontoTotal());
            ps.setString(4, p.getEstado());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error registrando pedido: " + e.getMessage());
            return false;

        } finally {
            cerrarConexion();
        }
    }

    // ------------------------------------------------------------
    // LISTAR PEDIDOS  (NUEVO)
    // ------------------------------------------------------------
    public List<Pedido> listarPedidos() {
        List<Pedido> lista = new ArrayList<>();
        String sql = "SELECT * FROM pedido";

        try {
            con = cn.establecerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pedido p = new Pedido();
                p.setIdPedido(rs.getInt("idPedido"));
                p.setIdCliente(rs.getString("idCliente"));
                p.setFechaPedido(rs.getDate("fechaPedido"));
                p.setMontoTotal(rs.getDouble("montoTotal"));
                p.setEstado(rs.getString("estado"));

                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error listando pedidos: " + e.getMessage());
        } finally {
            cerrarConexion();
        }

        return lista;
    }

    // ------------------------------------------------------------
    // CERRAR CONEXIÓN
    // ------------------------------------------------------------
    private void cerrarConexion() {
        try {
            if (con != null) {
                cn.disconnect();
            }
        } catch (Exception e) {
            System.out.println("Error cerrando conexión: " + e.getMessage());
        }
    }

}
