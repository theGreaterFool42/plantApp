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

### Todo

- DatePicker uses the wrong colors
- DatePicker should be a pop up (I guess)
- there should be a limit to the displayed data points.
    - I currently ask for all the measurements of a type in a given time period with no regards how many data points there are
    - So I have to calculate averages of points to match the limit
    - either directly in the api, or in vue
- Stock photo should be a photo from the database
    - so photos must be stored in the database
        -  in the far future there should be a camera, that shoots photos, to make timelapses
    - use a default one, if the plant has no photo
    - these photos should also be displayed in the pseudo carousel
- the http requests for the different measurements probably can be combined into one request with parameters for the type (temp/hum/mois/light)
- delete the dashboard
- the waterino should sleep between sending data
    - currently the waterino stops sending data. But sleeping will probably fix this
- add an account system, so specific users can only see specific plants