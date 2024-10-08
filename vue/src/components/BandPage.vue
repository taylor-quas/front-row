<template>
  <div class="band-view">
    
    <section class="content">  
      <div v-if="band">
        <h2 id="name">{{ bandName }}</h2>
        <button :class="{'follow-button': true, 'unfollow': isFollowing}" 
          v-if="band && isFollowing !== null" 
          @click="toggleFollow">
            {{ isFollowing? 'Unfollow' : 'Follow' }}
        </button>
        <button v-if="canEdit === true" @click="$router.push(`${bandName}/edit`)">
          Edit Page
        </button>
        <img id="heroImage" :src="band.band.bandHeroImage" alt="Band Hero Image">
        <section class="genres">
          <p>{{ band.genreNames.join(' • ') }}</p> 
        </section>
        <br>
        <p id="description">{{ band.band.bandDescription }}</p>
        <br><br><br>
        <section id="gallery">
          <h3 id="gallery-header">Gallery</h3>
          <image-upload v-if="canEdit === true" :admin="true"></image-upload>
          <gallery-image :bandName="bandName"/>
        </section>
      </div>
      <div v-else>
        Loading...
      </div>
    </section>
  </div>
</template>

<script>
import BandService from '../services/BandService';
import GalleryImage from './GalleryImage.vue';
import UserService from '../services/UserService';
import ImageUpload from './ImageUpload.vue';

export default {
  components: {
    GalleryImage,
    ImageUpload
  },
  data() {
    return {
        band: '',
        bandName: this.$route.params.bandName,
        isFollowing: null,
        BandService,
        canEdit: ''
    }
  },
  mounted() {
    this.getBand();
    this.checkRole()
  },
  methods: {
    getBand() {
      this.BandService.getBand(this.bandName)
        .then(response => {
          this.band = response.data;
          if (this.band && this.band.band.bandId) {
            this.getIsFollowing(this.band.band.bandId);
          } else {
            console.error("Band not found");
          }
        })
        .catch(error => {
          console.error(error);
        });
    },
    getIsFollowing(bandId) {
      this.BandService.getIsFollowing(bandId)
        .then(response => {
          this.isFollowing = response.data;
        })
        .catch(error => {
          console.error(error);
        });
    },
    toggleFollow() {
      if (this.isFollowing) {
        this.unfollowBand();
      } else {
        this.followBand();
      }
    },
    followBand() {
      this.BandService.followBand(this.band.band.bandId)
        .then(response => {
          console.log(response);
          this.isFollowing = true;
        })
        .catch(error => {
          console.error(error);
        });
    },
    unfollowBand() {
      this.BandService.unfollowBand(this.band.band.bandId)
        .then(response => {
          console.log(response);
          this.isFollowing = false;
        })
        .catch(error => {
          console.error(error);
        });
    },
    checkRole(){
      UserService.getRole().then(response => {
        if (response.data.role == 'ROLE_BAND' && response.data.managedBands.some(band => band.bandId === this.band.band.bandId)) {
          this.canEdit = true
        }
      })
    }
    
  }
}
</script>

<style scoped>

#description {
  grid-area: content;
  color: white;
  background-color: rgb(43, 43, 43);
  font-size: 1.2em;
  line-height: 1.6;
  padding: 15px 20px;
  border-radius: 8px;
  border: 1px solid #ddd;    /* Light grey border for emphasis */
  max-width: 90%;            /* Keep within content width */
  margin: 1em auto;
  text-align: justify;
}

.band-view{
  margin: 10vh;
  display: grid;
  grid-template-columns: 1fr 5fr 1fr;
  grid-template-areas: 
    ". name ."
    ". content ."
    ". content ."
    ". content ."
    ". content ."
    "gallery gallery gallery"
    "gallery gallery gallery"
    "gallery gallery gallery"; 
}

#name{
  grid-area: name;
  font-size: 2em;
  margin-bottom: 1em;
  text-align: left;
}

#description{
  font-size: 1.2em;
  margin-top: 1em;
}

#heroImage{
  width: 100%;
  height: 500px;
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

#gallery{
  grid-area: gallery;
}

#gallery-header{
  font-size: 1.5em;
  margin-bottom: 1em;
  justify-content: center;
}

section h3 {
    color: #FFF;
    text-align: start;
    font-family: Montserrat;
    font-size: 28px;
    font-style: normal;
    font-weight: 500;
    line-height: normal;
}

.follow-button {
  padding: 10px 20px;
  background-color: #999999;  /* Light grey */
  color: white;               /* White text */
  border: none;               /* Remove default border */
  border-radius: 5px;         /* Rounded corners */
  font-size: 16px;            /* Font size */
  cursor: pointer;            /* Pointer on hover */
  transition: background-color 0.3s ease; /* Smooth transition */
  margin-bottom: 1em;
}

.follow-button:hover {
  background-color: #808080;  /* Darker light grey on hover */
}

.follow-button:active {
  background-color: #333333;  /* Even darker grey when clicked */
}

.follow-button:focus {
  outline: none;              /* Remove focus outline */
}

.follow-button.unfollow {
  background-color: #666666;  /* Darker grey for unfollow */
}

.follow-button.unfollow:hover {
  background-color: #4d4d4d;  /* Darker grey on hover */
}

</style>