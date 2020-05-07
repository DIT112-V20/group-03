#include "WifiManager.hpp" // ESP32 WiFi include
#include "util/WebSocketClient.h"

char* WiFiSSID = "TheMancave";
char* WiFiPassword = "tagedirtybumpaberra";

char path[] = "/";
char host[] = "echo.websocket.org";
  
WebSocketClient webSocketClient;

// Use WiFiClient class to create TCP connections
WiFiClient client;

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

     // Connect to the websocket server
  if (client.connect("echo.websocket.org", 80)) {
    Serial.println("Connected");
  } else {
    Serial.println("Connection failed.");
    
    // while(1) {
    //   // Hang on failure
    // }
  }

  // Handshake with the server
  webSocketClient.path = path;
  webSocketClient.host = host;
  if (webSocketClient.handshake(client)) {
    Serial.println("Handshake successful");
  } else {
    Serial.println("Handshake failed.");
   // while(1) {
      // Hang on failure
   // }  
  }
}

void connectToWiFi(char* SSID, char* password) {

    WiFiSSID = SSID;
    WiFiPassword = password;
    connectToWiFi();
}