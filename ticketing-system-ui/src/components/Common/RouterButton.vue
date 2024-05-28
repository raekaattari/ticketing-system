<template>
    <div>
        <router-link :to="{ name: routeName }">
            <b-button size="sm"> {{buttonText}} </b-button>
        </router-link>
    </div>
</template>

<script>
import { BButton } from 'bootstrap-vue'
export default {
    name: 'RouterButton',
    components: {
        BButton
    },
  props: {
    routeName: {
      type: String,
      required: true
    },
    buttonText: {
      type: String,
      required: true
    }
  }
};
</script>