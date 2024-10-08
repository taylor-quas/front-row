<template>
  <div class="home">

    <HomeOutbox id="outbox" v-if="isManager"></HomeOutbox>
    <ManagedBands id="managed-bands" v-if="isManager"></ManagedBands>

    <div id="divider" v-if="isManager"></div>

    <HomeInbox id="inbox" @click="inboxView"></HomeInbox>
    <div id="manage-band-button">
      <button v-if="!isManager" id="create-button" @click="showCreateBand = true, hideButton()">Manage a Band!</button>
    </div>
    <FollowedBands id="followed-bands"></FollowedBands>



    <div v-if="showCreateBand" id="modal-overlay">
      <div id="modal-content">
        <CreateBand id="create-band" @close="closeModal" @band-created="bandCreated" />
      </div>
    </div>
  </div>
</template>

<script>
import { useRouter } from 'vue-router';
import FollowedBands from '../components/FollowedBands.vue';
import HomeInbox from '../components/HomeInbox.vue';
import BandService from '../services/BandService';
import ManagedBands from '../components/ManagedBands.vue';
import CreateBand from '../components/CreateBand.vue';
import HomeOutbox from '../components/HomeOutbox.vue';

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
    ManagedBands,
    CreateBand,
    HomeOutbox
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

<style scoped>
.home {
  padding-top: 12vh;
  padding-right: 2rem;
  padding-left: 2rem;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-areas:
    "outbox manage-band-button manage-band-button"
    "outbox managed-bands managed-bands"
    "divider divider divider"
    "inbox followed-bands followed-bands";
  background-color: rgb(22, 22, 22);
}

#inbox {
  grid-area: inbox;
  display: flex;
  flex-direction: column;
  margin: 5px;
  border-radius: 20px;
  justify-content: center;
  align-items: center;
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

#outbox {
  grid-area: outbox;
  display: flex;
  flex-direction: column;
  margin: 5px;
  border-radius: 20px;
  justify-content: center;
  align-items: center;
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

#divider {
  grid-area: divider;
  border-top: 1px solid white;
  margin: 2.5em;
  justify-content: center;
}

button {
  padding: 10px 20px;
  /* Consistent padding */
  margin: 0 5px;
  /* Spacing between buttons */
  border: none;
  /* No border */
  border-radius: 4px;
  /* Rounded corners */
  cursor: pointer;
  /* Pointer on hover */
  font-size: 1rem;
  /* Standard font size */
  transition: background-color 0.3s, transform 0.2s;
  /* Smooth transition */
}

#manage-band-button {
  grid-area: manage-band-button;
  display: flex;
  flex-direction: row;
  margin: 5px;
  border-radius: 20px;
  justify-content: center;
  align-items: center;
}

#create-button {
  background-color: white;
  /* Dark background */
  color: black;
  /* White text */
}

#create-button:hover {
  background-color: #aaa;
  /* Lighter shade on hover */
  transform: translateY(-1px);
  /* Lift effect on hover */
}
</style>
