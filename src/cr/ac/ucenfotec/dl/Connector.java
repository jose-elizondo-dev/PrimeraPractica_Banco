package cr.ac.ucenfotec.dl;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class Connector {
    private String url;
    private String user;
    private String password;
    private Connection connection;

    public Connector() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/cr/ac/ucenfotec/db.properties"));
            this.url = properties.getProperty("url");
            this.user = properties.getProperty("user");
            this.password = properties.getProperty("password");
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("Ocurrio un error inseperado");
        }
    }

    public void conectar() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
    }

    public void desconectar() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public void ejecutarSQL(String sql) throws Exception {
        conectar();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        statement.close();
        desconectar();
    }

    public ResultSet ejecutarQuery(String sql) throws Exception {
        conectar();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        return resultSet;
    }

    public void cerrarConexion(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                Statement statement = resultSet.getStatement();
                resultSet.close();
                if (statement != null) {
                    statement.close();
                }
            }
            desconectar();
        } catch (SQLException e) {
            System.err.println("Error al cerrar conexión: " + e.getMessage());
        }
    }
}