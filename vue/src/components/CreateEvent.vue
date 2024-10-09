<template>
    <main class="main-container">
        <header>
            <h2 id="title">Create Event</h2>
        </header>
        <div class="content">
            <label for="event-date">Event Date</label>
            <input type="date" id="event-date" name="event-date" v-model="eventDate">

            <label for="event-time">Event Time</label>
            <input type="time" id="event-time" name="event-time" v-model="eventTime">

            <label for="event-venue">Event Venue</label>
            <input type="text" id="event-venue" name="event-venue" v-model="event.event.eventVenue">

            <label for="event-address">Event Address</label>
            <input type="text" id="event-address" name="event-address" v-model="event.event.eventAddress">

            <label for="event-name">Event Name (Optional)</label>
            <input type="text" id="event-name" name="event-name" v-model="event.event.eventName">

            <button @click="createEvent()" id="create-event-button">CREATE EVENT</button>
            {{ event }}
        </div>
    </main>
</template>

<script>
import BandService from '../services/BandService';

export default {
    data() {
        return {
            eventDate: '',
            eventTime: '',
            event: {
                event: {
                    eventTime: '',
                    eventVenue: '',
                    eventAddress: '',
                    eventHost: '',
                    eventName: '',
                },
                bandName: this.$route.params.bandName
            },
            bandId: null,
        }
    },
    created() {
        this.fetchBand();
    },
    methods: {
        fetchBand() {
            BandService.getBand(this.event.bandName).then(response => {
                this.bandId = response.data.band.bandId;
            })
                .catch(error => {
                    console.error(error);
                });
        },
        createEvent() {
            const eventTime = `${this.eventDate}T${this.eventTime}:00`;
            this.event.event.eventTime = eventTime;

            this.event.event.eventHost = this.bandId;

                BandService.addEvent(this.event).then(response => {
                    console.log(response.data);
                    this.$emit('event-created');
                })
                .catch(error => {
                    console.error(error);
                });
        }
    }
}
</script>

<style scoped>
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
    color: white;
}

.content {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin: 1em;
}
</style>