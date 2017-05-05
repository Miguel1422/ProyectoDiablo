package conexiones;

import clases.User;
import clases.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VentasManager {

    public static ArrayList<Venta> compras(User u) {
        ArrayList<Venta> ventas = new ArrayList<>();
        try {
            Connection micon = Conexion.getInstance().getConnection();
            String cadsql = String.format("SELECT * FROM `ventas` WHERE `id_cliente` = %d", u.getId());
            PreparedStatement pstm = micon.prepareStatement(cadsql);
            ResultSet rs = pstm.executeQuery(cadsql);
            while (rs.next()) {
                Venta a = new Venta(
                        rs.getInt("id"),
                        rs.getDouble("total"),
                        rs.getString("descripcion"),
                        u.getNombre()
                );
                ventas.add(a);
            }
            pstm.execute();
            pstm.close();
            return ventas;
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
            return null;
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static ArrayList<Venta> compras() {
        ArrayList<Venta> ventas = new ArrayList<>();
        try {
            Connection micon = Conexion.getInstance().getConnection();
            String cadsql = String.format("SELECT * FROM `ventas`");
            PreparedStatement pstm = micon.prepareStatement(cadsql);
            ResultSet rs = pstm.executeQuery(cadsql);
            while (rs.next()) {
                int cliente = rs.getInt("id_cliente");
                User u = UserManager.getUser(cliente);
                Venta a = new Venta(
                        rs.getInt("id"),
                        rs.getDouble("total"),
                        rs.getString("descripcion"),
                        u.getNombre()
                );
                ventas.add(a);
            }
            pstm.execute();
            pstm.close();
            return ventas;
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
            return null;
        } catch (NullPointerException e) {
            return null;
        }
    }

}
