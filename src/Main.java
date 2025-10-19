import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Banco banco = new Banco();
        int opcion;

        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Registrar administrador");
            System.out.println("2. Login administrador");
            System.out.println("3. Login cliente");
            System.out.println("4. Salir");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(br.readLine());

            switch (opcion) {
                case 1: // Registrar administrador
                    System.out.print("Nombre: ");
                    String n = br.readLine();
                    System.out.print("Cédula: ");
                    String ci = br.readLine();
                    System.out.print("Correo: ");
                    String c = br.readLine();
                    System.out.print("Contraseña: ");
                    String p = br.readLine();
                    banco.registrarAdministrador(n, ci, c, p);
                    break;
                case 2: // Login admin
                    System.out.print("Correo: ");
                    String mail = br.readLine();
                    System.out.print("Contraseña: ");
                    String pass = br.readLine();
                    if (banco.loginAdministrador(mail, pass)) menuAdministrador(br, banco);
                    else System.out.println("Credenciales incorrectas");
                    break;
                case 3: // Login cliente
                    System.out.print("Correo: ");
                    String mailc = br.readLine();
                    System.out.print("Contraseña: ");
                    String passc = br.readLine();
                    Cliente cli = banco.buscarClientePorCorreo(mailc);
                    if (cli != null && cli.getContrasenia().equals(passc)) menuCliente(br, banco, cli);
                    else System.out.println("Credenciales incorrectas");
                    break;
            }

        } while (opcion != 4);
    }

    static void menuAdministrador(BufferedReader br, Banco banco) throws Exception {
        int op;
        do {
            System.out.println("\n--- ADMINISTRADOR ---");
            System.out.println("1. Registrar nuevo cliente");
            System.out.println("2. Ver clientes");
            System.out.println("3. Ver cuentas");
            System.out.println("4. Salir");
            System.out.print("Opción: ");
            op = Integer.parseInt(br.readLine());

            switch (op) {
                case 1: // Registrar cliente
                    System.out.print("Nombre completo: ");
                    String nombre = br.readLine();
                    System.out.print("Cédula: ");
                    String cedula = br.readLine();
                    System.out.print("Correo electrónico: ");
                    String correo = br.readLine();
                    System.out.print("Contraseña: ");
                    String contrasenia = br.readLine();
                    System.out.print("Sexo: ");
                    String sexo = br.readLine();
                    System.out.print("Profesión: ");
                    String profesion = br.readLine();
                    System.out.print("Dirección: ");
                    String direccion = br.readLine();

                    banco.registrarCliente(nombre, cedula, correo, contrasenia, sexo, profesion, direccion);
                    break;

                case 2:
                    banco.mostrarClientes();
                    break;

                case 3:
                    banco.mostrarCuentas();
                    break;
            }
        } while (op != 4);
    }


    static void menuCliente(BufferedReader br, Banco banco, Cliente cliente) throws Exception {
        int op;
        do {
            System.out.println("\n--- CLIENTE ---");
            System.out.println("1. Ver mis cuentas y saldo total");
            System.out.println("2. Crear nueva cuenta");
            System.out.println("3. Realizar transacción");
            System.out.println("4. Salir");
            System.out.print("Opción: ");
            op = Integer.parseInt(br.readLine());

            switch (op) {
                case 1:
                    cliente.mostrarCuentas();
                    break;
                case 2:
                    System.out.println("Tipo de cuenta: 1-Ahorro, 2-Débito, 3-Crédito");
                    int tipo = Integer.parseInt(br.readLine());
                    System.out.print("Número de cuenta: ");
                    String numero = br.readLine();
                    double saldo = 0, limite = 0;
                    String tipoCredito = "";

                    if (tipo == 1) {
                        do {
                            System.out.print("Saldo inicial (mínimo 100): ");
                            saldo = Double.parseDouble(br.readLine());
                        } while (saldo < 100);
                    } else if (tipo == 2) {
                        System.out.print("Saldo inicial: ");
                        saldo = Double.parseDouble(br.readLine());
                    } else if (tipo == 3) {
                        System.out.print("Límite de crédito: ");
                        limite = Double.parseDouble(br.readLine());
                        System.out.print("Tipo de crédito (Cashback, Millas, etc.): ");
                        tipoCredito = br.readLine();
                    }

                    banco.crearCuenta(cliente, tipo, numero, saldo, tipoCredito, limite);
                    System.out.println("Cuenta creada correctamente.");
                    break;

                case 3: // Transacciones
                    if (cliente.getCuentas().isEmpty()) {
                        System.out.println("No tiene cuentas registradas.");
                        break;
                    }
                    System.out.println("Seleccione cuenta:");
                    for (int i = 0; i < cliente.getCuentas().size(); i++) {
                        Cuenta c = cliente.getCuentas().get(i);
                        System.out.println((i + 1) + ". " + c.getNumeroCuenta() +
                                " (" + c.getClass().getSimpleName() + ")");
                    }
                    int sel = Integer.parseInt(br.readLine()) - 1;
                    if (sel < 0 || sel >= cliente.getCuentas().size()) {
                        System.out.println("Selección inválida.");
                        break;
                    }
                    Cuenta cuenta = cliente.getCuentas().get(sel);

                    System.out.println("1. Depositar / Abonar");
                    System.out.println("2. Retirar / Pagar");
                    int accion = Integer.parseInt(br.readLine());
                    System.out.print("Monto: ");
                    double monto = Double.parseDouble(br.readLine());

                    if (cuenta instanceof CuentaCredito) {
                        if (accion == 1) cuenta.depositar(monto);
                        else if (accion == 2) cuenta.retirar(monto);
                    } else {
                        if (accion == 1) cuenta.depositar(monto);
                        else if (accion == 2) cuenta.retirar(monto);
                    }
                    break;
            }
        } while (op != 4);
    }
}

