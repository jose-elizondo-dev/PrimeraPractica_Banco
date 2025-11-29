package cr.ac.ucenfotec.bl.entities.Banco;

public class Banco {
    private String nombre;
    private String codigo;

    public Banco() {
        this.nombre = "Banco UCF";
        this.codigo = "BUCF";
    }

    public Banco(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Banco{" +
                "nombre='" + nombre + '\'' +
                ", codigo='" + codigo + '\'' +
                '}';
    }
}