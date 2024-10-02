<template>
  <div>
    <h2>Band Page Component</h2>
    {{ band }}
  </div>
</template>

<script>
import BandService from '../services/BandService';

export default {
data() {
    
    return {
        props: {
            band: {
                type: Object,
                required: true
            }
        },
        BandService,
        band: ''
    }
  },
  created() {
    this.fetchBands();
  },
  methods: {
    fetchBands() {
      BandService.getBand()
        .then(response => {
          this.band = response.data;
          this.filterBands();
        })
        .catch(error => {
          console.error(error);
        });
    },
}
}
</script>

<style>

</style>