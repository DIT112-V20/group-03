package online.quar.application.controller;

import online.quar.application.Singleton;
import online.quar.application.manager.ApplicationManager;
import online.quar.application.model.CarControlInput;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestfulController {

    ApplicationManager applicationManager = Singleton.getApplicationManager();

    @RequestMapping("/controllerTest")
    public String index() {
        return "Hello World, We are TeamNull!";
    }

    @RequestMapping("/carClient")
    public CarControlInput carClientOutput(CarControlInput carClientControlRequest) throws Exception {
        return applicationManager.getCarManager().processCarClientControlRequest(carClientControlRequest);
    }

}