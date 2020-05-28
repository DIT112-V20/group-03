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
    public Boolean startRecRoute(long id) throws Exception {
        return applicationManager.getRouteManager().startRec(id);
    }

    @MessageMapping("/stopRecRoute")
    @SendTo("/topic/userInterface")
    public Boolean stopRecRoute(long id) throws Exception {
        return applicationManager.getRouteManager().stopRec(id);
    }

    @MessageMapping("/playRecRoute")
    @SendTo("/topic/userInterface")
    public Boolean playRecRoute(long id) throws Exception {
        return applicationManager.getRouteManager().playRec(id);
    }

}