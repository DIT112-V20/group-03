#include "HardwareUtil.hpp"
#include <Smartcar.h>

unsigned long BLINK_INTERVAL = 200;

VL53L0X frontSensor;
VL53L0X rearSensor;

const int frontSensorPin = 33;
const int rearSensorPin = 32;

const int FR_TRIGGER_PIN = 19;
const int FR_ECHO_PIN = 23;
const int FL_TRIGGER_PIN = 5;
const int FL_ECHO_PIN = 18;
const unsigned int MAX_DISTANCE = 300;

BrushedMotor leftMotor(smartcarlib::pins::v2::leftMotorPins);
BrushedMotor rightMotor(smartcarlib::pins::v2::rightMotorPins);

DifferentialControl control(leftMotor, rightMotor);

SR04 frontRight(FR_TRIGGER_PIN, FR_ECHO_PIN, MAX_DISTANCE);
SR04 frontLeft(FL_TRIGGER_PIN, FL_ECHO_PIN, MAX_DISTANCE);

SimpleCar car(control);
GY50 gyro(12);


const unsigned long LEFT_PULSES_PER_METER  = 943;
const unsigned long RIGHT_PULSES_PER_METER = 972;
DirectionalOdometer leftOdometer(smartcarlib::pins::v2::leftOdometerPins, []() {
  leftOdometer.update(); }, LEFT_PULSES_PER_METER);
DirectionalOdometer rightOdometer(smartcarlib::pins::v2::rightOdometerPins, []() {
  rightOdometer.update(); }, RIGHT_PULSES_PER_METER);

void setSpeed(int speed) {
  car.setSpeed(speed);
}

void setAngle(int angle) {
  car.setAngle(angle);
}

int getHeading() {
  gyro.update();
  return gyro.getHeading();
}

int getCarCurrentSpeed() {
  auto left = leftOdometer.getSpeed();
  auto right = rightOdometer.getSpeed();

  // Serial.println("Speed:");
  // Serial.print("Left: ");
  // Serial.println(left);
  // Serial.print("Right: ");
  // Serial.println(right);
  // Serial.print("Combined: ");
  // Serial.println((left+right)/2);
  

  return (((left+right)/2)*100);
}

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

  setSpeed(0);
  setAngle(0);

  pinMode(rearSensorPin, OUTPUT);
  pinMode(frontSensorPin, OUTPUT);
  digitalWrite(rearSensorPin, LOW);
  digitalWrite(frontSensorPin, LOW);

  delay(1000);

  //Set front sensor address
  // digitalWrite(frontSensorPin, HIGH);
  pinMode(frontSensorPin, INPUT);
  delay(150);
  // Serial.println("00");
  frontSensor.setTimeout(500);
  while (!frontSensor.init()) {
    Serial.println("Failed to initialise FRONT sensor");
    blink(2);

    pinMode(frontSensorPin, OUTPUT);
    digitalWrite(frontSensorPin, LOW);

    delay(1500);
    pinMode(frontSensorPin, INPUT);
  }

  delay(100);
  frontSensor.setAddress((uint8_t)01);
  
  // Serial.println("01");
  //Set rear sensor address
  // digitalWrite(rearSensorPin, HIGH);
  pinMode(rearSensorPin, INPUT);
  delay(150);
  // rearSensor.setTimeout(500);
  // // rearSensor.setAddress((uint8_t)01);
  while (!rearSensor.init()) {
    Serial.println("Failed to initialise REAR sensor");
    blink(2);
    pinMode(rearSensorPin, OUTPUT);
    digitalWrite(rearSensorPin, LOW);

    delay(1500);
    pinMode(rearSensorPin, INPUT);
  }
  // rearSensor.setAddress((uint8_t)02);

  SR04 frontRight(FR_TRIGGER_PIN, FR_ECHO_PIN, MAX_DISTANCE);
  SR04 frontLeft(FL_TRIGGER_PIN, FL_ECHO_PIN, MAX_DISTANCE);


  frontSensor.startContinuous();
  Serial.print("Front distance: ");
  Serial.println(frontSensor.readRangeContinuousMillimeters());

  rearSensor.startContinuous();
  Serial.print("Rear distance: ");
  Serial.println(rearSensor.readRangeContinuousMillimeters());
}

int getFrontDistance() {
  return frontSensor.readRangeContinuousMillimeters();
  if(frontSensor.timeoutOccurred()) {
    Serial.println("FrontSensor TIMEOUT");
  }
  // return 10;
}

int getLeftFrontDistance() {
  return frontLeft.getMedianDistance(3)*10;
}

int getRightFrontDistance() {
  return frontRight.getMedianDistance(3)*10;
}

int getRearDistance() {
  return rearSensor.readRangeContinuousMillimeters();
  if(rearSensor.timeoutOccurred()) {
    Serial.println("RearSensor TIMEOUT");
  }
}