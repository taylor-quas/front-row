<template>
  <div id="bg-image">
    <div id="anonymous-home">
      <div>
        <h1>FRONT ROW</h1>
        <h2>IT'S WHERE YOU WANT TO BE</h2>
      </div>
      <div id="description">
        <p>Tailored to strengthen the engagement between bands and their fans.<br>Stay up to date on all your favorites.</p>
        <div id="buttons">
          <button>
            <h2>
              <router-link class="router-button" v-bind:to="{ name: 'login' }">Login</router-link>
            </h2>
          </button>
          <button>
            <h2>
              <router-link class="router-button" v-bind:to="{ name: 'register' }">Create Account</router-link>
            </h2>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      
    }
  }
};
</script>

<style scoped>
  #bg-image {
    height: 100%;
    background-image: url('src/assets/BR.png');
    background-size: fill;
    background-position: center;

    display: flex; 
    flex-direction: column;
    align-items: center;

    height: 100vh;

    z-index: 50;
  }

  #buttons{
    display: flex;
    flex-direction: row;
    align-content: center;
    margin: auto;
    
  }

  button {
    color: white;
    border-radius: 24px;
    border: none;
    margin: 20px;
  }

  #description {
    display: flex;
    flex-direction: column;
    padding: 20px;
  }

  #anonymous-home {
    padding-top: 5vh;
    display: flex;
    width: 90%;
    flex-direction: column;
    align-items: center;
    gap: 50vh;
    margin: 0;
  }

  .router-button {
    color: black;
    text-decoration: none;
    
    font-family: Montserrat;
    font-size: 28px;
    font-style: normal;
    font-weight: 600;
    line-height: normal;

    margin-left: 10px;
    margin-right: 10px;
  }

  button:hover {
    background-color: #adadad;
  }

  #buttons button h2 {
    margin: 10px;
  }

</style>
