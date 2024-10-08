<template>
  <div id="profile">
    <h2 id="header">Your Profile</h2>
    <button v-if="isAdmin" @click="this.$router.push('/admin')">Admin Panel</button>

    <div id="profile-section">
      <h4 id="username">{{ user.username }}</h4>
      <h4 id="phone-number" v-if="user.phoneNumber">{{ user.phoneNumber }}</h4>
      <form id="update-form" v-if="showUpdateForm" @submit.prevent="updatePhoneNumber">
        <input type="text" v-model="user.phoneNumber" placeholder="00-000-000-0000">
        <button id="submit-button" type="submit">Update</button>
      </form>
      <button id="update-button" @click="toggleUpdateForm">{{showUpdateForm ? 'Cancel' : 'Update Profile'}}</button>
      
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
      followedByGenre: {},
      showUpdateForm: false,
      isAdmin: ''
    }
  },
  mounted() {
    this.fetchFollowedBands();
    this.fetchUser();
    this.fetchGenres();
    this.checkRole();
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
    },

    toggleUpdateForm() {
      this.showUpdateForm = !this.showUpdateForm;
    },

    checkRole(){
      UserService.getRole().then(response => {
        if (response.data.role == 'ROLE_ADMIN') {
          this.isAdmin = true
        }
      })
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
      flex-direction: column;
      text-align: center;
      justify-content: flex-start;
      align-items: center;
      margin: 1em;
    }

    #username {
      margin: 1em;
    }

    #phone-number {
      margin: 1em;
    }

    #update-button {
      margin: 1em;
      width: fit-content;
    }

    #update-form {
      margin: 1em;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
    }

    #submit-button {
      margin: 1em;
      color: black;
      border-radius: 20px;
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      align-content: center;
      justify-content: center;

    }

    button {
      margin: 1em;
      color: black;
      border-radius: 20px;
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      align-content: center;
      justify-content: center;
      width: fit-content;
      min-width: 120px;
      height: fit-content;
      min-height: 30px;
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