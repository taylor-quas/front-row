<template>
  <div>
    <h2>Your Messages</h2>
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
          const expirationTime = new Date(message.message.messageTimeExpiration);
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