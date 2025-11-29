package cr.ac.ucenfotec.bl.entities.CuentaCredito;

import cr.ac.ucenfotec.dl.Connector;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class DAOCuentaCredito {
    private Connector connector;

    public DAOCuentaCredito() {
        connector = new Connector();
    }

    public void registrarCuentaCredito(CuentaCredito cuentaCredito) throws Exception {
        // Primero insertar en la tabla Cuenta
        String queryCuenta = "INSERT INTO Cuenta (numeroCuenta, tipo, saldo, fechaCreacion, activa, cedulaCliente) " +
                "VALUES ('" + cuentaCredito.getNumeroCuenta() + "', 'CREDITO', " + cuentaCredito.getSaldo() + ", '" +
                cuentaCredito.getFechaCreacion() + "', " + cuentaCredito.isActiva() + ", '" + cuentaCredito.getCedulaCliente() + "')";
        connector.ejecutarSQL(queryCuenta);

        // Luego insertar en la tabla CuentaCredito
        String queryCredito = "INSERT INTO CuentaCredito (numeroCuenta, tipo, limiteCredito) " +
                "VALUES ('" + cuentaCredito.getNumeroCuenta() + "', '" + cuentaCredito.getTipo() + "', " +
                cuentaCredito.getLimiteCredito() + ")";
        connector.ejecutarSQL(queryCredito);
    }

    public ArrayList<CuentaCredito> listarCuentasCredito() throws Exception {
        ArrayList<CuentaCredito> cuentas = new ArrayList<>();
        String query = "SELECT c.*, cc.tipo as tipoCredito, cc.limiteCredito FROM Cuenta c " +
                "JOIN CuentaCredito cc ON c.numeroCuenta = cc.numeroCuenta " +
                "WHERE c.tipo = 'CREDITO'";
        ResultSet rs = connector.ejecutarQuery(query);
        while (rs.next()) {
            String numeroCuenta = rs.getString("numeroCuenta");
            double saldo = rs.getDouble("saldo");
            LocalDate fechaCreacion = rs.getDate("fechaCreacion").toLocalDate();
            boolean activa = rs.getBoolean("activa");
            String cedulaCliente = rs.getString("cedulaCliente");
            String tipo = rs.getString("tipoCredito");
            double limiteCredito = rs.getDouble("limiteCredito");
            cuentas.add(new CuentaCredito(numeroCuenta, saldo, fechaCreacion, activa, cedulaCliente, tipo, limiteCredito));
        }
        return cuentas;
    }

    public ArrayList<CuentaCredito> listarCuentasCreditoPorCliente(String cedulaCliente) throws Exception {
        ArrayList<CuentaCredito> cuentas = new ArrayList<>();
        String query = "SELECT c.*, cc.tipo as tipoCredito, cc.limiteCredito FROM Cuenta c " +
                "JOIN CuentaCredito cc ON c.numeroCuenta = cc.numeroCuenta " +
                "WHERE c.tipo = 'CREDITO' AND c.cedulaCliente = '" + cedulaCliente + "'";
        ResultSet rs = connector.ejecutarQuery(query);
        while (rs.next()) {
            String numeroCuenta = rs.getString("numeroCuenta");
            double saldo = rs.getDouble("saldo");
            LocalDate fechaCreacion = rs.getDate("fechaCreacion").toLocalDate();
            boolean activa = rs.getBoolean("activa");
            String tipo = rs.getString("tipoCredito");
            double limiteCredito = rs.getDouble("limiteCredito");
            cuentas.add(new CuentaCredito(numeroCuenta, saldo, fechaCreacion, activa, cedulaCliente, tipo, limiteCredito));
        }
        return cuentas;
    }

    public void actualizarSaldo(String numeroCuenta, double nuevoSaldo) throws Exception {
        String query = "UPDATE Cuenta SET saldo = " + nuevoSaldo + " WHERE numeroCuenta = '" + numeroCuenta + "'";
        connector.ejecutarSQL(query);
    }
}