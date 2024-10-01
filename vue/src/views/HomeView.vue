<template>
  <div class="home">
    <Header>
      <h1>Welcome, {{ username }}</h1>
      <section>
        <button @cick="search" id="search" placeholder="Search">Search</button>
        <input type="search" id="search" placeholder="Search bands..." />
        <button type="button" id="logout" value="Logout" @click="logout">Logout</button>
        <p>You must be authenticated to see this</p>
      </section>
    </Header>
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
    logout() {
      this.$store.commit('LOGOUT');
      this.$router.push('/login');
    },
  },
};
</script>
