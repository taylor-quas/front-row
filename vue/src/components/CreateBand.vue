<template>
  <main class="main-container">
    <header class="header">
        <div>
            <h1 id="title">Create a Band</h1>
        </div>
    </header>
    <div class="content">
        <div id="cover-image" >
            <ImageUpload 
            class="required" 
            :admin="false" 
            v-model="band.band.bandHeroImage" 
            required
            ></ImageUpload>
        </div>
        <!-- :imageUrl="band.band.bandHeroImage" -->
        <div class="input">
            <input 
            v-model="band.band.bandName"
            class="required"
            type="text"
            placeholder="Band Name"
            required
            >
        </div>
        <div class="input">
            <textarea
            v-model="band.band.bandDescription"
            class="required"
            placeholder="Band Description"
            required
            ></textarea>
        </div>
        <div>
            <h3 id="genre-heading">Add genres</h3>
            <div class="genre-list"> 
                <GenreSearch 
                class="required" 
                @update:selectedGenres="updateSelectedGenres"
                required
                />
            </div>
        </div>
        <div>
            <button @click="createBand()" id="create-button">CREATE BAND</button>
            <button @click="cancel()" id="cancel-button">Cancel</button>
        </div>
    </div>
    
  </main>
</template>

<script>
import BandService from '../services/BandService';
import ImageUpload from '../components/ImageUpload.vue';
import GenreSearch from './GenreSearch.vue';
import { watch } from 'vue';

export default {
    data() {
        return {
            band: {
                band: {
                    bandName: '',
                    bandDescription: '',
                    bandHeroImage: '',  
                },
                genreNames: []
            }
        }
    },
    components: {
            ImageUpload,
            GenreSearch
        },
    props: {
        showCreateBand: {
            type: Boolean,
            required: true
        }
    },
    methods: {
        createBand() {
            BandService.create(this.band).then(response => {
            if (response.status === 201) {
                BandService.updateRole('USER_BAND'); 
                this.$emit('band-created');
                this.$router.push('/' + this.band.band.bandName);
            }
            })
            .catch(error => {
            console.error(error);
            });
        },
        updateSelectedGenres(genres) {
            this.band.genreNames = genres;
        },
        cancel() {
        this.$emit('close');
        },
        mounted() {
            this.debugger();
        }
    },
    watch: {
        'band.band.bandHeroImage'(newValue) {
            console.log("Band Hero Image updated: ", newValue);
        }
    },

}
</script>


<style scoped>
#title {
    text-align: center;
    position: relative;
    margin-top: 10vh;
    background-color: rgba(240, 34, 27, 0.925);
    color: white; /* Change text color for better contrast */
    padding: 1rem; /* Add padding for a better look */
    border-radius: 5px; /* Slightly round the corners */
    font-size: 5rem;
}

.genre-list {
    text-align: center;
    position: relative;
    margin-top: 2rem; /* Adjusted for spacing */
}

.main-container {
    height: 100vh;
    display: flex;
    flex-direction: column;
    background-color: #f9f9f9; /* Light background */
    justify-content: center;
    
}

.header {
    flex-shrink: 0;
    padding: 1rem;
}

.content {
    flex-grow: 1;
    overflow-y: auto;
    padding: 1rem;
    background-color: #fff; /* Clean white background */
    border-radius: 8px; /* Rounded corners */
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); /* Subtle shadow */
    
}

.input {
    display: flex;
    flex-direction: column;
    margin-bottom: 1rem; /* Spacing between inputs */
}

input, textarea {
    width: 100%;
    padding: 10px; /* Added padding for better usability */
    border: 1px solid #ccc; /* Light border for inputs */
    border-radius: 4px; /* Slightly round the corners */
    margin-bottom: 1rem;
    transition: border-color 0.3s; /* Smooth transition on focus */
}

input:focus, textarea:focus {
    border-color: #555; /* Darker border on focus */
}

#genre-heading {
    text-align: center;
    font-weight: bold; /* Make heading bold */
}

.button-container {
    display: flex; /* Align buttons in a row */
    justify-content: center; /* Center the buttons */
    margin-top: 1rem; /* Space above the buttons */
}

button {
    padding: 10px 15px; /* Consistent padding for buttons */
    border: none; /* Remove default border */
    border-radius: 4px; /* Slightly round the corners */
    cursor: pointer; /* Pointer on hover */
    transition: background-color 0.3s; /* Smooth transition on hover */
}

#create-button {
    background-color: #333; /* Dark background for create button */
    color: #fff; /* White text */
}

#create-button:hover {
    background-color: #555; /* Lighter shade on hover */
}

#cancel-button {
    background-color: #ccc; /* Grey background for cancel button */
    color: #333; /* Dark text */
}

#cancel-button:hover {
    background-color: #aaa; /* Darker grey on hover */
}

.input {
    display: flex;
    flex-direction: column; 
    align-items: flex-start; 
}
</style>

