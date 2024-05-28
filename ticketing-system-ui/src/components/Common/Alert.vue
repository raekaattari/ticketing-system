<template>
    <b-alert :variant="variant" :dismissible="dismissible" v-model="localShowAlert" 
    @dismissed="handleDismiss"
        class="position-fixed fixed-top m-0 rounded-0" style="z-index: 2000;">
        {{ message }}
    </b-alert>
</template>

<script>
import { BAlert } from 'bootstrap-vue'
export default {
    name: 'AlertResponse',
    components: {
        BAlert
    },
    props: {
        variant: {
            type: String,
            required: true
        },
        dismissible: {
            type: Boolean,
            default: true
        },
        message: {
            type: String,
            required: true
        },
        showAlert: {
            type: Boolean,
            default: true
        }
    },
    data() {
        return {
            localShowAlert: false
        }
    },
    watch: {
        showAlert(newVal) {
            this.localShowAlert = newVal;
        }
    },
    methods: {
        handleDismiss() {
            this.$emit('update:showTop', false);
        }
    }
};
</script>

<style scoped>
/* Add any scoped styles here if needed */
</style>