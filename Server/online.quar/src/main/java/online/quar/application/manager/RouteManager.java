package online.quar.application.manager;

import online.quar.application.Singleton;
import online.quar.application.model.CarControlInput;
import online.quar.application.model.Route;

import java.util.concurrent.TimeUnit;

public class RouteManager {

    CarManager carMngr = Singleton.getApplicationManager().getCarManager();
    Route newRout;

    public Boolean startRec(long id){
        carMngr.findCar(id).setPlzRec(true);
        newRout = new Route(System.currentTimeMillis(),id);
        return true;
    }

    public Boolean stopRec(long id){
        carMngr.findCar(id).setPlzRec(false);
        return true;
    }

    public Boolean playRec(long id) throws InterruptedException {
        for(int x=0;x<newRout.getDurAry().size();x++) {
            TimeUnit.MILLISECONDS.sleep(newRout.getDurAt(x));           //LIETH: "IM SURE THIS GOES ON TOP!"
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
