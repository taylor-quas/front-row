import axios from 'axios';

export default {
  search(searchTerm) {
    return axios.post(`/search/${searchTerm}`)
  },
  
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
