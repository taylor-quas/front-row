<template>
  <div id="manager-view">
    <outbox id="outbox"></outbox>
    <div id="managed-bands">
        <ManagedBands></ManagedBands>
    </div>
  </div>
    
</template>

<script>
import Outbox from '../components/Outbox.vue';
import BandComponent from '../components/BandComponent.vue';
import BandService from '../services/BandService';
import ManagedBands from '../components/ManagedBands.vue';

export default {
  components: {
      Outbox,
      BandComponent,
      ManagedBands
  },
  data() {
    return {
      showCreateBand: false,
      managedBands: []
    }
  },
  created() {
    this.fetchManagedBands();
  },
  methods: {
    fetchManagedBands() {
      BandService.getManagedBands().then(response => {
        this.managedBands = response.data;
      })
      .catch(error => {
        console.error(error);
      });
    },
  }
}
</script>

<style scoped>
#manager-view {
  display: grid;
  grid-template-columns: 1fr 2fr;
  grid-template-areas: 
    "outbox managed-bands";
  padding: 20px;
}

#managed-bands {
  display: flex;
  grid-gap: 0.5em;
  grid-area: managed-bands;
  flex-wrap: wrap;
  justify-content: center;
  align-items: flex-start;
}

#outbox {
  grid-area: outbox;
}

</style>