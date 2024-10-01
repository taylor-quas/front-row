import axios from 'axios';

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API
});

export default {

  search(searchTerm) {
    return http.post(`/search/${searchTerm}`)
  }
  
}
