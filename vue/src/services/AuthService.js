import axios from 'axios';

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API
});

export default {

  login(user) {
    return http.post('/login', user)
  },

  register(user) {
    return http.post('/register', user)
  },
<<<<<<< HEAD
=======

>>>>>>> 05e58cdf0083fe9dc170c1d55afc05bf7c8f36e3
}
