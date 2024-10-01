import axios from 'axios';

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API
});

export default {

  search(searchTerm) {
    return http.post(`/search/${searchTerm}`)
  },
  
  getGenres() {
    return http.get('/genres');
  },

  getBands() {
    return http.get(`/bands`);
  },

  getFollowedBands() {
    return http.get('/my-bands');
  }

}
