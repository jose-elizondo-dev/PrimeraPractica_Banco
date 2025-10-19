public class CuentaDebito extends Cuenta {
    private final double INTERES = 1.0; // predefinido 1%

    public CuentaDebito(String numeroCuenta, Cliente propietario, double saldo) {
        super(numeroCuenta, propietario, saldo);
        if (saldo < 0) throw new IllegalArgumentException("Saldo inicial no puede ser negativo");
    }

    @Override
    public void depositar(double monto) {
        if (!activa) { System.out.println("Cuenta inactiva"); return; }
        saldo += monto;
    }

    @Override
    public void retirar(double monto) {
        if (!activa) { System.out.println("Cuenta inactiva"); return; }
        if (saldo - monto >= 0) saldo -= monto;
        else System.out.println("No puede retirar, saldo insuficiente");
    }

    public void generarInteres() {
        saldo += saldo * (INTERES / 100);
    }
}
