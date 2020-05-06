package online.quar.application.manager;

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
        Car ret = databaseManager.getCar(carId, true);

        //If the car is not found in the database, null will be returned
        return ret;
    }

    public CarControlInput processCarControlInput(CarControlInput controlInput) {
//        Car car = findCar(controlInput.getCarId());
//
//        if(car == null || !car.isOnline()) {
//            return null;
//        }
//
//        car.setSetSpeed(controlInput.getCarSetSpeed());
//        car.setSetAngle(controlInput.getCarSetAngle());
//
//        controlInput.setCarActualSpeed(car.getActualSpeed());
//        controlInput.setCarActualAngle(car.getActualAngle());

        log.d(controlInput.toString());

        return controlInput;
    }


}
