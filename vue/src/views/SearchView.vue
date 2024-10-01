<template>
  <div class="search">
    <h2>Search Results</h2>
    <div class="Search-Results">
      <SearchResult :searchQuery="searchQuery" :selectedGenres="selectedGenres"/>
      <GenreSearch @update:selectedGenres="updateSelectedGenres" />
    </div>
    <!-- create binding between search box text (in nav in app.vue)
    filter bands at /search endpoint -->
  </div>
</template>

<script>
import SearchResult from '../components/SearchResult.vue';
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
    SearchResult,
    GenreSearch
  },
  props: ['searchQuery'],
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

<style>
  .search {
    padding-top: 12vh;
  }

  
</style>
