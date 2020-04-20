package online.quar.application.manager;

import online.quar.application.Singleton;
import online.quar.application.helper.QueryHelper;
import online.quar.application.util.Logger;
import org.postgresql.util.PSQLException;

import java.sql.*;

public class DatabaseManager {
    Logger log = Singleton.getLogger();

    // DB connection configuration
    private static String DRIVER_CLASS = "org.postgresql.Driver";
    private static String DB_USER = "quaronline";
    private static String DB_PASSWORD = "#!.~A_f8gLEBM5p8";
    private static String DB_URL = "jdbc:postgresql://localhost:5432/quaronline";
    private static int EXIT_FAILURE = 1;


    public ResultSet executeQuery(Connection c, String queryToExecute) {
        ResultSet rs = null;
        try {
            rs = c.createStatement().executeQuery(queryToExecute);
        } catch (SQLException e) {
            System.out.println("An error has been thrown:\n" + e.getMessage());
        }
        return rs;
    }

    private Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void initialise() throws SQLException {
        Connection c = getConnection();
        if(c == null) {
            System.out.println("Couldn't establish connection to database, terminating.");
            return;
        }

        PreparedStatement initialiseDataBaseStatement;
        PreparedStatement updateDataBaseStatement;
        try {
            initialiseDataBaseStatement = c.prepareStatement(QueryHelper.sqlQuery("sql/sql_initialisation.sql"));
            initialiseDataBaseStatement.execute();

            updateDataBaseStatement = c.prepareStatement(QueryHelper.sqlQuery("sql/sql_update.sql"));
            updateDataBaseStatement.execute();
        }

        catch (SQLException e){
            log.c(e.getMessage());
        }

      //  ResultSet rs = executeQuery(c, "SELECT 15 AS retval;");
//        if(rs.next()) {
//
//        }
//
//        rs.close();
        c.close();
    }


}
