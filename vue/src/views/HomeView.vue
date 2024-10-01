<template>
  <div class="home">
    <p>You must be authenticated to see this</p>
    <div id="inbox" @click="inboxView">
      <h2>INBOX</h2>
    </div>
    <div id="bands">
      
    </div>
  </div>
</template>

<script>
import { useRouter } from 'vue-router';

export default {
  components: {},
  data() {
    const router = useRouter();
    return {
      router
    };
  },
  methods: {
    inboxView() {
      this.router.push('/inbox')
    }
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
