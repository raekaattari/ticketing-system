<template>
    <div class="mt-3 mr-3">
        <b-form-textarea id="textarea" v-model="commentText" placeholder="Enter new comment..." rows="3"
                max-rows="6"></b-form-textarea>
        <br>
        <b-button variant="primary" @click="createNewComment"> Create New Comment</b-button>
    </div>
</template>

<script>
import { BButton, BFormTextarea } from 'bootstrap-vue'
export default {
    name: 'NewComment',
    components: {
        BFormTextarea,
        BButton
    },
    data() {
        return {
            commentText: ''
        }
    },
    props: {
        ticketId: {
            type: String,
        },
    },
    mounted() {
        console.log(this.ticketId)

    },
    methods: {
        async createNewComment() {
            await this.sendNewComment() 
            this.$emit('new-comment');
            this.commentText = ''

        },
        async sendNewComment() {
            const url = '/ticketing-system/comments'
            const data = {
                'ticketId': this.ticketId,
                'commentText': this.commentText
            };
            const options = {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data),
            };
            try {
                await fetch(url, options)
            } catch (error) {
                console.log(error)
            }
        },

    }
}
</script>