import axios from 'axios';

export default {
  uploadImage(url) {
    return axios.post(`/band/gallery-upload`, url);
  }

}
