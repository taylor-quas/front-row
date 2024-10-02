<template>
    <div id="bands">
      <div class="bands-list">
        <h2>Your Bands</h2>
        
          <div v-for="band in followedBands" :key="band.bandId">
            <BandComponent :band="band" />
          </div>

      </div>
    </div>
  </template>
  
  <script>
  import BandService from '../services/BandService';
  import BandComponent from './BandComponent.vue';

  export default {
    components: {
      BandComponent,
    },
    data() {
      return {
        followedBands: [],
      };
    },
    created() {
      this.fetchFollowedBands();
    },
    methods: {
      fetchFollowedBands() {
        BandService.getFollowedBands().then(response => {
            this.followedBands = response.data;
            console.log(this.followedBands);
        })
        .catch(error => {
            console.error(error);
        });
      }
    }

  };
  </script>
  
  <style>
  
    
  </style>
  