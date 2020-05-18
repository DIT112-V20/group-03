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

//            return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");

//    @MessageMapping("/carClient")
//    @SendTo("/topic/carClient")
//    public CarControlInput carClientOutput(CarControlInput carClientControlRequest) throws Exception {
//        return applicationManager.getCarManager().processCarClientControlRequest(carClientControlRequest);
//    }

}