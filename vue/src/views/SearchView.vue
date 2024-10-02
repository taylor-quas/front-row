<template>
  <div class="search">
    <h2>(SearchView working title)</h2>
    <div class="Search-Results">
      <BandSearch :searchQuery="searchQuery" :bands="bands"/>
      <GenreSearch @update:selectedGenres="updateSelectedGenres" />
    </div>
  </div>
</template>

<script>
import BandSearch from '../components/BandSearch.vue';
import BandService from '../services/BandService';
import GenreSearch from '../components/GenreSearch.vue';


export default {
  // data() {
  //   return {
  //     searchQuery: '',
  //     genres: [], 
  //     selectedGenres: [],
  //     selectAll: true,
  //     bands: [], 
  //     filteredBands: []
  //   };
  // },
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
    updateSelectedGenres(genres) {
      this.selectedGenres = genres;
    },
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

  
</style>
