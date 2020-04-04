#ifndef movementManager_H_   /* Include guard */
#define movementManager_H_

#include "util/HardwareUtil.hpp"

void setDesiredVehicleSpeed (int speed);
void setDesireTurnAngle (int heading);
void collisionAvoidance();

#endif // movementManager_H_