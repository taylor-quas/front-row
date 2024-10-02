<template>
  <div class="band-view">  
    <h2>Band Page Component</h2>
    {{ $route.params.bandName }}
    {{ band }}
  </div>
</template>

<script>
import BandService from '../services/BandService';

export default {
data() {
    return {
        band: '',
        bandName: this.$route.params.bandName,
        BandService
    }
  },
  created() {
    this.getBand();
  },
  methods: {
    getBand() {
      this.BandService.getBand(this.bandName)
        .then(response => {
          this.band = response.data;
        })
        .catch(error => {
          console.error(error);
        });
    },
}
}
</script>

<style>
  .band-view{
    margin-top: 10vh;
  }
</style>