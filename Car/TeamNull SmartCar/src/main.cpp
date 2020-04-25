#include "main.hpp"

unsigned long loadTime = 0;
bool a = false;
bool b = false;
bool c = false;
bool d = false;
bool e = false;
bool f = false;

void setup() {

    // Open Serial Connection
    Serial.begin(9600);
    Wire.begin();
    pinMode(LED_BUILTIN, OUTPUT);

    blink(2);

    initialiseSensors();

    // Connect to WiFi - commented out do to location specific WiFi credentials
    // - Yes, I am aware that I am giving out my Wifi details :p
    char* WiFiSSID = "TheMancave";
    char* WiFiPassword = "tagedirtybumpaberra";
    connectToWiFi(WiFiSSID, WiFiPassword);
    blink(3);

    Serial.println("Returned from connectToWiFi");

    //Test collision avoidance
    // setDesiredVehicleSpeed(30);
    loadTime = millis();
}

void movementDemo() {
  unsigned long previousToggle = 0;
  int blinkCount = 0;

//   while((blinkCount/2) < toBlink) {
    unsigned long currentTime = millis();

    //DEMO Code
    if(currentTime > loadTime + 4000 && !a) {
        a = true;
        setDesiredVehicleSpeed(35);
        setDesireTurnAngle(0);
    } else if(currentTime > loadTime + 9000 && !b) {
        b = true;
        setDesiredVehicleSpeed(33);
        setDesireTurnAngle(90);
    } else if(currentTime > loadTime + 13000 && !c) {
        c = true;
        setDesiredVehicleSpeed(0);
        delay(40);
        setDesiredVehicleSpeed(-35);
        setDesireTurnAngle(0);
    } else if(currentTime > loadTime + 15000 && !d) {
        d = true;
        setDesiredVehicleSpeed(0);
        delay(35);
        setDesiredVehicleSpeed(30);
        delay(40);
        setDesiredVehicleSpeed(50);
        setDesireTurnAngle(-45);
    } else if(currentTime > loadTime + 16500 && !e) {
        e = true;
        setDesiredVehicleSpeed(0);
        delay(35);
        setDesiredVehicleSpeed(-30);
        setDesireTurnAngle(180);
    } else if(currentTime > loadTime + 18500 && !f) {
        f = true;
        setDesiredVehicleSpeed(0);
    }
//   }
//   digitalWrite(LED_BUILTIN, false);
//   builtinLEDState = false;
}   

void loop() {
    movementDemo();
    collisionAvoidance();
    // Serial.print("Front Distance: ");
    // Serial.println(getFrontDistance());
    // Serial.print("Rear Distance: ");
    // Serial.println(getRearDistance());
    // sleep(1);
}