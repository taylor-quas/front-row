<template>
  <main>
    <div>
        <h1 id="title">Create a Band</h1>
    </div>
    <div id="cover-image">
        <p v-if="!coverImage">Upload a cover image</p>
        <img v-else :src="coverImage" alt="cover image">
        <input type="file" @change="onImageUpload" accept="image/">
    </div>
    <div>
        <input
        v-model="bandName"
        id="band-name-input"
        type="text"
        placeholder="Band Name"
        >
    </div>
    <div>
        <h2>Add Genres</h2>
        <div v-for="genre in genres" :key="genre">
            <span>{{ genre }}</span>
            
        </div>
    </div>
    <div>
        <textarea
        v-model="bandDescription"
        id="band-description-input"
        placeholder="Band Description"
        ></textarea>
    </div>
    <div>
        <button @click="saveBand()" id="create-button">CREATE BAND</button>
        <button @click="cancel()" id="cancel-button">Cancel</button>
    </div>
    
  </main>
</template>

<script>
import BandService from '../services/BandService';
import ImageUpload from '../components/ImageUpload.vue';

export default {
    props: {
        showCreateBand: {
            type: Boolean,
            required: true
        }
    },
    methods: {
    saveBand() {
      BandService
        .create(this.band)
        .then(response => {
          if (response.status === 201) {
            this.$emit('band-created');
          }
        })
        .catch(error => {
          console.error(error);
        });
    },
    cancel() {
      this.$emit('close');
    }
    }
}
</script>

<style>
    #title {
        text-align: center;
        margin-top: 10vh;
    }
</style>