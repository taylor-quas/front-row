<template>
  <div>
    <h2>Your Messages</h2>
    <h4>Filter By Band</h4>
    <select class="filterByBand" v-model="selectedBand">
      <option value="all">All</option>
      <option v-for="band in followedBands" :key="band.bandId" :value="band.band.bandName">{{band.band.bandName}}</option>
    </select>
    <div v-for="message in filteredMessages" :key="message.messageId">
      <MessageComponent :message="message"/>
    </div>
  </div>
</template>

<script>
import MessageService from "../services/MessageService.js";
import BandService from "../services/BandService.js";
import MessageComponent from "../components/MessageComponent.vue";

export default {
  components: {
    MessageComponent
  },
    data() {
      return {
        messages: [],
        followedBands: [],
        selectedBand: 'all'
      };
    },
    computed: {
      filteredMessages() {
        const currentTime = new Date();

        return this.messages.filter(message => {
          const expirationTime = new Date(message.message.messageTimeExpiration);
          const notExpired = expirationTime > currentTime;
          const bandMatches = this.selectedBand === 'all' || message.bandName === this.selectedBand;

          return notExpired && bandMatches;
        });
      }
    },
    created() {
      MessageService.getUserInbox().then(response => {
            this.messages = response.data 
        })
        .catch(error => {
            console.error(error);
        });
      BandService.getFollowedBands().then(response => {
            this.followedBands = response.data;
        })
        .catch(error => {
            console.error(error);
        });

    },
}
</script>

<style>

</style>