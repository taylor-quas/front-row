<template>
  <div id="capstone-app">
    <div id="nav">
      <div id="left-nav">
        <div class="nav-item">
          <router-link v-bind:to="{ name: 'home' }" id="home-button">Home</router-link>
        </div>
        <div class="nav-item">
            <div id="searchbox-wrapper">&nbsp;🔎 <input id="searchbox" type="text" placeholder="SEARCH" @focus="startSearch" v-model="searchQuery" /></div>
        </div>
      </div>
      <div id="right-nav">
        <div class="nav-item">
          <router-link v-bind:to="{ name: 'profile' }" v-if="$store.state.token != ''" id="profile-button">Profile</router-link>
        </div>
        <div class="nav-item">
          <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''" id="logout-button">Logout</router-link>
        </div>
      </div>
    </div>
    <router-view :searchQuery="searchQuery"/>
  </div>
</template>

<script>
import { useRouter } from 'vue-router';

export default {
  components: {

  },
  data() {
    const router = useRouter();
    return {
      router,
      searchQuery: ''
    };
  },
  methods: {
    startSearch() {
      if(this.$store.state.token != ''){
        this.router.push('/search')
      }
      else this.router.push('/discover')
    }
  }
};


</script>

<style>
  #capstone-app{
    background-color: rgb(22, 22, 22);
    min-height: 100vh;
    display: flex;
    flex-direction: column;
  }

  #nav{
    position: fixed;
    
    display: flex;
    justify-content: space-evenly;
    align-items: center;

    background-color: white;
    width: 100%;
    height: 6vh;
    z-index: 1000;
  }

  .nav-item {
    padding: 20px;
  }

  #left-nav {
    width: 50%;
    display: flex;
    justify-content: flex-start;
  }

  #right-nav {
    width: 50%;
    display: flex;
    justify-content: flex-end;
  }

  #home-button{
    color: black;
    font-family: Montserrat;
    font-size: 20px;
    font-weight: 600;
    text-decoration-line: none;
  }

  #logout-button {
    color: black;
    font-family: Montserrat;
    font-size: 20px;
    font-weight: 600;
    text-decoration-line: none;
  }

  #searchbox {
    outline: none;  
    border: none;
  }

  #searchbox-wrapper {
    display: flex;
    width: 20vw;
    height: 1.5rem;
    border: 2px solid black;
    border-radius: 16px;
  }

  #profile-button {
    color: black;
    font-family: Montserrat;
    font-size: 20px;
    font-weight: 600;
    align-content: center;
    text-decoration-line: none;
  }
  

</style>
