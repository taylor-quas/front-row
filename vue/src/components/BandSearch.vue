<template>
  <div class="results">
    <div v-if="$store.state.token == ''" @click="$router.push('/fbc')">
      <h4 style="color: red;">X</h4>
    </div>
    <div id="band-results-grid" v-if="filteredBands.length > 0">
      <BandComponent v-for="band in filteredBands" :key="band.bandId" :band="band" :hasMessage="false" />
    </div>
    <div v-else>
      <p>No bands found</p>
    </div>
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
        const search = this.searchQuery.toLowerCase();

        filteredBands = this.bands.filter(band => {
          const bandName = band.band.bandName || '';
        
          return (
            bandName.toLowerCase().includes(search)
          );
        });
      }

      if (this.selectedGenres && this.selectedGenres.length > 0) {
        filteredBands = filteredBands.filter(band => {
          
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
  margin: 5px;
  border-radius: 20px;
  display: flex;
  flex-wrap: wrap;
  justify-content: start;
  align-items: flex-start;
  z-index: 1;
}

</style>