<template>
  <div id="login">
    <div id="title">
      <h1 >Log in to Front Row</h1>
    </div>
    <div id="login-form">
      <form v-on:submit.prevent="login">
        <div id="alert" role="alert" v-if="invalidCredentials">
          ⚠ Incorrect username and password!
        </div>
        <div role="alert" v-if="this.$route.query.registration">
          Thank you for registering, please log in.
        </div>
        <div class="form-input-group">
          <label for="username">
            <p>Username</p>
          </label>
          <input type="text" id="username" v-model="user.username" required autofocus />
        </div>
        <div class="form-input-group">
          <label for="password">
            <p>Password</p>
          </label>
          <input type="password" id="password" v-model="user.password" required />
        </div>
        <div id="login-button">
          <button id="submit" type="submit">
            <h2>Log in</h2>
          </button>
        </div>
        <p>
          Don't have an account yet? 
          <router-link id="router-link" v-bind:to="{ name: 'register' }">Sign up.</router-link>
        </p>
      </form>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
  .form-input-group {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    margin-bottom: 1rem;
    
  }
  
  label {
    margin-right: 0.5rem;
  }
  
  h1 {
    font-size: 48px;
  }

  #login {
    padding-top: 12vh;
    display: flex;
    flex-direction: column;
    align-items: center;

  }

  #alert {
    display: flex;
    font-family: Montserrat;
    
    justify-content: center;

    background-color: red;
    color: white;
    margin: 20px;
    padding: 20px;
  }

  #router-link {
    color: white;
  }

  #title{
    margin-bottom: 40px;
  }

  input {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    box-sizing: border-box;
  }

  button h2 {
    color: black;
    
    font-family: Montserrat;
    font-size: 28px;
    font-style: normal;
    font-weight: 600;
    line-height: normal;

    margin: 12px;
  }

  button {
    color: white;
    border-radius: 24px;
    border: none;
    margin: 20px;

    width: 100%;
  }

  button:hover {
    background-color: #adadad;
  }

  #login-button {
    display: flex;
    justify-content: center;
  }

</style>