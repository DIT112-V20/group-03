package online.quar.application.controller;

import online.quar.application.Singleton;
import online.quar.application.manager.ApplicationManager;
import online.quar.application.model.CarControlInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestfulController {

    ApplicationManager applicationManager = Singleton.getApplicationManager();

    @RequestMapping("/controllerTest")
    public String index() {
        return "Hello World, We are TeamNull!";
    }

//    @RequestMapping("/carClient")
//    public CarControlInput carClientOutput(CarControlInput carClientControlRequest) throws Exception {
//        return applicationManager.getCarManager().processCarClientControlRequest(carClientControlRequest);
//    }

    @RequestMapping(path= "/carClient", method = RequestMethod.GET)
    public String addEmployee(
            @RequestBody CarControlInput carClientControlRequest)
                 throws Exception {

//        carClientControlRequest = applicationManager.getCarManager().processCarClientControlRequest(carClientControlRequest);
//
//        //Send location in response
//        return carClientControlRequest.toString();
        return "Hello World :)";
        }

}