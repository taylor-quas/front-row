<template>
  <div id="bg-image">
    <!-- Your content here -->
    <div class="splash">
      <h1 id="page-title">FRONT ROW</h1>
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
    height: 100%;
    background-image: url('src/assets/BR.png');
    background-size: cover;
    background-position: center;
    display: flex; 
    flex-direction: column;
    height: 100vh;
    align-items: center;
  }

  .splash{
    padding-top: 100px;
  }

  #page-title{
    color: white;
  }
</style>
