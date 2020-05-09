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

    @PostMapping(path= "/carClient", consumes = "application/json", produces = "application/json")
    public String addEmployee(
//            @RequestHeader(name = "X-COM-PERSIST", required = true) String headerPersist,
//            @RequestHeader(name = "X-COM-LOCATION", required = false, defaultValue = "ASIA") String headerLocation,
            @RequestBody CarControlInput carClientControlRequest)
                 throws Exception {

        carClientControlRequest = applicationManager.getCarManager().processCarClientControlRequest(carClientControlRequest);

        //Send location in response
        return carClientControlRequest.toString();
    }

}