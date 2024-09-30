<template>
  <div id="capstone-app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
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
    align-content: center;
    justify-content: center;
    background-color: white;
    width: 100%;
    height: 6vh;
  }

</style>
