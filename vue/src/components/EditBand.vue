  <template>
    <div class="band-view">
        <h2>Edit Band</h2>
        <form @submit.prevent="updateItem">
            <input type="text" :placeholder="band.band.bandName" v-model="band.band.bandName" />
            <input type="text-area" :placeholder="band.band.bandDescription" v-model="band.band.bandDescription" />
            <image-upload-vue @click="setImage"  ref="imageUpload"></image-upload-vue>
            <button type="submit">Save</button>     
        </form>
    </div>
  </template>
  
  <script>
  import BandService from '../services/BandService';
  import ImageUploadVue from './ImageUpload.vue';
  
  export default {
    components: {
        ImageUploadVue
    },
    data() {
      return {
          band: '', 
          bandName: this.$route.params.bandName,
          BandService,
          imageUrl: ''
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
      setImage(){
        this.imageUrl = this.imageUpload.imageUrl;
        console.log(this.imageUrl)
      }
    }
  }
  </script>
  
  <style scoped>
    .band-view {
        margin-top: 12vh;
    }

  </style>