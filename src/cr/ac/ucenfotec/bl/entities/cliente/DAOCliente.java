package cr.ac.ucenfotec.bl.entities.cliente;

import cr.ac.ucenfotec.dl.Connector;

import java.sql.ResultSet;
import java.util.ArrayList;

public class DAOCliente {
    private Connector connector;

    public DAOCliente() {
        connector = new Connector();
    }

    public void registrarCliente(Cliente cliente) throws Exception {
        String query = "INSERT INTO Cliente (cedula, nombreCompleto, correoElectronico, contrasenia, sexo, profesion, direccion) " +
                "VALUES ('" + cliente.getCedula() + "', '" + cliente.getNombreCompleto() + "', '" +
                cliente.getCorreoElectronico() + "', '" + cliente.getContrasenia() + "', '" +
                cliente.getSexo() + "', '" + cliente.getProfesion() + "', '" + cliente.getDireccion() + "')";
        connector.ejecutarSQL(query);
    }

    public Cliente buscarCliente(String correoElectronico) throws Exception {
        String query = "SELECT * FROM Cliente WHERE correoElectronico = '" + correoElectronico + "'";
        ResultSet rs = connector.ejecutarQuery(query);
        if (rs.next()) {
            String cedula = rs.getString("cedula");
            String nombreCompleto = rs.getString("nombreCompleto");
            String contrasenia = rs.getString("contrasenia");
            String sexo = rs.getString("sexo");
            String profesion = rs.getString("profesion");
            String direccion = rs.getString("direccion");
            return new Cliente(nombreCompleto, cedula, correoElectronico, contrasenia, sexo, profesion, direccion);
        }
        return null;
    }

    public ArrayList<Cliente> listarClientes() throws Exception {
        ArrayList<Cliente> clientes = new ArrayList<>();
        String query = "SELECT * FROM Cliente";
        ResultSet rs = connector.ejecutarQuery(query);
        while (rs.next()) {
            String cedula = rs.getString("cedula");
            String nombreCompleto = rs.getString("nombreCompleto");
            String correoElectronico = rs.getString("correoElectronico");
            String contrasenia = rs.getString("contrasenia");
            String sexo = rs.getString("sexo");
            String profesion = rs.getString("profesion");
            String direccion = rs.getString("direccion");
            clientes.add(new Cliente(nombreCompleto, cedula, correoElectronico, contrasenia, sexo, profesion, direccion));
        }
        return clientes;
    }
}