import axios from 'axios';

export default {
  getGallery(bandName){
    return axios.get(`/${bandName}/gallery`)
  }
}