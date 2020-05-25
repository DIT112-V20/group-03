package online.quar.application.controller;

import online.quar.application.Singleton;
import online.quar.application.manager.ApplicationManager;
import online.quar.application.model.CarControlInput;
import online.quar.application.model.HelloMessage;
import online.quar.application.model.Greeting;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class WSController {

    ApplicationManager applicationManager = Singleton.getApplicationManager();

// Adapted from: https://spring.io/guides/gs/messaging-stomp-websocket/

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @MessageMapping("/carControl")
    @SendTo("/topic/car")
    public CarControlInput carControlInput(CarControlInput carControlInput) throws Exception {
        return applicationManager.getCarManager().processCarControlInput(carControlInput);
    }

//    @MessageMapping("/carClient")
//    @SendTo("/topic/carClient")
//    public CarControlInput carClientOutput(CarControlInput carClientControlRequest) throws Exception {
//        return applicationManager.getCarManager().processCarClientControlRequest(carClientControlRequest);
//    }

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