<template>
  <div id="manager-view">
    <outbox id="outbox"></outbox>
    <div id="managed-bands">
      <BandComponent v-for="band in managedBands" :key="band.bandId" :band="band" />
    </div>
    </div>
    
</template>

<script>
import Outbox from '../components/Outbox.vue';
import BandComponent from '../components/BandComponent.vue';
import BandService from '../services/BandService';

export default {
  components: {
      Outbox,
      BandComponent
  },
  data() {
    return {
      showCreateBand: false,
      managedBands: [],
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
  flex-wrap: wrap;
  justify-content: space-around;
  width: 100%;
  grid-area: managed-bands;
}

#outbox {
  grid-area: outbox;
}

</style>