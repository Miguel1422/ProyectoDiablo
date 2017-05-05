package conexiones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * *
 *
 * @author Miguel Clase para poder loguearse
 */
public class LoginManager {

    /**
     * *
     * Metodo que devuelve verdadero si el usario y contraseña son corrector
     *
     * @param user Usuario
     * @param pass Contraseña
     * @return verdadero si existe de lo contrario falso
     */
    public static int login(String user, String pass) {
        try {
            Connection micon = Conexion.getInstance().getConnection();
            String cadsql = String.format("SELECT * FROM usuarios WHERE nombre = '%s'", user);
            PreparedStatement pstm = micon.prepareStatement(cadsql);
            ResultSet rs = pstm.executeQuery(cadsql);
            while (rs.next()) {
                if (rs.getString("password").equals(pass)) {
                    pstm.execute();
                    pstm.close();
                    return rs.getInt("tipo");

                }
            }
            pstm.execute();
            pstm.close();
            return 0;
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
            return 0;
        } catch (NullPointerException e) {
            return 0;
        }
    }
}
