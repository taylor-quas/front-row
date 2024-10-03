<template>
  <div class="profile">
    <Profile></Profile>
    <image-upload></image-upload>
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
  .inbox {
    padding-top: 12vh;
  }
</style>
