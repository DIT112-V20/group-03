package online.quar.application.manager;

import online.quar.application.Singleton;
import online.quar.application.model.Car;

import java.util.ArrayList;

public class CarManager {
    private long carId;

    ArrayList<Car> cars = new ArrayList<>();

    public Car addCar(Car car) {
        Car carToAdd = findCar(car);
        cars.add(carToAdd);

        //Save the user that was just added
        Singleton.getApplicationManager().getDatabaseManager().save(carToAdd);
        return carToAdd;
    }

    public boolean removeCar(Car car) {
        Car carToRemove = findCar(car);
        cars.remove(carToRemove);

        //to "remove" from saved
        carToRemove.setActive(false);
        Singleton.getApplicationManager().getDatabaseManager().save(carToRemove);

        if (findCar(carToRemove) == null) {
            return true;
        }
        return false;
    }

    public Car findCar(Car car) {
        carId = car.getId();

        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getId() == carId) {
                return cars.get(i);
            }
        }
        return null;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }
}
