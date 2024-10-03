<template>
  <div class="band-view">
    <section class="content">  
      <div v-if="band">
        <h2 id="name">{{ bandName }}</h2>
        <img id="heroImage" :src="band.band.bandHeroImage" alt="Band Hero Image">
        <section class="genres">
          <p>{{ band.genreNames.join(' • ') }}</p>
          
        </section>
        <br>
        <p id="description">{{ band.band.bandDescription }}</p>
        
        <!-- {{ $route.params.bandName }}
        {{ band }} -->
        <br><br><p>{{ band }}</p>

      </div>
      <div v-else>
        Loading...
      </div>
    </section>
  </div>
</template>

<script>
import BandService from '../services/BandService';

export default {
data() {
    return {
        band: '',
        bandName: this.$route.params.bandName,
        BandService
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
}
}
</script>

<style scoped>

  .band-view{
    margin: 10vh;
    display: grid;
    grid-template-columns: 1fr 5fr 1fr;
    grid-template-areas: 
      ". content .";
  }

  #name{
    font-size: 2em;
    margin-bottom: 1em;
    justify-content: flex-start;
  }

  #description{
    font-size: 1.2em;
    margin-top: 1em;
  }

  #heroImage{
    width: 100%;
    height: 50%;
    object-fit: cover;
    justify-content: center;
    align-items: center;
  }

  .genres{
    display: flex;
    flex-direction: row;
  }

  .content{
    grid-area: content;
    display: flex;
    /* flex-direction: column;
    align-items: center; */
  }
</style>