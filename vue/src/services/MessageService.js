import axios from 'axios';

export default {
  getUserInbox() {
    return axios.get('/inbox');
  }

}
