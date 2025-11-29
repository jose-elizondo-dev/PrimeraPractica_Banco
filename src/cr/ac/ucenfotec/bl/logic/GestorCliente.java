package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.entities.cliente.Cliente;
import cr.ac.ucenfotec.bl.entities.cliente.DAOCliente;

import java.util.ArrayList;

public class GestorCliente {
    private DAOCliente daoCliente;

    public GestorCliente() {
        daoCliente = new DAOCliente();
    }

    public String registrarCliente(String nombreCompleto, String cedula, String correoElectronico,
                                   String contrasenia, String sexo, String profesion, String direccion) {
        try {
            Cliente cliente = new Cliente(nombreCompleto, cedula, correoElectronico, contrasenia, sexo, profesion, direccion);
            daoCliente.registrarCliente(cliente);
            return "Cliente registrado exitosamente!";
        } catch (Exception e) {
            return "Error al registrar cliente: " + e.getMessage();
        }
    }

    public Cliente buscarCliente(String correoElectronico) {
        try {
            return daoCliente.buscarCliente(correoElectronico);
        } catch (Exception e) {
            return null;
        }
    }

    public String validarCliente(String correoElectronico, String contrasenia) {
        try {
            Cliente cliente = daoCliente.buscarCliente(correoElectronico);
            if (cliente != null && cliente.getContrasenia().equals(contrasenia)) {
                return "Cliente validado exitosamente!";
            } else {
                return "Credenciales incorrectas!";
            }
        } catch (Exception e) {
            return "Error al validar cliente: " + e.getMessage();
        }
    }

    public ArrayList<Cliente> listarClientes() {
        try {
            return daoCliente.listarClientes();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}