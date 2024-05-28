<template>
    <div>
        <b-card-group deck class="mt-3">
            <b-card v-if="commentCreatedAt" bg-variant="Default" text-variant="black" :header=commentCreatedAt class="text-left">
                <b-card-text> {{ commentText }}</b-card-text>
            </b-card>
        </b-card-group>
    </div>
</template>

<script>
import { BCardGroup, BCard, BCardText } from 'bootstrap-vue'
export default {
    name: 'CommentModal',
    components: {
        BCardGroup,
        BCard,
        BCardText
    },
    data() {
        return {
        }
    },
    props: {
        commentText: {
            type: String,
        },
        commentCreatedAt: {
            type: String
        }
    },
    methods: {

    }
}
</script>