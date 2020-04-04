#include "HardwareUtil.hpp"

unsigned long BLINK_INTERVAL = 200;

VL53L0X frontSensor;

void blink(int toBlink){
  boolean builtinLEDState = false;
  unsigned long previousToggle = 0;
  int blinkCount = 0;

  while((blinkCount/2) < toBlink) {
    unsigned long currentTime = millis();
    if(currentTime > previousToggle + BLINK_INTERVAL) {
      builtinLEDState = !builtinLEDState;
      digitalWrite(LED_BUILTIN, builtinLEDState);
      previousToggle = currentTime;
      ++blinkCount;
    }
  }
  digitalWrite(LED_BUILTIN, false);
  builtinLEDState = false;
}

void blink(int toBlink, unsigned long blinkDuration) {
    BLINK_INTERVAL = blinkDuration;
    blink(toBlink);
}

void initialiseSensors() {
  frontSensor.setTimeout(500);
  if (!frontSensor.init()) {
    Serial.println("Failed to initialise sensor");
    while (1) {
    }
  }
  frontSensor.startContinuous();
  Serial.println(frontSensor.readRangeContinuousMillimeters());
}

int getFrontDistance() {
  return frontSensor.readRangeContinuousMillimeters();
  if(frontSensor.timeoutOccurred()) {
    Serial.println("FrontSensor TIMEOUT");
  }
  // return 10;
}