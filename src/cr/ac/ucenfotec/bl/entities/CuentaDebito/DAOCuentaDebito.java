package cr.ac.ucenfotec.bl.entities.CuentaDebito;

import cr.ac.ucenfotec.dl.Connector;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class DAOCuentaDebito {
    private Connector connector;

    public DAOCuentaDebito() {
        connector = new Connector();
    }

    public void registrarCuentaDebito(CuentaDebito cuentaDebito) throws Exception {
        // Primero insertar en la tabla Cuenta
        String queryCuenta = "INSERT INTO Cuenta (numeroCuenta, tipo, saldo, fechaCreacion, activa, cedulaCliente) " +
                "VALUES ('" + cuentaDebito.getNumeroCuenta() + "', 'DEBITO', " + cuentaDebito.getSaldo() + ", '" +
                cuentaDebito.getFechaCreacion() + "', " + cuentaDebito.isActiva() + ", '" + cuentaDebito.getCedulaCliente() + "')";
        connector.ejecutarSQL(queryCuenta);

        // Luego insertar en la tabla CuentaDebito
        String queryDebito = "INSERT INTO CuentaDebito (numeroCuenta, porcentajeInteres) " +
                "VALUES ('" + cuentaDebito.getNumeroCuenta() + "', " + cuentaDebito.getPorcentajeInteres() + ")";
        connector.ejecutarSQL(queryDebito);
    }

    public ArrayList<CuentaDebito> listarCuentasDebito() throws Exception {
        ArrayList<CuentaDebito> cuentas = new ArrayList<>();
        String query = "SELECT c.*, cd.porcentajeInteres FROM Cuenta c " +
                "JOIN CuentaDebito cd ON c.numeroCuenta = cd.numeroCuenta " +
                "WHERE c.tipo = 'DEBITO'";
        ResultSet rs = connector.ejecutarQuery(query);
        while (rs.next()) {
            String numeroCuenta = rs.getString("numeroCuenta");
            double saldo = rs.getDouble("saldo");
            LocalDate fechaCreacion = rs.getDate("fechaCreacion").toLocalDate();
            boolean activa = rs.getBoolean("activa");
            String cedulaCliente = rs.getString("cedulaCliente");
            double porcentajeInteres = rs.getDouble("porcentajeInteres");
            cuentas.add(new CuentaDebito(numeroCuenta, saldo, fechaCreacion, activa, cedulaCliente, porcentajeInteres));
        }
        return cuentas;
    }

    public ArrayList<CuentaDebito> listarCuentasDebitoPorCliente(String cedulaCliente) throws Exception {
        ArrayList<CuentaDebito> cuentas = new ArrayList<>();
        String query = "SELECT c.*, cd.porcentajeInteres FROM Cuenta c " +
                "JOIN CuentaDebito cd ON c.numeroCuenta = cd.numeroCuenta " +
                "WHERE c.tipo = 'DEBITO' AND c.cedulaCliente = '" + cedulaCliente + "'";
        ResultSet rs = connector.ejecutarQuery(query);
        while (rs.next()) {
            String numeroCuenta = rs.getString("numeroCuenta");
            double saldo = rs.getDouble("saldo");
            LocalDate fechaCreacion = rs.getDate("fechaCreacion").toLocalDate();
            boolean activa = rs.getBoolean("activa");
            double porcentajeInteres = rs.getDouble("porcentajeInteres");
            cuentas.add(new CuentaDebito(numeroCuenta, saldo, fechaCreacion, activa, cedulaCliente, porcentajeInteres));
        }
        return cuentas;
    }

    public void actualizarSaldo(String numeroCuenta, double nuevoSaldo) throws Exception {
        String query = "UPDATE Cuenta SET saldo = " + nuevoSaldo + " WHERE numeroCuenta = '" + numeroCuenta + "'";
        connector.ejecutarSQL(query);
    }
}