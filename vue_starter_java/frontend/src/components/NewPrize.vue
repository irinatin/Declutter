<template>
  <div>
    <div class="table-container">
    <table class="table">
      <thead>
        <tr>
          <th></th>
          <th></th>
          <th></th>
          <th></th>
          <th>Prize Name</th>
          <th>Description</th>
          <th>Milestone</th>
          <th>Quantity of Prizes</th>
          <th>Start Date</th>
          <th>End Date</th>
        </tr>
      </thead>
      <tr v-for="prize in prizes" :key="prize.id">
        <td>
          <a href="#" v-if="isParent" v-on:click.prevent="editPrize(parseInt(prize.prizeId))">
            <i class="fas fa-edit"></i>
          </a>
        </td>
        <td></td>
        <td>
          <a
            class="delete"
            href="#"
            v-if="isParent"
            v-on:click.prevent="deletePrize(parseInt(prize.prizeId))"
          ></a>
        </td>
        <td></td>
        <td>{{prize.prizeName}}</td>
        <td>{{prize.prizeDescription}}</td>
        <td>{{prize.milestone}}</td>
        <td>{{prize.numOfPrizes}}</td>
        <td>{{prize.startDate}}</td>
        <td>{{prize.endDate}}</td>
      </tr>
    </table>
</div>
  </div>
</template>

<script>
import axios from "axios";
import "@fortawesome/fontawesome-free/css/all.css";
import "@fortawesome/fontawesome-free/js/all.js";
import { eventBus } from "../main.js";

export default {
  props: {},

  data() {
    return {
      isParent: false,
      prizes: []
    };
  },

  methods: {
    editPrize(id) {
      this.$emit("editPrize", id);
    },
    deletePrize(id) {
      this.$emit("deletePrize", id);
      const index = this.prizes.map(prize => prize.prizeId).indexOf(id);
      this.prizes.splice(index, 1);
    }
  },
  created() {
    axios
      .get(`${process.env.VUE_APP_REMOTE_API}/api/getPrizes`, {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("Authorization")
        }
      })
      .then(response => {
        this.prizes = response.data;
        if (response.data != null) {
          this.noPrizes = false;
        }
      })
      .catch(error => {
        console.log(error + " there was an error");
      });
      
    axios
      .get(`${process.env.VUE_APP_REMOTE_API}/api/getCurrentUser`, {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("Authorization")
        }
      })
      .then(response => {
        this.currentUser = response.data;
        if (this.currentUser.role === "user") {
          this.isParent = true;
        }
      })
      .catch(error => {
        console.log(error + " there was an error");
      });

    eventBus.$on("refreshReadingEvent", () => {
      axios
        .get(`${process.env.VUE_APP_REMOTE_API}/api/getPrizes`, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("Authorization")
          }
        })
        .then(response => {
          this.prizes = response.data;
          if (response.data != null) {
            this.noPrizes = false;
          }
        })
        .catch(error => {
          console.log(error + " there was an error");
        });
    });
  }
};
</script>



<style>
.yellow {
  background: #ffff00;
}
.salmon {
  background-color: #ff6666;
}
</style>