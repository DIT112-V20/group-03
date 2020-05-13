#ifndef movementManager_H_   /* Include guard */
#define movementManager_H_

#include "util/HardwareUtil.hpp"

void setDesiredVehicleSpeed (int speed);
void setDesireTurnAngle (int heading);
void collisionAvoidance();
int getActualCarSpeed();
String getActualCarStatus();


#endif // movementManager_H_