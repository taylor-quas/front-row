<template>
      <div id="outbox">
        <div id="header">
            <h2>Sent Messages</h2>
            <button>New Message</button>
            <div id="filter">
             <h4>Filter By Band</h4>
             <select class="filterByBand" v-model="selectedBand">
                <option value="all">All</option>
                <option v-for="band in followedBands" :key="band.bandId" :value="band.band.bandName">{{band.band.bandName}}</option>
             </select>
            </div>
        </div>
        <div id="message-card" v-for="message in filteredMessages" :key="message.messageId">
            <MessageComponent :message="message"/>
        </div>
        <!-- <div class="message">
            <h3>Message Title</h3>
             <h3>{{ message.title }}</h3>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
             <p>{{ message.content }}</p> 
        </div> -->
        <p id="outbox-end">No more Messages!</p>
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