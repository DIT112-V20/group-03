#include "WifiManager.hpp" // ESP32 WiFi include
#include <HTTPClient.h>
#include "manager/MovementManager.hpp"

char* WiFiSSID = "TheMancave";
char* WiFiPassword = "tagedirtybumpaberra";

HTTPClient http;

// // char path[] = "/carClient";
// // char host[] = "leith.quar.online";

// char path[] = "/";
// char host[] = "echo.websocket.org";

// /**
// * Stomp server settings
// **/
// bool useWSS                       = true;
// const char* ws_host               = "quar.online";
// const int ws_port                 = 8080;

// // URL for STOMP endpoint.
// // For the default config of Spring's STOMP support, the default URL is "/socketentry/websocket".
// const char* stompUrl            = "/carClient"; // don't forget the leading "/" !!!

// const char* ws_baseurl            = "/carClient/"; // don't forget leading and trailing "/" !!!

// WebSocketsClient webSocket;

const char* root_ca= \
"-----BEGIN CERTIFICATE-----\n" \
"MIIFTTCCBDWgAwIBAgISA2LZSMgJdAwQWIG+qPf9jpdnMA0GCSqGSIb3DQEBCwUA\n" \
"MEoxCzAJBgNVBAYTAlVTMRYwFAYDVQQKEw1MZXQncyBFbmNyeXB0MSMwIQYDVQQD\n" \
"ExpMZXQncyBFbmNyeXB0IEF1dGhvcml0eSBYMzAeFw0yMDA0MDgxNDE1NDVaFw0y\n" \
"MDA3MDcxNDE1NDVaMBYxFDASBgNVBAMTC3F1YXIub25saW5lMIIBIjANBgkqhkiG\n" \
"9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwcsPQmR4SMrjTvExcUwi3+hmwf1xo0UOFJcj\n" \
"ELX7H5IMPZoRg/2s3wU41TH/16KibwBaBmXcwWdxHZP2+Zj5PsAUePUAPuPrbH7y\n" \
"mw01ZcaMhqZ2VExNkzj4qJbIz+Mv99tDR+2y8iKIE7E5M+/JPD2FZMPE5meY3K3l\n" \
"57hGtRSMOgl1tb0AlOuNtJKc6pkpD07mL1CsbWW5wOi9L48dttnqhGrULwfquB7u\n" \
"is8/xMI2/5ZgKJHpYlF2EsUsNHv9gD3BGy8wtINJGOGTGj0YvlbMx/syZ2fLRP29\n" \
"Q1/MvUgtFmwYfcg/kPZwC/DB2vFoKzMTJQR2uKdQ33slkFpaLwIDAQABo4ICXzCC\n" \
"AlswDgYDVR0PAQH/BAQDAgWgMB0GA1UdJQQWMBQGCCsGAQUFBwMBBggrBgEFBQcD\n" \
"AjAMBgNVHRMBAf8EAjAAMB0GA1UdDgQWBBS6VaGsIE234+0TqNFt9AIk3Ka+6jAf\n" \
"BgNVHSMEGDAWgBSoSmpjBH3duubRObemRWXv86jsoTBvBggrBgEFBQcBAQRjMGEw\n" \
"LgYIKwYBBQUHMAGGImh0dHA6Ly9vY3NwLmludC14My5sZXRzZW5jcnlwdC5vcmcw\n" \
"LwYIKwYBBQUHMAKGI2h0dHA6Ly9jZXJ0LmludC14My5sZXRzZW5jcnlwdC5vcmcv\n" \
"MBYGA1UdEQQPMA2CC3F1YXIub25saW5lMEwGA1UdIARFMEMwCAYGZ4EMAQIBMDcG\n" \
"CysGAQQBgt8TAQEBMCgwJgYIKwYBBQUHAgEWGmh0dHA6Ly9jcHMubGV0c2VuY3J5\n" \
"cHQub3JnMIIBAwYKKwYBBAHWeQIEAgSB9ASB8QDvAHUAB7dcG+V9aP/xsMYdIxXH\n" \
"uuZXfFeUt2ruvGE6GmnTohwAAAFxWlyI0gAABAMARjBEAiA3fC0WEZrwPfwivIpa\n" \
"zsAvp6JX9VT7z9K9WRjH5Ru29gIgLFb6Yd3hZuxdfHIEDBu1pujO5yjKEYt0KEa5\n" \
"zsOSLmkAdgDwlaRZ8gDRgkAQLS+TiI6tS/4dR+OZ4dA0prCoqo6ycwAAAXFaXIp4\n" \
"AAAEAwBHMEUCIH6IujATFucIdvEpoy/TIHEyJuQJhmNmSlNJ+u+C5wNaAiEAvB50\n" \
"el2fDgubc+yqpHAcXwAXXzSRn5ewaOW02dGayGgwDQYJKoZIhvcNAQELBQADggEB\n" \
"AG5ZxinY8Z/5E2EhFkr7XIIn6EACUJo6/eEtcHL9v0Q/E7iViNwEeUt4EDUyRYYs\n" \
"5drqAqb5Id63tu3dvlw92pqNkgdb7r1BxCtZNK67ul0ambIvFZuAaLr+Lgg9SHMj\n" \
"kDNqGBRIUi/4GHt/8ubRVoCyXn17a+cKwP4AhF+heo95H6dG5Wfp6g9Y5GGXKnlV\n" \
"lWEqZmS+Gk5By+XvljtC25PKgFREu4aZF2+yGuMuWg5SHBaf6+Q646Q+PS29GdqR\n" \
"xzzHOPlZ2j0/MjDZjp7baD7d1VwAX0bRnjyVT6QnxhJroqv4n3w6NSllv3rZp6Gj\n" \
"myJmL68nr8mw4u12qFp/AyA=\n" \
"-----END CERTIFICATE-----\n";

// "-----BEGIN CERTIFICATE-----\n" \
// "MIICiTCCAg+gAwIBAgIQH0evqmIAcFBUTAGem2OZKjAKBggqhkjOPQQDAzCBhTEL\n" \
// "MAkGA1UEBhMCR0IxGzAZBgNVBAgTEkdyZWF0ZXIgTWFuY2hlc3RlcjEQMA4GA1UE\n" \
// "BxMHU2FsZm9yZDEaMBgGA1UEChMRQ09NT0RPIENBIExpbWl0ZWQxKzApBgNVBAMT\n" \
// "IkNPTU9ETyBFQ0MgQ2VydGlmaWNhdGlvbiBBdXRob3JpdHkwHhcNMDgwMzA2MDAw\n" \
// "MDAwWhcNMzgwMTE4MjM1OTU5WjCBhTELMAkGA1UEBhMCR0IxGzAZBgNVBAgTEkdy\n" \
// "ZWF0ZXIgTWFuY2hlc3RlcjEQMA4GA1UEBxMHU2FsZm9yZDEaMBgGA1UEChMRQ09N\n" \
// "T0RPIENBIExpbWl0ZWQxKzApBgNVBAMTIkNPTU9ETyBFQ0MgQ2VydGlmaWNhdGlv\n" \
// "biBBdXRob3JpdHkwdjAQBgcqhkjOPQIBBgUrgQQAIgNiAAQDR3svdcmCFYX7deSR\n" \
// "FtSrYpn1PlILBs5BAH+X4QokPB0BBO490o0JlwzgdeT6+3eKKvUDYEs2ixYjFq0J\n" \
// "cfRK9ChQtP6IHG4/bC8vCVlbpVsLM5niwz2J+Wos77LTBumjQjBAMB0GA1UdDgQW\n" \
// "BBR1cacZSBm8nZ3qQUfflMRId5nTeTAOBgNVHQ8BAf8EBAMCAQYwDwYDVR0TAQH/\n" \
// "BAUwAwEB/zAKBggqhkjOPQQDAwNoADBlAjEA7wNbeqy3eApyt4jf/7VGFAkK+qDm\n" \
// "fQjGGoe9GKhzvSbKYAydzpmfz1wPMOG+FDHqAjAU9JM8SaczepBGR7NjfRObTrdv\n" \
// "GDeAU/7dIOA1mjbRxwG55tzd8/8dLDoWV9mSOdY=\n" \
// "-----END CERTIFICATE-----\n";

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

    // http.begin("https://quar.online:8443/carClient?json=", root_ca); //Specify the URL and certificate

}

void connectToWiFi(char* SSID, char* password) {

    WiFiSSID = SSID;
    WiFiPassword = password;
    connectToWiFi();
}

void getInstructionsFromServer() {

    String data;
    data = "{\"carId\":1,\"carSetSpeed\":0,\"carSetAngle\":0,\"carActualSpeed\":45,\"carActualAngle\":168}";
    


  if ((WiFi.status() == WL_CONNECTED)) { //Check the current connection status
    // String url = "https://quar.online:8443/carClient?json=" + data;
    // String url = "https://quar.online:8443/carClient?json={\\\"carId\\\":1,\\\"carSetSpeed\\\":0,\\\"carSetAngle\\\":0,\\\"carActualSpeed\\\":45,\\\"carActualAngle\\\":168}";
    String url = "https://quar.online:8443/carClient?" + getActualCarStatus();//carId=1&carActualSpeed=20&carActualAngle=10";
    http.begin(url, root_ca);
    int httpCode = http.GET();  //Make the request
    // int httpCode = http.POST(data);  //Make the request
 
    if (httpCode > 0) { //Check for the returning code
 
        String payload = http.getString();
        Serial.println(httpCode);
        Serial.println(payload);
      }
 
    else {
      Serial.println("Error on HTTP request");
    }
 
    http.end(); //Free the resources
  }
 
  delay(300);
}

