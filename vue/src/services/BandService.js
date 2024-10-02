import axios from 'axios';

export default {
  getGenres() {
    return axios.get('/genres');
  },

  getBands() {
    return axios.get(`/bands`);
  },

  getFollowedBands() {
    return axios.get('/my-bands');
  }

}
