<template>
    <div>
        <b-modal v-if="selectedTicket.ticketId" hide-backdrop content-class="shadow" title="Ticket Viewer" id="modal-xl"
            size="xl" v-model="localModalShow" hide-footer @close="closeModal">
            <b-card-group deck class="mt-3">
                <b-card bg-variant="primary" text-variant="white" :header=ticketHeader class="text-left">
                    <b-card-text>
                        Creation Date: {{ selectedTicket.createdAt }}, Status: {{ selectedTicket.status }} <br>
                        Description: {{ selectedTicket.description }}
                    </b-card-text>
                </b-card>
            </b-card-group>
            <div v-if="ticketComments.length > 0">
                <div v-for="comment in ticketComments" :key="comment.commentId">
                    <CommentModal :comment-text="comment.commentText" :comment-created-at="comment.createdAt" />
                </div>
            </div> <br>
            <NewComment @new-comment="getAllComments" :ticket-id="this.selectedTicket.ticketId"></NewComment>
            <StatusUpdater @status-update="reloadModal" :ticket-id="this.selectedTicket.ticketId"> </StatusUpdater>
        </b-modal>
    </div>
</template>
<script>
import { BModal, BCardGroup, BCard, BCardText } from 'bootstrap-vue'
import CommentModal from './CommentModal.vue'
import StatusUpdater from './StatusUpdater.vue'
import NewComment from './NewComment.vue'
export default {
    name: 'TicketModal',
    components: {
        BModal,
        BCardGroup,
        BCard,
        BCardText,
        CommentModal,
        StatusUpdater,
        NewComment
    },
    props: {
        selectedTicketId: {
            type: String,
            required: true
        },
        modalShow: {
            type: Boolean,
            required: true
        }
    },
    data() {
        return {
            ticketComments: [],
            localModalShow: this.modalShow,
            commentText: '',
            selectedTicket: {},
            statusOptions: [
                { text: 'new', value: 'NEW' },
                { text: 'in progress', value: 'IN_PROGRESS' },
                { text: 'resolved', value: 'RESOLVED' },
            ],
            selectedStatusUpdate: 'NEW',
        }
    },
    async mounted() {
        await this.getTicketById()
        await this.getAllComments()
    },
    computed: {
        ticketHeader() {
            if (!this.selectedTicket) return ''
            return `Name: ${this.selectedTicket.name}, Email: ${this.selectedTicket.email}`
        }
    },
    watch: {
        modalShow(newVal) {
            this.localModalShow = newVal
        },
        localModalShow(newVal) {
            if (newVal) {
                console.log('in local modal show')
                this.onModalShow();
            }
            this.$emit('update:modalShow', newVal);
        }
    },
    methods: {
        async getAllComments() {
            try {
                const response = await fetch('/ticketing-system/comments/' + this.selectedTicket.ticketId)
                if (!response.ok) {
                    throw new Error('Failed to fetch comments for ticketId ' + this.selectedTicket.ticketId)
                }
                this.ticketComments = await response.json()
            } catch (error) {
                console.log(error)
            }
        },
        closeModal() {
            this.localModalShow = false
            this.$emit('update-modal-show');
        },
        reloadModal() {
            this.getTicketById()
        },
        async getTicketById() {
            try {
                const response = await fetch('/ticketing-system/tickets/' + this.selectedTicketId)
                if (!response.ok) {
                    throw new Error('Failed to fetch ticket')
                }
                const data = await response.json()
                this.selectedTicket = data
            } catch (error) {
                console.log(error)
            }
        },
    }
}
</script>