<template>
    <div class="genre-search">
      <div class="genres">
        <label>
          <input 
            type="checkbox" 
            v-model="selectAll" 
            @change="toggleSelectAll"
          /> Select All
        </label>
      </div>
      <div class="genre-list"> 
        <label v-for="genre in genres" :key="genre">
          <br />
          <input 
            type="checkbox" 
            v-model="selectedGenres" 
            :value="genre" 
            @change="updateSelectedGenres" 
          /> <p style="text-transform: capitalize; color: black;">{{ genre }}</p>
        </label>
      </div>
    </div>
  </template>
  
<script>
import BandService from '../services/BandService';

export default {
  data() {
    return {
      genres: [], 
      selectedGenres: [],
      selectAll: true, 
    };
  },
  methods: {
    fetchGenres() {
      BandService.getGenres().then(response => {
        this.genres = response.data;
        this.selectedGenres = this.genres.map(genre => genre);
        this.updateSelectedGenres();
      });
    },
    toggleSelectAll() {
      this.selectedGenres = this.selectAll ? this.genres.map(genre => genre) : [];
      this.updateSelectedGenres();
    },
    updateSelectedGenres() {
      this.$emit('update:selectedGenres', this.selectedGenres);
    }
  },
  watch: {
    selectedGenres(newVal) {
      this.selectAll = newVal.length === this.genres.length;
    }
  },
  created() {
    this.fetchGenres();
    BandService.getGenres().then(response => {
          this.genres = response.data 
      })
      .catch(error => {
          console.error(error);
      });
  }
}
</script>

<style>
.genre-search {
    font-family: Montserrat, sans-serif;
    color: #1a1a1a; /* Darker grey for text */
    background-color: #e6e6e6; /* Very light grey background */
    padding: 25px;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Soft shadow for depth */
    max-width: 420px;
    margin: 0 auto;
}

.genres label {
    font-size: 18px;
    color: #1a1a1a; /* Darker grey for "Select All" */
    font-weight: 600;
    display: flex;
    align-items: center;
    margin-bottom: 12px;
}

.genre-list {
    border-top: 1px solid #b3b3b3; /* Medium grey border to separate sections */
    padding-top: 15px;
    margin-top: 15px;
}

.genre-list label {
    display: flex;
    align-items: center;
    font-size: 16px;
    color: #333;
    padding: 8px 0;
    border-bottom: 1px solid #ccc; /* Light grey separator between genres */
}

.genre-list label:last-of-type {
    border-bottom: none; /* Remove bottom border for the last item */
}

input[type="checkbox"] {
    accent-color: #666; /* Medium grey for checkbox */
    margin-right: 12px;
    transform: scale(1.2); /* Slightly larger checkbox */
}

input[type="checkbox"]:hover {
    accent-color: #333; /* Darker grey on hover */
    cursor: pointer;
}

</style>