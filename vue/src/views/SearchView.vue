<template>
  <div class="search">
    <h2>SearchView</h2>
    <div class="Search-Results">
      <GenreSearch id="genres" @update:selectedGenres="updateSelectedGenres" />
      <BandSearch id="bands" :searchQuery="searchQuery" :bands="bands" :selectedGenres="selectedGenres"/>
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
  // created() {
  //   this.fetchBands();
  // },
  methods: {
    updateSelectedGenres(genres) {
      this.selectedGenres = genres;
      this.filterBands();
    },
    // fetchBands() {
    //   BandService.getBands()
    //     .then(response => {
    //       this.bands = response.data;
    //     })
    //     .catch(error => {
    //       console.error(error);
    //     });
    // }
  },
  // computed: {
  //   filterBands() {
  //     let filteredBands = this.bands;
  //     if (this.searchQuery) {
  //       filteredBands = filteredBands.filter(band => {
  //         return band.name.toLowerCase().includes(this.searchQuery.toLowerCase());
  //       });
  //     }
  //     if (this.selectedGenres.length > 0) {
  //       filteredBands = filteredBands.filter(band => 
  //         this.selectedGenres.includes(band.genre)
  //       );
  //     }
  //     return filteredBands;
  //   }
  // },
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
