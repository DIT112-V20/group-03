package online.quar.application.manager;

import online.quar.application.Singleton;

import java.sql.SQLException;

public class ApplicationManager {

    DatabaseManager dm = new DatabaseManager();

    public void initialiseDatabase() throws SQLException {
        dm.initialise();
    }
    public DatabaseManager getDatabaseManager(){
        return dm;
    }

}
