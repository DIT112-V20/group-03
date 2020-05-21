#ifndef hardwareUtil_H_   /* Include guard */
#define hardwareUtil_H_

#include <Arduino.h>
// #include <Wire.h>
#include <VL53L0X.h>

void setSpeed(int speed);
void setAngle(int angle);
int getCarCurrentSpeed();
int getCarCurrentAngle();
void blink(int blinkCount);
void blink(int blinkCount, unsigned long blinkDuration);
void initialiseSensors();
int getFrontDistance();
int getLeftFrontDistance();
int getRightFrontDistance();
int getRearDistance();

#endif // hardwareUtil_H_