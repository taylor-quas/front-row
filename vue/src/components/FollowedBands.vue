<template>
      <div id="bands">
        <div class="bands-list">
          <h2>Your Bands</h2>
          <ul>
            <li v-for="band in followedBands" :key="band.bandId">{{ band.bandName }}</li>
            {{ followedBands }}
            {{  $store.state.token }}
          </ul>
        </div>
      </div>
  </template>
  
  <script>
  import BandService from '../services/BandService';

  export default {
    data() {
      return {
        followedBands: [],
        BandService,
      };
    },
    created() {
      this.$store.commit('SET_AUTH_TOKEN', this.$store.state.token);
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
  