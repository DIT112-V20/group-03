package online.quar.application.manager;

import online.quar.application.Singleton;
import online.quar.application.helper.QueryHelper;
import online.quar.application.model.Car;
import online.quar.application.model.User;
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

    private final static String sqlPath = "sql/";
    private final static String insertUserScript = sqlPath + "sql_insertUser.sql";
    private final static String updateUserScript = sqlPath + "sql_updateUser.sql";
    private final static String insertCarScript = sqlPath + "sql_insertCar.sql";
    private final static String updateCarScript = sqlPath + "sql_updateCar.sql";


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
        if (c == null) {
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
        } catch (SQLException e) {
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

    public User save(User user) {
        try {
            String query;
            PreparedStatement statement;
            Connection connection = getConnection();

            if (user.getId() != -1) {
                //If the user id does not exist, insert one.
                query = QueryHelper.sqlQuery(insertUserScript);
                statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            } else {
                //If the user id does exist, update it.
                query = QueryHelper.sqlQuery(updateUserScript);
                statement = connection.prepareStatement(query);

                statement.setLong(4, user.getId());
            }
            statement.setString(1, user.getUsername());
            statement.setBytes(2, user.getPassword());
            statement.setString(3, user.getFullname());

            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            connection.commit();
            statement.close();

            //TODO: Check what this is.
            //connection.setAutoCommit(false);
        } catch (SQLException e) {
            log.c(e.getMessage());
        }
        return user;
    }

    public Car save(Car car) {
        try {
            String query;
            PreparedStatement statement;
            Connection connection = getConnection();

            if (car.getId() != -1) {
                //If the user id does not exist, insert one.
                query = QueryHelper.sqlQuery(insertCarScript);
                statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            } else {
                //If the user id does exist, update it.
                query = QueryHelper.sqlQuery(updateCarScript);
                statement = connection.prepareStatement(query);

                statement.setLong(2, car.getId());
            }

            statement.setString(1, car.getDescription());

            statement.execute();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    car.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            log.c(e.getMessage());
        }
        return car;
    }


}
