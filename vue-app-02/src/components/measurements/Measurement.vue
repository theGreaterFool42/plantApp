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
    <v-card-text>
      <div class="text-subtitle-1 font-weight-normal">
        {{measurementType}} in the last {{measurementsLength}} minutes
      </div>
    </v-card-text>
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
  name: "TempMeasurement",
  props: ['slyderBegin',
          'slyderEnd',
          'measurementType'],
  data() {
    return {
      offset: this.slyderEnd,
      pageSize: this.slyderBegin,
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
  created() {
      axios.get('http://localhost:8080/api/measurements'
          + '/' + this.measurementType
          + '/' + this.$route.params.plantId
          + '/' + 'pagination/timeStamp'
          + '/' + this.offset
          + '/' + this.pageSize )
      .then(response => {
          //var slicedData = response.data.slice(response.data.length - this.slyder, response.data.length);
          //this.measurements = slicedData;
          //this.measurementsLength = slicedData.length;
          //console.log(slicedData);
          this.measurements = response.data.content
          this.measurementsLength = this.measurements.length
          console.log(this.offset)
          console.log(this.pageSize)
          console.log(this.measurements)
      });
    // PlantService.getTempMeasurements().then((response) => {
    //   this.measurements = response.data;
    // });
  },
};
</script>