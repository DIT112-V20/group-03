package online.quar.application.controller;

import online.quar.application.Singleton;
import online.quar.application.manager.ApplicationManager;
import online.quar.application.model.CarControlInput;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WSController {

    ApplicationManager applicationManager = Singleton.getApplicationManager();

    @MessageMapping("/carControl")
    @SendTo("/topic/car")
    public CarControlInput carControlInput(CarControlInput carControlInput) throws Exception {
        return applicationManager.getCarManager().processCarControlInput(carControlInput);
    }

    @MessageMapping("/startRecRoute")
    @SendTo("/topic/userInterface")
    public Boolean startRecRoute(CarControlInput carControlInput) throws Exception {
        return applicationManager.getRouteManager().startRec(carControlInput.getCarId());
    }

    @MessageMapping("/stopRecRoute")
    @SendTo("/topic/userInterface")
    public Boolean stopRecRoute(CarControlInput carControlInput) throws Exception {
        return applicationManager.getRouteManager().stopRec(carControlInput.getCarId());
    }

    @MessageMapping("/playRecRoute")
    @SendTo("/topic/userInterface")
    public Boolean playRecRoute(CarControlInput carControlInput) throws Exception {
        return applicationManager.getRouteManager().playRec(carControlInput.getCarId());
    }

}