<template>
  <div class="results">
    <router-link v-bind:to="{ name: 'bands' }"></router-link>
  </div>
</template>

<script>
import BandService from '../services/BandService';

export default {
    data() {
        return {
            bands: [],
            BandService
        }
    },
    created() {
        BandService.getBands().then(response => {
            this.bands = response.data
            console.log(response.data)
        })
        .catch(error => {
            console.error(error);
        });
    }
}
</script>

<style>

</style>