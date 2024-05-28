<template>
    <div>
        <b-form-textarea id="input-text-state" v-model="descriptionText" :state="isValid"
            placeholder="Enter a description of your problem..." rows="3"
            @input="validateDescription"></b-form-textarea>

        <b-form-invalid-feedback id="input-text-state">
            Enter at least 10 characters
        </b-form-invalid-feedback>
    </div>
</template>

<script>
import { BFormTextarea, BFormInvalidFeedback } from 'bootstrap-vue'
import { EventBus } from '../../event-bus';

export default {
    name: 'DescriptionInput',
    components: {
        BFormTextarea,
        BFormInvalidFeedback,
    },
    props: {
    },
    data() {
        return {
            descriptionText: ''
        };
    },
    computed: {
        isValid() {
            return this.descriptionText.length >= 10;
        }
    },
    created() {
        EventBus.$on('clear-values', this.clearValues);
    },
    beforeDestroy() {
        EventBus.$off('clear-values', this.clearValues);
    },
    methods: {
        validateDescription() {
            if (this.isValid) {
                EventBus.$emit('update-input', {component: 'descriptionText', value: this.descriptionText})
            }  else {
                EventBus.$emit('update-input', {component: 'descriptionText', value: null})

            }
        },
        clearValues() {
            this.descriptionText = '';
        },
    }
}
</script>

<style scoped>
/* Add any custom styles here */
</style>