<template>
    <div class="results">
      <h3>Band Results</h3>
      <BandComponent v-for="band in filteredBands" :key="band.band.bandId" :band="band" />
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
  import BandComponent from './BandComponent.vue';
  
  export default {
    props: ['searchQuery', 'selectedGenres'],
    components: {
      BandComponent
    },
    data() {
      return {
        bands: [],
        filteredBands: [],
        BandService
      }
    },
    created() {
      this.fetchBands();
    },
    methods: {
      fetchBands() {
        BandService.getBands()
          .then(response => {
            this.bands = response.data;
            this.filterBands();
          })
          .catch(error => {
            console.error(error);
          });
      },
      filterBands() {
        let filteredBands = this.bands;
        if (this.searchQuery) {
          filteredBands = filteredBands.filter(band => {
            return band.name.toLowerCase().includes(this.searchQuery.toLowerCase());
          });
        }

        if (this. selectedGenres && this.selectedGenres.length > 0) {
          filteredBands = filteredBands.filter(band => {
            return this.selectedGenres.some(genre => band.genres.includes(genre));
          });
        }
        this.filteredBands = filteredBands;
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
  };
  </script>

<style>

</style>