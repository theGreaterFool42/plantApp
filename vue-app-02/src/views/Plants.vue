<template>
  <div class="plants">
    <div class="plants pa-6">
    </div>
    <Plant />
  </div>
</template>

<script>
export default {
  name: "Plants",
    components: {
      Plant: () => import('../components/Plant.vue')
    }
};
</script>
