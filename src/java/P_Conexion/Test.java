package P_Conexion;

public class Test {

    public static void main(String[] args) {
        
        Conexion conect = new Conexion();
        conect.establecerConexion();
        conect.disconnect();
    }
     
}
