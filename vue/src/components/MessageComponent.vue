<template>
  <div class="messageComponent">
    <div id="band-date">
        <h3>{{ message.bandName }}</h3>
        <h4>{{ new Date(message.message.messageTimeSent).toLocaleString('en-US', {month: 'long', day: '2-digit', year: 'numeric', hour: 'numeric', minute:'2-digit'}) }}</h4>
    </div>

    
    <p class="content">{{ message.message.messageContent }}</p>
    <p class="expiration">This message will expire on {{ new Date(message.message.messageTimeExpiration).toLocaleString('en-US', {month: 'long', day: '2-digit', year: 'numeric', hour: 'numeric', minute:'2-digit'}) }}</p>

  </div>
</template>

<script>
export default {
    data() {
        return {
            isViewed: false
        }
    },
    props: {
        message: {
            type: Object,
            required: true
        },
        band: {
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
    margin: 5px;
    background-color: rgb(27, 27, 27);
    color: white;
    padding: 5px;
    align-content: center;
    justify-content: center;
    width: 90%;
    border-bottom: 1px solid darkgray;
    border-top: 1px solid darkgray;

}

#band-date {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    justify-content: center;
    margin: 0.5em;
    text-align: left;
    
}

#band-date h3 {
    font-family:'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
    font-size: 2em;
    text-align: left;
}

#band-date h4 {
    font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
    font-size: 1em;
    font-style: italic;
    margin-top: 0.2em;
    text-align: left;
    
}

h3 {
    font-family:'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
    font-size: 25px;
    text-align: right;
}

p {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.content {
    font-size: 18px;
    justify-content: flex-start;
    text-align: left;

}

.expiration {
    font-size: 14px;
    font-style: italic;
    justify-content: flex-start;
    color: darkgray;
    text-align: left;
}



</style>