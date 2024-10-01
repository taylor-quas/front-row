<template>
  <div class="home">
    <p>You must be authenticated to see this</p>
    <div id="inbox" @click="inboxView">
      <h2>INBOX</h2>

    </div>
    <div id="bands">
      <div class="bands-list">
        <h2>Your Bands</h2>
        <ul>
          <li v-for="band in bands" :key="band.id">{{ band.name }}</li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import { useRouter } from 'vue-router';

export default {
  data() {
    const router = useRouter();
    return {
      router,
      username: '',
      isFocused: false,
    };
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
  }
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
