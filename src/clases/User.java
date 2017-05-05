package clases;

/**
 *
 * @author Miguel
 */
public class User {

    private int type;
    private String nombre;
    private String pass;
    private int id;

    public User(int type, String nombre, String pass, int id) {
        this.type = type;
        this.nombre = nombre;
        this.pass = pass;
        this.id = id;
    }

    public User(int type, String nombre, String pass) {
        this(type, nombre, pass, 0);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
