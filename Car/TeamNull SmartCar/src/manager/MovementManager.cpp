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

void obstacleAvoidance(int safeDistance) {
if (checkFront == false){
if( checkRight(safeDistance)){
        turnRight();
    }
    else if (checkLeft(safeDistance)==false){
        turnLeft();
    }
    else collisionAvoidance();
}
}

//returns false if the obstacle in front is closer than safeFrontDistance
boolean checkFront(int safeDistance) {
int distanceFromObstacle = 0 ;
distanceFromObstacle = getFrontDistance();
    if (distanceFromObstacle < safeDistance){
    return false;
    }
else return true;
}

//returns false if the obstacle on right is closer than safeFrontDistance
boolean checkRight(int safeDistance) {
int distanceFromObstacle = 0 ;
distanceFromObstacle= getRightFrontDistance();
    if (distanceFromObstacle < safeDistance){
    return false;
    }
    else 
    return true;
}

//returns false if the obstacle on left is closer than safeFrontDistance
boolean checkLeft(int safeDistance) {
int distanceFromObstacle = 0 ;
distanceFromObstacle= getRightFrontDistance();
    if (distanceFromObstacle < safeDistance){
    return false;
    }
    else 
    return true;
}

void turnRight(){}
void turnLeft(){}
void chooseNewDirection(){}