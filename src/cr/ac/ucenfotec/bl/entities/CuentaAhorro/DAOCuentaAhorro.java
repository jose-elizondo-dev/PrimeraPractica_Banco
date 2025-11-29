package cr.ac.ucenfotec.bl.entities.CuentaAhorro;

import cr.ac.ucenfotec.dl.Connector;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class DAOCuentaAhorro {
    private Connector connector;

    public DAOCuentaAhorro() {
        connector = new Connector();
    }

    public void registrarCuentaAhorro(CuentaAhorro cuentaAhorro) throws Exception {
        // Primero insertar en la tabla Cuenta
        String queryCuenta = "INSERT INTO Cuenta (numeroCuenta, tipo, saldo, fechaCreacion, activa, cedulaCliente) " +
                "VALUES ('" + cuentaAhorro.getNumeroCuenta() + "', 'AHORRO', " + cuentaAhorro.getSaldo() + ", '" +
                cuentaAhorro.getFechaCreacion() + "', " + cuentaAhorro.isActiva() + ", '" + cuentaAhorro.getCedulaCliente() + "')";
        connector.ejecutarSQL(queryCuenta);

        // Luego insertar en la tabla CuentaAhorro
        String queryAhorro = "INSERT INTO CuentaAhorro (numeroCuenta, porcentajeInteres) " +
                "VALUES ('" + cuentaAhorro.getNumeroCuenta() + "', " + cuentaAhorro.getPorcentajeInteres() + ")";
        connector.ejecutarSQL(queryAhorro);
    }

    public ArrayList<CuentaAhorro> listarCuentasAhorro() throws Exception {
        ArrayList<CuentaAhorro> cuentas = new ArrayList<>();
        String query = "SELECT c.*, ca.porcentajeInteres FROM Cuenta c " +
                "JOIN CuentaAhorro ca ON c.numeroCuenta = ca.numeroCuenta " +
                "WHERE c.tipo = 'AHORRO'";
        ResultSet rs = connector.ejecutarQuery(query);
        while (rs.next()) {
            String numeroCuenta = rs.getString("numeroCuenta");
            double saldo = rs.getDouble("saldo");
            LocalDate fechaCreacion = rs.getDate("fechaCreacion").toLocalDate();
            boolean activa = rs.getBoolean("activa");
            String cedulaCliente = rs.getString("cedulaCliente");
            double porcentajeInteres = rs.getDouble("porcentajeInteres");
            cuentas.add(new CuentaAhorro(numeroCuenta, saldo, fechaCreacion, activa, cedulaCliente, porcentajeInteres));
        }
        return cuentas;
    }

    public ArrayList<CuentaAhorro> listarCuentasAhorroPorCliente(String cedulaCliente) throws Exception {
        ArrayList<CuentaAhorro> cuentas = new ArrayList<>();
        String query = "SELECT c.*, ca.porcentajeInteres FROM Cuenta c " +
                "JOIN CuentaAhorro ca ON c.numeroCuenta = ca.numeroCuenta " +
                "WHERE c.tipo = 'AHORRO' AND c.cedulaCliente = '" + cedulaCliente + "'";
        ResultSet rs = connector.ejecutarQuery(query);
        while (rs.next()) {
            String numeroCuenta = rs.getString("numeroCuenta");
            double saldo = rs.getDouble("saldo");
            LocalDate fechaCreacion = rs.getDate("fechaCreacion").toLocalDate();
            boolean activa = rs.getBoolean("activa");
            double porcentajeInteres = rs.getDouble("porcentajeInteres");
            cuentas.add(new CuentaAhorro(numeroCuenta, saldo, fechaCreacion, activa, cedulaCliente, porcentajeInteres));
        }
        return cuentas;
    }

    public void actualizarSaldo(String numeroCuenta, double nuevoSaldo) throws Exception {
        String query = "UPDATE Cuenta SET saldo = " + nuevoSaldo + " WHERE numeroCuenta = '" + numeroCuenta + "'";
        connector.ejecutarSQL(query);
    }
}