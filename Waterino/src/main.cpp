#include <Arduino.h>
#include <SPI.h>
#include <Wire.h>
#include <Adafruit_Sensor.h>
#include <DHT.h>
#include <RBD_LightSensor.h>
#include <ArduinoJson.h>
#include <ESP8266HTTPClient.h>
#include <ESP8266WiFi.h>

/************************************DHT stuff*****************************/
#define DHTPIN 4
#define DHTTYPE DHT11
DHT dht(DHTPIN, DHTTYPE);
/**************************************************************************/

/*****************************Light sensor stuff***************************/
RBD::LightSensor light_sensor(A0);
const int dark = 700;
const int bright = 25;
/**************************************************************************/

/****************************Soil moisture sensor stuff********************/
int sensorPin = D3;
int sensorValue = 0;
int percent = 0;
/**************************************************************************/

/*******************************WI-FI and HTTP*****************************/
const char *ssid = "XPS-15-Hotspot";
const char *password = "12345678";
char jsonOutput[256];
WiFiClient client;
HTTPClient http;
int postInterval = 60000;
/**************************************************************************/

struct PlantData
{
  int id;
  char *name;
  char *type;
};
PlantData plantData;
void setPlantData()
{
  plantData.id = 0;
  plantData.name = "ArduinoPlant";
  plantData.type = "Technical";
}
struct Values
{
  int id;
  int temp;
  int hum;
  int mois;
  int light;
};
Values values;
void resetValues()
{
  values.id = plantData.id;
  values.temp = 0;
  values.hum = 0;
  values.mois = 0;
  values.light = 0;
}

int convertToPercent(int value)
{
  int percentValue = 0;
  percentValue = map(value, 1023, 350, 0, 100);
  if (percentValue > 100)
    percentValue = 100;
  return percentValue;
}

void getPlants()
{
  http.begin(client, "http://169.254.57.15:8080/api/plant");
  http.addHeader("Content-Type", "application/json");
  int httpCode = http.GET();

  if (httpCode > 0)
  {
    String payload = http.getString();
    Serial.print("StatusCode: ");
    Serial.println(String(httpCode));
    Serial.println(payload);

    DynamicJsonDocument doc(1536);
    DeserializationError error = deserializeJson(doc, payload);

    if (error)
    {
      Serial.print(F("deserializeJson() failed: "));
      Serial.println(error.f_str());
      return;
    }

    for (JsonObject item : doc.as<JsonArray>())
    {
      const char *name = item["name"];
      int id = item["id"];
      if (String(name) == String(plantData.name))
      {
        plantData.id = id;
        // Serial.println(id);
      }
    }
  }
  else
  {
    Serial.println("Error on HTTP request");
  }
  http.end();
}
void addPlant()
{
  http.begin(client, "http://169.254.57.15:8080/api/plant");
  http.addHeader("Content-Type", "application/json");
  StaticJsonDocument<256> doc;
  JsonObject object = doc.to<JsonObject>();
  object["name"] = plantData.name;
  object["type"] = plantData.type;

  serializeJson(doc, jsonOutput);

  int httpCode = http.POST(String(jsonOutput));
  String payload = http.getString();

  Serial.print("json: ");
  Serial.println((String)jsonOutput);

  Serial.print("httpCode: ");
  Serial.println(httpCode);

  Serial.print("payload: ");
  Serial.println(payload);

  Serial.println("You should see this only once");
  http.end();
}

void postMeasurements()
{
  http.begin(client, "http://169.254.57.15:8080/api/measurements");
  http.addHeader("Content-Type", "application/json");
  StaticJsonDocument<256> doc;
  JsonObject object = doc.to<JsonObject>();
  object["plantId"] = values.id;
  object["temp"] = values.temp;
  object["humidity"] = values.hum;
  object["moisture"] = values.mois;
  object["light"] = values.light;

  serializeJson(doc, jsonOutput);
  serializeJsonPretty(doc, Serial);
  int httpCode = http.POST(String(jsonOutput));
  String payload = http.getString();
  http.end();
}

void setup()
{
  Serial.begin(9600);
  /*****************************Init all the sensors*************************/
  light_sensor.setCeiling(dark);
  light_sensor.setFloor(bright);
  dht.begin();
  setPlantData();
  /********************************Start WI-FI******************************/
  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED)
  {
    delay(500);
    Serial.println("Waiting for connection");
  }
  Serial.println("Connected with: " + WiFi.SSID());
  getPlants();
  resetValues();
}

void loop()
{
  /****************************Soil moisture sensor stuff********************/
  sensorValue = analogRead(sensorPin);
  values.mois = convertToPercent(sensorValue);
  /**************************************************************************/

  /************************************DHT stuff*****************************/
  constrain(values.hum = dht.readHumidity(), 0, 100);
  constrain(values.temp = dht.readTemperature(), 0, 100);
  /**************************************************************************/

  /*****************************Light sensor stuff***************************/
  values.light = light_sensor.getInversePercentValue();
  /**************************************************************************/

  /****************************Random values for testing*********************/
  values.temp = random(10, 50);
  values.hum = random(20, 80);
  values.mois = random(0, 2);
  values.light = random(25, 700);
  /**************************************************************************/

  if (WiFi.status() == WL_CONNECTED)
  {
    //The plantID sequence in spring start with 1
    //So it cannot have the value 0
    //So if it still is 0 -> update
    if (plantData.id == 0)
    {
      addPlant();
      getPlants();
      values.id = plantData.id;
    }
    postMeasurements();
    Serial.println();
    Serial.println(plantData.name);
    Serial.println(plantData.id);
  }
  delay(postInterval);
}
