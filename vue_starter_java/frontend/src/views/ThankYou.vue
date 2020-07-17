<template>
 <div class="has-background-white">
    <section class="hero is-primary">
      <div class="container">
          <div class="columns">
          <div class="column hero-body">
            <h1 class="title">Thank You, Tech Elevator Pittsburgh!</h1>
          </div>
        </div>
      </div>
    
      <img class="photo" src="./assets/Confetti.jpg" style="width:100%;"/>


      <!-- <img class="photo" src="./assets/Marissa.png" style="width:25em;"/> -->
      
    </section>
 </div>
</template>

<script>

import axios from 'axios';

export default {
    name: 'thankyou',

    data() {
        return {

        }
    }
}
</script>

<style scoped>

    .photo {
        size: 50px;
    }


</style>

