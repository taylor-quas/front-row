<template>
  <div class="home">
    <Header>
      <h1>Welcome, {{ username }}</h1>
      <section>
        <button @cick="search" id="search" placeholder="Search">Search</button>
        <input type="search" id="search" placeholder="Search bands..." @focus="isFocused=true" @blur="isFocused=false" />
        <p>You must be authenticated to see this</p>
      </section>
    </Header>

    <div class="main-content">
      <aside v-if="showInbox" class="inbox">
        <h2>Inbox</h2>
        <ul>
          <li v-for="message in inboxMessages" :key="message.id">{{ message.content }}</li>
        </ul>
      </aside>
      
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
export default {
  components: {

  },
  data() {
    return {
      username: '',
      isFocused: false,
    };
  },
  methods: {

  },
  computed: {
    inboxCount() {
      return this.inboxMessages.length;
    },
  }
};
</script>

<style>

</style>
