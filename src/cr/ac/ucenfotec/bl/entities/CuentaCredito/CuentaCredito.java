package cr.ac.ucenfotec.bl.entities.CuentaCredito;

import cr.ac.ucenfotec.bl.entities.Cuenta.Cuenta;

import java.time.LocalDate;

public class CuentaCredito extends Cuenta {
    private String tipo;
    private double limiteCredito;

    public CuentaCredito() {
    }

    public CuentaCredito(String numeroCuenta, double saldo, LocalDate fechaCreacion, boolean activa, String cedulaCliente, String tipo, double limiteCredito) {
        super(numeroCuenta, saldo, fechaCreacion, activa, cedulaCliente);
        this.tipo = tipo;
        this.limiteCredito = limiteCredito;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    @Override
    public boolean retirar(double monto) {
        if (!activa) return false;
        if (saldo - monto >= -limiteCredito) {
            saldo -= monto;
            return true;
        }
        return false;
    }

    @Override
    public boolean depositar(double monto) {
        // En cuentas de crédito, los depósitos se llaman abonos
        if (!activa) return false;
        double nuevoSaldo = saldo + monto;
        if (nuevoSaldo > 0) {
            return false; // No puede tener saldo positivo
        }
        saldo = nuevoSaldo;
        return true;
    }

    public boolean abonar(double monto) {
        return depositar(monto);
    }

    @Override
    public String toString() {
        return "CuentaCredito{" +
                "numeroCuenta='" + numeroCuenta + '\'' +
                ", saldo=" + saldo +
                ", fechaCreacion=" + fechaCreacion +
                ", activa=" + activa +
                ", cedulaCliente='" + cedulaCliente + '\'' +
                ", tipo='" + tipo + '\'' +
                ", limiteCredito=" + limiteCredito +
                '}';
    }
}