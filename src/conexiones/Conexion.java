package conexiones;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Miguel
 */
public class Conexion {

    private String url;
    private String server;
    private String puerto;
    public static String BD;
    private String user;
    private String pwd;
    private String driver;

    private static Conexion instancia;
    private Connection con;

    public static Conexion getInstance() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    private Conexion() {
        server = "localhost";
        puerto = "3306";
        BD = "proyecto";
        user = "root";
        pwd = "";
        driver = "com.mysql.jdbc.Driver";

        url = "jdbc:mysql://" + server + ":" + puerto + "/" + BD;

        //System.out.println(url);
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pwd);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error del driver");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al conectarnos");
        }

    }

    public Connection getConnection() {
        return con;
    }
}
