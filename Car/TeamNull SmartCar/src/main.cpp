#include "main.hpp"

unsigned long loadTime = 0;
int safeDistance = 1000;
void setup() {

    // Open Serial Connection
    Serial.begin(115200);
    Wire.begin();
    pinMode(LED_BUILTIN, OUTPUT);

    blink(1);

    initialiseSensors();

    // Connect to WiFi
    // - Yes, I am aware that I am giving out my snakeoil Wifi details
    char* WiFiSSID = "TheMancave";
    char* WiFiPassword = "tagedirtybumpaberra";
    connectToWiFi(WiFiSSID, WiFiPassword);
    blink(1);
    
    // Configure if logging is enabled or disabled
    enableLogging(false);

    loadTime = millis();

}

void loop() {
    //collisionAvoidance();
    obstacleAvoidance(safeDistance);

    syncWithServer();
}