package br.com.agenda.factory;

// TODO: encapsulate secrets in environment variables

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    private static final String USERNAME="root";
    private static final String PASSWORD="password";
    private static final String DATABASE_URL= "jdbc:mysql://localhost:3306/agenda";

    public static Connection createConnectionToMySQL() throws Exception {
        // Loads class in JVM
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = (Connection) DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

        return connection;
    }

    public static void main(String[] args) throws Exception {
        Connection con = createConnectionToMySQL();

        if(con != null){
            System.out.printf("Conexão com sucesso!");
            con.close();
        }
    }
}
