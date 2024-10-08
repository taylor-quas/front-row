<template>
  <div class="inbox">
    <inbox />
  </div>
</template>

<script>
import Inbox from '../components/Inbox.vue';

export default {
  components: {
    Inbox
  }
};
</script>

<style scoped>
.inbox {
  padding-top: 12vh;
  background-color: rgb(22, 22, 22);
}
</style>
