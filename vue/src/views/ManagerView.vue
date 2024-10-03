<template>
  <div id="manager-view">
    <outbox id="outbox"></outbox>
    <div v-if="showCreateBand" class="create-band-overlay">
      <button class="close-button" @click="showCreateBand = true">Create Band</button>
      <CreateBand id="create-band"/>
    </div>
    <div id="followed-bands">
      <BandComponent v-for="band in managedBands" :key="band.bandId" :band="band" />
    </div>

</div>
    
</template>

<script>
import Outbox from '../components/Outbox.vue';
import CreateBand from '../components/CreateBand.vue';
import BandComponent from '../components/BandComponent.vue';

export default {
  components: {
      Outbox,
      CreateBand
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