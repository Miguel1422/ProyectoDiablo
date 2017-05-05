package conexiones;

import clases.Libro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookManager {

    /**
     *
     * @param book usuario a buscar
     * @return el id del usuario buscado o 0 si no se encuentra
     */
    public static int getBookId(String book) {
        try {
            Connection micon = Conexion.getInstance().getConnection();
            String cadsql = String.format("SELECT * FROM libros WHERE titulo = '%s'", book);
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

    public static boolean addBook(Libro l) {
        try {
            if (getBookId(l.getNombre()) != 0) {
                return false;
            }
            Connection micon = Conexion.getInstance().getConnection();
            String cadsql = String.format("INSERT INTO `proyecto`.`libros` (`titulo`, `copias`, `precio`) "
                    + "VALUES ('%s', '%d', '%f')", l.getNombre(), l.getCopias(), l.getPrecio());
            PreparedStatement pstm = micon.prepareStatement(cadsql);

            pstm.execute();
            pstm.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static Libro getBook(int id) {
        try {
            Connection micon = Conexion.getInstance().getConnection();
            String cadsql = String.format("SELECT * FROM libros WHERE id = '%d'", id);
            PreparedStatement pstm = micon.prepareStatement(cadsql);
            ResultSet rs = pstm.executeQuery(cadsql);
            while (rs.next()) {
                pstm.execute();
                pstm.close();
                String titulo = rs.getString("titulo");
                int copias = rs.getInt("copias");
                double precio = rs.getFloat("precio");
                return new Libro(id, titulo, copias, precio);
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

    public static Libro getBook(String user) {
        int id = getBookId(user);
        return BookManager.getBook(id);
    }

    public static boolean removeBook(Libro u) {
        try {
            if (getBookId(u.getNombre()) == 0) {
                return false;
            }
            Connection micon = Conexion.getInstance().getConnection();
            //String cadsql = "Insert into peliculas (nombre,precio)" + " values (?,?)";
            String cadsql = String.format("DELETE FROM `proyecto`.`libros` WHERE `libros`.`id` = %d", u.getId());
            PreparedStatement pstm = micon.prepareStatement(cadsql);

            pstm.execute();
            pstm.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean editBook(Libro u) {
        try {
            if (getBookId(u.getNombre()) == 0) {
                return false;
            }
            Connection micon = Conexion.getInstance().getConnection();
            String cadsql = String.format(
                    "UPDATE `proyecto`.`libros` SET "
                    + "`titulo` = '%s', "
                    + "`copias` = '%d', "
                    + "`precio` = '%f'"
                    + " WHERE `libros`.`id` = %d",
                    u.getNombre(), u.getCopias(), u.getPrecio(), u.getId());
            PreparedStatement pstm = micon.prepareStatement(cadsql);

            pstm.execute();
            pstm.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
