package cr.ac.ucenfotec.tl;

import cr.ac.ucenfotec.bl.entities.Administrador.Administrador;
import cr.ac.ucenfotec.bl.entities.cliente.Cliente;
import cr.ac.ucenfotec.bl.entities.CuentaAhorro.CuentaAhorro;
import cr.ac.ucenfotec.bl.entities.CuentaCredito.CuentaCredito;
import cr.ac.ucenfotec.bl.entities.CuentaDebito.CuentaDebito;
import cr.ac.ucenfotec.bl.logic.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    private GestorAdministrador gestorAdministrador;
    private GestorCliente gestorCliente;
    private GestorCuentaAhorro gestorCuentaAhorro;
    private GestorCuentaDebito gestorCuentaDebito;
    private GestorCuentaCredito gestorCuentaCredito;

    public Controller() {
        this.gestorAdministrador = new GestorAdministrador();
        this.gestorCliente = new GestorCliente();
        this.gestorCuentaAhorro = new GestorCuentaAhorro();
        this.gestorCuentaDebito = new GestorCuentaDebito();
        this.gestorCuentaCredito = new GestorCuentaCredito();
    }

    // Métodos para Administrador
    public String registrarAdministrador(String nombreCompleto, String cedula, String correoElectronico, String contrasenia) {
        return gestorAdministrador.registrarAdministrador(nombreCompleto, cedula, correoElectronico, contrasenia);
    }

    public String validarAdministrador(String correoElectronico, String contrasenia) {
        return gestorAdministrador.validarAdministrador(correoElectronico, contrasenia);
    }

    public Administrador buscarAdministrador(String correoElectronico) {
        return gestorAdministrador.buscarAdministrador(correoElectronico);
    }

    // Métodos para Cliente
    public String registrarCliente(String nombreCompleto, String cedula, String correoElectronico,
                                   String contrasenia, String sexo, String profesion, String direccion) {
        return gestorCliente.registrarCliente(nombreCompleto, cedula, correoElectronico, contrasenia, sexo, profesion, direccion);
    }

    public String validarCliente(String correoElectronico, String contrasenia) {
        return gestorCliente.validarCliente(correoElectronico, contrasenia);
    }

    public Cliente buscarCliente(String correoElectronico) {
        return gestorCliente.buscarCliente(correoElectronico);
    }

    public ArrayList<Cliente> listarClientes() {
        return gestorCliente.listarClientes();
    }

    // Métodos para Cuentas de Ahorro
    public String registrarCuentaAhorro(double saldo, LocalDate fechaCreacion,
                                        boolean activa, String cedulaCliente) {
        return gestorCuentaAhorro.registrarCuentaAhorro(saldo, fechaCreacion, activa, cedulaCliente);
    }

    public ArrayList<CuentaAhorro> listarCuentasAhorro() {
        return gestorCuentaAhorro.listarCuentasAhorro();
    }

    public ArrayList<CuentaAhorro> listarCuentasAhorroPorCliente(String cedulaCliente) {
        return gestorCuentaAhorro.listarCuentasAhorroPorCliente(cedulaCliente);
    }

    public String retirarCuentaAhorro(String numeroCuenta, double monto) {
        return gestorCuentaAhorro.retirar(numeroCuenta, monto);
    }

    public String depositarCuentaAhorro(String numeroCuenta, double monto) {
        return gestorCuentaAhorro.depositar(numeroCuenta, monto);
    }

    public String generarInteresCuentaAhorro(String numeroCuenta) {
        return gestorCuentaAhorro.generarInteres(numeroCuenta);
    }

    // Métodos para Cuentas de Débito
    public String registrarCuentaDebito(double saldo, LocalDate fechaCreacion,
                                        boolean activa, String cedulaCliente) {
        return gestorCuentaDebito.registrarCuentaDebito(saldo, fechaCreacion, activa, cedulaCliente);
    }

    public ArrayList<CuentaDebito> listarCuentasDebito() {
        return gestorCuentaDebito.listarCuentasDebito();
    }

    public ArrayList<CuentaDebito> listarCuentasDebitoPorCliente(String cedulaCliente) {
        return gestorCuentaDebito.listarCuentasDebitoPorCliente(cedulaCliente);
    }

    public String retirarCuentaDebito(String numeroCuenta, double monto) {
        return gestorCuentaDebito.retirar(numeroCuenta, monto);
    }

    public String depositarCuentaDebito(String numeroCuenta, double monto) {
        return gestorCuentaDebito.depositar(numeroCuenta, monto);
    }

    public String generarInteresCuentaDebito(String numeroCuenta) {
        return gestorCuentaDebito.generarInteres(numeroCuenta);
    }

    // Métodos para Cuentas de Crédito
    public String registrarCuentaCredito(LocalDate fechaCreacion,
                                         boolean activa, String cedulaCliente, String tipo, double limiteCredito) {
        return gestorCuentaCredito.registrarCuentaCredito(fechaCreacion, activa, cedulaCliente, tipo, limiteCredito);
    }

    public ArrayList<CuentaCredito> listarCuentasCredito() {
        return gestorCuentaCredito.listarCuentasCredito();
    }

    public ArrayList<CuentaCredito> listarCuentasCreditoPorCliente(String cedulaCliente) {
        return gestorCuentaCredito.listarCuentasCreditoPorCliente(cedulaCliente);
    }

    public String retirarCuentaCredito(String numeroCuenta, double monto) {
        return gestorCuentaCredito.retirar(numeroCuenta, monto);
    }

    public String abonarCuentaCredito(String numeroCuenta, double monto) {
        return gestorCuentaCredito.abonar(numeroCuenta, monto);
    }
}