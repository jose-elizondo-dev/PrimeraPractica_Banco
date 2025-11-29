package cr.ac.ucenfotec.bl.entities.CuentaDebito;

import cr.ac.ucenfotec.bl.entities.Cuenta.Cuenta;

import java.time.LocalDate;

public class CuentaDebito extends Cuenta {
    private double porcentajeInteres;

    public CuentaDebito() {
    }

    public CuentaDebito(String numeroCuenta, double saldo, LocalDate fechaCreacion, boolean activa, String cedulaCliente, double porcentajeInteres) {
        super(numeroCuenta, saldo, fechaCreacion, activa, cedulaCliente);
        this.porcentajeInteres = porcentajeInteres;
    }

    public double getPorcentajeInteres() {
        return porcentajeInteres;
    }

    public void setPorcentajeInteres(double porcentajeInteres) {
        this.porcentajeInteres = porcentajeInteres;
    }

    @Override
    public boolean retirar(double monto) {
        if (!activa) return false;
        if (saldo - monto >= 0) {
            saldo -= monto;
            return true;
        }
        return false;
    }

    @Override
    public boolean depositar(double monto) {
        if (!activa) return false;
        saldo += monto;
        return true;
    }

    public void generarInteres() {
        if (activa) {
            saldo += saldo * (porcentajeInteres / 100);
        }
    }

    @Override
    public String toString() {
        return "CuentaDebito{" +
                "numeroCuenta='" + numeroCuenta + '\'' +
                ", saldo=" + saldo +
                ", fechaCreacion=" + fechaCreacion +
                ", activa=" + activa +
                ", cedulaCliente='" + cedulaCliente + '\'' +
                ", porcentajeInteres=" + porcentajeInteres +
                '}';
    }
}