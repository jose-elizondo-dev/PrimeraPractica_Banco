import java.util.ArrayList;

public class Banco {
    private Administrador administrador;
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Cuenta> cuentas = new ArrayList<>();

    public void registrarAdministrador(String nombre, String cedula, String correo, String contrasenia) {
        if (administrador != null) { System.out.println("Ya existe administrador"); return; }
        administrador = new Administrador(nombre, cedula, correo, contrasenia);
        System.out.println("Administrador registrado correctamente.");
    }

    public boolean loginAdministrador(String correo, String contrasenia) {
        return administrador != null &&
                administrador.getCorreoElectronico().equals(correo) &&
                administrador.getContrasenia().equals(contrasenia);
    }

    public void registrarCliente(String nombre, String cedula, String correo, String contrasenia,
                                 String sexo, String profesion, String direccion) {
        if (administrador == null) { System.out.println("Debe registrar un administrador primero"); return; }
        Cliente c = new Cliente(nombre, cedula, correo, contrasenia, sexo, profesion, direccion);
        clientes.add(c);
        System.out.println("Cliente registrado correctamente.");
    }

    public Cliente buscarClientePorCorreo(String correo) {
        for (Cliente c : clientes) {
            if (c.getCorreoElectronico().equals(correo)) return c;
        }
        return null;
    }

    public void crearCuenta(Cliente cliente, int tipo, String numero, double saldo, String tipoCredito, double limite) {
        Cuenta nuevaCuenta = null;

        switch (tipo) {
            case 1: nuevaCuenta = new CuentaAhorro(numero, cliente, saldo);break;
            case 2: nuevaCuenta = new CuentaDebito(numero, cliente, saldo);break;
            case 3: nuevaCuenta = new CuentaCredito(numero, cliente, limite, tipoCredito);break;
        }

        if (nuevaCuenta != null){
            cuentas.add(nuevaCuenta);
            cliente.agregarCuenta(nuevaCuenta);
        }

    }

    public void mostrarClientes() {
        System.out.println("\n--- Clientes ---");
        for (Cliente c : clientes) System.out.println(c.getNombreCompleto() + " | " + c.getCorreoElectronico());
    }

    public void mostrarCuentas() {
        System.out.println("\n--- Cuentas ---");
        for (Cuenta c : cuentas) {
            System.out.println(c.getClass().getSimpleName() + " | Cliente: " + c.getPropietario().getNombreCompleto() +
                    " | Saldo: $" + c.getSaldo() + " | Activa: " + c.isActiva());
        }
    }
}
