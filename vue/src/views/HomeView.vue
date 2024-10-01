<template>
  <div class="home">
    <p>You must be authenticated to see this</p>
    <div id="inbox" @click="inboxView">
      <h2>INBOX</h2>
    </div>
    <div>
      <followed-bands-vue></followed-bands-vue>
    </div>    
  </div>
</template>

<script>
import { useRouter } from 'vue-router';
import FollowedBandsVue from '../components/FollowedBands.vue';

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
    FollowedBandsVue
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
  }

  #inbox {
    border:1px solid black;

  }

  
</style>
