#include <Wire.h>
#include <VL53L0X.h>
#include <Arduino.h>
#include <Smartcar.h>

// Declare the distance sensor
VL53L0X sensor;

// Create the 'car'
BrushedMotor leftMotor(smartcarlib::pins::v2::leftMotorPins);
BrushedMotor rightMotor(smartcarlib::pins::v2::rightMotorPins);
DifferentialControl control(leftMotor, rightMotor);
SimpleCar car(control);

void setup() {

    // Open Serial Connection
    sleep(1);
    Serial.begin(9600);
    sleep(1);
    Serial.println("Serial port working");

    Wire.begin();
    Serial.println("Wire begun");

    sensor.setTimeout(500);
    if(!sensor.init()) {
        Serial.println("Failed to initialise sensor");
        while (1) {}
    }

     // Set sensor to continously read the distance
    sensor.startContinuous();

     // Make car start moving forward at 40% speed
    car.setSpeed(40);
}

//This method is called continuosly once the setup method completes
void loop() {
    //get the inital distance reading
    int distance = sensor.readRangeContinuousMillimeters();
    Serial.println(distance);
    if(sensor.timeoutOccurred()) {
        Serial.println("Timeout");
    }
     //if car is too close to an object, stop it, else drive
    if(distance < 275) {
        car.setSpeed(0);
    } else {
        car.setSpeed(40);
    }
}