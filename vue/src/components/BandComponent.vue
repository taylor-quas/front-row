<template>
    <div class="bandItem" @click="this.$router.push({ path: `/${band.band.bandName}` })">
        <img :src="band.band.bandHeroImage" alt="band image"/>
        <div class="artistGenre">
            <h4>{{ band.band.bandName }}</h4>
            <div class="genreList">
                <p v-for="genre in band.genreNames" :key="genre">{{ genre }}</p>
            </div>
            
        </div>
        
        <p>{{ band.band.bandDescription }}</p>
    </div>
</template>

<script>
export default {
    props: {
        band: {
            type: Object,
            required: true

        }
    }
    
}
</script>

<style>

.bandItem {
    display: flex;
    justify-content: space-evenly;
    margin: 5%;
    border: 1px solid black;
    border-radius: 20px;
}

.artistGenre {
    display: flex;
    flex-direction: column;
    justify-content: space-evenly;
    align-items: center;
    flex-basis: 30%;
    align-content: space-around;
}

h4 {
    font-family:'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
    font-size: 30px;
    text-align: center;
    justify-content: center;
    align-items: center;
}

.genreList > p {
    display: flex;
    flex-direction: column;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    font-size: 20px;
    text-align: center;
    justify-content: center;
    align-items: center;
}

ul {
    display: flex;
    
    justify-content: center;
    align-items: center;
    
    list-style-type: none;
    padding-left: 20px;
}

li {
    display: flex;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    justify-content: center;
    align-items: center;
    
}

img {
    display: flex;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    flex-basis: 40%;
    justify-content: center;
    align-items: center;
    align-self: center;
    width: 10%;
    height: 10%;
    padding: 2%;
}

p {
    display: flex;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    font-size: 15px;
    flex-basis: 40%;
    text-align: right;
    justify-content: center;
    align-items: center;
    padding: 5px;
}

</style>