package online.quar.application.manager;

import com.github.javafaker.App;
import online.quar.application.Singleton;
import online.quar.application.model.Car;
import online.quar.application.model.CarControlInput;
import online.quar.application.util.Logger;

import java.util.ArrayList;

public class CarManager {
    ArrayList<Car> cars = new ArrayList<>();
    Logger log = Singleton.getLogger();
    public Car addCar(Car carToAdd) {
        cars.add(carToAdd);

        //Save the car that was just added
        Singleton.getApplicationManager().getDatabaseManager().save(carToAdd);
        return carToAdd;
    }

    public boolean removeCar(Car car) {
        Car carToRemove = findCar(car.getId());
        if(carToRemove == null) {
            return true;
        }
        cars.remove(carToRemove);

        //to "remove" from saved
        carToRemove.setActive(false);
        Singleton.getApplicationManager().getDatabaseManager().save(carToRemove);

        if (findCar(carToRemove.getId()) == null) {
            return true;
        }
        return false;
    }

    public Car findCar(long carId) {
        //First check for car in cache
        for (Car car : cars) {
            if (car.getId() == carId && car.isActive()) {
                return car;
            }
        }

        //Car was not found in memory, check database
        DatabaseManager databaseManager = Singleton.getApplicationManager().getDatabaseManager();
        Car car = databaseManager.getCar(carId, true);

        if(car != null) {
            cars.add(car);
        }

        log.d("Found car: " + car.toString());

        //If the car is not found in the database, null will be returned
        return car;
    }

    public CarControlInput processCarControlInput(CarControlInput controlInput) {

        Car car = findCar(controlInput.getCarId());

        if(car == null || !car.isOnline()) {
            return null;
        }

        car.setSetSpeed(controlInput.getCarSetSpeed());
        car.setSetAngle(controlInput.getCarSetAngle());

        controlInput.setCarActualSpeed(car.getActualSpeed());
        controlInput.setCarActualAngle(car.getActualAngle());

        controlInput.setCarObstacleAvoidance(car.isCarObstacleAvoidance());
        controlInput.setCarCollisionAvoidance(car.isCarCollisionAvoidance());
        controlInput.setFrontDistance(car.getFrontDistance());
        controlInput.setLeftFrontDistance(car.getLeftFrontDistance());
        controlInput.setRightFrontDistance(car.getRightFrontDistance());
        controlInput.setRearDistance(car.getRearDistance());

        Singleton.getApplicationManager().getRouteManager().catchInput(controlInput);

        return controlInput;
    }


    public CarControlInput processCarClientControlRequest(CarControlInput carClientControlRequest) {

        log.d(carClientControlRequest.toString());

        Car car = findCar(carClientControlRequest.getCarId());

        if(car == null) {
            return null;
        }

        if(!car.isOnline()) {
            car.setOnline(true);
        }

        carClientControlRequest.setCarSetSpeed(car.getSetSpeed());
        carClientControlRequest.setCarSetAngle(car.getSetAngle());

        car.setActualSpeed(carClientControlRequest.getCarActualSpeed());
        car.setActualAngle(carClientControlRequest.getCarActualAngle());

        car.setCarObstacleAvoidance(carClientControlRequest.isCarObstacleAvoidance());
        car.setCarCollisionAvoidance(carClientControlRequest.isCarCollisionAvoidance());
        car.setFrontDistance(carClientControlRequest.getFrontDistance());
        car.setLeftFrontDistance(carClientControlRequest.getLeftFrontDistance());
        car.setRightFrontDistance(carClientControlRequest.getRightFrontDistance());
        car.setRearDistance(carClientControlRequest.getRearDistance());

        log.d(car.toString());
        log.d(carClientControlRequest.toString());

        return carClientControlRequest;
    }
}
