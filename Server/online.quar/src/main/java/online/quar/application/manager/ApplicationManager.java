package online.quar.application.manager;

import java.sql.SQLException;

public class ApplicationManager {

    AuthenticationManager am = new AuthenticationManager();
    CarManager cm = new CarManager();
    DatabaseManager dm = new DatabaseManager();
    RouteManager rm = new RouteManager();
    UserManager um = new UserManager();


    public void initialiseDatabase() throws SQLException {
        dm.initialise();
    }

    public AuthenticationManager getAuthenticationManager() {
        return am;
    }

    public CarManager getCarManager() {
        return cm;
    }

    public DatabaseManager getDatabaseManager() {
        return dm;
    }

    public RouteManager getRouteManager() {
        return rm;
    }

    public UserManager getUserManager() {
        return um;
    }
}
