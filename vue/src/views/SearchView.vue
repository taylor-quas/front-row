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
    updateSelectedGenres(genres) {
      this.selectedGenres = genres;
    }
  }
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
