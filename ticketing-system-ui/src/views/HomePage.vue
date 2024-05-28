<template>
  <div>
    <div class="homepage"></div>
    <h1> Ticketing System</h1>
    <RouterButton route-name="AdminHome" button-text="Admin Home"> </RouterButton>
    <RouterButton route-name="RequestorHome" button-text="Requestor Home"> </RouterButton>


  </div>
</template>



<script>
import RouterButton from '../components/Common/RouterButton.vue'

export default {
  name: 'HomePage',
  components: {
    RouterButton
  }
}
</script>