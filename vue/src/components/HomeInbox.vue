<template>
    <div id="inbox">
      <h2>My Messages</h2>
      <div id="message-card" v-for="message in sortedMessages" :key="message.messageId">
        <MiniMessageComponent :message="message" :isRead="message.isRead" @markAsRead="handleMarkAsRead"/>
      </div>
    </div>
  </template>
  
  <script>
  import MessageService from "../services/MessageService.js";
  import BandService from "../services/BandService.js";
  import MiniMessageComponent from "../components/MiniMessageComponent.vue";
  
  export default {
    components: {
      MiniMessageComponent
    },
      data() {
        return {
          messages: [],
          followedBands: [],
          selectedBand: 'all',
          selectedSort: 'newest'
        };
      },
      computed: {
        filteredMessages() {
          const currentTime = new Date();
  
          return this.messages.filter(message => {
            const expirationTime = new Date(message.message.messageTimeExpiration);
            const notExpired = expirationTime > currentTime;
            const bandMatches = this.selectedBand === 'all' || message.bandName === this.selectedBand;
  
            return notExpired && bandMatches;
          });
        },
        sortedMessages() {
          let sortedMessages = this.filteredMessages;
  
          const removeThe = (name) => {
            return name.trim().toLowerCase().startsWith('the ')
              ? name.trim().slice(4).trim()
              : name.trim();
          }
  
          if (this.selectedSort === 'newest') {
            sortedMessages = sortedMessages.sort((a, b) => {
              return new Date(b.message.messageTimeSent) - new Date(a.message.messageTimeSent);
            });
          } else if (this.selectedSort === 'oldest') {
            sortedMessages = sortedMessages.sort((a, b) => {
              return new Date(a.message.messageTimeSent) - new Date(b.message.messageTimeSent);
            });
          } else if (this.selectedSort === 'band-name') {
            sortedMessages = sortedMessages.sort((a, b) => {
              return removeThe(a.bandName).localeCompare(removeThe(b.bandName));
            });
          } else if (this.selectedSort === 'band-name-reverse') {
            sortedMessages = sortedMessages.sort((a, b) => {
              return removeThe(b.bandName).localeCompare(removeThe(a.bandName));
            });
          }
  
          return sortedMessages;
  
        }
      },
      created() {
        this.fetchInboxMessages();
        BandService.getFollowedBands().then(response => {
              this.followedBands = response.data;
          })
          .catch(error => {
              console.error(error);
          });
  
      },
      watch: {
        followedBands() {
          this.fetchInboxMessages();
        }
      },
      methods: {
        handleMarkAsRead(messageId) {
          MessageService.markAsRead(messageId).then(() => {
            const message = this.messages.find(message => message.message.messageId === messageId);
            if (message) {
            message.isRead = true;
            }
          })
          .catch(error => {
            console.error(error);
          });
        },

        fetchInboxMessages() {
          MessageService.getUserInbox().then(response => {
              this.messages = response.data;
          })
          .catch(error => {
              console.error(error);
          });
        }
      }
  }
  </script>
  
  <style>
  
  #inbox {
    display: flex;
    flex-direction: column;
    /* justify-content: center; */
    align-items: center;
    flex-basis: 100%;
    width: 100%;
    font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
  }
  
  </style>