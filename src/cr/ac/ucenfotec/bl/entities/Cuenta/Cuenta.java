package cr.ac.ucenfotec.bl.entities.Cuenta;

import java.time.LocalDate;

public abstract class Cuenta {
    protected String numeroCuenta;
    protected double saldo;
    protected LocalDate fechaCreacion;
    protected boolean activa;
    protected String cedulaCliente;

    public Cuenta() {
    }

    public Cuenta(String numeroCuenta, double saldo, LocalDate fechaCreacion, boolean activa, String cedulaCliente) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.fechaCreacion = fechaCreacion;
        this.activa = activa;
        this.cedulaCliente = cedulaCliente;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(String cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public abstract boolean retirar(double monto);
    public abstract boolean depositar(double monto);

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cuenta cuenta = (Cuenta) obj;
        return numeroCuenta.equals(cuenta.numeroCuenta);
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "numeroCuenta='" + numeroCuenta + '\'' +
                ", saldo=" + saldo +
                ", fechaCreacion=" + fechaCreacion +
                ", activa=" + activa +
                ", cedulaCliente='" + cedulaCliente + '\'' +
                '}';
    }
}