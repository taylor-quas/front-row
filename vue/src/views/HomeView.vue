<template>
  <div class="home">
    <!-- <p>You must be authenticated to see this</p> -->
    <div id="inbox" @click="inboxView">
      <Inbox></Inbox>
    </div>
    <div id="followed-bands">
      <followed-bands-vue></followed-bands-vue>
    </div>    
  </div>
</template>

<script>
import { useRouter } from 'vue-router';
import FollowedBandsVue from '../components/FollowedBands.vue';
import Inbox from '../components/Inbox.vue';

export default {
  data() {
    const router = useRouter();
    return {
      router,
      username: '',
      isFocused: false,
    };
  },
  components: {
    FollowedBandsVue,
    Inbox
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
    padding-top: 12vh;
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    grid-template-areas: 
      "inbox followed-bands followed-bands";
  }

  #inbox {
    grid-area: inbox;
    margin: 5px;
    border-radius: 20px;
  }

  #followed-bands {
    grid-area: followed-bands;
    margin: 5px;
    border-radius: 20px;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    align-items: flex-start;
  }

  
</style>
