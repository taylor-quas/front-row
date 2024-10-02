<template>
    <div class="results">
      <h3>Band Results</h3>
      {{ bands }}
      <ul>
        <li v-for="band in bands" :key="band.bandId">{{ band.bandName }}</li>
        
      </ul>
    </div>
  </template>
  
  Search filtering
  //take the value input into the search box
  //compare that value in a filter method to the array of all bands
  //compare against - band name, band artists* (need artists in database)
  //include all matching Results in the filtered list
  

  Search and genre need to work together
  //module 3 unit 12 lecture has an example of filters working together
  //Line 222 for check all, line 238 for filtering

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