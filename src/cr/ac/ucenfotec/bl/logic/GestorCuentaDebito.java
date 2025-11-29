package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.entities.CuentaDebito.CuentaDebito;
import cr.ac.ucenfotec.bl.entities.CuentaDebito.DAOCuentaDebito;
import cr.ac.ucenfotec.utils.Utils;

import java.time.LocalDate;
import java.util.ArrayList;

public class GestorCuentaDebito {
    private DAOCuentaDebito daoCuentaDebito;

    public GestorCuentaDebito() {
        daoCuentaDebito = new DAOCuentaDebito();
    }

    public String registrarCuentaDebito(double saldo, LocalDate fechaCreacion,
                                        boolean activa, String cedulaCliente) {
        try {
            if (saldo < 0) {
                return "Error: La cuenta de débito no puede tener saldo negativo";
            }

            // Generar número de cuenta único
            String numeroCuenta;
            do {
                numeroCuenta = "DB" + Utils.generarNumeroCuentaUnico();
            } while (existeNumeroCuenta(numeroCuenta));

            double porcentajeInteres = Utils.getPorcentajeInteresDebito();
            CuentaDebito cuentaDebito = new CuentaDebito(numeroCuenta, saldo, fechaCreacion, activa, cedulaCliente, porcentajeInteres);
            daoCuentaDebito.registrarCuentaDebito(cuentaDebito);
            return "Cuenta de débito registrada exitosamente! Número de cuenta: " + numeroCuenta;
        } catch (Exception e) {
            return "Error al registrar cuenta de débito: " + e.getMessage();
        }
    }

    private boolean existeNumeroCuenta(String numeroCuenta) {
        try {
            ArrayList<CuentaDebito> cuentas = listarCuentasDebito();
            for (CuentaDebito cuenta : cuentas) {
                if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<CuentaDebito> listarCuentasDebito() {
        try {
            return daoCuentaDebito.listarCuentasDebito();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public ArrayList<CuentaDebito> listarCuentasDebitoPorCliente(String cedulaCliente) {
        try {
            return daoCuentaDebito.listarCuentasDebitoPorCliente(cedulaCliente);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public String retirar(String numeroCuenta, double monto) {
        try {
            ArrayList<CuentaDebito> cuentas = listarCuentasDebito();
            for (CuentaDebito cuenta : cuentas) {
                if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                    if (cuenta.retirar(monto)) {
                        daoCuentaDebito.actualizarSaldo(numeroCuenta, cuenta.getSaldo());
                        return "Retiro exitoso! Nuevo saldo: $" + cuenta.getSaldo();
                    } else {
                        return "Error: No se pudo realizar el retiro. Verifique que la cuenta esté activa y tenga saldo suficiente";
                    }
                }
            }
            return "Error: Cuenta no encontrada";
        } catch (Exception e) {
            return "Error al realizar retiro: " + e.getMessage();
        }
    }

    public String depositar(String numeroCuenta, double monto) {
        try {
            ArrayList<CuentaDebito> cuentas = listarCuentasDebito();
            for (CuentaDebito cuenta : cuentas) {
                if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                    if (cuenta.depositar(monto)) {
                        daoCuentaDebito.actualizarSaldo(numeroCuenta, cuenta.getSaldo());
                        return "Depósito exitoso! Nuevo saldo: $" + cuenta.getSaldo();
                    } else {
                        return "Error: No se pudo realizar el depósito. Verifique que la cuenta esté activa";
                    }
                }
            }
            return "Error: Cuenta no encontrada";
        } catch (Exception e) {
            return "Error al realizar depósito: " + e.getMessage();
        }
    }

    public String generarInteres(String numeroCuenta) {
        try {
            ArrayList<CuentaDebito> cuentas = listarCuentasDebito();
            for (CuentaDebito cuenta : cuentas) {
                if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                    cuenta.generarInteres();
                    daoCuentaDebito.actualizarSaldo(numeroCuenta, cuenta.getSaldo());
                    return "Interés generado exitosamente! Nuevo saldo: $" + cuenta.getSaldo();
                }
            }
            return "Error: Cuenta no encontrada";
        } catch (Exception e) {
            return "Error al generar interés: " + e.getMessage();
        }
    }
}