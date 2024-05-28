<template>
    <div class="admin-home">
        <h1> Admin Home</h1>
        <div>
            <b-form-select label="sort by" v-model="selectedStatus" :options="options"></b-form-select>
            <b-table variant="#ff0000" sticky-header :items="tickets" :fields="fields" select-mode="single" responsive="sm" ref="selectableTable"
                selectable @row-selected="onRowSelected">
                <b-thead head-variant="dark"></b-thead>
            </b-table>
            <ticket-modal v-if="modalShow" :selected-ticket-id="selectedTicket.ticketId" :modal-show="modalShow"
                @update-modal-show="handleModalShowUpdate" />
            <RouterButton route-name="HomePage" button-text="Return Home"> </RouterButton>

        </div>
    </div>
</template>

<script>
import { BTable, BFormSelect } from 'bootstrap-vue'
import TicketModal from './TicketModal.vue'
import RouterButton from '../Common/RouterButton.vue'

export default {
    name: 'AdminHome',
    components: {
        BTable,
        BFormSelect,
        TicketModal,
        RouterButton
    },
    data() {
        return {
            sortBy: 'name',
        sortDesc: false,
            tickets: [],
            selectedStatus: 'ALL',
            fields: [
                { key: 'ticketId', sortable: false },
                { key: 'name', sortable: false },
                { key: 'email', sortable: false },
                { key: 'description', sortable: false },
                { key: 'status', sortable: false },
                { key: 'createdAt', sortable: false }
            ],
            options: [
                { text: 'new', value: 'NEW' },
                { text: 'in progress', value: 'IN_PROGRESS' },
                { text: 'resolved', value: 'RESOLVED' },
                { text: 'all', value: 'ALL' }
            ],
            selectedTicket: {},
            modalShow: false,
        }
    },
    mounted() {
        this.getAllTickets()
    },
    watch: {
        selectedStatus: function () {
            if (this.selectedStatus === 'ALL') {
                this.getAllTickets()
            } else {
                this.getTicketsByStatus()
            }
        }
    },
    methods: {
        async getAllTickets() {
            try {
                const response = await fetch('/ticketing-system/tickets')
                if (!response.ok) {
                    throw new Error('Failed to fetch tickets')
                }
                this.tickets = await response.json()
            } catch (error) {
                console.log(error)
            }
        },
        async getTicketsByStatus() {
            try {
                const response = await fetch('/ticketing-system/tickets/status/' + this.selectedStatus)
                if (!response.ok) {
                    throw new Error('Failed to fetch tickets')
                }
                this.tickets = await response.json()
            } catch (error) {
                console.log(error)
            }
        },
        async onRowSelected(items) {
            this.selectedTicket = items[0]
            this.modalShow = true
        },
        handleModalShowUpdate() {
            this.modalShow = false;
        }
    }
}
</script>

<style scoped>
.admin-home {
    margin: 80px;
}
</style>
