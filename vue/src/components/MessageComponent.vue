<template>
  <div class="messageComponent">
    <h3>Message from {{ message.bandName }}</h3>
    <h4>{{ new Date(message.message.messageTimeSent).toLocaleString('en-US', {month: 'long', day: '2-digit', year: 'numeric', hour: 'numeric', minute:'2-digit'}) }}</h4>
    <p class="content">{{ message.message.messageContent }}</p>
    <p class="expiration">This message will expire on {{ new Date(message.message.messageTimeExpiration).toLocaleString('en-US', {month: 'long', day: '2-digit', year: 'numeric', hour: 'numeric', minute:'2-digit'}) }}</p>

  </div>
</template>

<script>
export default {
    props: {
        message: {
            type: Object,
            required: true
        }
    }

}
</script>

<style>

.messageComponent {
    display: flex;
    flex-direction: column;
    margin: 2%;
    border: 1px solid black;
    border-radius: 20px;
    padding: 2%;
    align-content: center;
    justify-content: center;

}

h3 {
    font-family:'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
    font-size: 25px;
    display: flex;
}

h4 {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    font-size: 20px;
    display: flex;
    justify-content: flex-start;

}

p {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.content {
    font-size: 18px;
    justify-content: flex-start;
}

.expiration {
    font-style: italic;
    justify-content: flex-start;
}



</style>