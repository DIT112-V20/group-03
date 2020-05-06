#include "WifiManager.hpp" // ESP32 WiFi include

char* WiFiSSID = "";
char* WiFiPassword = "";

void connectToWiFi() {

    //WiFi.mode(WIFI_STA);
    WiFi.begin(WiFiSSID, WiFiPassword);
    Serial.print("Connecting to WiFi \"");
    Serial.print(WiFiSSID);
    Serial.println("\"");

    uint8_t i = 0;
    while (WiFi.status() != WL_CONNECTED) {
        Serial.print('.');
        delay(500);

        if ((++i % 16) == 0) {
            Serial.println(F(" Connecting to WiFi.."));
        }
    }

    Serial.print(F("Connected to the WiFi network. My IP address is: "));
    Serial.println(WiFi.localIP());
}

void connectToWiFi(char* SSID, char* password) {

    WiFiSSID = SSID;
    WiFiPassword = password;
    connectToWiFi();
}