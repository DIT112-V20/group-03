#ifndef hardwareUtil_H_   /* Include guard */
#define hardwareUtil_H_

#include <Arduino.h>
// #include <Wire.h>
#include <VL53L0X.h>

void blink(int blinkCount);
void blink(int blinkCount, unsigned long blinkDuration);
void initialiseSensors();
int getFrontDistance();

#endif // hardwareUtil_H_