#include "MovementManager.hpp"
#include "WifiManager.hpp"

int carSpeedSet = 0;
int carSpeedActual = 0;
int turnAngleSet = 0;
int turnAngleActual = 0;
int classSafeDistance = 0 ;

boolean colisionBeingAvoided = false;
boolean obstacleBeingAvoided = false;
boolean obstacleAvoidanceTurnedRight = false;
boolean obstacleAvoidanceTurnedLeft = false;
long timeObstacleWasAvoided = millis();
long timereturnToRouteBegan = millis();

//This is a helper method for logging purposes only
String booleanToString(boolean boolean)
{
    if (boolean)
    {
        return "true";
    }
    else
    {
        return "false";
    }
}

void setDesiredVehicleSpeed(int speed)
{
    carSpeedSet = speed;
    // setSpeed(carSpeedSet);
    // carSpeedActual = carSpeedSet;
}

void setDesireTurnAngle(int heading)
{
    turnAngleSet = heading;
    setAngle(turnAngleSet);
    turnAngleActual = turnAngleSet;
}

void collisionAvoidance()
{
    // logging("___CollisionAvoidance%20ENTERED___");
    int distanceFromObstacle = 0;
    if (carSpeedSet >= 0) {
        distanceFromObstacle = getFrontDistance();
    }
    else {
        distanceFromObstacle = getRearDistance();
    }
    if (distanceFromObstacle < 400 && distanceFromObstacle != 0) {
        setSpeed(0);
        carSpeedActual = 0;
        logging("COLLISION%20AVOIDANCE%20distance%20from%20obstacle%20" + (String) distanceFromObstacle + "mm");
        colisionBeingAvoided = true;
    }
    else if (carSpeedActual != carSpeedSet) {
        setSpeed(carSpeedSet);
        carSpeedActual = carSpeedSet;
        colisionBeingAvoided = false;
    }
    // logging("___CollisionAvoidance%20LEFT___");
}

int getActualCarSpeed()
{
    return getCarCurrentSpeed();
}

String getActualCarStatus()
{
    String result = "carId=";
    result += "1";
    result += "&carActualSpeed=";
    result += getActualCarSpeed();
    result += "&carActualAngle=";
    result += turnAngleActual;

    return result;
}

void obstacleAvoidance(int safeDistance) {
classSafeDistance = safeDistance;

    if (carSpeedActual > 0) {
        logging("Car%20is%20going%20forward");
        logging("checkFront%20output_" + booleanToString(checkFront(safeDistance)));
        if (checkFront(safeDistance) == false) {
            logging("__FRONT%20OBSTACLE__");
            logging("___obstacleAvoidance%20Activated___");
            if (checkRight(safeDistance)) {
                logging("__RIGHT%20IS%20SAFE__");
                if(!obstacleAvoidanceTurnedRight){
                    obstacleAvoidanceTurnedRight = true;
                    obstacleBeingAvoided = true;
                    timeObstacleWasAvoided = millis();
                    turnRight();
                }
            } else if (checkLeft(safeDistance)) {
                logging("__LEFT%20IS%20SAFE__");
                if(!obstacleAvoidanceTurnedLeft){
                    obstacleAvoidanceTurnedLeft = true;
                    obstacleBeingAvoided = true;
                    timeObstacleWasAvoided = millis();
                    turnLeft();
                }            
            } else {
                logging("__No%20safe%20way%20to%20turn%20CollisionAvoidance");
                collisionAvoidance();
            }            
        } else if (obstacleAvoidanceTurnedRight) {
            //TODO: This should be temporary, and then set back to user direction...
            if (checkLeft(safeDistance)) {
                timereturnToRouteBegan = millis();
                turnLeft();
            }
            obstacleAvoidanceTurnedRight = false;
        } else if (obstacleAvoidanceTurnedLeft) {
            //TODO: This should be temporary, and then set back to user direction...
            if (checkRight(safeDistance)) {
                timereturnToRouteBegan = millis();
                turnRight();
            }
            obstacleAvoidanceTurnedLeft = false;
        }
    }
    else if (carSpeedActual < 0) {
        logging("Car%20is%20going%20backward");

        if (checkRear(safeDistance) == false) {
              logging("__REAR%20OBSTACLE__");
            collisionAvoidance();
        }
    }
    collisionAvoidance();
}

//returns false if the obstacle in front is closer than safeFrontDistance
boolean checkFront(int safeDistance)
{
    int distanceFromObstacle = getFrontDistance();
    if (distanceFromObstacle < safeDistance) {
        return false;
    }
    else
    {
        return true;
    }
}

//returns false if the obstacle on right is closer than safeFrontDistance
boolean checkRight(int safeDistance)
{
    int distanceFromObstacle = 0;
    distanceFromObstacle = getRightFrontDistance();
    if (distanceFromObstacle < safeDistance && distanceFromObstacle != 0) {
        return false;
    }
    else {
        return true;
    }
}

//returns false if the obstacle on left is closer than safeFrontDistance
boolean checkLeft(int safeDistance)
{
    int distanceFromObstacle = 0;
    distanceFromObstacle = getRightFrontDistance();
    if (distanceFromObstacle < safeDistance && distanceFromObstacle != 0)
    {
        return false;
    }
    else
    {
        return true;
    }
}

//returns false if the obstacle on rear is closer than safeFrontDistance
boolean checkRear(int safeDistance)
{
    int distanceFromObstacle = 0;
    distanceFromObstacle = getRearDistance();
    if (distanceFromObstacle < safeDistance && distanceFromObstacle != 0)
    {
        return false;
    }
    else
    {
        return true;
    }
}

void turnRight() {

    /*No, the user should still be able to stop the car, or reverse,
    also, this does not consider if an obstacle apears on the right
    at a later stage, or if the car does in fact NOT turn in time
    to avoid the obstacle.*/
    //Further, this stops the entire system from functioning, logging etc

    // while(checkFront(classSafeDistance)==false){
        logging(" Turning right "); 
        setAngle(45);
    // }
}

void turnLeft()
{
    /*No, the user should still be able to stop the car, or reverse,
    also, this does not consider if an obstacle apears on the left
    at a later stage, or if the car does in fact NOT turn in time
    to avoid the obstacle.*/
    //Further, this stops the entire system from functioning, logging etc

    //  while(checkFront(classSafeDistance)==false){
        logging(" Turning left "); 
        setAngle(-45);
    // }
}

void chooseNewDirection() {
}