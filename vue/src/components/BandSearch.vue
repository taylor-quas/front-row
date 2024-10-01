<template>
    <div id="bands">
      <div class="bands-list">
        <h2>Search Result</h2>
        <ul>
          <li v-for="band in bands" :key="band.bandId">{{ band.bandName }}</li>
        </ul>
      </div>
    </div>
</template>

<script>
import BandService from '../services/BandService';

export default {
  data() {
    return {
      bands: [],
      BandService,
    };
  },
  created() {
    BandService.getBands().then(response => {
          this.bands = response.data 
      })
      .catch(error => {
          console.error(error);
      });
  },

};
</script>

<style>

  
</style>
