public class Usuario {
    protected String nombreCompleto;
    protected String cedula;
    protected String correoElectronico;
    protected String contrasenia;

    public Usuario(String nombreCompleto, String cedula, String correoElectronico, String contrasenia) {
        this.nombreCompleto = nombreCompleto;
        this.cedula = cedula;
        this.correoElectronico = correoElectronico;
        this.contrasenia = contrasenia;
    }

    public String getNombreCompleto() { return nombreCompleto; }
    public String getCedula() { return cedula; }
    public String getCorreoElectronico() { return correoElectronico; }
    public String getContrasenia() { return contrasenia; }
}
