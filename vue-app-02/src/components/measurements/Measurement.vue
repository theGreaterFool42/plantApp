<template>
  <v-container>
    <v-card-text>
      <v-sheet color="teal lighten-3">
        <v-sparkline
          :labels=measurements
          :value=measurements
          :gradient="gradient"
          :smooth="radius || false"
          :padding="padding"
          :line-width="width"
          :stroke-linecap="lineCap"
          :gradient-direction="gradientDirection"
          :fill="fill"
          :type="type"
          :auto-line-width="autoLineWidth"
          auto-draw
        ></v-sparkline>
      </v-sheet>
    </v-card-text>
    <!-- <v-card-text>
      <div class="text-subtitle-1 font-weight-normal">
        {{measurementType}} in the last {{measurementsLength}} minutes
      </div>
    </v-card-text> -->
  </v-container>
</template>

<script>
//  import PlantService from "../services/PlantService";
import axios from 'axios';
const gradients = [
  ["#222"],
  ["#42b3f4"],
  ["red", "orange", "yellow"],
  ["purple", "violet"],
  ["#00c6ff", "#F0F", "#FF0"],
  ["#f72047", "#ffd200", "#1feaea"],
];

export default {
  name: "Measurement",
  props: ['pickerDates',
          'measurementType'],
  data() {
    return {
      startDate: this.pickerDates[0],
      endDate: this.pickerDates[1],
      measurements: [],
      measurementsLength: '',
      width: 2,
      radius: 10,
      padding: 8,
      lineCap: "round",
      gradient: gradients[5],
      gradientDirection: "top",
      gradients,
      fill: false,
      type: "trend",
      autoLineWidth: false,
    };
  },
  methods: {},
  //the calculation for the reduced dataset isn't working reliable. I'm not sure if
  //the first and the last values are correct
  //With datasets below the max(32) things can go wrong
  created() {
    axios.get('http://localhost:8080/api/measurements'
          + '/' + this.measurementType
          + '/' + this.$route.params.plantId
          + '/' + 'between/',
          { params: {begin: this.startDate, end: this.endDate}})
      .then(response => {
          //console.log(response.data)
          this.measurements = response.data
          this.measurementsLength = this.measurements.length
          // console.log(this.startDate)
          // console.log(this.endDate)
          // console.log(this.measurements)
          var factor = Math.floor(this.measurementsLength/32)
          console.log(this.measurementsLength)
          console.log(factor)
          var processedData = [];
          var tempValue = 0;
          for (let key in this.measurements) {
            var measurement = this.measurements[key];
              tempValue+=measurement;
            if(key%factor == 0) {
              tempValue=Math.floor(tempValue/factor)
              processedData.push(tempValue);
              tempValue = 0;
            }
            console.log(key);
          }
          console.log(processedData);
      });
    // PlantService.getTempMeasurements().then((response) => {
    //   this.measurements = response.data;
    // });
  },
};
</script>