<template>
  <div id="profile">
    <h2 id="header">Your Profile</h2>
    
    <div id="profile-section">
      <h4>{{ user.username }}</h4>
      <h4 v-if="user.phoneNumber">{{ user.phoneNumber }}</h4>
      <form @submit.prevent="updatePhoneNumber">
        <input type="text" v-model="user.phoneNumber" placeholder="Phone Number">
        <button type="submit">Update Phone Number</button>
      </form>
    </div>

    <div id="stats-section">
      <p>You are following {{ followedBands.length }} bands</p>
      <!-- <p v-for="band in followedBands" :key="band.band.bandId">{{band.band.bandName}}{{ band.genreNames }}</p> -->
      <p v-for="(count, genre) in followedByGenre" :key="genre">{{ genre }}: {{ count }}</p>
    </div>
    

  </div>
</template>

<script>
import BandService from '../services/BandService';
import UserService from '../services/UserService';

export default {
  data(){
    return{
      followedBands: [],
      user: {},
      genres: [],
      followedByGenre: {}
    }
  },
  created() {
    this.fetchFollowedBands();
    this.fetchUser();
    this.fetchGenres();
  },
  methods: {
    fetchFollowedBands() {
      BandService.getFollowedBands().then(response => {
          this.followedBands = response.data;
          this.getFollowedByGenre();
      })
      .catch(error => {
          console.error(error);
      });
    },

    fetchUser() {
      UserService.getUser().then(response => {
          this.user = response.data;
      })
      .catch(error => {
          console.error(error);
      });
    },

    fetchGenres() {
      BandService.getGenres().then(response => {
        this.genres = response.data;
      });
    },

    getFollowedByGenre() {
      let count = {};
      this.followedBands.forEach(band => {
        let genres = band.genreNames;

        genres.forEach(genre => {
          genre = genre.trim();

          if (count[genre]) {
            count[genre]++;
          } else {
            count[genre] = 1;
          }

        });  
      });

      this.followedByGenre = count;
    },

    updatePhoneNumber() {
      UserService.updateUserPhoneNumber(this.user.phoneNumber).then(response => {
        console.log(response);
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
      align-items: flex-start;
      margin: 1em;
    }

    #stats-section {
      grid-area: stats-section;
      display: flex;
      flex-direction: column;
      text-align: center;
      justify-content: flex-start;
      align-items: center;
      margin: 1em;
    }

</style>