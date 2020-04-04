#ifndef hardwareUtil_H_   /* Include guard */
#define hardwareUtil_H_

#include <Arduino.h>
// #include <Wire.h>
#include <VL53L0X.h>

void setSpeed(int speed);
void setAngle(int angle);
void blink(int blinkCount);
void blink(int blinkCount, unsigned long blinkDuration);
void initialiseSensors();
int getFrontDistance();
int getRearDistance();

#endif // hardwareUtil_H_