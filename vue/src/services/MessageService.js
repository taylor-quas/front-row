import axios from 'axios';

export default {
  getUserInbox() {
    return axios.get('/inbox');
  },

  markAsRead(messageId) {
    return axios.put(`/inbox/${messageId}/read`);
  }

}
