package online.quar.application.manager;

import online.quar.application.Singleton;
import online.quar.application.model.CarControlInput;
import online.quar.application.model.Route;
import online.quar.application.util.Logger;

import java.util.concurrent.TimeUnit;

public class RouteManager {
    Logger log = Singleton.getLogger();

    CarManager carMngr = Singleton.getApplicationManager().getCarManager();
    Route newRout;

    public Boolean startRec(long id){
        log.d("Started recording route");
        carMngr.findCar(id).setPlzRec(true);
        newRout = new Route(System.currentTimeMillis(),id);
        return true;
    }

    public Boolean stopRec(long id){
        log.d("Stop recording route");
        carMngr.findCar(id).setPlzRec(false);
        return true;
    }

    public Boolean playRec(long id) throws InterruptedException {
        log.d("Started replaying recorded route");
        for(int x=0;x<newRout.getDurAry().size();x++) {
            TimeUnit.MILLISECONDS.sleep(newRout.getDurAt(x));
            carMngr.processCarControlInput(newRout.getDirAt(x));
        }
        return true;
    }

    public void catchInput(CarControlInput imp){
        if(carMngr.findCar(imp.getCarId()).isPlzRec()){     //if bool to record == true
            newRout.setNewMove(imp, System.currentTimeMillis());
        }
    }
}
