<template>
  <div class="results">
    <h3>Band Results</h3>
    <BandComponent v-for="band in filteredBands" :key="band.bandId" :band="band" />
  </div>
</template>

<script>
import BandService from '../services/BandService';
import BandComponent from './BandComponent.vue';

export default {
  props: {
    searchQuery: {
      type: String,
      required: false
    },
    selectedGenres: {
      type: Array,
      required: false
    }
  },
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
      let filteredBands = this.bands || [];

      if (this.searchQuery) {
        console.log(this.searchQuery);
        const search = this.searchQuery.toLowerCase();

        console.log(this.bands);
        console.log(this.filteredBands);

        filteredBands = this.bands.filter(band => {
          const bandName = band.band.bandName || '';

          console.log(band.band.bandName.toLowerCase());
          console.log(search);

          return (
            bandName.toLowerCase().includes(search)
        )

        });
      }

      if (this.selectedGenres && this.selectedGenres.length > 0) {
        filteredBands = filteredBands.filter(band => {
          console.log(band);
          console.log(band.genreNames);
          console.log(this.selectedGenres);

          return this.selectedGenres.some(genre => {
            return band.genreNames.includes(genre)
          });
          
        });
      } else if (this.selectedGenres && this.selectedGenres.length === 0) {
        filteredBands = [];
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