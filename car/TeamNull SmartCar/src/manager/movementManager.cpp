#include "movementManager.hpp"
#include <Smartcar.h>

BrushedMotor leftMotor(smartcarlib::pins::v2::leftMotorPins);
BrushedMotor rightMotor(smartcarlib::pins::v2::rightMotorPins);
DifferentialControl control(leftMotor, rightMotor);

SimpleCar car(control);

int carSpeedSet = 0;
int carSpeedActual = 0;
int turnAngleSet = 0;
int turnAngleActual = 0;

//TODO: Calculate gyroscope offset
int gyroscopeOffset = 0;

void setDesiredVehicleSpeed (int speed) {
    carSpeedSet = speed;
    car.setSpeed(carSpeedSet);
    carSpeedActual = carSpeedSet;
}

void setDesireTurnAngle (int heading) {
    carSpeedSet = heading;
    car.setSpeed(carSpeedSet);
    carSpeedActual = carSpeedSet;
}

void collisionAvoidance() {
    int distanceFromObstacle = getFrontDistance();
    if(distanceFromObstacle < 275) {
        car.setSpeed(0);
        carSpeedActual = 0;
        Serial.print("COLLISION AVOIDANCE: distance from obstacle: ");
        Serial.print(distanceFromObstacle);
        Serial.println("mm");
    } else  if (carSpeedActual != carSpeedSet) {
        car.setSpeed(carSpeedSet);
        carSpeedActual = carSpeedSet;
    }
}