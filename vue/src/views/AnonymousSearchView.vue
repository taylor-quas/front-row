<template>
  <div class="search">
    <h2>SearchView</h2>
    <div class="Search-Results">
      <BandSearch id="bands" :searchQuery="searchQuery" :bands="bands"/>
    </div>
  </div>
</template>

<script>
import BandSearch from '../components/BandSearch.vue';

export default {
  components: {
    BandSearch,
  },
  props: ['searchQuery'],
  data() {
    return {
      bands: [], 
      filteredBands: []
    };
  },
  methods: {

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
