<template>
  <div class="home">
    <!-- <p>You must be authenticated to see this</p> -->
    <div id="inbox" @click="inboxView">
      <Inbox></Inbox>
    </div>
    <div id="followed-bands">
      <followed-bands-vue></followed-bands-vue>
    </div>
    <div id="manager-view" v-if="isManager">
      <ManagerView></ManagerView>
    </div>    
    <HomeInbox id="inbox" @click="inboxView"></HomeInbox>
    <followed-bands-vue id="followed-bands"></followed-bands-vue>
  </div>
</template>

<script>
import { useRouter } from 'vue-router';
import FollowedBandsVue from '../components/FollowedBands.vue';
import Inbox from '../components/Inbox.vue';
import ManagerView from './ManagerView.vue';
import HomeInbox from '../components/HomeInbox.vue';

export default {
  data() {
    const router = useRouter();
    return {
      router,
      username: '',
      isFocused: false,
      isManager: false,
    };
  },
  components: {
    FollowedBandsVue,
    Inbox,
    ManagerView,
    HomeInbox,
  },
  methods: {
    inboxView() {
      this.router.push('/inbox')
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
