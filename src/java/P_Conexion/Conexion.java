package P_Conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    protected Connection con=null;
    static String username = "root";
    static String password = "";
    static String database = "sesion07";
    static String url = "jdbc:mysql://localhost:3306/" + database + "?useSSL=false&serverTimezone=America/Lima&allowPublicKeyRetrieval=true";

    public Connection establecerConexion() {
        if (con == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(url, username, password);
                System.out.println("Conexion establecida");
            } catch (Exception ex) {
                System.out.println(ex.toString());
                System.out.println("No se pudo conectar a la base de datos");
            }
        }
        return con;
    }

    public void disconnect() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Conexión terminada");
                con = null;
            } catch (Exception ex) {
                System.out.println(ex.toString());
                System.out.println("");
            }
        }
    }
}
