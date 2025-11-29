package cr.ac.ucenfotec.bl.entities.cliente;

import cr.ac.ucenfotec.bl.entities.Usuario.Usuario;

public class Cliente extends Usuario {
    private String sexo;
    private String profesion;
    private String direccion;

    public Cliente() {
    }

    public Cliente(String nombreCompleto, String cedula, String correoElectronico, String contrasenia, String sexo, String profesion, String direccion) {
        super(nombreCompleto, cedula, correoElectronico, contrasenia);
        this.sexo = sexo;
        this.profesion = profesion;
        this.direccion = direccion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombreCompleto='" + nombreCompleto + '\'' +
                ", cedula='" + cedula + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", sexo='" + sexo + '\'' +
                ", profesion='" + profesion + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}