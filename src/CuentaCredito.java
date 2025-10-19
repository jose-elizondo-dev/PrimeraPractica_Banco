public class CuentaCredito extends Cuenta {
    private double limiteCredito;
    private String tipoCredito;

    public CuentaCredito(String numeroCuenta, Cliente propietario, double limiteCredito, String tipoCredito) {
        super(numeroCuenta, propietario, 0); // siempre inicia en 0
        this.limiteCredito = limiteCredito;
        this.tipoCredito = tipoCredito;
    }


    public void depositar(double monto) {
        if (!activa) { System.out.println("Cuenta inactiva"); return; }
        saldo += monto; // abono reduce deuda
        if (saldo > 0){
            saldo = 0;
            System.out.println("El saldo excede el monto pendiente a pagar");
        }  // no puede tener saldo positivo
    }


    public void retirar(double monto) {
        if (!activa) { System.out.println("Cuenta inactiva"); return; }
        if (saldo - monto >= -limiteCredito) saldo -= monto;
        else System.out.println("No puede retirar, supera límite de crédito");
    }
}
