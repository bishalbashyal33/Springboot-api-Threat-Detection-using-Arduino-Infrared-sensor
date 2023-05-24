# Springboot-api-Threat-Detection-using-Arduino-Infrared-sensor
Uses esp8266 wifi module, arduinohttpclient and springboot to implement a trivial threat detection system for posting on /obstacle api endpoint , as a possible detection 
of threat signal.
![image](https://github.com/bishalbashyal33/Springboot-api-Threat-Detection-using-Arduino-Infrared-sensor/assets/63231700/e28092f5-8b85-4ee8-a629-eabc30538c50)




Arduino Code:
--------------------------------------------------------------------------------------------------------------------------------------
#include <ESP8266WiFi.h>
#include <SoftwareSerial.h>
#include <ArduinoHttpClient.h>

const char* ssid = "your ssid";
const char* password = "your wifi password";
const char* serverAddress = "127.0.0.1";
const int serverPort = 8080;

const char* obstacleEndpoint = "/api/obstacle";
const char* contentTypeHeader = "Content-Type";
const char* contentTypeValue = "application/json";

const int obstaclePin = 2;
const int ledPin = 13;

SoftwareSerial espSerial(2, 3); // RX, TX for ESP8266
WiFiClient wifiClient;
HttpClient http(wifiClient, serverAddress, serverPort);

void setup() {
  pinMode(obstaclePin, INPUT);
  pinMode(ledPin, OUTPUT);

  Serial.begin(9600);
  espSerial.begin(115200);
  connectToWiFi();
}

void loop() {
  int obstacleState = digitalRead(obstaclePin);

  if (obstacleState == HIGH) {
    digitalWrite(ledPin, HIGH);  // Turn on the LED
    sendObstacleData();
  } else {
    digitalWrite(ledPin, LOW);   // Turn off the LED
  }

  delay(1000);
}

void connectToWiFi() {
  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.print("Connecting to WiFi...");
  }

  Serial.println("Connected to WiFi");
  Serial.print("IP address: ");
  Serial.println(WiFi.localIP());
}

void sendObstacleData() {
  String requestBody = "{\"status\": \"obstacle_detected\"}";

  if (http.connect(serverAddress, serverPort)) {
    http.beginRequest();
    http.post(obstacleEndpoint);
    http.sendHeader(contentTypeHeader, contentTypeValue);
    http.sendHeader("Content-Length", String(requestBody.length()));
    http.beginBody();
    http.print(requestBody);
    http.endRequest();

    int statusCode = http.responseStatusCode();
    String response = http.responseBody();

    Serial.print("Status code: ");
    Serial.println(statusCode);
    Serial.print("Response: ");
    Serial.println(response);
  } else {
    Serial.println("Failed to connect to server");
  }
}
