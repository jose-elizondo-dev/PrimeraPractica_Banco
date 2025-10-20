public class CuentaAhorro extends Cuenta {
    private double INTERES = 3.0;

    public CuentaAhorro(String numeroCuenta, Cliente propietario, double saldo) {
        super(numeroCuenta, propietario, saldo);
    }

    public void depositar(double monto) {
        if (!activa) { System.out.println("Cuenta inactiva"); return; }
        saldo += monto;
    }

    public void retirar(double monto) {
        if (!activa) { System.out.println("Cuenta inactiva"); return; }
        if (saldo - monto >= 100) saldo -= monto;
        else System.out.println("No puede retirar, saldo mínimo 100");
    }

    public void generarInteres() {
        saldo += saldo * (INTERES / 100);
    }
}
