import axios from 'axios';

export default {
  
    getGenresToManage(){
        return axios.get('/manage-genres')
    },
    
    addGenre(genre){
        return axios.post('/add-genre', genre)
    }

}
