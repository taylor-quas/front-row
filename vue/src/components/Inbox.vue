<template>
  <div id="inbox">
    <h2>My Messages</h2>
    <div id="filter">
      <div id="filter-box">
        <h4>Filter By Band</h4>
      <select class="filterByBand" v-model="selectedBand">
        <option value="all">All</option>
        <option v-for="band in followedBands" :key="band.bandId" :value="band.band.bandName">{{band.band.bandName}}</option>
      </select>
      </div>

      <div id="sort-box">
        <h4>Sort By</h4>
      <select class="sort-by" v-model="selectedSort">
        <option value="newest">Newest</option>
        <option value="oldest">Oldest</option>
        <option value="band-name">Band Name (A-Z)</option>
        <option value="band-name-reverse">Band Name (Z-A)</option>
      </select>
      </div>
      
    </div>
    <div id="message-card" v-for="message in sortedMessages" :key="message.messageId">
      <MessageComponent :message="message" :isRead="message.isRead" @markAsRead="handleMarkAsRead"/>
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
      this.fetchInboxMessages();
      BandService.getFollowedBands().then(response => {
            this.followedBands = response.data;
        })
        .catch(error => {
            console.error(error);
        });

    },
    watch: {
      followedBands() {
        this.fetchInboxMessages();
      }
    },

    methods: {
      handleMarkAsRead(messageId) {
        const message = this.messages.find(message => message.message.messageId === messageId);
          if (message) {
            message.isRead = true;
            console.log(message);
          }
        MessageService.markAsRead(messageId).then(() => {
          this.fetchInboxMessages();
          console.log(message);
        })
        .catch(error => {
          console.error(error);
        });

      },
      markAllAsRead() {
        this.messages.forEach(message => {

          this.handleMarkAsRead(message.message.messageId);

          // message.isRead = true;
          // const readMessages = JSON.parse(localStorage.getItem('readMessages')) || [];
          // if (!readMessages.includes(message.message.messageId)) {
          //   readMessages.push(message.message.messageId);
          // }
          // localStorage.setItem('readMessages', JSON.stringify(readMessages));

        });
      },

      fetchInboxMessages() {
        this.message = [];
        MessageService.getUserInbox().then(response => {
          console.log(response.data);
          this.messages = response.data;
        })
        .catch(error => {
          console.error(error);
        });
      }

    },
    beforeUnmount() {
      this.markAllAsRead();
    }
}
</script>

<style scoped>

#inbox {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  flex-basis: 100%;
  width: 100%;
  color: rgba(255, 255, 255, 0.743);
  font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
}

#filter {
  display: flex;
  flex-direction: row;
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

#filter-box {
  display: flex;
  flex-direction: column;
  align-items: center;
}

#sort-box {
  display: flex;
  flex-direction: column;
  align-items: center;
}

</style>