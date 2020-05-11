#include "MovementManager.hpp"

int carSpeedSet = 0;
int carSpeedActual = 0;
int turnAngleSet = 0;
int turnAngleActual = 0;

//TODO: Calculate gyroscope offset
int gyroscopeOffset = 0;

void setDesiredVehicleSpeed (int speed) {
    carSpeedSet = speed;
    // setSpeed(carSpeedSet);
    // carSpeedActual = carSpeedSet;
}

void setDesireTurnAngle (int heading) {
    turnAngleSet = heading;
    setAngle(turnAngleSet);
    turnAngleActual = turnAngleSet;
}

void collisionAvoidance() {
    int distanceFromObstacle = 0;
    if(carSpeedSet >= 0) {
        distanceFromObstacle = getFrontDistance();
    } else {
        distanceFromObstacle = getRearDistance();
    }
    if(distanceFromObstacle < 275 && distanceFromObstacle != 0) {
        setSpeed(0);
        carSpeedActual = 0;
        Serial.print("COLLISION AVOIDANCE: distance from obstacle: ");
        Serial.print(distanceFromObstacle);
        Serial.println("mm");
    } else  if (carSpeedActual != carSpeedSet) {
        setSpeed(carSpeedSet);
        carSpeedActual = carSpeedSet;
    }
}

int getActualCarSpeed() {
    return 7;
}

int getActualCarAngle() {
    return 21;
}

String getActualCarStatus() {
    String result = "carId=";
    result += "1";
    result += "&carActualSpeed=";
    result += "20";
    result += "&carActualAngle=";
    result += "10";

    return result;
}

void obstacleAvoidance() {

}