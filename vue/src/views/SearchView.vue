<template>
  <div class="search">
    <h2>(SearchView working title)</h2>
    <div class="Search-Results">
      <!-- <band-search></band-search>  -->
      <SearchResult :searchQuery="searchQuery" :bands="bands"/>
      <GenreSearch @update:selectedGenres="updateSelectedGenres" />
    </div>
  </div>
</template>

<script>
import SearchResult from '../components/SearchResult.vue';
import BandService from '../services/BandService';
import GenreSearch from '../components/GenreSearch.vue';
import BandSearch from '../components/BandSearch.vue';

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
    GenreSearch,
    // BandSearch
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

<style>
  .search {
    padding-top: 12vh;
  }

  
</style>
