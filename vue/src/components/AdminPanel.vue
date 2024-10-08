<template>
  <div id="admin">
    <h2>Admin Panel</h2>

    <div class="genre-list">    
        {{ genres }}
        {{genreToAdd}}
        <form @submit.prevent="updateGenres">
            <label v-for="genre in genres" :key="genre">
              <br />
              <input
                type="checkbox" 
                v-model="genre.visible"
              /> <p style="text-transform: capitalize; color: black;">{{ genre.genreName }}</p>
            </label>
            <button type="submit" @click="updateGenres">Update Genres</button>
        </form>
      </div>
      <div>
        <form @submit.prevent="">
            <input type="text" v-model="genreToAdd.name">
            <button type="submit" @click="addGenre">Add Genre</button>
        </form>
      </div>
  </div>
</template>

<script>
import GenreService from '../services/GenreService';

export default {
    data(){
        return {
            genres: [], 
            genreToAdd: {
                name: ''
            }
        }
    },
    created() {
        GenreService.getGenresToManage().then(response => {
            this.genres = response.data 
        })
        .catch(error => {
            console.error(error);
        });
    },
    methods: {
        updateGenres(){
            GenreService.updateGenres(this.genres)
            .then(response => {
                console.log
            })
        },
        addGenre(){
            GenreService.addGenre(this.genreToAdd)
            .then(response => {
                location.reload();
            })
            .catch(error => {
                console.error(error.response.data);
            });
        }
    }
}
</script>

<style>
    #admin {
        margin-block: 12vh;
    }

</style>