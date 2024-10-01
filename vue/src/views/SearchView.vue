<template>
  <div class="search">
    <SearchResult>Here is the Search Result</SearchResult>
    <GenreSearch>Here is the Genre List</GenreSearch>
    <p>This is the search view</p>
    <!-- create binding between search box text (in nav in app.vue)
    filter bands at /search endpoint -->
  </div>
</template>

<script>
import SearchResult from '../components/SearchResult.vue';
import BandService from '../services/BandService';
import GenreSearch from '../components/GenreSearch.vue';

export default {
  data() {
    return {
      searchQuery: '',
      genres: [], 
      selectedGenres: [],
      selectAll: true,
      bands: [], 
      filteredBands: []
    };
  },
  components: {
    SearchResult,
    GenreSearch
  },
  methods: {
    
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
