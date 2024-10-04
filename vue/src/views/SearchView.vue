<template>
  <div class="search">
    <h2>Search</h2>
    <div class="Search-Results">
      <h3 id="genre-heading">Filter by Genre</h3>
      <GenreSearch id="genres" @update:selectedGenres="updateSelectedGenres" />
      <h3 id="results-heading">Band Results</h3>
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
    padding-right: 2rem;
    padding-left: 2rem;
    background-color: rgb(22, 22, 22);
  }

  .Search-Results {
    display: grid;
    grid-template-columns: 1fr 4fr;
    grid-template-areas: 
      "genre-heading results-heading"
      "genres bands";
    gap: 1rem;
  }

  #genres {
    grid-area: genres;
    padding-left: 2rem;
  }

  #bands {
    grid-area: bands;
    padding-right: 2rem;
  }

  #genre-heading {
    grid-area: genre-heading;
    text-align: center;
    margin-bottom: 1rem;
  }

  #results-heading {
    grid-area: results-heading;
    text-align: start;
    margin-bottom: 1rem;
    padding-left: 2rem;
  }

  h2 {
    text-align: center;
    margin-bottom: 1rem;
  }

  h3 {
    color: #FFF;
    text-align: start;
    font-family: Montserrat;
    font-size: 28px;
    font-style: normal;
    font-weight: 500;
    line-height: normal;
}

  
</style>
