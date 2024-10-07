  <template>
    <div class="band-view">
        {{ band }}
        <h2>Edit Band</h2>
        <form @submit.prevent="updateItem">
            <input type="text" :placeholder="band.band.bandName" v-model="band.band.bandName" />
            <textarea type="text" :placeholder="band.band.bandDescription" v-model="band.band.bandDescription"></textarea>
            Image: <img :src="band.band.bandHeroImage" alt="">
            <image-upload-vue @update:modelValue="setImage"></image-upload-vue>
            
            <div class="genre-list"> 
                <GenreSearch @update:selectedGenres="updateSelectedGenres"/>
            </div>
            
            <button type="submit" @click="updateBand">Save</button>
        </form>
    </div>
  </template>
  
  <script>
  import BandService from '../services/BandService';
  import ImageUploadVue from './ImageUpload.vue';
  import GenreSearch from './GenreSearch.vue';
  
  export default {
    components: {
        ImageUploadVue,
        GenreSearch
    },
    data() {
      return {
          band: '', 
          bandName: this.$route.params.bandName,
          BandService,
          newImageUrl: ''
      }
    },
    created() {
      this.getBand();
    },
    methods: {
      getBand() {
        this.BandService.getBand(this.bandName)
          .then(response => {
            this.band = response.data;
          })
          .catch(error => {
            console.error(error);
          });
      },
      setImage(data){
        this.band.band.bandHeroImage = data;
      },
      updateSelectedGenres(genres) {
            this.band.genreNames = genres;
      },
      updateBand(){
        BandService.updateBand(this.band.band.bandId, this.band)
      }
    }
  }
  </script>
  
  <style scoped>
    .band-view {
        margin-top: 12vh;
    }

  </style>