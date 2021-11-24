import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify'
// import TempMeasurement from './components/measurements/TempMeasurement'
// import HumMeasurement from './components/measurements/HumMeasurement'
// import MoisMeasurement from './components/measurements/MoisMeasurement'
// import LightMeasurement from './components/measurements/LightMeasurement'
import DatePicker from './components/DatePicker'
import Measurement from './components/measurements/Measurement'
import Plant from './components/Plant'

// Vue.component('temp-measurements-component', TempMeasurement)
// Vue.component('hum-measurements-component', HumMeasurement)
// Vue.component('mois-measurements-component', MoisMeasurement)
// Vue.component('light-measurements-component', LightMeasurement)
Vue.component('date-picker-component', DatePicker)
Vue.component('measurements-component', Measurement)
Vue.component('plant-card-component', Plant)
Vue.config.productionTip = false

new Vue({
  router,
  vuetify,
  render: h => h(App)
}).$mount('#app')
