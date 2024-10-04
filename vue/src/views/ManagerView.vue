<template>
  <div id="manager-view">
    <outbox id="outbox"></outbox>
    <div id="managed-band">
      <BandComponent v-for="band in managedBands" :key="band.bandId" :band="band" />
    </div>
    </div>
    
</template>

<script>
import Outbox from '../components/Outbox.vue';
import BandComponent from '../components/BandComponent.vue';

export default {
  components: {
      Outbox,
  },
  data() {
    return {
      showCreateBand: false
    }
  }
}
</script>

<style scoped>
#manager-view {
  display: flex;
  padding: 20px;
}

</style>