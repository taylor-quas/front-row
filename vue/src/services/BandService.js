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
  },

  getBand(bandName){
    return axios.get(`/${bandName}`)
  },

  create(band) {
    return axios.post('/bands', band);
  },

  followBand(bandId) {
    return axios.post(`/subscribe/${bandId}`);
  },

}
