package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.entities.CuentaAhorro.CuentaAhorro;
import cr.ac.ucenfotec.bl.entities.CuentaAhorro.DAOCuentaAhorro;
import cr.ac.ucenfotec.utils.Utils;

import java.time.LocalDate;
import java.util.ArrayList;

public class GestorCuentaAhorro {
    private DAOCuentaAhorro daoCuentaAhorro;

    public GestorCuentaAhorro() {
        daoCuentaAhorro = new DAOCuentaAhorro();
    }

    public String registrarCuentaAhorro(double saldo, LocalDate fechaCreacion,
                                        boolean activa, String cedulaCliente) {
        try {
            if (saldo < 100) {
                return "Error: La cuenta de ahorro debe tener al menos $100 de saldo inicial";
            }

            // Generar número de cuenta único
            String numeroCuenta;
            do {
                numeroCuenta = "AH" + Utils.generarNumeroCuentaUnico();
            } while (existeNumeroCuenta(numeroCuenta));

            double porcentajeInteres = Utils.getPorcentajeInteresAhorro();
            CuentaAhorro cuentaAhorro = new CuentaAhorro(numeroCuenta, saldo, fechaCreacion, activa, cedulaCliente, porcentajeInteres);
            daoCuentaAhorro.registrarCuentaAhorro(cuentaAhorro);
            return "Cuenta de ahorro registrada exitosamente! Número de cuenta: " + numeroCuenta;
        } catch (Exception e) {
            return "Error al registrar cuenta de ahorro: " + e.getMessage();
        }
    }

    private boolean existeNumeroCuenta(String numeroCuenta) {
        try {
            ArrayList<CuentaAhorro> cuentas = listarCuentasAhorro();
            for (CuentaAhorro cuenta : cuentas) {
                if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<CuentaAhorro> listarCuentasAhorro() {
        try {
            return daoCuentaAhorro.listarCuentasAhorro();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public ArrayList<CuentaAhorro> listarCuentasAhorroPorCliente(String cedulaCliente) {
        try {
            return daoCuentaAhorro.listarCuentasAhorroPorCliente(cedulaCliente);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public String retirar(String numeroCuenta, double monto) {
        try {
            ArrayList<CuentaAhorro> cuentas = listarCuentasAhorro();
            for (CuentaAhorro cuenta : cuentas) {
                if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                    if (cuenta.retirar(monto)) {
                        daoCuentaAhorro.actualizarSaldo(numeroCuenta, cuenta.getSaldo());
                        return "Retiro exitoso! Nuevo saldo: $" + cuenta.getSaldo();
                    } else {
                        return "Error: No se pudo realizar el retiro. Verifique que la cuenta esté activa y mantenga al menos $100";
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
            ArrayList<CuentaAhorro> cuentas = listarCuentasAhorro();
            for (CuentaAhorro cuenta : cuentas) {
                if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                    if (cuenta.depositar(monto)) {
                        daoCuentaAhorro.actualizarSaldo(numeroCuenta, cuenta.getSaldo());
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
            ArrayList<CuentaAhorro> cuentas = listarCuentasAhorro();
            for (CuentaAhorro cuenta : cuentas) {
                if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                    cuenta.generarInteres();
                    daoCuentaAhorro.actualizarSaldo(numeroCuenta, cuenta.getSaldo());
                    return "Interés generado exitosamente! Nuevo saldo: $" + cuenta.getSaldo();
                }
            }
            return "Error: Cuenta no encontrada";
        } catch (Exception e) {
            return "Error al generar interés: " + e.getMessage();
        }
    }
}