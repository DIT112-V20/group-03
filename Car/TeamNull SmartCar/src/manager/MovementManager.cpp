#include "MovementManager.hpp"
#include "WifiManager.hpp"

int carSpeedSet = 0;
int carSpeedActual = 0;
int turnAngleSet = 0;
int turnAngleActual = 0;
int classSafeDistance = 0 ;

//TODO: Calculate gyroscope offset
int gyroscopeOffset = 0;

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
      logging("*-- CollisionAvoidance ACTIVATED--*");
    int distanceFromObstacle = 0;
    if (carSpeedSet >= 0)
    {
        distanceFromObstacle = getFrontDistance();
    }
    else
    {
        distanceFromObstacle = getRearDistance();
    }
    if (distanceFromObstacle < 300 && distanceFromObstacle != 0)
    {
        setSpeed(0);
        carSpeedActual = 0;
        Serial.print("COLLISION AVOIDANCE: distance from obstacle: ");
        Serial.print(distanceFromObstacle);
        Serial.println("mm");
    }
    else if (carSpeedActual != carSpeedSet)
    {
        setSpeed(carSpeedSet);
        carSpeedActual = carSpeedSet;
    }
    logging("*-- CollisionAvoidance DECTIVATED--*");
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

void obstacleAvoidance(int safeDistance)
{
classSafeDistance= safeDistance;
                        logging("!**obstacleAvoidance Activated **!");

    if (carSpeedActual > 0)
    {
                    logging("Car is going forward");
        logging("checkFront output =" + booleanToString(checkFront(safeDistance)));
        if (checkFront(safeDistance) == false)
        {
            logging("* FRONT OBSTACLE  *");
            if (checkRight(safeDistance))
            {
                logging("* RIGHT IS SAFE *");
              
                turnRight();
            
            }
            else if (checkLeft(safeDistance))
            {
                logging("* LEFT IS SAFE *");

                turnLeft();
            }
            else
                logging("* No safe way to turn! CollisionAvoidance");
            collisionAvoidance();
        }
    }
    else
    {                    logging("Car is going backward");

        if (checkRear(safeDistance) == false)
        {
              logging("* REAR OBSTACLE *");
            collisionAvoidance();
        }
    }
    collisionAvoidance();
}

//returns false if the obstacle in front is closer than safeFrontDistance
boolean checkFront(int safeDistance)
{
    int distanceFromObstacle = getFrontDistance();
    if (distanceFromObstacle < safeDistance)
    {
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
    if (distanceFromObstacle < safeDistance && distanceFromObstacle != 0)
    {
        return false;
    }
    else
    {
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
    if (distanceFromObstacle < safeDistance < safeDistance && distanceFromObstacle != 0)
    {
        return false;
    }
    else
    {
        return true;
    }
}

void turnRight()
{
  
    while(checkFront(classSafeDistance)==false){
  logging(" Turning right "); 
    setAngle(45);}
}

void turnLeft()
{
     while(checkFront(classSafeDistance)==false){
  logging(" Turning left "); 
    setAngle(-45);}
}

void chooseNewDirection()
{
}