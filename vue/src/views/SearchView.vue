<template>
  <div class="search">

    <div class="genres">
      <h3>Genres</h3>
      <label>
        <input 
          type="checkbox" 
          v-model="selectAll" 
          @input="toggleSelectAll"
        /> Select All
      </label>
      <div class="genre-list">
        <label v-for="genre in genres" :key="genre.id">
          <input 
            type="checkbox" 
            v-model="selectedGenres" 
            :value="genre.name" 
            @change="filterBands" 
          /> {{ genre.name }}
        </label>
      </div>
    </div>
    <p>This is the search view</p>
    create binding between search box text (in nav in app.vue)
    filter bands at /search endpoint
  </div>
</template>

<script>
import BandService from '../services/BandService';

export default {
  data() {
    return {
      searchQuery: '',
      genres: [], 
      selectedGenres: [],
      selectAll: true,
      bands: [], 
      filteredBands: []
    };
  },
  created() {
    this.fetchGenres();
    this.fetchBands();
  },
  methods: {
    fetchGenres() {
      BandService.getGenres().then(response => {
        this.genres = response.data;
        this.selectedGenres = this.genres.map(genre => genre.name); 
    });
    },
    filterBands() {
      // this.filteredBands = this.bands.filter(band => {
      //   const matchesSearch = band.name.toLowerCase().includes(this.searchQuery.toLowerCase());
      //   const matchesGenre = this.selectedGenres.length === 0 || this.selectedGenres.includes(band.genre);
      //   return matchesSearch && matchesGenre;
      //});
    },
    toggleSelectAll() {
      this.selectedGenres = this.selectAll ? this.genres.map(genre => genre.name) : [];
      this.filterBands(); 
    }
  },
  watch: {
    selectedGenres(newVal) {
      this.selectAll = newVal.length === this.genres.length; 
    }
  },
  computed: {

  }
};
</script>

<style>
  .search {
    padding-top: 12vh;
  }

  
</style>
