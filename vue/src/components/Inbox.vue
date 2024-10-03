<template>
  <div id="inbox">
    <h2>My Messages</h2>
    <div id="filter">
    <h4>Filter By Band</h4>
    <select class="filterByBand" v-model="selectedBand">
      <option value="all">All</option>
      <option v-for="band in followedBands" :key="band.bandId" :value="band.band.bandName">{{band.band.bandName}}</option>
    </select>
    <h4>Sort By</h4>
    <select class="sort-by" v-model="selectedSort">
      <option value="newest">Newest</option>
      <option value="oldest">Oldest</option>
      <option value="band-name">Band Name (A-Z)</option>
      <option value="band-name-reverse">Band Name (Z-A)</option>
    </select>
    </div>
    <div id="message-card" v-for="message in sortedMessages" :key="message.messageId">
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
        selectedBand: 'all',
        selectedSort: 'newest'
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
      },
      sortedMessages() {
        let sortedMessages = this.filteredMessages;

        const removeThe = (name) => {
          return name.trim().toLowerCase().startsWith('the ')
            ? name.trim().slice(4).trim()
            : name.trim();
        }

        if (this.selectedSort === 'newest') {
          sortedMessages = sortedMessages.sort((a, b) => {
            return new Date(b.message.messageTimeSent) - new Date(a.message.messageTimeSent);
          });
        } else if (this.selectedSort === 'oldest') {
          sortedMessages = sortedMessages.sort((a, b) => {
            return new Date(a.message.messageTimeSent) - new Date(b.message.messageTimeSent);
          });
        } else if (this.selectedSort === 'band-name') {
          sortedMessages = sortedMessages.sort((a, b) => {
            return removeThe(a.bandName).localeCompare(removeThe(b.bandName));
          });
        } else if (this.selectedSort === 'band-name-reverse') {
          sortedMessages = sortedMessages.sort((a, b) => {
            return removeThe(b.bandName).localeCompare(removeThe(a.bandName));
          });
        }

        return sortedMessages;

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

#inbox {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  flex-basis: 100%;
  width: 100%;
}

#filter {
  display: flex;
  flex-direction: column;
  margin: 5px;
  padding: 5px;
  border-radius: 20px;
  justify-content: center;
  align-items: center;
}

.filterByBand {
  margin: 5px;
  padding: 5px;
  border-radius: 20px;
}

.sort-by {
  margin: 5px;
  padding: 5px;
  border-radius: 20px;
}

#message-card {
  display: flex;
  width: 100%;
  padding: 5px;
  align-content: center;
  justify-content: center;
}

</style>