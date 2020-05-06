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

}