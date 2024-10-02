<template>
  <div class="search">
    <h2>SearchView</h2>
    <div class="Search-Results">
      <GenreSearch id="genres" @update:selectedGenres="updateSelectedGenres" />
      <BandSearch id="bands" :searchQuery="searchQuery" :bands="bands"/>
    </div>
  </div>
</template>

<script>
import BandSearch from '../components/BandSearch.vue';
import BandService from '../services/BandService';
import GenreSearch from '../components/GenreSearch.vue';


export default {
  components: {
    BandSearch,
    GenreSearch,
  },
  props: ['searchQuery'],
  data() {
    return {
      genres: [], 
      selectedGenres: [],
      selectAll: true,
      bands: [], 
      filteredBands: []
    };
  },
  methods: {
    fetchBands() {
      BandService.getBands().then(response => {
        this.bands = response.data;
        this.filterBands();
      }).catch(error => {
        console.error('Error fetching bands:', error);
      });
    },
    updateSelectedGenres(genres) {
      this.selectedGenres = genres;
      this.filterBands();
    },
    filterBands() {
      if (this.selectedGenres.length === 0) {
        this.filteredBands = this.bands;
      } else {
        this.filteredBands = this.bands.filter(band => 
          band.genres.some(genre => this.selectedGenres.includes(genre))
        );
      }
    }
  },
  computed: {
    searchResults() {
      return this.filteredBands;
    }
  },
};
</script>

<style scoped>
  .search {
    padding-top: 12vh;
  }

  .Search-Results {
    display: grid;
    grid-template-columns: 1fr 4fr;
    gap: 1rem;
  }

  #genres {
    padding-left: 2rem;
  }

  #bands {
    padding-right: 2rem;
  }

  h2 {
    text-align: center;
    margin-bottom: 1rem;
  }

  
</style>
