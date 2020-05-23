package online.quar.application.controller;

import online.quar.application.Singleton;
import online.quar.application.manager.ApplicationManager;
import online.quar.application.model.CarControlInput;
import online.quar.application.util.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestfulController {

    ApplicationManager applicationManager = Singleton.getApplicationManager();
    Logger logger = Singleton.getLogger();
    @RequestMapping("/controllerTest")
    public String index() {
        return "Hello World, We are TeamNull!";
    }

    @RequestMapping(path= "/carClient")
    public String processCarRequest( @RequestParam long carId, int carActualSpeed, int carActualAngle, boolean carObstacleAvoidance, boolean carCollisionAvoidance,
                                     int frontDistance, int leftFrontDistance, int rightFrontDistance, int rearDistance) {
        CarControlInput carClientRequest = new CarControlInput(carId, carActualSpeed, carActualAngle, carObstacleAvoidance, carCollisionAvoidance, frontDistance, leftFrontDistance, rightFrontDistance, rearDistance);
        carClientRequest = applicationManager.getCarManager().processCarClientControlRequest(carClientRequest);
        return carClientRequest.toJSON();
    }

    @RequestMapping(path= "/carLog")
    public String processCarLogRequest( @RequestParam long carId, String message ) {
        String timestamp = ""; //TODO: Create timestamp for logging
        logger.d(timestamp + "|Log entry from car " + carId + ": " + message);
        return ("Good Job!");
    }

}