<template>
  <div class="results">
    <div v-if="$store.state.token == ''" @click="$router.push('/fbc')">
      <h4 style="color: red;">X</h4>
    </div>
    <div id="band-results-grid">
      <BandComponent v-for="band in filteredBands" :key="band.bandId" :band="band" />
    </div>
  </div>
</template>

<script>
import router from '../router';
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
#band-results-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  grid-gap: 0.5em;
}

</style>