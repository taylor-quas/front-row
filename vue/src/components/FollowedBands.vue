<template>
    <div id="bands">
      <div>
        <h2>My Bands</h2>
        
          <div class="followedBands">
            <div v-for="band in followedBands" :key="band.bandId">
              <BandComponent :band="band" />
            </div>
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
  
  .followedBands {
    display: flex;
    margin: 5px;
    border: 1px solid black;
    border-radius: 20px;
    padding: 5px;
    align-content: center;
    justify-content: center;
    width: 100%;
  
  }
    
  </style>
  