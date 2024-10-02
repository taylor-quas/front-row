<template>
    <div class="genre-search">
      <h3>Filter by Genre</h3>
      <div class="genres">
        <label>
          <input 
            type="checkbox" 
            v-model="selectAll" 
            @change="toggleSelectAll"
          /> Select All
        </label>
      </div>
      <div class="genre-list"> This is the Genre List
        <label v-for="genre in genres" :key="genre">
          <br />
          <input 
            type="checkbox" 
            v-model="selectedGenres" 
            :value="genre" 
            @change="updateSelectedGenres" 
          /> {{ genre }}
        </label>
      </div>
    </div>
  </template>
  
  <script>
  //get list of genres from /genres endpoint
  //use list to create a bunch of checkboxes
  //when the box is checked call a filter function that filters the bands array
  //should include if the band has a genre that matches the checked genre
  
  import BandService from '../services/BandService';
  import BandComponent from './BandComponent.vue';
  
  export default {
    data() {
      return {
        genres: [], 
        selectedGenres: [],
        selectAll: true, 
      };
    },
    methods: {
      fetchGenres() {
        BandService.getGenres().then(response => {
          this.genres = response.data;
          this.selectedGenres = this.genres.map(genre => genre);
          this.updateSelectedGenres();
        });
      },
      toggleSelectAll() {
        this.selectedGenres = this.selectAll ? this.genres.map(genre => genre) : [];
        this.updateSelectedGenres();
      },
      updateSelectedGenres() {
        this.$emit('update:selectedGenres', this.selectedGenres);
      }
    },
    watch: {
      selectedGenres(newVal) {
        this.selectAll = newVal.length === this.genres.length;
      }
    },
    created() {
      this.fetchGenres();
      BandService.getGenres().then(response => {
            this.genres = response.data 
        })
        .catch(error => {
            console.error(error);
        });
    }
  }
  </script>

<style>

</style>