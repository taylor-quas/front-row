<template>
  <div class="inbox">
    <inbox></inbox>
  </div>
</template>

<script>
import Inbox from '../components/Inbox.vue';
import MessageService from '../services/MessageService';

export default {
  components: {
    Inbox
  }
};
</script>

<style>
  .inbox {
    padding-top: 12vh;
  }
</style>
