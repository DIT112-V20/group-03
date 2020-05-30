#ifndef WiFiManager_H_   /* Include guard */
#define WiFiManager_H_

#include "WiFi.h" // ESP32 WiFi include

void connectToWiFi(char* SSID, char* password);
void syncWithServer();
void logging (String message);
void enableLogging(boolean enabled);

#endif // WiFiManager_H_