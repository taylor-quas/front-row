<template>
  <div class="search">
    <div id="filter-genre">
      <h3 id="genre-heading">Filter by Genre</h3>
      <GenreSearch id="genres" @update:selectedGenres="updateSelectedGenres" />
    </div>
    <div id="search-results"> 
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
    display: grid;
    grid-template-areas: 
      "filter-genre search-results";
  }

  #filter-genre {
    grid-area: filter-genre;
    margin: 0.5em;
  }

  #search-results {
    grid-area: search-results;
    margin: 0.5em;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    align-items: flex-start;
  }

  #genre-heading {
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
