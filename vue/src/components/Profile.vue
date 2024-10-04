<template>
  <div id="profile">
    <h2 id="header">Your Profile</h2>

    <div id="profile-section">
      <h4>{{ user.username }}</h4>
    </div>

    <p>You are following {{ followedBands.length }} bands</p>

  </div>
</template>

<script>
import BandService from '../services/BandService';
import UserService from '../services/UserService';

export default {
  data(){
    return{
      followedBands: [],
      user: {}
    }
  },
  created() {
    this.fetchFollowedBands();
    this.fetchUser();
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

    fetchUser() {
      UserService.getUser().then(response => {
          this.user = response.data;
          console.log(this.user);
      })
      .catch(error => {
          console.error(error);
      });
    }
  }
}
</script>

<style>
    #profile {
      margin-top: 6vh;
      color: white;
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      display: grid;
      grid-template-areas: 
        "header header"
        "profile-section stats-section";
    }

    #header {
      grid-area: header;
      margin: 1em;
      text-align: center;
      justify-content: center;
      align-items: center;
    }

    #profile-section {
      grid-area: profile-section;
      display: flex;
      text-align: center;
      justify-content: center;
      align-items: center;
      margin: 1em;
    }

    #stats-section {
      grid-area: stats-section;
      display: flex;
      text-align: center;
      justify-content: center;
      align-items: center;
      margin: 1em;
    }

</style>