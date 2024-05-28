<template>
    <div class="mt-3 mr-3">
        <b-form-select v-model="selectedStatusUpdate" :options="statusOptions"> </b-form-select>
        <b-button variant="secondary" @click="updateStatus"> Save New Status</b-button>
    </div>
</template>

<script>
import { BFormSelect, BButton } from 'bootstrap-vue'
export default {
    name: 'StatusUpdater',
    components: {
        BFormSelect,
        BButton
    },
    data() {
        return {
            statusOptions: [
                { text: 'new', value: 'NEW' },
                { text: 'in progress', value: 'IN_PROGRESS' },
                { text: 'resolved', value: 'RESOLVED' },
            ],
            selectedStatusUpdate: 'NEW'
        }
    },
    props: {
        ticketId: {
            type: String,
        },
    },
    mounted () {

    },
    methods: {
        async updateStatus() {
            const paramString = new URLSearchParams({
                'status': this.selectedStatusUpdate
            })
            const url = '/ticketing-system/tickets/status/' + this.ticketId + '?' + paramString;
            const options = {
                method: 'PATCH'
            };
            try {
                await fetch(url, options);
            } catch (error) {
                console.log(error)
            }
            this.$emit('status-update');
        },

    }
}
</script>