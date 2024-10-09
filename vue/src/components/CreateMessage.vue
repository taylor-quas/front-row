<template>
    <main class="main-container">
        <header class="header">
            <h2 id="title">New Message</h2>
        </header>
        <div class="content">
            <select v-model="selectedBand" id="selected-band">
                <option disabled value="">Select Band Sending Message</option>
                <option v-for="band in managedBands" :key="band.bandId" :value="band">{{ band.band.bandName }}</option>
            </select>

            <textarea id="message-box" type="text" placeholder="Hey fans..." v-model="message.messageContent"
                rows="5"></textarea>

            <label for="expiration-date">Expiration Date</label>
            <input type="date" id="expiration-date" name="expiration-date" v-model="expirationDate">
            
            <label for="expiration-time">Expiration Time</label>
            <input type="time" id="expiration-time" name="expiration-time" v-model="expirationTime">

            <div>
                <button @click="sendMessage()" id="send-button">SEND MESSAGE</button>
                <button @click="cancel()" id="cancel-button">CANCEL</button>
            </div>
        </div>

    </main>
</template>

<script>
import BandService from '../services/BandService';
import MessageService from '../services/MessageService';

export default {
    data() {
        return {
            managedBands: [],
            selectedBand: '',
            expirationDate: '',
            expirationTime: '23:59',
            message: {
                messageContent: '',
                messageTimeSent: '',
                messageTimeExpiration: '',
                messageSender: null,
            },
            band: null
        }
    },
    created() {
        this.fetchManagedBands();
        this.expirationDate = this.getDefaultExpirationDate();
    },
    methods: {
        fetchManagedBands() {
            BandService.getManagedBands().then(response => {
                this.managedBands = response.data;
            })
                .catch(error => {
                    console.error(error);
                });
        },
        cancel() {
            this.$emit('close');
        },
        mounted() {
            this.debugger();
        },
        getDefaultExpirationDate() {
            const today = new Date();
            today.setDate(today.getDate() + 14);
            return today.toISOString().split('T')[0];
        },

        sendMessage() {
            console.log('Selected Band: ', this.selectedBand);

            const currentTime = new Date().toISOString();
            this.message.messageTimeSent = currentTime;

            const expirationDateTime = `${this.expirationDate}T${this.expirationTime}:00`;
            this.message.messageTimeExpiration = expirationDateTime;

            this.message.messageSender = this.selectedBand.band.bandId;

            console.log('Message: ', this.message);

            MessageService.sendMessage(this.message).then(response => {
                if (response.status === 201) {
                    this.$emit('message-sent');
                }
            })
                .catch(error => {
                    console.error(error);
                });
        },

    }

}
</script>


<style scoped>
#title {
    text-align: center;
    color: white;
    /* Better contrast for the title */
    border-radius: 5px;
    /* Rounded corners */
    margin: 0.5em;
}

label {
    color: white;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.main-container {
    display: flex;
    flex-direction: column;
    background-color: rgb(27, 27, 27);
    /* Light background */
    justify-content: center;
    border: 1px solid white;
    border-radius: 20px;
    width: 90%;
    margin-top: 1em;
    margin-bottom: 1em;

}

.content {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin: 1em;
}

#selected-band {
    margin-bottom: 1em;
    width: fit-content;
    min-width: 40%;
    border-radius: 10px;
    padding: 5px;
}

#message-box {
    margin-bottom: 1em;
    width: 90%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 10px;
    background-color: #f4f4f4;
    font-size: 1rem;
    resize: vertical;
    overflow: auto;
    min-height: 100px;
}

#expiration-date {
    margin-bottom: 1em;
    width: 90%;
}

#expiration-time {
    margin-bottom: 1em;
    width: 90%;
}

input {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    /* Light border */
    border-radius: 10px;
    /* Rounded corners */
    background-color: #f4f4f4;
    /* Light grey background for inputs */
    transition: border-color 0.3s;
    /* Smooth transition */
    font-size: 1rem;
    /* Standard font size */
}

.button-container {
    display: flex;
    justify-content: center;
    /* Center buttons */
    margin-top: 1rem;
    /* Space above buttons */
}

button {
    padding: 10px 20px;
    /* Consistent padding */
    margin: 0.5em;
    /* Spacing between buttons */
    border: none;
    /* No border */
    border-radius: 10px;
    /* Rounded corners */
    cursor: pointer;
    /* Pointer on hover */
    font-size: 1rem;
    /* Standard font size */
    transition: background-color 0.3s, transform 0.2s;
    /* Smooth transition */
}

#send-button {
    background-color: white;
    color: rgb(27, 27, 27);
}

#send-button:hover {
    background-color: #aaa;
    /* Lighter shade on hover */
    transform: translateY(-1px);
    /* Lift effect on hover */
}

#cancel-button {
    background-color: white;
    /* Light grey */
    color: rgb(27, 27, 27);
    /* Dark text */
}

#cancel-button:hover {
    background-color: #aaa;
    /* Darker grey on hover */
    transform: translateY(-1px);
    /* Lift effect on hover */
}
</style>