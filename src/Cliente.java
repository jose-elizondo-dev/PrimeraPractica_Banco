import java.util.ArrayList;

public class Cliente extends Usuario {
    private String sexo, profesion, direccion;
    private ArrayList<Cuenta> cuentas = new ArrayList<>();

    public Cliente(String nombreCompleto, String cedula, String correoElectronico, String contrasenia,
                   String sexo, String profesion, String direccion) {
        super(nombreCompleto, cedula, correoElectronico, contrasenia);
        this.sexo = sexo;
        this.profesion = profesion;
        this.direccion = direccion;
    }

    public void agregarCuenta(Cuenta c) { cuentas.add(c); }
    public ArrayList<Cuenta> getCuentas() { return cuentas; }

    public void mostrarCuentas() {
        double total = 0;
        System.out.println("\nCuentas de " + nombreCompleto + ":");
        for (Cuenta c : cuentas) {
            System.out.println("- " + c.getClass().getSimpleName() +
                    " | N°: " + c.getNumeroCuenta() +
                    " | Saldo: $" + c.getSaldo() +
                    " | Activa: " + c.isActiva());
            total += c.getSaldo();
        }
        System.out.println("Saldo consolidado total: $" + total);
    }
}
