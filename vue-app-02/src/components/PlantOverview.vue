<!--
<template>
<v-container>
<ul id="example-1">
  <li v-for="plant in plants" :key="plant.id">
    <v-btn :to="'/plants/' + plant.id"> {{ plant.name }} {{ plant.type }} {{ plant.dop }}</v-btn>
  </li>
</ul>
</v-container>
</template>-->

<template>
  <v-sheet
    class="mx-auto"
    elevation="8"
    max-width="800"
  >
    <v-slide-group
      v-model="model"
      class="pa-4"
      show-arrows
    >
      <v-slide-item
        v-for="p in plants"
        :key="p.id"
        v-slot="{ active, toggle }"
      >
        <v-card
          :color="active ? 'primary' : 'teal lighten-3'"
          class="ma-4"
          height="200"
          width="100"
          @click="toggle"
          :to="'/plants/' + p.id"
        >
        <p>{{p.id}}</p>
        <!-- <v-img
          height="250"
          src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
        ></v-img> -->
          <v-row
            class="fill-height"
            align="center"
            justify="center"
          >
            <v-scale-transition>
              <v-icon
                v-if="active"
                color="white"
                size="48"
                v-text="'mdi-close-circle-outline'"
              ></v-icon>
            </v-scale-transition>
          </v-row>
        </v-card>
      </v-slide-item>
    </v-slide-group>
    <v-expand-transition>
      <v-sheet
        v-if="model != null"
        height="650"
        tile
      >
        <v-row
          class="fill-height"
          align="center"
          justify="center"
        >
        </v-row>
      </v-sheet>
    </v-expand-transition>
  </v-sheet>
</template>

<script>
import PlantService from "../services/PlantService";
export default {
  name: "PlantOverview",
  data() {
    return {
      plant: '',
      model: null,
      headers: [
          {
            text: 'PlantsOverview',
            align: 'start',
            sortable: true,
            value: 'name',
          },
          { text: 'id', value: 'id' },
          { text: 'Description', value: 'type' },
          { text: 'Date of planting', value: 'dop' },
        ],
      plants: [],
    };
  },
  methods: {
    getPlants() {
      PlantService.getPlants().then((response) => {
        this.plants = response.data;
      });
    },
  },
  created() {
    this.getPlants();
  },
};
</script>
