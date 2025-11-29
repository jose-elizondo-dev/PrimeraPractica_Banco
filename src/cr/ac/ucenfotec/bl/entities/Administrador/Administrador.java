package cr.ac.ucenfotec.bl.entities.Administrador;

import cr.ac.ucenfotec.bl.entities.Usuario.Usuario;

public class Administrador extends Usuario {

    public Administrador() {
    }

    public Administrador(String nombreCompleto, String cedula, String correoElectronico, String contrasenia) {
        super(nombreCompleto, cedula, correoElectronico, contrasenia);
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "nombreCompleto='" + nombreCompleto + '\'' +
                ", cedula='" + cedula + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                '}';
    }
}