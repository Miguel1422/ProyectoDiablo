package clases;

public class Libro {
    private int id;
    private String titulo;
    private int copias;
    private double precio;

    public Libro(int id, String nombre, int copias, double precio) {
        this.id = id;
        this.titulo = nombre;
        this.copias = copias;
        this.precio = precio;
    }

    public Libro(String nombre, int copias, double precio) {
        this(0, nombre, copias, precio);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return titulo;
    }

    public void setNombre(String titulo) {
        this.titulo = titulo;
    }

    public int getCopias() {
        return copias;
    }

    public void setCopias(int copias) {
        this.copias = copias;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }


}
