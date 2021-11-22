<template>
  <div class="dashboard">
    <!-- <div class="dashboard pa-6">
      <h1>Home</h1>
    </div> -->
    <Dashboard />
  </div>
</template>

<script>
  export default {
    name: 'Home',

    components: {
      Dashboard: () => import('../components/Dashboard.vue')
    },
  };
</script>
