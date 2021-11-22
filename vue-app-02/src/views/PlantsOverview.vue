<template>
  <div class="plantsOverview">
    <div class="plantsOverview pa-6">
      <!-- <h1>Here are all your plantastic Plants</h1> -->
    </div>
    <PlantOverview />
  </div>
</template>

<script>
export default {
  name: "PlantsOverview",
    components: {
      PlantOverview: () => import('../components/PlantOverview.vue')
    }
};
</script>
