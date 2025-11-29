package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.entities.Administrador.Administrador;
import cr.ac.ucenfotec.bl.entities.Administrador.DAOAdministrador;

public class GestorAdministrador {
    private DAOAdministrador daoAdministrador;

    public GestorAdministrador() {
        daoAdministrador = new DAOAdministrador();
    }

    public String registrarAdministrador(String nombreCompleto, String cedula, String correoElectronico, String contrasenia) {
        try {
            Administrador administrador = new Administrador(nombreCompleto, cedula, correoElectronico, contrasenia);
            daoAdministrador.registrarAdministrador(administrador);
            return "Administrador registrado exitosamente!";
        } catch (Exception e) {
            return "Error al registrar administrador: " + e.getMessage();
        }
    }

    public Administrador buscarAdministrador(String correoElectronico) {
        try {
            return daoAdministrador.buscarAdministrador(correoElectronico);
        } catch (Exception e) {
            return null;
        }
    }

    public String validarAdministrador(String correoElectronico, String contrasenia) {
        try {
            Administrador administrador = daoAdministrador.buscarAdministrador(correoElectronico);
            if (administrador != null && administrador.getContrasenia().equals(contrasenia)) {
                return "Administrador validado exitosamente!";
            } else {
                return "Credenciales incorrectas!";
            }
        } catch (Exception e) {
            return "Error al validar administrador: " + e.getMessage();
        }
    }
}