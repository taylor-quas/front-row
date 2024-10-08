<template>
    <div id="outbox">
        <div id="header">
            <h2 id="title">Sent Messages</h2>
            <button id="new-message" @click="toggleNewMessage">NEW MESSAGE</button>
        </div>
        <div id="message-card" v-for="message in filteredMessages" :key="message.messageId">
            <MessageComponent :message="message"/>
        </div>
        <p id="outbox-end">No more Messages!</p>

        <div v-if="showNewMessage" id="new-message-form">
            <CreateMessage @message-sent="handleMessageSent" @close="toggleNewMessage"></CreateMessage>
        </div>

    </div>
</template>

<script>
import MessageComponent from "../components/MessageComponent.vue";
import CreateMessage from "../components/CreateMessage.vue";


export default {
    components: {
        MessageComponent,
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

        handleMessageSent() {
            this.toggleNewMessage();
        }
    }
}

</script>

<style>


#message-card {
  display: flex;
  width: 100%;
  padding: 5px;
}

#new-message-form {
    display: flex;
    flex-direction: column;
    align-content: center;
    justify-content: center;
    width: 100%;
    padding: 5px;
}

#outbox {
    display: flex;
    flex-direction: column;
    align-content: center;
    width: 100%;
    padding: 5px;
}

#new-message {
    background-color: white;
    color: rgb(27, 27, 27);
    padding: 10px 20px; /* Consistent padding */
    margin: 0.5em; /* Spacing between buttons */
    border: none; /* No border */
    border-radius: 10px; /* Rounded corners */
    cursor: pointer; /* Pointer on hover */
    font-size: 1rem; /* Standard font size */
    transition: background-color 0.3s, transform 0.2s; /* Smooth transition */
}

#new-message:hover {
    background-color: #aaa; /* Lighter shade on hover */
    transform: translateY(-1px); /* Lift effect on hover */
}

#header {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    align-items: center;
    width: 100%;
    padding: 5px;
}


</style>