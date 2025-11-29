package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.entities.CuentaCredito.CuentaCredito;
import cr.ac.ucenfotec.bl.entities.CuentaCredito.DAOCuentaCredito;
import cr.ac.ucenfotec.utils.Utils;

import java.time.LocalDate;
import java.util.ArrayList;

public class GestorCuentaCredito {
    private DAOCuentaCredito daoCuentaCredito;

    public GestorCuentaCredito() {
        daoCuentaCredito = new DAOCuentaCredito();
    }

    public String registrarCuentaCredito(LocalDate fechaCreacion,
                                         boolean activa, String cedulaCliente, String tipo, double limiteCredito) {
        try {
            if (limiteCredito <= 0) {
                return "Error: El límite de crédito debe ser mayor a 0";
            }

            // Generar número de cuenta único
            String numeroCuenta;
            do {
                numeroCuenta = "CR" + Utils.generarNumeroCuentaUnico();
            } while (existeNumeroCuenta(numeroCuenta));

            CuentaCredito cuentaCredito = new CuentaCredito(numeroCuenta, 0, fechaCreacion, activa, cedulaCliente, tipo, limiteCredito);
            daoCuentaCredito.registrarCuentaCredito(cuentaCredito);
            return "Cuenta de crédito registrada exitosamente! Número de cuenta: " + numeroCuenta;
        } catch (Exception e) {
            return "Error al registrar cuenta de crédito: " + e.getMessage();
        }
    }

    private boolean existeNumeroCuenta(String numeroCuenta) {
        try {
            ArrayList<CuentaCredito> cuentas = listarCuentasCredito();
            for (CuentaCredito cuenta : cuentas) {
                if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<CuentaCredito> listarCuentasCredito() {
        try {
            return daoCuentaCredito.listarCuentasCredito();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public ArrayList<CuentaCredito> listarCuentasCreditoPorCliente(String cedulaCliente) {
        try {
            return daoCuentaCredito.listarCuentasCreditoPorCliente(cedulaCliente);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public String retirar(String numeroCuenta, double monto) {
        try {
            ArrayList<CuentaCredito> cuentas = listarCuentasCredito();
            for (CuentaCredito cuenta : cuentas) {
                if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                    if (cuenta.retirar(monto)) {
                        daoCuentaCredito.actualizarSaldo(numeroCuenta, cuenta.getSaldo());
                        return "Retiro exitoso! Nuevo saldo: $" + cuenta.getSaldo();
                    } else {
                        return "Error: No se pudo realizar el retiro. Verifique que la cuenta esté activa y no exceda el límite de crédito";
                    }
                }
            }
            return "Error: Cuenta no encontrada";
        } catch (Exception e) {
            return "Error al realizar retiro: " + e.getMessage();
        }
    }

    public String abonar(String numeroCuenta, double monto) {
        try {
            ArrayList<CuentaCredito> cuentas = listarCuentasCredito();
            for (CuentaCredito cuenta : cuentas) {
                if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                    if (cuenta.abonar(monto)) {
                        daoCuentaCredito.actualizarSaldo(numeroCuenta, cuenta.getSaldo());
                        return "Abono exitoso! Nuevo saldo: $" + cuenta.getSaldo();
                    } else {
                        return "Error: No se pudo realizar el abono. Verifique que la cuenta esté activa y no resulte en saldo positivo";
                    }
                }
            }
            return "Error: Cuenta no encontrada";
        } catch (Exception e) {
            return "Error al realizar abono: " + e.getMessage();
        }
    }
}