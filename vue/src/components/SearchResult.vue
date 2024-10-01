<template>
    <div class="results">
      <h3>Band Results</h3>
      <ul>
        <li v-for="band in filteredBands" :key="band.id">{{ band.name }}</li>
      </ul>
    </div>
  </template>
  
  <script>
  import BandService from '../services/BandService';
  
  export default {
    props: ['searchQuery', 'selectedGenres'],
    data() {
      return {
        bands: [],
        filteredBands: []
      }
    },
    created() {
      this.fetchBands();
    },
    methods: {
      fetchBands() {
        BandService.getBands().then(response => {
          this.bands = response.data;
          this.filterBands();
        })
        .catch(error => {
          console.error(error);
        });
      },
      filterBands() {
        this.filteredBands = this.bands.filter(band => {
          const matchesSearch = this.searchQuery ? 
            band.name.toLowerCase().includes(this.searchQuery.toLowerCase()) : true;
          const matchesGenre = this.selectedGenres.length === 0 || 
            this.selectedGenres.some(genre => band.genres.includes(genre));
          return matchesSearch && matchesGenre;
        });
      }
    },
    watch: {
      searchQuery() {
        this.filterBands();
      },
      selectedGenres: {
        handler() {
          this.filterBands();
        },
        deep: true
      }
    }
  }
  </script>

<style>

</style>