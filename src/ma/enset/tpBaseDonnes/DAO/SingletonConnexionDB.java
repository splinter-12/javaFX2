package ma.enset.tpBaseDonnes.DAO;

import java.sql.*;

public class SingletonConnexionDB {
    private static Connection connection;
    static {
        // charger le pilote JDBC pour MYSQL

        try {
            Class.forName("com.mysql.jdbc.Driver");
            //établir la connection avec db
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ges","root","");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection()
    {
        return connection;
    }
}
