<template>
      <div id="outbox">
        <div id="header">
            <h2>Sent Messages</h2>
            <button id="new-message" @click="toggleNewMessage">New Message</button>
        </div>
        <div id="message-card" v-for="message in filteredMessages" :key="message.messageId">
            <MessageComponent :message="message"/>
        </div>
        <p id="outbox-end">No more Messages!</p>

        <div v-if="showNewMessage">
            <CreateMessage></CreateMessage>
        </div>

    </div>
</template>

<script>
import MessageService from "../services/MessageService.js";
import BandService from "../services/BandService.js";
import MessageComponent from "../components/MessageComponent.vue";
import CreateMessage from "../components/CreateMessage.vue";


export default {
    components: {
        MessageComponent,
        BandService,
        MessageService,
        CreateMessage
    },
    data() {
        return {
            messages: [],
            managedBands: [],
            showNewMessage: false
        };
    },
    methods: {
        toggleNewMessage() {
            this.showNewMessage = !this.showNewMessage;
        },
    }
}

</script>

<style>


#message-card {
  display: flex;
  width: 100%;
  padding: 5px;
  align-content: left;
  justify-content: left;
}
</style>