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
      <div class="genre-list">
        <label v-for="genre in genres" :key="genre.id">
          <input 
            type="checkbox" 
            v-model="selectedGenres" 
            :value="genre.name" 
            @change="updateSelectedGenres" 
          /> {{ genre.name }}
        </label>
      </div>
    </div>
  </template>
  
  <script>
  import BandService from '../services/BandService';
  
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
          this.selectedGenres = this.genres.map(genre => genre.name);
          this.updateSelectedGenres();
        });
      },
      toggleSelectAll() {
        this.selectedGenres = this.selectAll ? this.genres.map(genre => genre.name) : [];
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
    }
  }
  </script>

<style>

</style>