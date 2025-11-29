package cr.ac.ucenfotec.utils;

import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Utils {
    private static final Random random = new Random();

    // Constantes para porcentajes de interés
    public static final double PORCENTAJE_INTERES_AHORRO = 2.5; // 2.5% para cuentas de ahorro
    public static final double PORCENTAJE_INTERES_DEBITO = 1.2; // 1.2% para cuentas de débito

    public static String generarNumeroCuentaUnico() {

        LocalDate fecha = LocalDate.now();
        String anio = String.valueOf(fecha.getYear());
        String mes = String.format("%02d", fecha.getMonthValue());
        String randomDigits = String.format("%06d", random.nextInt(1000000));

        return anio + mes + randomDigits;
    }

//    public static String formatearFecha(LocalDate fecha) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        return fecha.format(formatter);
//    }
//
//    public static boolean validarEmail(String email) {
//        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
//    }
//
//    public static boolean validarCedula(String cedula) {
//        return cedula != null && cedula.matches("\\d+");
//    }

    public static double getPorcentajeInteresAhorro() {
        return PORCENTAJE_INTERES_AHORRO;
    }

    public static double getPorcentajeInteresDebito() {
        return PORCENTAJE_INTERES_DEBITO;
    }
}