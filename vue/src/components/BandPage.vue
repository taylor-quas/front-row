<template>
  <div class="band-view">  
    <h2>Band Page Component</h2>
    {{ $route.params.bandName }}
    <button id="follow-button" v-if="band && isFollowing !== null" @click="toggleFollow">{{ isFollowing? 'Unfollow' : 'Follow' }}</button>
    {{ band }}
  </div>
</template>

<script>
import BandService from '../services/BandService';

export default {
data() {
    return {
        band: '',
        bandName: this.$route.params.bandName,
        isFollowing: null,
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
    
  }
}
</script>

<style>
  .band-view{
    margin-top: 10vh;
  }
</style>