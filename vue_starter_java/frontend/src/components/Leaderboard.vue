<template>
  <div>
    <h2 class="label">Monthly Leaderboard</h2>
    <div>
      <table class="table is-fullwidth">
        <thead>
          <tr>
            <th class="has-text-centered">BookWorm</th>
            <th class="has-text-centered">Total Minutes</th>
          </tr>
        </thead>
        <tbody>
          <p class="is-small">
          <tr v-for="user in leaderboard" v-bind:key="user.userName">
            <td class="has-text-centered">{{user.firstName}} {{user.lastName}}</td>
            <td class="has-text-centered">{{user.totalReading}}</td>
          </tr>
          </p>
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
      leaderboard: {}
    };
  },

  methods: {},

  created() {
    axios
      .get(`${process.env.VUE_APP_REMOTE_API}/api/getLeaderboard`, {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("Authorization")
        }
      })
      .then(response => {
        this.leaderboard = response.data;
      })
      .catch(error => {
        console.log(error + " there was an error");
      });

    eventBus.$on("refreshReadingEvent", () => {
      axios
        .get(`${process.env.VUE_APP_REMOTE_API}/api/getLeaderboard`, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("Authorization")
          }
        })
        .then(response => {
          this.leaderboard = response.data;
        })
        .catch(error => {
          console.log(error + " there was an error");
        });
    });
  }
};
</script>



<style scoped>
h2 {
  background-color:mediumpurple;
}
</style>
