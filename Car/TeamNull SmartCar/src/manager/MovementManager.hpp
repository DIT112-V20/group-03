#ifndef movementManager_H_   /* Include guard */
#define movementManager_H_

#include "util/HardwareUtil.hpp"

void setDesiredVehicleSpeed (int speed);
void setDesireTurnAngle (int heading);
void collisionAvoidance();
int getActualCarSpeed();
String getActualCarStatus();
boolean checkFront(int safeDistance);
boolean checkRight(int safeDistance);
boolean checkLeft(int safeDistance);
boolean checkRear(int safeDistance);
void turnRight();
void turnLeft();
void chooseNewDirection();
void obstacleAvoidance(int safeDistance);
#endif // movementManager_H_