public abstract class Cuenta {
    protected String numeroCuenta;
    protected Cliente propietario;
    protected double saldo;
    protected boolean activa;

    public Cuenta(String numeroCuenta, Cliente propietario, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.propietario = propietario;
        this.saldo = saldo;
        this.activa = true; // por defecto activa
    }

    public String getNumeroCuenta() { return numeroCuenta; }
    public Cliente getPropietario() { return propietario; }
    public double getSaldo() { return saldo; }
    public boolean isActiva() { return activa; }
    public void setActiva(boolean activa) { this.activa = activa; }

    public abstract void depositar(double monto);
    public abstract void retirar(double monto);
}
