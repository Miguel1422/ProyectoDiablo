package clases;

public class Venta {

    private int id;
    private double total;
    private String descripcion;
    private String user;

    public Venta(int id, double total, String descripcion, String user) {
        this.id = id;
        this.total = total;
        this.descripcion = descripcion;
        this.user = user;
    }

    public Venta(double total, String descripciom, String user) {
        this(0, total, descripciom, user);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
