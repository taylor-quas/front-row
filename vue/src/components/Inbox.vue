<template>
  <div>
    <h2>This is the inbox</h2>
    <div v-for="message in filteredMessages" :key=message.messageId>
    <h3>Message: {{ message.message.messageContent }}</h3>
    <h4>From: {{ message.bandName }}</h4>
    </div>
  </div>
</template>

<script>
import MessageService from "../services/MessageService.js"

export default {
    data() {
      return {
        messages: [],
        MessageService,
      };
    },
    computed: {
      filteredMessages() {
        const currentTime = new Date();
        return this.messages.filter(message => {
          const expirationTime = new Date(message.messageTimeExpiration);
          return expirationTime > currentTime;
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
    },
}
</script>

<style>

</style>