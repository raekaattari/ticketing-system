<template>
    <div class="requestor-home">
        <h1> Requestor Home</h1>
        <div class='homepage'>
            <body> To create a ticket please enter your name, email, and a description of your issue </body>
            <br>

            <NameInput />
            <EmailInput />
            <DescriptionInput />

            <b-button size="sm" variant="primary mt-3" :disabled="isDisabled" @click="createNewTicket"> Create
                Ticket</b-button>

            <br> <br>
            <RouterButton route-name="HomePage" button-text="Return Home"> </RouterButton>

            <AlertResponse :variant="apiCall" :message="popupMessage" :dismissible="true" :show-alert="showTop" />
        </div>
    </div>
</template>

<script>
import { BButton } from 'bootstrap-vue'
import RouterButton from '../Common/RouterButton.vue'
import AlertResponse from '../Common/Alert.vue'
import DescriptionInput from './DescriptionInput.vue'
import NameInput from './NameInput.vue'
import EmailInput from './EmailInput.vue'
import { EventBus } from '../../event-bus';

export default {
    name: 'RequestorHome',
    components: {
        BButton,
        RouterButton,
        AlertResponse,
        DescriptionInput,
        NameInput,
        EmailInput
    },
    data() {
        return {
            popupMessage: '',
            isDisabled: true,
            apiCall: '',
            showTop: false,
            values: {
                descriptionText: null,
                email: null,
                name: null
            },
        }
    },
    created() {
        EventBus.$on('update-input', this.handleValueEmitted);
    },
    beforeDestroy() {
        EventBus.$off('update-input', this.handleValueEmitted);
    },
    methods: {
        handleValueEmitted({ component, value }) {
            this.values[component] = value;
            if (this.values.descriptionText && this.values.email && this.values.name) {
                this.isDisabled = false;
            } else {
                this.isDisabled = true
            }
        },
        finalize() {
            this.showTop = true
            EventBus.$emit('clear-values');
        },
        handleSuccess(data) {
            this.apiCall = 'success'
            this.popupMessage = 'Successfully created ticket'
            console.log('(To be sent in an email) Ticket created with the following information: ' + data)
        },
        handleFailure(error) {
            this.apiCall = 'failure'
            this.popoverMessage = 'Unable to create ticket'
            console.log(error)
        },
        createDataObject() {
            return {
                'name': this.values.name,
                'email': this.values.email,
                'description': this.values.descriptionText
            };
        },
        createRequestOptions(data) {
            return {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            };
        },
        async createNewTicket() {
            const url = '/ticketing-system/tickets'
            const data = this.createDataObject();
            const options = this.createRequestOptions(data);

            try {
                await fetch(url, options)
                this.handleSuccess(options.body)
            } catch (error) {
                this.handleFailure(error)
            }
            this.finalize()
        },
    }
}
</script>
<style>
.requestor-home {
    margin: 80px;
}
</style>
