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
    private final static String getCarScript = sqlPath + "sql_getCar.sql";
    private final static String getUserScript = sqlPath + "sql_getUser.sql";


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

                statement.setLong(5, user.getId());
            }
            statement.setString(1, user.getUsername());
            statement.setBytes(2, user.getPassword());
            statement.setString(3, user.getFullname());
            statement.setBoolean(4, user.isActive());

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
            connection.close();
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

                statement.setLong(3, car.getId());
            }

            statement.setString(1, car.getDescription());
            statement.setBoolean(2, car.isActive());

            statement.execute();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    car.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating Car failed, no ID obtained.");
                }
            }
            connection.commit();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            log.c(e.getMessage());
        }
        return car;
    }


    public Car getCar(long carId, Boolean activeOnly) {
        if(carId < 0){
            return null;
        }
        Car car = null;
        try {
            String query;
            PreparedStatement statement;
            Connection connection = getConnection();

            query = QueryHelper.sqlQuery(getCarScript);
            assert connection != null;
            statement = connection.prepareStatement(query);
            statement.setLong(1, carId);

            statement.execute();

            try (ResultSet r = statement.getResultSet()) {
                if (r.next()) {
                    car = new Car(r.getLong("id"), r.getString("description"), r.getBoolean("active"));
                }
            }
            connection.commit();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            log.c(e.getMessage());
        }
        if(car != null) {
            if(activeOnly && !car.isActive()){
                car = null;
            }
        }
        return car;
    }

    public User getUser(long userId, boolean activeOnly) {
        if(userId < 0){
            return null;
        }
        User user = null;
        try {
            String query;
            PreparedStatement statement;
            Connection connection = getConnection();

            query = QueryHelper.sqlQuery(getUserScript);
            assert connection != null;
            statement = connection.prepareStatement(query);
            statement.setLong(1, userId);

            statement.execute();

            try (ResultSet r = statement.getResultSet()) {
                if (r.next()) {
                    user = new User(r.getLong("id"), r.getString("username"), r.getBytes("password"), r.getString("fullname"), r.getBoolean("active"));
                }
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            log.c(e.getMessage());
        }
        if(user != null) {
            if(activeOnly && !user.isActive()){
                user = null;
            }
        }
        return user;
    }
}
