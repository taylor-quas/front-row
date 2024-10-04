<template>
  <div id="profile">
    <h2>This is the profile</h2>
    <h2>You are following: {{ followedBands.length }} bands</h2>
  </div>
</template>

<script>
import BandService from '../services/BandService'

export default {
  data(){
    return{
      followedBands: [],
    }
  },
  created() {
    this.fetchFollowedBands()
  },
  methods: {
    fetchFollowedBands() {
        BandService.getFollowedBands().then(response => {
            this.followedBands = response.data;
            console.log(this.followedBands);
        })
        .catch(error => {
            console.error(error);
        });
      },
  }
}
</script>

<style>
    #profile {
        margin-top: 6vh;
    }
</style>