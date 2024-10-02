<template>
  <div class="inbox">
    <inbox></inbox>
    <MessageComponent/>
  </div>
</template>

<script>
import Inbox from '../components/Inbox.vue';
import MessageComponent from '../components/MessageComponent.vue';

export default {
  components: {
    Inbox,
    MessageComponent
  }  
};
</script>

<style>
  .inbox {
    padding-top: 12vh;
  }
</style>
