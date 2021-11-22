import axios from 'axios'

// var plantId = 1
const PLANT_API_BASE_URL = 'http://localhost:8080/api/plant'
// const ALL_MEASUREMENTS_API_BASE_URL = 'http://localhost:8080/api/measurements/' + plantId
// const TEMP_MEASUREMENTS_API_BASE_URL = 'http://localhost:8080/api/measurements/temp/' + plantId
// const HUM_MEASUREMENTS_API_BASE_URL = 'http://localhost:8080/api/measurements/humidity/' + plantId
// const MOIS_MEASUREMENTS_API_BASE_URL = 'http://localhost:8080/api/measurements/moisture/' + plantId
// const LIGHT_MEASUREMENTS_API_BASE_URL = 'http://localhost:8080/api/measurements/light/' + plantId


class PlantService {
    getPlants() {
        return axios.get(PLANT_API_BASE_URL);
    }
    // getAllMeasurements() {
    //     return axios.get(ALL_MEASUREMENTS_API_BASE_URL);
    // }
    // getTempMeasurements() {
    //     return axios.get(TEMP_MEASUREMENTS_API_BASE_URL);
    // }
    // getHumMeasurements() {
    //     return axios.get(HUM_MEASUREMENTS_API_BASE_URL);
    // }
    // getMoisMeasurements() {
    //     return axios.get(MOIS_MEASUREMENTS_API_BASE_URL);
    // }
    // getLightMeasurements() {
    //     return axios.get(LIGHT_MEASUREMENTS_API_BASE_URL);
    // }
}

export default new PlantService()