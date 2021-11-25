<template>
  <v-container>
    <v-card-text>
      <v-sheet color="teal lighten-3">
        <v-sparkline
          :labels=processedData
          :value=processedData
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
      processedData: [],
      maxValues: 32,
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
  //Moisture is always 0, becasue if you get an average of 0 and 1 and floor it, it is 0
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
          console.log("measurements: " + this.measurements)
          console.log("measurementsLength: " + this.measurementsLength)
          // calc compressed array
          if (this.measurementsLength > this.maxValues) {
            var factor = Math.floor(this.measurementsLength/this.maxValues)
            var mod = this.measurementsLength%this.maxValues;
            var reducedDate = this.measurements.slice(0, (this.measurementsLength-mod))
            //console.log("mod: " + mod)
            //console.log("factor: " + factor)
            //console.log("reducedData: " + reducedDate)
            var tempValue = 0;
            for (let key in reducedDate) {
              var measurement = reducedDate[key];
                tempValue+=measurement;
              if(key%factor == (factor-1)) {
                tempValue=Math.floor(tempValue/factor)
                this.processedData.push(tempValue);
                tempValue = 0;
              }
              //console.log("key: " + key);
            }
          }
          else {
            this.processedData = this.measurements;
          }
          console.log("processedData: " + this.processedData);
          console.log("processedDataLength: " + this.processedData.length)
      });
    // PlantService.getTempMeasurements().then((response) => {
    //   this.measurements = response.data;
    // });
  },
};
</script>