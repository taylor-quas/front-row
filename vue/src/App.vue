<template>
  <div id="capstone-app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }" id="home-button">Home</router-link>
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''" id="logout-button">Logout</router-link>
    </div>
    <router-view />
  </div>
</template>

<style>
  #capstone-app{
    background-color: rgb(165, 165, 255);
    min-height: 100vh;
  }

  #nav{
    position: fixed;
    display: flex;
    flex-direction: row;
    background-color: white;
    width: 100%;
    height: 6vh;
    padding-left: 20px;
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
  

</style>
