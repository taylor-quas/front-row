<template>
  <div class="results">
    <router-link v-for="band in bands" v-bind:key="band.id"
      v-bind:to="{ name: 'SearchView', params: { id: band.id } }">
      <div class="band" v-bind:style="{ 'background-color': board.backgroundColor }">{{ band }}</div>
    </router-link>
  </div>
</template>

<script>
export default {
    props: ['bands'],
    
}
</script>

<style>

</style>