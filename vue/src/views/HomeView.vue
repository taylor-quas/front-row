<template>
  <div class="home">
      <div id="followed-bands">
        <followed-bands-vue></followed-bands-vue>
      </div>
      <div id="manager-view" v-if="isManager">
        <ManagerView></ManagerView>
      </div>    
      
      <HomeInbox id="inbox" @click="inboxView"></HomeInbox>
      <followed-bands-vue id="followed-bands"></followed-bands-vue>
      <button v-if="isButtonVisible" id="create-button" @click="showCreateBand = true, hideButton()">Manage a Band!</button>
    <div v-if="showCreateBand" id="modal-overlay">
      <div id="modal-content">
        <CreateBand id="create-band" @close="closeModal" @band-created="bandCreated"/>
      </div>
    </div>
  </div>
</template>

<script>
import { useRouter } from 'vue-router';
import FollowedBandsVue from '../components/FollowedBands.vue';
import ManagerView from './ManagerView.vue';
import HomeInbox from '../components/HomeInbox.vue';
import CreateBand from '../components/CreateBand.vue';
import BandService from '../services/BandService';

export default {
  data() {
    const router = useRouter();
    return {
      router,
      username: '',
      isFocused: false,
      showCreateBand: false,
      isManager: false,
      isButtonVisible: true,
    };
  },
  components: {
    FollowedBandsVue,
    ManagerView,
    HomeInbox,
    CreateBand,
  },
  created() {
    this.checkIfManager();
  },
  methods: {
    inboxView() {
      this.router.push('/inbox')
    },
    hideButton() {
      this.isButtonVisible = false;
    },
    closeModal() {
      this.showCreateBand = false;
      this.isButtonVisible = true;
    },
    bandCreated() {
      this.showCreateBand = false;
      this.isButtonVisible = true;
      // More code to be added here for band creation
    },
    checkIfManager() {
      BandService.getManagedBands().then(response => {
        this.isManager = response.data.length > 0;
      });
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
      "manager-view manager-view manager-view"
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
    display: flex;
  }

  #modal-overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    backdrop-filter: blur(5px);
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 10;

  }
  #modal-content {
    background-color:rgba(240, 34, 27, 0.925);
    padding: 20px;
    border-radius: 5px;
  }
</style>
