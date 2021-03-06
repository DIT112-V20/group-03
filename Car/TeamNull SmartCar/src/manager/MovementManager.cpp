#include "MovementManager.hpp"
#include "WifiManager.hpp"

int carSpeedSet = 0;
int carSpeedActual = 0;
int turnAngleSet = 0;
int turnAngleActual = 0;
int classSafeDistance = 0;
int scaleAngle = 0;

boolean colisionBeingAvoided = false;
boolean obstacleBeingAvoided = false;
boolean obstacleAvoidanceTurnedRight = false;
boolean obstacleAvoidanceTurnedLeft = false;
long timeObstacleWasAvoided = millis();
long timereturnToRouteBegan = millis();

//This is a helper method for logging purposes only
String booleanToString(boolean b) {
    if (b)
    {
        return "true";
    }
    else
    {
        return "false";
    }
}

void setDesiredVehicleSpeed(int speed) {
    carSpeedSet = speed;
}

void setDesiredTurnAngle(int heading) {
    turnAngleSet = heading;
    if (!obstacleBeingAvoided) {
        setAngle(turnAngleSet);
        turnAngleActual = turnAngleSet;
    }
}

void collisionAvoidance() {
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
        logging("COLLISION%20AVOIDANCE%20distance%20from%20obstacle%20" + (String)distanceFromObstacle + "mm");
        colisionBeingAvoided = true;
    }
    else if (carSpeedActual != carSpeedSet) {
        setSpeed(carSpeedSet);
        carSpeedActual = carSpeedSet;
        colisionBeingAvoided = false;
    }
}

int getActualCarSpeed() {
    return getCarCurrentSpeed();
}

String getActualCarStatus() {
    String result = (String) "carId=" + (String) "1" + (String) "&carActualSpeed=" + (String)getActualCarSpeed() + (String) "&carActualAngle=" + (String)turnAngleActual + (String) "&carObstacleAvoidance=" + booleanToString(obstacleBeingAvoided) + (String) "&carCollisionAvoidance=" + booleanToString(colisionBeingAvoided) + (String) "&frontDistance=" + (String)getFrontDistance() + (String) "&leftFrontDistance=" + (String)getLeftFrontDistance() + (String) "&rightFrontDistance=" + (String)getRightFrontDistance() + (String) "&rearDistance=" + (String)getRearDistance() + (String) "&c=7" + getFrontDistance();

    return result;
}

void obstacleAvoidance(int safeDistance) {

    if (carSpeedActual > 0) {
        logging("Car%20is%20going%20forward");
        boolean frontIsSafe = checkFront(safeDistance);
        logging("FrontisSafe%20output_" + booleanToString(frontIsSafe));
        if (!frontIsSafe) {
            logging("__FRONT%20OBSTACLE__");
            if (checkRight(safeDistance)) {
                logging("__RIGHT%20IS%20SAFE__");
                if (!obstacleAvoidanceTurnedRight) {
                    logging("startToAvoid");
                    obstacleAvoidanceTurnedRight = true;
                    obstacleBeingAvoided = true;
                    // timeObstacleWasAvoided = millis();
                    setScaleAngle();
                    logging("turningRightAfterObstacle:" + (String)scaleAngle);
                    turn(45);
                }
                else {
                    logging("turningRightAfterObstacle:" + (String)scaleAngle);
                    turn(45);
                }
            }
            else if (checkLeft(safeDistance)) {
                logging("__LEFT%20IS%20SAFE__");
                if (!obstacleAvoidanceTurnedLeft) {
                    obstacleAvoidanceTurnedLeft = true;
                    obstacleBeingAvoided = true;
                    //    timeObstacleWasAvoided = millis();
                    setScaleAngle();
                    logging("turningLeftAfterObstacle:" + (String)scaleAngle);
                    turn(-45);
                }
                else {
                    logging("turningLeftAfterObstacle:" + (String)scaleAngle);
                    turn(-45);
                }
            }
            else {
                logging("__No%20safe%20way%20to%20turn%20CollisionAvoidance");
                obstacleBeingAvoided = false;
                obstacleAvoidanceTurnedRight = false;
                obstacleAvoidanceTurnedLeft = false;
                collisionAvoidance();
            }
        }
        else if (obstacleAvoidanceTurnedRight) {
            logging("obstacleAvoidanceTurnedRight");

            if (checkLeft(safeDistance)) {
                setScaleAngle();
                logging("turninnBackLeftAfterAvoidance");
                turn(-90);
                obstacleAvoidanceTurnedRight = false;

            }

            obstacleBeingAvoided = false;
        }
        else if (obstacleAvoidanceTurnedLeft) {
            logging("obstacleAvoidanceTurnedLeft");

            if (checkRight(safeDistance)) {
                setScaleAngle();
                logging("turninnBackRightAfterAvoidance");
                turn(90);
                obstacleAvoidanceTurnedLeft = false;
            }
            obstacleBeingAvoided = false;
        }
    }

    else if (carSpeedActual < 0) {
        logging("Car%20is%20going%20backward");

        if (checkRear(safeDistance) == false) {
            logging("__REAR%20OBSTACLE__");
            obstacleBeingAvoided = true;
            collisionAvoidance();
        }
        else {
            obstacleBeingAvoided = false;
        }

        if (obstacleAvoidanceTurnedRight) {
            obstacleAvoidanceTurnedRight = false;
        }
        else if (obstacleAvoidanceTurnedLeft) {
            obstacleAvoidanceTurnedLeft = false;
        }

        if (turnAngleActual != turnAngleSet) {
            setAngle(turnAngleActual);
            turnAngleSet = turnAngleActual;
        }
    } else {
        obstacleBeingAvoided = false;
    }
    
    collisionAvoidance();
}

//returns false if the obstacle in front is closer than safeFrontDistance
boolean checkFront(int safeDistance) {
    int distanceFromObstacle = getFrontDistance();
    if (distanceFromObstacle < safeDistance) {
        return false;
    }
    else {
        return true;
    }
}

//returns false if the obstacle on right is closer than safeFrontDistance
boolean checkRight(int safeDistance) {
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
boolean checkLeft(int safeDistance) {
    int distanceFromObstacle = 0;
    distanceFromObstacle = getLeftFrontDistance();
    if (distanceFromObstacle < safeDistance && distanceFromObstacle != 0) {
        return false;
    }
    else {
        return true;
    }
}

//returns false if the obstacle on rear is closer than safeFrontDistance
boolean checkRear(int safeDistance) {
    int distanceFromObstacle = 0;
    distanceFromObstacle = getRearDistance();
    if (distanceFromObstacle < safeDistance && distanceFromObstacle != 0) {
        return false;
    }
    else {
        return true;
    }
}

void setScaleAngle() {
    scaleAngle = getHeading();
}

void turn(int endAngle) {
    logging("insideTurnFunction%20endAngle:" + (String)endAngle + "%20-currentAngle:" + (String)(getHeading() - scaleAngle));

    if (endAngle < 0 && endAngle < getHeading() - scaleAngle - 2) {
        setAngle(-90);
        logging("turning_left-endAngle:" + (String)endAngle + "%20-currentAngle:" + (String)(getHeading() - scaleAngle));
    } else if (endAngle > 0 && endAngle > getHeading() - scaleAngle + 2) {
        setAngle(90);
        logging("turning_right-endAngle:" + (String)endAngle + "%20-currentAngle:" + (String)(getHeading() - scaleAngle));
    } else {
        logging("the_angle_is_settled_scaleAngle_is:" + (String)scaleAngle);
        scaleAngle = getHeading();
        obstacleBeingAvoided = false;
    }
}
