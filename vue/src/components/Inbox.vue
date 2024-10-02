<template>
  <div>
    <h2>This is the inbox</h2>
    <div v-for="message in messages" :key=message.messageId>
    <h3>{{ message.messageContent }}</h3>
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