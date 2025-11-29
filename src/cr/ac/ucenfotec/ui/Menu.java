package cr.ac.ucenfotec.ui;

//import cr.ac.ucenfotec.bl.entities.Administrador.Administrador;
import cr.ac.ucenfotec.bl.entities.cliente.Cliente;
import cr.ac.ucenfotec.bl.entities.CuentaAhorro.CuentaAhorro;
import cr.ac.ucenfotec.bl.entities.CuentaCredito.CuentaCredito;
import cr.ac.ucenfotec.bl.entities.CuentaDebito.CuentaDebito;
import cr.ac.ucenfotec.tl.Controller;
import cr.ac.ucenfotec.utils.Utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private Controller controller;
    private Scanner scanner;

    public Menu(Controller controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion;
        do {
            System.out.println("\n=== SISTEMA BANCARIO ===");
            System.out.println("1. Registrar administrador");
            System.out.println("2. Ingresar como administrador");
            System.out.println("3. Ingresar como cliente");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarAdministrador();
                    break;
                case 2:
                    ingresarAdministrador();
                    break;
                case 3:
                    ingresarCliente();
                    break;
                case 4:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 4);
    }

    private void registrarAdministrador() {
        System.out.println("\n=== REGISTRAR ADMINISTRADOR ===");
        System.out.print("Nombre completo: ");
        String nombreCompleto = scanner.nextLine();
        System.out.print("Cédula: ");
        String cedula = scanner.nextLine();
        System.out.print("Correo electrónico: ");
        String correoElectronico = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasenia = scanner.nextLine();

        String resultado = controller.registrarAdministrador(nombreCompleto, cedula, correoElectronico, contrasenia);
        System.out.println(resultado);
    }

    private void ingresarAdministrador() {
        System.out.println("\n=== INGRESAR COMO ADMINISTRADOR ===");
        System.out.print("Correo electrónico: ");
        String correoElectronico = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasenia = scanner.nextLine();

        String resultado = controller.validarAdministrador(correoElectronico, contrasenia);
        System.out.println(resultado);

        if (resultado.equals("Administrador validado exitosamente!")) {
            menuAdministrador();
        }
    }

    private void menuAdministrador() {
        int opcion;
        do {
            System.out.println("\n=== MENÚ ADMINISTRADOR ===");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Listar clientes");
            System.out.println("3. Listar cuentas de ahorro");
            System.out.println("4. Listar cuentas de débito");
            System.out.println("5. Listar cuentas de crédito");
            System.out.println("6. Cerrar sesión");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarCliente();
                    break;
                case 2:
                    listarClientes();
                    break;
                case 3:
                    listarCuentasAhorro();
                    break;
                case 4:
                    listarCuentasDebito();
                    break;
                case 5:
                    listarCuentasCredito();
                    break;
                case 6:
                    System.out.println("Cerrando sesión...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 6);
    }

    private void registrarCliente() {
        System.out.println("\n=== REGISTRAR CLIENTE ===");
        System.out.print("Nombre completo: ");
        String nombreCompleto = scanner.nextLine();
        System.out.print("Cédula: ");
        String cedula = scanner.nextLine();
        System.out.print("Correo electrónico: ");
        String correoElectronico = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasenia = scanner.nextLine();
        System.out.print("Sexo (M/F): ");
        String sexo = scanner.nextLine();
        System.out.print("Profesión: ");
        String profesion = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();

        String resultado = controller.registrarCliente(nombreCompleto, cedula, correoElectronico, contrasenia, sexo, profesion, direccion);
        System.out.println(resultado);
    }

    private void listarClientes() {
        System.out.println("\n=== LISTA DE CLIENTES ===");
        ArrayList<Cliente> clientes = controller.listarClientes();
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }

    private void listarCuentasAhorro() {
        System.out.println("\n=== LISTA DE CUENTAS DE AHORRO ===");
        ArrayList<CuentaAhorro> cuentas = controller.listarCuentasAhorro();
        if (cuentas.isEmpty()) {
            System.out.println("No hay cuentas de ahorro registradas");
        } else {
            for (CuentaAhorro cuenta : cuentas) {
                System.out.println(cuenta);
            }
        }
    }

    private void listarCuentasDebito() {
        System.out.println("\n=== LISTA DE CUENTAS DE DÉBITO ===");
        ArrayList<CuentaDebito> cuentas = controller.listarCuentasDebito();
        if (cuentas.isEmpty()) {
            System.out.println("No hay cuentas de débito registradas");
        } else {
            for (CuentaDebito cuenta : cuentas) {
                System.out.println(cuenta);
            }
        }
    }

    private void listarCuentasCredito() {
        System.out.println("\n=== LISTA DE CUENTAS DE CRÉDITO ===");
        ArrayList<CuentaCredito> cuentas = controller.listarCuentasCredito();
        if (cuentas.isEmpty()) {
            System.out.println("No hay cuentas de crédito registradas");
        } else {
            for (CuentaCredito cuenta : cuentas) {
                System.out.println(cuenta);
            }
        }
    }

    private void ingresarCliente() {
        System.out.println("\n=== INGRESAR COMO CLIENTE ===");
        System.out.print("Correo electrónico: ");
        String correoElectronico = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasenia = scanner.nextLine();

        String resultado = controller.validarCliente(correoElectronico, contrasenia);
        System.out.println(resultado);

        if (resultado.equals("Cliente validado exitosamente!")) {
            Cliente cliente = controller.buscarCliente(correoElectronico);
            menuCliente(cliente);
        }
    }

    private void menuCliente(Cliente cliente) {
        int opcion;
        do {
            System.out.println("\n=== MENÚ CLIENTE ===");
            System.out.println("Bienvenido, " + cliente.getNombreCompleto());
            System.out.println("1. Crear cuenta de ahorro");
            System.out.println("2. Crear cuenta de débito");
            System.out.println("3. Crear cuenta de crédito");
            System.out.println("4. Realizar transacciones");
            System.out.println("5. Generar intereses");
            System.out.println("6. Reporte de estados de cuenta");
            System.out.println("7. Cerrar sesión");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    crearCuentaAhorro(cliente);
                    break;
                case 2:
                    crearCuentaDebito(cliente);
                    break;
                case 3:
                    crearCuentaCredito(cliente);
                    break;
                case 4:
                    menuTransacciones(cliente);
                    break;
                case 5:
                    generarIntereses(cliente);
                    break;
                case 6:
                    reporteEstadosCuenta(cliente);
                    break;
                case 7:
                    System.out.println("Cerrando sesión...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 7);
    }

    private void crearCuentaAhorro(Cliente cliente) {
        System.out.println("\n=== CREAR CUENTA DE AHORRO ===");
        System.out.println("Porcentaje de interés : " + Utils.getPorcentajeInteresAhorro() + "%");
        System.out.print("Saldo inicial (mínimo $100): ");
        double saldo = scanner.nextDouble();
        scanner.nextLine();

        String resultado = controller.registrarCuentaAhorro(saldo, LocalDate.now(), true, cliente.getCedula());
        System.out.println(resultado);
    }

    private void crearCuentaDebito(Cliente cliente) {
        System.out.println("\n=== CREAR CUENTA DE DÉBITO ===");
        System.out.println("Porcentaje de interés asignado por el sistema: " + Utils.getPorcentajeInteresDebito() + "%");
        System.out.print("Saldo inicial: ");
        double saldo = scanner.nextDouble();
        scanner.nextLine();

        String resultado = controller.registrarCuentaDebito(saldo, LocalDate.now(), true, cliente.getCedula());
        System.out.println(resultado);
    }

    private void crearCuentaCredito(Cliente cliente) {
        System.out.println("\n=== CREAR CUENTA DE CRÉDITO ===");
        System.out.print("Tipo de cuenta (Cashback, Millas, etc.): ");
        String tipo = scanner.nextLine();
        System.out.print("Límite de crédito: ");
        double limiteCredito = scanner.nextDouble();
        scanner.nextLine();

        String resultado = controller.registrarCuentaCredito(LocalDate.now(), true, cliente.getCedula(), tipo, limiteCredito);
        System.out.println(resultado);
    }

    private void menuTransacciones(Cliente cliente) {
        int opcion;
        do {
            System.out.println("\n=== TRANSACCIONES ===");
            System.out.println("1. Retirar de cuenta de ahorro");
            System.out.println("2. Depositar a cuenta de ahorro");
            System.out.println("3. Retirar de cuenta de débito");
            System.out.println("4. Depositar a cuenta de débito");
            System.out.println("5. Retirar de cuenta de crédito");
            System.out.println("6. Abonar a cuenta de crédito");
            System.out.println("7. Volver");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    realizarRetiroAhorro();
                    break;
                case 2:
                    realizarDepositoAhorro();
                    break;
                case 3:
                    realizarRetiroDebito();
                    break;
                case 4:
                    realizarDepositoDebito();
                    break;
                case 5:
                    realizarRetiroCredito();
                    break;
                case 6:
                    realizarAbonoCredito();
                    break;
                case 7:
                    System.out.println("Volviendo...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 7);
    }

    private void realizarRetiroAhorro() {
        System.out.print("Número de cuenta: ");
        String numeroCuenta = scanner.nextLine();
        System.out.print("Monto a retirar: ");
        double monto = scanner.nextDouble();
        scanner.nextLine();

        String resultado = controller.retirarCuentaAhorro(numeroCuenta, monto);
        System.out.println(resultado);
    }

    private void realizarDepositoAhorro() {
        System.out.print("Número de cuenta: ");
        String numeroCuenta = scanner.nextLine();
        System.out.print("Monto a depositar: ");
        double monto = scanner.nextDouble();
        scanner.nextLine();

        String resultado = controller.depositarCuentaAhorro(numeroCuenta, monto);
        System.out.println(resultado);
    }

    private void realizarRetiroDebito() {
        System.out.print("Número de cuenta: ");
        String numeroCuenta = scanner.nextLine();
        System.out.print("Monto a retirar: ");
        double monto = scanner.nextDouble();
        scanner.nextLine();

        String resultado = controller.retirarCuentaDebito(numeroCuenta, monto);
        System.out.println(resultado);
    }

    private void realizarDepositoDebito() {
        System.out.print("Número de cuenta: ");
        String numeroCuenta = scanner.nextLine();
        System.out.print("Monto a depositar: ");
        double monto = scanner.nextDouble();
        scanner.nextLine();

        String resultado = controller.depositarCuentaDebito(numeroCuenta, monto);
        System.out.println(resultado);
    }

    private void realizarRetiroCredito() {
        System.out.print("Número de cuenta: ");
        String numeroCuenta = scanner.nextLine();
        System.out.print("Monto a retirar: ");
        double monto = scanner.nextDouble();
        scanner.nextLine();

        String resultado = controller.retirarCuentaCredito(numeroCuenta, monto);
        System.out.println(resultado);
    }

    private void realizarAbonoCredito() {
        System.out.print("Número de cuenta: ");
        String numeroCuenta = scanner.nextLine();
        System.out.print("Monto a abonar: ");
        double monto = scanner.nextDouble();
        scanner.nextLine();

        String resultado = controller.abonarCuentaCredito(numeroCuenta, monto);
        System.out.println(resultado);
    }

    private void generarIntereses(Cliente cliente) {
        System.out.println("\n=== GENERAR INTERESES ===");
        System.out.println("1. Generar intereses en cuenta de ahorro");
        System.out.println("2. Generar intereses en cuenta de débito");
        System.out.print("Seleccione una opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Número de cuenta: ");
        String numeroCuenta = scanner.nextLine();

        String resultado;
        if (opcion == 1) {
            resultado = controller.generarInteresCuentaAhorro(numeroCuenta);
        } else if (opcion == 2) {
            resultado = controller.generarInteresCuentaDebito(numeroCuenta);
        } else {
            resultado = "Opción inválida";
        }
        System.out.println(resultado);
    }

    private void reporteEstadosCuenta(Cliente cliente) {
        System.out.println("\n=== REPORTE DE ESTADOS DE CUENTA ===");

        ArrayList<CuentaAhorro> cuentasAhorro = controller.listarCuentasAhorroPorCliente(cliente.getCedula());
        ArrayList<CuentaDebito> cuentasDebito = controller.listarCuentasDebitoPorCliente(cliente.getCedula());
        ArrayList<CuentaCredito> cuentasCredito = controller.listarCuentasCreditoPorCliente(cliente.getCedula());

        double saldoTotal = 0;

        System.out.println("\n--- Cuentas de Ahorro ---");
        if (cuentasAhorro.isEmpty()) {
            System.out.println("No tiene cuentas de ahorro");
        } else {
            for (CuentaAhorro cuenta : cuentasAhorro) {
                System.out.println(cuenta);
                saldoTotal += cuenta.getSaldo();
            }
        }

        System.out.println("\n--- Cuentas de Débito ---");
        if (cuentasDebito.isEmpty()) {
            System.out.println("No tiene cuentas de débito");
        } else {
            for (CuentaDebito cuenta : cuentasDebito) {
                System.out.println(cuenta);
                saldoTotal += cuenta.getSaldo();
            }
        }

        System.out.println("\n--- Cuentas de Crédito ---");
        if (cuentasCredito.isEmpty()) {
            System.out.println("No tiene cuentas de crédito");
        } else {
            for (CuentaCredito cuenta : cuentasCredito) {
                System.out.println(cuenta);
                saldoTotal += cuenta.getSaldo(); // Las cuentas de crédito tienen saldo negativo o cero
            }
        }

        System.out.println("\n--- RESUMEN FINAL ---");
        System.out.println("Saldo consolidado total: $" + saldoTotal);
    }
}