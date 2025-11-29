package cr.ac.ucenfotec.dl;

import java.sql.*;

public class DBAccess {
    private Connection connection;

    public DBAccess(String url, String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(url, user, password);
    }

    public Connection getConnection() {
        return connection;
    }

    public void cerrarConexion() throws SQLException {
        if(connection != null) connection.close();
    }
}
