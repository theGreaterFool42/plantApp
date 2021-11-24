# plantApp
app to watch over your plants

## spring backend

manages the postgres database

## vue frontend

displays all your plants in neat cards and shows the data from the db
in sparkline graphs.

Currently there still is a "Dashboard". Earlier this was to register new plants.
But now the plants are registered by the waterino automatically when in the same wifi.
The final app most likely will only have a plantOverview site and a plant site.

## waterino

this is the arduino c++ code, that generates the data for the app

- Automatically connects to WiFi and register a the plant in the database.
- Reads data from multiple sensors, parse the values to json and POSTs them to the database
- plantName, plantType, WiFi-SSID and pw are hardcoded in the c++