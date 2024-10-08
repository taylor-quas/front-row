<template>
    <div class="outbox">
        <Outbox />
    </div>
</template>

<script>
import Outbox from '../components/Outbox.vue';

export default {
    components: {
        Outbox
    }
};
</script>

<style scoped>
.inbox {
    padding-top: 12vh;
    background-color: rgb(22, 22, 22);
}
</style>