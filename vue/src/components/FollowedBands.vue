<template>
      <div id="bands">
        <div class="bands-list">
          <h2>Your Bands</h2>
          <div v-for="band in followedBands" :key="band.bandId">
            <h3>{{ band.bandName }}</h3>
          </div>
        </div>
      </div>
  </template>
  
  <script>
  import BandService from '../services/BandService';
  import BandComponent from './BandComponent.vue';

  export default {
    components: {
      BandComponent
    },
    data() {
      return {
        followedBands: [],
        BandService,
      };
    },
    created() {
      BandService.getFollowedBands().then(response => {
            this.followedBands = response.data 
        })
        .catch(error => {
            console.error(error);
        });
    },

  };
  </script>
  
  <style>
  
    
  </style>
  