<!--<template>
    <v-container>
        <p>{{ plants ? plants.id : 'Hey Dude, where are my plants???'}}</p>
    </v-container>
</template>-->

<template>
  <v-card
    :loading="loading"
    class="mx-auto"
    max-width="374"
    color="teal lighten-4"
  >
    <template slot="progress">
      <v-progress-linear
        color="deep-purple"
        height="10"
        indeterminate
      ></v-progress-linear>
    </template>

    <v-img
      height="250"
      src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
    ></v-img>

    <v-card-title>{{ plantName }}</v-card-title>

    <v-card-text>
      <v-row align="center" class="mx-0">
        <v-rating
          :value="4.5"
          color="amber"
          dense
          half-increments
          readonly
          size="14"
        ></v-rating>
      </v-row>

      <div class="my-4 text-subtitle-1">
        <p>{{ plantType }}</p>
      </div>

      <div>
        <p>alive since {{ plantDop }}</p>
      </div>
    </v-card-text>

    <v-divider class="mx-4"></v-divider>

    <v-card-title>Look! Graphs!</v-card-title>
    <v-chip @click="chipDatePicker = !chipDatePicker">DatePicker</v-chip>
<v-col
      cols="12"
      sm="6"
      v-if="chipDatePicker"
    >
      <v-date-picker
        v-model="dates"
        range
      ></v-date-picker>
    </v-col>
    <v-col
      cols="12"
      sm="6"
      v-if="chipDatePicker"
    >
      <v-text-field
        v-model="dateRangeText"
        label="Date range"
        prepend-icon="mdi-calendar"
        readonly
      ></v-text-field>
    </v-col>
    <!-- <date-picker-component v-if="chipDatePicker"></date-picker-component> -->
    <!-- <v-slider
        v-model="sliderValueStart"
        color="deep-purple lighten-1"
        thumb-label="always"
        max="32">
      </v-slider>
    <v-slider
        v-model="sliderValueEnd"
        color="amber lighten-1"
        thumb-label="always"
        max="4">
      </v-slider> -->
    <v-card-text>
      <v-chip-group
        multiple
        v-model="selection"
        active-class="deep-purple lighten-1 white--text"
        color="deep-purple lighten-1"
        column
      >
        <v-chip @click="chipTemp = !chipTemp">Temperatures</v-chip>
        <v-chip @click="chipHum = !chipHum">Humidity</v-chip>
        <v-chip @click="chipMois = !chipMois">Moisture</v-chip>
        <v-chip @click="chipLight = !chipLight">Light</v-chip>

        <measurements-component
          v-if="chipTemp"
          :pickerDates="dates"
          measurementType="temp"
        ></measurements-component>
        <measurements-component
          v-if="chipHum"
          :pickerDates="dates"
          measurementType="humidity"
        ></measurements-component>
        <measurements-component
          v-if="chipMois"
          :pickerDates="dates"
          measurementType="moisture"
        ></measurements-component>
        <measurements-component
          v-if="chipLight"
          :pickerDates="dates"
          measurementType="light"
        ></measurements-component>
      </v-chip-group>
    </v-card-text>

    <v-divider class="mx-4"></v-divider>

    <v-card-actions>
      <v-btn
        color="deep-purple lighten-1"
        text
        :to="'/plantsOverview'"
        @click="reserve"
      >
        Back to the Overview
      </v-btn>
    </v-card-actions>
  </v-card>
</template>


<script>
//get the length of the measurement
//create a date picker
//get index of measurement/temp where time_stamp == date picker => set to slider min
//get index of measurement/temp where time_stamp == date picker => set to slider max
import PlantService from "../services/PlantService";
export default {
  name: "Plant",
  data() {
    return {
      loading: false,
      selection: [],
      plantId: "1",
      plantName: "",
      plantType: "",
      plantDop: "",
      chipTemp: false,
      chipHum: false,
      chipMois: false,
      chipLight: false,
      chipDatePicker: false,
      dates: ["2021-09-01", "2021-11-30"],
    };
  },
  methods: {
    reserve() {
      this.loading = true;

      setTimeout(() => (this.loading = false), 2000);
    },
  },
  created() {
    this.plantId = this.$route.params.plantId;
    PlantService.getPlants().then((response) => {
      //console.log(response)
      const data = response.data;
      //console.log(data)
      const plants = [];
      for (let key in data) {
        const plant = data[key];
        //plant.ident = key
        plants.push(plant);
      }
      //console.log(plants)
      //let index = plants.findIndex((plant) => plant.id === 19);
      let index = plants
        .map(function (elelement) {
          return elelement.id;
        })
        .indexOf(Number(this.plantId));
      // let index = plants.map((el) => el.id).indexOf(19);
      console.log("id: " + this.plantId);
      console.log("index: " + index);
      this.plantName = plants[index].name;
      this.plantType = plants[index].type;
      this.plantDop = plants[index].dop;
      //console.log(plants.map((el) => el.id).indexOf(1));
    });
  },
  computed: {
    dateRangeText() {
      return this.dates.join(" ~ ");
    },
  },
};
</script>
