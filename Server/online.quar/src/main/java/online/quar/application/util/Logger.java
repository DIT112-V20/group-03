package online.quar.application.util;

import online.quar.application.IO;
import online.quar.application.Singleton;

public class Logger {
    enum Level {
        TEMPORARY,
        PERSISTENT,
        VERBOSE
    }

    Level logLevel;
    IO io;




    public Logger(){
        io = Singleton.getIO();
        logLevel = Level.TEMPORARY;
    }

    public Logger(Level logLevel){
        io = Singleton.getIO();
        this.logLevel = logLevel;
    }

    public void log(String level, String message){
        d(message);
    }

    public void d(String message){
        io.p(message);
        if(logLevel == Level.PERSISTENT || logLevel == Level.VERBOSE) {
            io.toLogFile(message);
        }
    }

    public void c(String message){
        io.p(message);
        if(logLevel == Level.PERSISTENT || logLevel == Level.VERBOSE) {
            io.toLogFile(message);
        }
    }

    public void v(String message){
        io.p(message);
        if(logLevel == Level.PERSISTENT || logLevel == Level.VERBOSE) {
            io.toLogFile(message);
        }
    }
}
