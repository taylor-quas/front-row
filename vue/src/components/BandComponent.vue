<template>
    <div class="bandItem">
        <h4>{{ band.band.bandName }}</h4>
        <ul style="list-style-type: none;">
            <li v-for="genre in band.genreNames" :key="genre">{{ genre }}</li>
        </ul>
        <img src="{{ band.band.bandImage }}" alt="band image">
        <p>{{ band.band.bandDescription }}</p>
    </div>
</template>

<script>
import BandService from '../services/BandService';

export default {
    props: ['band'],
    data() {
        return {
            bands: [],
            filteredBands: [],
            BandService
        }
    },
    created() {
        this.fetchBands();
        BandService.getBands().then(response => {
            this.bands = response.data 
        })
        .catch(error => {
            console.error(error);
        });
    },
    methods: {
        fetchBands() {
            BandService.getBands().then(response => {
                this.bands = response.data;
                this.filterBands();
            })
            .catch(error => {
                console.error(error);
            });
        },
        filterBands() {
            this.filteredBands = this.bands.filter(band => {
                const matchesSearch = this.searchQuery ? 
                    band.name.toLowerCase().includes(this.searchQuery.toLowerCase()) : true;
                const matchesGenre = this.selectedGenres.length === 0 || 
                    this.selectedGenres.some(genre => band.genres.includes(genre));
                return matchesSearch && matchesGenre;
            });
        }
    },
    watch: {
        searchQuery() {
            this.filterBands();
        },
        selectedGenres: {
            handler() {
                this.filterBands();
            },
            deep: true
        }
    }

}
</script>

<style>

</style>