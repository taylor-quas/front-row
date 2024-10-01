<template>
    <div class="results">
      <h3>Band Results</h3>
      {{ bands }}
      <ul>
        <li v-for="band in bands" :key="band.bandId">{{ band.bandName }}</li>
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
        filteredBands: [],
        BandService
      }
    },
    created() {
      this.fetchBands();
      BandService.getBands().then(response => {
            this.bands = response.data 
        })
        .catch(error => {
            console.error(error);
        });
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