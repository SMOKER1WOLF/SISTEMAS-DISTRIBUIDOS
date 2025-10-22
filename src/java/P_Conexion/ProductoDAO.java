package P_Conexion;

import Entidades.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductoDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();

    // 🔹 LISTAR PRODUCTOS
    public ArrayList<Producto> listar() {
        ArrayList<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM producto";

        try {
            con = cn.establecerConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Producto p = new Producto();
                p.setIdArticulo(rs.getString("ID_articulo_01"));
                p.setNombre(rs.getString("nombre"));
                p.setCantidad(rs.getString("cantidad"));
                p.setPrecio(rs.getString("precio"));
                lista.add(p);
            }

            System.out.println("✅ Productos listados correctamente. Total: " + lista.size());
        } catch (SQLException e) {
            System.out.println("❌ Error al listar productos: " + e.getMessage());
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {}
        }
        return lista;
    }

    // 🔹 INSERTAR PRODUCTO
    public void insertar(Producto p) {
        String sql = "INSERT INTO producto (ID_articulo_01, nombre, cantidad, precio) VALUES (?, ?, ?, ?)";
        try {
            con = cn.establecerConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getIdArticulo());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getCantidad());
            ps.setString(4, p.getPrecio());
            ps.executeUpdate();

            System.out.println("✅ Producto insertado correctamente: " + p.getNombre());
        } catch (SQLException e) {
            System.out.println("❌ Error al insertar producto: " + e.getMessage());
        } finally {
            try { if (con != null) con.close(); } catch (SQLException e) {}
        }
    }

    // 🔹 OBTENER PRODUCTO POR ID (para MODIFICAR)
    public Producto obtenerPorId(String idArticulo) {
        Producto p = null;
        String sql = "SELECT * FROM producto WHERE ID_articulo_01 = ?";
        try {
            con = cn.establecerConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, idArticulo);
            rs = ps.executeQuery();

            if (rs.next()) {
                p = new Producto();
                p.setIdArticulo(rs.getString("ID_articulo_01"));
                p.setNombre(rs.getString("nombre"));
                p.setCantidad(rs.getString("cantidad"));
                p.setPrecio(rs.getString("precio"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al obtener producto por ID: " + e.getMessage());
        } finally {
            try { if (con != null) con.close(); } catch (SQLException e) {}
        }
        return p;
    }

    // 🔹 ACTUALIZAR PRODUCTO (MODIFICAR)
    public void actualizar(Producto p) {
        String sql = "UPDATE producto SET nombre=?, cantidad=?, precio=? WHERE ID_articulo_01=?";
        try {
            con = cn.establecerConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getCantidad());
            ps.setString(3, p.getPrecio());
            ps.setString(4, p.getIdArticulo());
            ps.executeUpdate();

            System.out.println("✅ Producto actualizado correctamente: " + p.getIdArticulo());
        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar producto: " + e.getMessage());
        } finally {
            try { if (con != null) con.close(); } catch (SQLException e) {}
        }
    }

    // 🔹 ELIMINAR PRODUCTO
    public void eliminar(String idArticulo) {
        String sql = "DELETE FROM producto WHERE ID_articulo_01=?";
        try {
            con = cn.establecerConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, idArticulo);
            ps.executeUpdate();

            System.out.println("🗑️ Producto eliminado: " + idArticulo);
        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar producto: " + e.getMessage());
        } finally {
            try { if (con != null) con.close(); } catch (SQLException e) {}
        }
    }

    // 🔍 NUEVO: BUSCAR PRODUCTOS POR NOMBRE O ID
    public ArrayList<Producto> buscar(String texto) {
        ArrayList<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM producto WHERE ID_articulo_01 LIKE ? OR nombre LIKE ?";

        try {
            con = cn.establecerConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + texto + "%");
            ps.setString(2, "%" + texto + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Producto p = new Producto();
                p.setIdArticulo(rs.getString("ID_articulo_01"));
                p.setNombre(rs.getString("nombre"));
                p.setCantidad(rs.getString("cantidad"));
                p.setPrecio(rs.getString("precio"));
                lista.add(p);
            }

            System.out.println("🔎 Resultados encontrados: " + lista.size());
        } catch (SQLException e) {
            System.out.println("❌ Error al buscar productos: " + e.getMessage());
        } finally {
            try { if (con != null) con.close(); } catch (SQLException e) {}
        }
        return lista;
    }
}
