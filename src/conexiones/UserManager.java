package conexiones;

import clases.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserManager {

    /**
     * **
     *
     * @param user usuario a buscar
     * @return el id del usuario buscado o 0 si no se encuentra
     */
    public static int getUserId(String user) {
        try {
            Connection micon = Conexion.getInstance().getConnection();
            String cadsql = String.format("SELECT * FROM usuarios WHERE nombre = '%s'", user);
            PreparedStatement pstm = micon.prepareStatement(cadsql);
            ResultSet rs = pstm.executeQuery(cadsql);
            while (rs.next()) {
                pstm.execute();
                pstm.close();
                return rs.getInt("id");
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


    public static boolean addUser(User u) {
        try {
            if (getUserId(u.getNombre()) != 0) {
                return false;
            }
            Connection micon = Conexion.getInstance().getConnection();
            //String cadsql = "Insert into peliculas (nombre,precio)" + " values (?,?)";
            String cadsql = String.format("INSERT INTO `proyecto`.`usuarios` (`nombre`, `password`, `tipo`) "
                    + "VALUES ('%s', '%s', '%d')", u.getNombre(), u.getPass(), u.getType());
            PreparedStatement pstm = micon.prepareStatement(cadsql);

            pstm.execute();
            pstm.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static User getUser(int id) {
        try {
            Connection micon = Conexion.getInstance().getConnection();
            String cadsql = String.format("SELECT * FROM usuarios WHERE id = '%d'", id);
            PreparedStatement pstm = micon.prepareStatement(cadsql);
            ResultSet rs = pstm.executeQuery(cadsql);
            while (rs.next()) {
                pstm.execute();
                pstm.close();
                int tipo = rs.getInt("tipo");
                String user = rs.getString("nombre");
                String pass = rs.getString("password");
                return new User(tipo, user, pass, id);
            }
            pstm.execute();
            pstm.close();
            return null;
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
            return null;
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static User getUser(String user) {
        int id = getUserId(user);
        return getUser(id);
    }


    public static boolean removeUser(User u) {
        try {
            if (getUserId(u.getNombre()) == 0) {
                return false;
            }
            Connection micon = Conexion.getInstance().getConnection();
            //String cadsql = "Insert into peliculas (nombre,precio)" + " values (?,?)";
            String cadsql = String.format("DELETE FROM `proyecto`.`usuarios` WHERE `usuarios`.`id` = %d", u.getId());
            PreparedStatement pstm = micon.prepareStatement(cadsql);

            pstm.execute();
            pstm.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
