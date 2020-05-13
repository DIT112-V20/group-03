#ifndef WiFiManager_H_   /* Include guard */
#define WiFiManager_H_

#include "WiFi.h" // ESP32 WiFi include

void connectToWiFi(char* SSID, char* password);
void syncWithServer();
#endif // WiFiManager_H_