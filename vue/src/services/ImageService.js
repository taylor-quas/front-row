import axios from 'axios';

export default {
  
  getGallery(bandName){
    return axios.get(`/${bandName}/gallery`)
  },

  uploadImage(url) {
    return axios.post(`/band/gallery-upload`, { url });
  }

}
