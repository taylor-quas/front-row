<template>
  <div id="inbox">
    <h2>Inbox</h2>
    <div id="message-card" v-for="message in filteredMessages" :key="message.message.messageId">
      <MiniMessageComponent :message="message" :isRead="checkIfMessageIsRead(message)"/>
    </div>
  </div>
</template>

<script>
import MessageService from "../services/MessageService.js";
import BandService from "../services/BandService.js";
import MiniMessageComponent from "../components/MiniMessageComponent.vue";

export default {
  components: {
    MiniMessageComponent
  },
  data() {
    return {
      messages: [],
      selectedBand: 'all',
      selectedSort: 'newest',
      pollInterval: null
    };
  },
  computed: {
    filteredMessages() {
      const currentTime = new Date();

      return this.messages.filter(message => {
        const expirationTime = new Date(message.message.messageTimeExpiration);
        const notExpired = expirationTime > currentTime;

        return notExpired;
      });
    },
  },
  created() {
    this.fetchInboxMessages();
    this.fetchFollowedBands();
  },
  methods: {
    handleMarkAsRead(messageId) {
      const messageIndex = this.messages.findIndex(message => message.message.messageId === messageId);
      if (messageIndex !== -1) {
        this.$set(this.messages, messageIndex, { ...this.messages[messageIndex], isRead: true });
      }
      MessageService.markAsRead(messageId).then(() => {
        this.fetchInboxMessages();
        this.$emit('messages-updated');
      })
        .catch(error => {
          console.error(error);
        });
    },

    fetchInboxMessages() {
      MessageService.getUserInbox().then(response => {
        this.messages = response.data;
        console.log(response.data);

      })
        .catch(error => {
          console.error(error);
        });
    },

    fetchFollowedBands() {
      BandService.getFollowedBands().then(response => {
        this.followedBands = response.data;
      })
        .catch(error => {
          console.error(error);
        });
    },

    handleMessageReceived() {
      this.fetchInboxMessages();
    },

    checkIfMessageIsRead(targetMessage) {
      return this.messages.some(message => message.message.messageId === targetMessage.message.messageId && message.isRead);
    }
  }
}
</script>

<style scoped>
#inbox {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
  padding: 5px;
}

#message-card {
  width: 100%;
  padding: 5px;
}
</style>