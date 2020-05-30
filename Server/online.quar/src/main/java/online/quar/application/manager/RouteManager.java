package online.quar.application.manager;

import online.quar.application.Singleton;
import online.quar.application.model.CarControlInput;
import online.quar.application.model.Route;
import online.quar.application.util.Logger;

import java.util.concurrent.TimeUnit;

public class RouteManager {
    Logger log = Singleton.getLogger();

    CarManager carMngr = null;
    Route newRout;

    //TODO: this should be called in Application Manager on load
    private void getCarManagerIfNeeded() {
        if(carMngr == null) {
            carMngr = Singleton.getApplicationManager().getCarManager();
        }
    }

    public Boolean startRec(long id){
        getCarManagerIfNeeded();
        log.d("Started recording route");
        carMngr.findCar(id).setPlzRec(true);
        newRout = new Route(System.currentTimeMillis(),id);
        return true;
    }

    public Boolean stopRec(long id){
        getCarManagerIfNeeded();
        log.d("Stop recording route");
        carMngr.findCar(id).setPlzRec(false);
        return true;
    }

    public Boolean playRec(long id) throws InterruptedException {
        getCarManagerIfNeeded();
        log.d("Started replaying recorded route");
        if(newRout.getDurAry().size() > 0) {
            Long lastDur = 0l;
            for (int x = 0; x < newRout.getDurAry().size(); x++) {
                TimeUnit.MILLISECONDS.sleep(newRout.getDurAt(x)-lastDur);
                carMngr.processCarControlInput(newRout.getDirAt(x));
                lastDur = newRout.getDurAt(x);
            }
        }
        logger.d("---------------- DONE PLAYING RECORDING ---------------- DONE PLAYING RECORDING ----------------");
        return true;
    }

    public void catchInput(CarControlInput imp){
        getCarManagerIfNeeded();
        if(carMngr.findCar(imp.getCarId()).isPlzRec()){     //if bool to record == true
            newRout.setNewMove(imp, System.currentTimeMillis());
        }
    }
}
