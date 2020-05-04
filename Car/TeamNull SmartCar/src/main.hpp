#ifndef main_H_   /* Include guard */
#define main_H_

// #include <Smartcar.h>

#include "manager/WifiManager.hpp" // My WiFi configuration.
#include "util/HardwareUtil.hpp"
#include <Wire.h>
#include "manager/MovementManager.hpp"


void driveUntilObstacle();

#endif //main_H_