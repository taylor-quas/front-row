<template>
  <div id="capstone-app">
    <div id="nav">
      <div class="nav-item">
        <router-link v-bind:to="{ name: 'home' }" id="home-button">Home</router-link>
      </div>
      <div class="nav-item">
          <input id="searchbox" type="text" placeholder="🔎 SEARCH" @focus="startSearch" v-model="searchQuery" />
      </div>
      <div class="nav-item">
        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''" id="logout-button">Logout</router-link>
      </div>
    </div>
    <router-view :searchQuery="searchQuery"/>
  </div>
</template>

<script>
import { useRouter } from 'vue-router';

export default {
  components: {},
  data() {
    const router = useRouter();
    return {
      router,
      searchQuery: ''
    };
  },
  methods: {
    startSearch() {
      this.router.push('/search')
    }
  }
};


</script>

<style>
  #capstone-app{
    background-color: rgb(165, 165, 255);
    min-height: 100vh;
    display: flex;
    flex-direction: column;
  }

  #nav{
    position: fixed;
    
    display: flex;
    flex-direction: row;
    justify-content: space-evenly;
    align-items: center;

    background-color: white;
    width: 100%;
    height: 6vh;
    padding-left: 20px;
  }

  .nav-item {
    display: flex;
  }

  #home-button{
    color: black;
    font-family: Montserrat;
    font-size: 20px;
    font-weight: 600;
    align-content: center;
    text-decoration-line: none;
  }

  #logout-button {
    color: black;
    font-family: Montserrat;
    font-size: 20px;
    font-weight: 600;
    align-content: center;
    text-decoration-line: none;
  }

  #searchbox {
    width: 20vw;
    height: 1.5rem;
    border-radius: 16px;
    display: flex;
    align-content: center;
  }
  

</style>
