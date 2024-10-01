<template>
  <div class="search">
<<<<<<< HEAD

    <div class="genres">
      <h3>Genres</h3>
      <label>
        <input 
          type="checkbox" 
          v-model="selectAll" 
          @input="toggleSelectAll"
        /> Select All
      </label>
      <div class="genre-list">
        <label v-for="genre in genres" :key="genre.id">
          <input 
            type="checkbox" 
            v-model="selectedGenres" 
            :value="genre.name" 
            @change="filterBands" 
          /> {{ genre.name }}
        </label>
      </div>
    </div>
=======
    <SearchResult>Here is the Search Result</SearchResult>
    <GenreSearch>Here is the Genre List</GenreSearch>
>>>>>>> 05e58cdf0083fe9dc170c1d55afc05bf7c8f36e3
    <p>This is the search view</p>
    <!-- create binding between search box text (in nav in app.vue)
    filter bands at /search endpoint -->
  </div>
</template>

<script>
import SearchResultVue from '../components/SearchResult.vue';
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
<<<<<<< HEAD
=======
  components: {
    SearchResult,
    GenreSearch
  },
>>>>>>> 05e58cdf0083fe9dc170c1d55afc05bf7c8f36e3
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
