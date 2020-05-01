package online.quar.application;

import online.quar.application.manager.ApplicationManager;
import online.quar.application.manager.DatabaseManager;
import online.quar.application.util.Logger;

/**
 *
 * Singleton class for accessing exactly one instance of the dataManager, across all classes
 */
public class Singleton {

    private static ApplicationManager instance;
    private static IO ioInstance;
    private static Logger logInstance;

    public static synchronized ApplicationManager getApplicationManager() {
        //if there is no existing instance, we first create one, before returning it
        if(instance == null){
            instance = new ApplicationManager();
        }
        //otherwise return the known instance
        return instance;
    }

    public static synchronized IO getIO() {
        if(ioInstance == null){
            ioInstance = new IO();
        }
        return ioInstance;
    }

    public static synchronized Logger getLogger() {
        if(logInstance == null){
            logInstance = new Logger();
        }
        return logInstance;
    }

}