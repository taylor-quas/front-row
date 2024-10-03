<template>
  <div class="home">
    <HomeInbox id="inbox" @click="inboxView"></HomeInbox>
    <followed-bands-vue id="followed-bands"></followed-bands-vue>
  </div>
</template>

<script>
import { useRouter } from 'vue-router';
import FollowedBandsVue from '../components/FollowedBands.vue';
import HomeInbox from '../components/HomeInbox.vue';

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
