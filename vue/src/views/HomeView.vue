<template>
  <div class="home">
    <div id="followed-bands">
      <followed-bands-vue></followed-bands-vue>
    </div>
    <div id="manager-view" v-if="bandCreated">
      <ManagerView></ManagerView>
    </div>    
    <HomeInbox id="inbox" @click="inboxView"></HomeInbox>
    <followed-bands-vue id="followed-bands"></followed-bands-vue>
    <button v-if="isButtonVisible" id="create-button" @click="showCreateBand = true, hideButton()">Manage a Band!</button>
    <div v-if="showCreateBand" @close="showCreateBand = false" class="create-band-overlay">
      <CreateBand id="create-band"/>
    </div>
  </div>
</template>

<script>
import { useRouter } from 'vue-router';
import FollowedBandsVue from '../components/FollowedBands.vue';
import ManagerView from './ManagerView.vue';
import HomeInbox from '../components/HomeInbox.vue';
import CreateBand from '../components/CreateBand.vue';

export default {
  data() {
    const router = useRouter();
    return {
      router,
      username: '',
      isFocused: false,
      showCreateBand: false,
      bandCreated: false,
      isButtonVisible: true,
    };
  },
  components: {
    FollowedBandsVue,
    ManagerView,
    HomeInbox,
    CreateBand,
  },
  methods: {
    inboxView() {
      this.router.push('/inbox')
    },
    hideButton() {
      this.isButtonVisible = false; // Set to false to hide the button
    }
  },
  computed: {
    inboxCount() {
      return this.inboxMessages.length;
    },
  },
  
};
</script>

<style>

  .home {
    padding-top: 10vh;
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    grid-template-areas: 
      "inbox followed-bands followed-bands";
    background-color: rgb(22, 22, 22);
  }

  #inbox {
    grid-area: inbox;
    margin: 5px;
    border-radius: 20px;
    z-index: 1;
  }

  #followed-bands {
    grid-area: followed-bands;
    margin: 5px;
    border-radius: 20px;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    align-items: flex-start;
    z-index: 1;
  }

  #manager-view {
    grid-area: manager-view;
    margin: 5px;
    border-radius: 20px;
    z-index: 100;
  }
  
</style>
