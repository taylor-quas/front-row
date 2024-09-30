<template>
  <div id="bg-image">
    <!-- Your content here -->
    <div class="home">
      <h1>Here's what you would see if you were not logged in.</h1>
    </div>
    <div>
      <h2>
        <router-link v-bind:to="{ name: 'login' }">Login.</router-link>
      </h2>
      <h2>
        <router-link v-bind:to="{ name: 'register' }">Create Account.</router-link>
      </h2>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      
    }
  }
};
</script>

<style scoped>
  #bg-image {
    background-image: url('src/assets/BR.png');
    background-size: cover;
    background-position: center;
  }
</style>
