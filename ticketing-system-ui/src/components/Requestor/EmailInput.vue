<template>
    <div>
        <b-form-input id="email-input" v-model="email" :state="isValid"
            aria-describedby="input-live-help input-email-feedback" placeholder="Enter your email" trim
            @input="validateEmail"></b-form-input>

        <b-form-invalid-feedback id="input-email-feedback">
            Enter a valid email
        </b-form-invalid-feedback>
    </div>
</template>

<script>
import { BFormInput, BFormInvalidFeedback } from 'bootstrap-vue'
import { EventBus } from '../../event-bus';

export default {
    name: 'EmailInput',
    components: {
        BFormInput,
        BFormInvalidFeedback,
    },
    data() {
        return {
            email: ''
        };
    },
    computed: {
        isValid() {
            const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
            return emailRegex.test(this.email);
        }
    },
    created() {
        EventBus.$on('clear-values', this.clearValues);
    },
    beforeDestroy() {
        EventBus.$off('clear-values', this.clearValues);
    },
    methods: {
        validateEmail() {
            if (this.isValid) {
                EventBus.$emit('update-input', { component: 'email', value: this.email })
            } else {
                EventBus.$emit('update-input', { component: 'email', value: null })
            }
        },
        clearValues() {
            this.email = '';
        },
    }
}
</script>

<style scoped>
/* Add any custom styles here */
</style>