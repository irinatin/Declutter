<template>
<div>
<h2 class="label">Your Prizes</h2>
  <div class="table-container">
    <table class="table">
      <thead>
        <tr>
          <th class="has-text-black-bis"></th>
          <th class="has-text-black-bis">Prize Name</th>
          <th class="has-text-black-bis"> Prize Description</th>
          <th class="has-text-black-bis">Milestone</th>
          <th class="has-text-black-bis">Date Awarded</th>
        </tr>
      </thead>
      <tbody>
        
        <tr v-for="prize in prizes" v-bind:key="prize">
          <td><img class="star" src="../views/assets/star.png"/></td>
          <td>{{prize.prizeName}}</td> 
          <td>{{prize.prizeDescription}} </td>
          <td>{{prize.milestone}} </td>
          <td>{{prize.awardDate}}</td>

        </tr>
        
      </tbody>
    </table>
  </div>  
    
 </div>   
</template>

<script>
import axios from "axios";
import { eventBus } from "../main.js";

export default {
  data() {
    return {
      prizes: []
    };
  },

  created() {
    axios
      .get(`${process.env.VUE_APP_REMOTE_API}/api/getPrizesPerUser`, {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("Authorization")
        }
      })
      .then(response => {
        this.prizes = response.data;
      })
      .catch(error => {
        console.log(error + " there was an error");
      });

    eventBus.$on("refreshReadingEvent", () => {
      axios
        .get(`${process.env.VUE_APP_REMOTE_API}/api/getPrizesPerUser`, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("Authorization")
          }
        })
        .then(response => {
          this.prizes = response.data;
        })
        .catch(error => {
          console.log(error + " there was an error");
        });
  });
  eventBus.$on("refreshCreatePrize", () => {
      axios
        .get(`${process.env.VUE_APP_REMOTE_API}/api/getPrizesPerUser`, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("Authorization")
          }
        })
        .then(response => {
          this.prizes = response.data;
        })
        .catch(error => {
          console.log(error + " there was an error");
        });
  });
  }
};
</script>
<style scoped>
.star {
  width: 20px;
  height: auto;
  padding-right: 0%;
}
h2 {
  background-color:gold;
}
</style>
