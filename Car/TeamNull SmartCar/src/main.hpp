#ifndef main_H_   /* Include guard */
#define main_H_

// #include <Smartcar.h>

#include "manager/wifiManager.hpp" // My WiFi configuration.
#include "util/HardwareUtil.hpp"
#include <Wire.h>
#include "manager/movementManager.hpp"


void driveUntilObstacle();

#endif //main_H_