<template>
  <main>
    <div>
        <h1 id="title">Create a Band</h1>
    </div>
    <div id="cover-image" >
       <ImageUpload :admin="false" v-model="band.bandHeroImage"></ImageUpload>
    </div>
    <div>
        <input 
        v-model="band.bandName"
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
        v-model="band.bandDescription"
        id="band-description-input"
        placeholder="Band Description"
        ></textarea>
    </div>
    <div>
        <button @click="createBand()" id="create-button">CREATE BAND</button>
        <button @click="cancel()" id="cancel-button">Cancel</button>
    </div>
    
  </main>
</template>

<script>
import BandService from '../services/BandService';
import ImageUpload from '../components/ImageUpload.vue';

export default {
    data() {
        return {
            band: {
                bandId: null,
                bandName: '',
                bandDescription: '',
                bandHeroImage: '',
                bandManagerId: null, 
            },
        }
    },
    components: {
        ImageUpload
    },
    props: {
        showCreateBand: {
            type: Boolean,
            required: true
        }
    },
    methods: {
    createBand() {
        this.debugger();
    BandService
        .create(this.band)
        .then(response => {
        if (response.status === 201) { 
            this.$emit('band-created');
            this.$router.push('/band/' + response.data.bandName);
        }
        })
        .catch(error => {
        console.error(error);
        });
    },
    cancel() {
      this.$emit('close');
    },
    debugger() {
        console.log(this.band);
    },
    mounted() {
        this.debugger();
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