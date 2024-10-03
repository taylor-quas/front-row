<template>
    <div id="bands">
      <div>
        <h2>My Bands</h2>
        
          <div id="followed-bands">
            <div id="add-band" @click="searchView">
              <img id="plus-sign" src="../assets/plus-sign-icon-2048x2048-mp0pz4g8.png" alt="Add Band" />
            </div>
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
      },
      searchView() {
        this.$router.push('/search')
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

  #add-band {
    width: 200px;
    height: 200px;
    position: relative;
    overflow: hidden;
    margin: 1em;
    border-radius: 20px;
    z-index: 1;
    background-color: white;
    display: flex;
    flex-direction: column;
    justify-content: space-evenly;
    align-items: center;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    font-size: 1em;
  }

  #plus-sign {
    width: 50%;
    height: 50%;
  }
    
  </style>
  