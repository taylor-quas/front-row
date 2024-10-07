<template>
  <div class="home">
      
    <Outbox id="outbox" v-if="isManager"></Outbox>
    <ManagedBands id="managed-bands" v-if="isManager"></ManagedBands>

    <HomeInbox id="inbox" @click="inboxView"></HomeInbox>
    <FollowedBands id="followed-bands"></FollowedBands>

    <!-- <button v-if="isButtonVisible" id="create-button" @click="showCreateBand = true, hideButton()">Manage a Band!</button> -->

    <!-- <div v-if="showCreateBand" id="modal-overlay">
      <div id="modal-content">
        <CreateBand id="create-band" @close="closeModal" @band-created="bandCreated"/>
      </div>
    </div> -->
  </div>
</template>

<script>
import { useRouter } from 'vue-router';
import FollowedBands from '../components/FollowedBands.vue';
import HomeInbox from '../components/HomeInbox.vue';
import BandService from '../services/BandService';
import Outbox from '../components/Outbox.vue';
import ManagedBands from '../components/ManagedBands.vue';

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
    FollowedBands,
    HomeInbox,
    Outbox,
    ManagedBands
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
    padding-top: 12vh;
    padding-right: 2rem;
    padding-left: 2rem;
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    grid-template-areas: 
      "outbox managed-bands managed-bands"
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

  #outbox {
    grid-area: outbox;
  }

  #managed-bands {
    grid-area: managed-bands;
    margin: 5px;
    border-radius: 20px;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    align-items: flex-start;
  }

</style>
