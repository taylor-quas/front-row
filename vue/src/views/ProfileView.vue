<template>
  <div class="profile">
    <Profile></Profile>
    <image-upload :admin="true"></image-upload>
  </div>
</template>

<script>
import Profile from '../components/Profile.vue'
import ImageUpload from '../components/ImageUpload.vue';

export default {
  components: {
    Profile,
    ImageUpload
  }
};
</script>

<style>
  .profile {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  }
</style>
