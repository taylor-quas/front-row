<template>
  <div id="manager-view">
    <outbox></outbox>
  </div>
    <div v-if="showCreateBand" class="create-band-overlay">
      <button class="close-button" @click="showCreateBand = false">Close</button>
      <CreateBand />
    </div>
    
</template>

<script>
import Outbox from '../components/Outbox.vue';

export default {
  components: {
      Outbox
  },
}
</script>

<style>

</style>