package cr.ac.ucenfotec.bl.entities.Administrador;

import cr.ac.ucenfotec.dl.Connector;

import java.sql.ResultSet;
import java.util.ArrayList;

public class DAOAdministrador {
    private Connector connector;

    public DAOAdministrador() {
        connector = new Connector();
    }

    public void registrarAdministrador(Administrador administrador) throws Exception {
        // Verificar si ya existe un administrador
        if (existeAdministrador()) {
            throw new Exception("Ya existe un administrador registrado en el sistema");
        }

        String query = "INSERT INTO Administrador (cedula, nombreCompleto, correoElectronico, contrasenia) " +
                "VALUES ('" + administrador.getCedula() + "', '" + administrador.getNombreCompleto() + "', '" +
                administrador.getCorreoElectronico() + "', '" + administrador.getContrasenia() + "')";
        connector.ejecutarSQL(query);
    }

    public boolean existeAdministrador() throws Exception {
        String query = "SELECT COUNT(*) as count FROM Administrador";
        ResultSet rs = connector.ejecutarQuery(query);
        if (rs.next()) {
            int count = rs.getInt("count");
            connector.cerrarConexion(rs);
            return count > 0;
        }
        connector.cerrarConexion(rs);
        return false;
    }

    public Administrador buscarAdministrador(String correoElectronico) throws Exception {
        String query = "SELECT * FROM Administrador WHERE correoElectronico = '" + correoElectronico + "'";
        ResultSet rs = connector.ejecutarQuery(query);
        if (rs.next()) {
            String cedula = rs.getString("cedula");
            String nombreCompleto = rs.getString("nombreCompleto");
            String contrasenia = rs.getString("contrasenia");
            connector.cerrarConexion(rs);
            return new Administrador(nombreCompleto, cedula, correoElectronico, contrasenia);
        }
        connector.cerrarConexion(rs);
        return null;
    }

    public ArrayList<Administrador> listarAdministradores() throws Exception {
        ArrayList<Administrador> administradores = new ArrayList<>();
        String query = "SELECT * FROM Administrador";
        ResultSet rs = connector.ejecutarQuery(query);
        while (rs.next()) {
            String cedula = rs.getString("cedula");
            String nombreCompleto = rs.getString("nombreCompleto");
            String correoElectronico = rs.getString("correoElectronico");
            String contrasenia = rs.getString("contrasenia");
            administradores.add(new Administrador(nombreCompleto, cedula, correoElectronico, contrasenia));
        }
        connector.cerrarConexion(rs);
        return administradores;
    }
}