<template>
    <div id="bands">
      <div>
        <h2>My Bands</h2>
        
          <div id="followed-bands">
              <BandComponent v-for="band in followedBands" :key="band.bandId" :band="band" />
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
  
  #followed-bands {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
    grid-gap: 0.5em;
  
  }
    
  </style>
  