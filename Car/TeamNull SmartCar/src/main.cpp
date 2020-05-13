#include "main.hpp"

unsigned long loadTime = 0;

void setup() {

    // Open Serial Connection
    Serial.begin(115200);
    Wire.begin();
    pinMode(LED_BUILTIN, OUTPUT);

    blink(1);

    initialiseSensors();

    // Connect to WiFi - commented out do to location specific WiFi credentials
    // - Yes, I am aware that I am giving out my Wifi details :p
    char* WiFiSSID = "TheMancave";
    char* WiFiPassword = "tagedirtybumpaberra";
    connectToWiFi(WiFiSSID, WiFiPassword);
    blink(1);

    Serial.println("Returned from connectToWiFi");

    //Test collision avoidance
    // setDesiredVehicleSpeed(30);
    loadTime = millis();
}

void loop() {
    collisionAvoidance();
    
    // Sensor debugging
    // Serial.print("Front: ");
    // Serial.print(getFrontDistance());
    // Serial.print(" FL: ");
    // Serial.print(getLeftFrontDistance());
    // Serial.print(" FR: ");
    // Serial.print(getRightFrontDistance());
    // Serial.print(" Rear: ");
    // Serial.println(getRearDistance());
    // Serial.println("-----------------------");
    // delay(1000);

    // Serial.println("loop");

    syncWithServer();
}