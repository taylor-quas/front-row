<template>
    <div class="genres">
      <h3>Genres</h3>
      <label>
        <input 
          type="checkbox" 
          v-model="selectAll" 
          @input="toggleSelectAll"
        /> Select All
      </label>
      </div>
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
    };
  },
    methods: {
    filterBands() {
      BandService.getBands(this.selectedGenres).then(response => {
        this.filteredBands = response.data;
      });
    },
    fetchGenres() {
        BandService.getGenres().then(response => {
            this.genres = response.data;
            this.selectedGenres = this.genres.map(genre => genre.name); 
        });
    },
    watch: {
    selectedGenres(newVal) {
      this.selectAll = newVal.length === this.genres.length; 
    }
    },
    toggleSelectAll() {
      this.selectedGenres = this.selectAll ? this.genres.map(genre => genre.name) : [];
      this.filterBands(); 
    }
  },
}
</script>

<style>

</style>