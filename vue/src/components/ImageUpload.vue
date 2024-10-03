<template>
  <div>
    <button @click="openWidget">Upload Image</button>
  </div>
</template>

<script>
import ImageService from '../services/ImageService';

export default {
  methods: {
    openWidget() {
      const widget = window.cloudinary.createUploadWidget(
        {
          cloudName: 'dfxvcpj23',
          uploadPreset: 'system_uploader_1e2ddab171f769b9_c929acf69d8900b2aaba20b2a4ea4d9459'
        },
        (error, result) => {
          if (!error && result && result.event === 'success') {
            console.log("Done uploading", result.info.secure_url)
            this.sendImageToServer(result.info.secure_url);
          }
        }
      );
      widget.open();
    },
    
    sendImageToServer(imageUrl) {
      ImageService.uploadImage(imageUrl)
        .then(response => {
          console.log(response.data);
        })
        .catch(error => {
          console.error(error.response.data);
        });
    }
  }
}
</script>