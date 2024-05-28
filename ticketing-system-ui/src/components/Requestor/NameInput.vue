<template>
    <div>
        <b-form-input id="name-input" v-model="name" :state="isValid"
            aria-describedby="input-live-help input-name-feedback" placeholder="Enter your full name" trim
            @input="validateName"></b-form-input>
            
        <b-form-invalid-feedback id="input-name-feedback">
            Enter at least 3 characters
        </b-form-invalid-feedback>
    </div>
</template>

<script>
import { BFormInput, BFormInvalidFeedback } from 'bootstrap-vue'
import { EventBus } from '../../event-bus';

export default {
    name: 'NameInput',
    components: {
        BFormInput,
        BFormInvalidFeedback,
    },
    data() {
        return {
            name: ''
        };
    },
    computed: {
        isValid() {
            return this.name.length >= 3;
        }
    },
    created() {
        EventBus.$on('clear-values', this.clearValues);
    },
    beforeDestroy() {
        EventBus.$off('clear-values', this.clearValues);
    },
    methods: {
        validateName() {
            if (this.isValid) {
                EventBus.$emit('update-input', {component: 'name', value: this.name})
            } else {
                EventBus.$emit('update-input', {component: 'name', value: null})
            }
        },
        clearValues() {
            this.name = '';
        },
    }
}
</script>

<style scoped>
/* Add any custom styles here */
</style>