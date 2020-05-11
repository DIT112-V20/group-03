#include "WifiManager.hpp" // ESP32 WiFi include
#include "util/WebSocketClient.h"

char* WiFiSSID = "TheMancave";
char* WiFiPassword = "tagedirtybumpaberra";

char path[] = "/carClient";
char host[] = "quar.online";
  
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
     // TODO: change this port to 80
  if (client.connect("quar.online", 8080)) {
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

void getInstructionsFromServer() {

    String data;

  if (client.connected()) {
    
    webSocketClient.getData(data);
    if (data.length() > 0) {
      Serial.print("Received data: ");
      Serial.println(data);
    }
    
    // capture the value of analog 1, send it along
    // pinMode(1, INPUT);
    // data = String(analogRead(1));

    data = "{\"carId\":1,\"carSetSpeed\":0,\"carSetAngle\":0,\"carActualSpeed\":10,\"carActualAngle\":70}";
    
    webSocketClient.sendData(data);
    
  } else {
    Serial.println("Client disconnected.");
    //TODO: Car should stop driving until re-connected to server
    connectToWiFi();
    // while (1) {
    //   // Hang on disconnect.
    // }
  }
  
  // wait to fully let the client disconnect
 // delay(3000);
}