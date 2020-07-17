<template>
<div>
  <h2 class="label">Prizes</h2>
  <div class="create_new_prize">
    <div
      class="alert alert-danger"
      role="alert"
      v-if="formErrors"
    >There were problems creating this prize.</div>
    <div v-if="noPrizes">There are no prizes entered! Add a prize!!!</div>
    <button v-if="!showForm" v-on:click="showFormButton()">Create New Prize</button>
    <button v-if="showForm" v-on:click="hideFormButton()">Hide Form</button>
    <div v-if="showForm">
      <form class="new_prize_form" @submit.prevent="addNewPrize">
        <label for="prize_name">Prize Name</label>
        <br>
        <input
          type="text"
          id="prize_name"
          name="prize_name"
          placeholder="Enter Prize Name"
          v-model="prizeinfo.prizeName"
          required
          autofocus
        />
        <br>

        <label for="description">Description</label>
        <br>
        <input
          type="text"
          id="description"
          name="description"
          placeholder="Enter Prize Description"
          v-model="prizeinfo.prizeDescription"
          required
          autofocus
        />
        <br>

        <label for="milestone">Milestone (minutes reading)</label>
        <br>
        <input
          type="text"
          id="milestone"
          name="milestone"
          placeholder="Enter Milestone"
          v-model="prizeinfo.milestone"
          required
          autofocus
        />
        <br>

        <label for="user_group">Parent/Child Prize</label>
        <select id="users" v-model="prizeinfo.userGroup">
          <option>Parent</option>
          <option>Child</option>
        </select>  

        <br>

        <label for="max_prizes">Prize Cap</label>
        <br>
        <input
          type="text"
          id="numOfPrizes"
          name="numOfPrizes"
          placeholder="Enter Prize Cap"
          v-model="prizeinfo.numOfPrizes"
          required
          autofocus
        />
        <br>

        <label for="start_date">Start Date</label>
        <br>
        <input
          type="text"
          id="start_date"
          name="start_date"
          placeholder="yyyy-mm-dd"
          v-model="prizeinfo.startDate"
          required
          autofocus
        />
        <br>

        <label for="end_date">End Date</label>
        <br>
        <input
          type="text"
          id="end_date"
          name="end_date"
          placeholder="yyyy-mm-dd"
          v-model="prizeinfo.endDate"
          required
          autofocus
        />
        <br>

        <button class="add_prize_button" type="submit">Add Prize</button>
        <br>
      </form>
    </div>

  </div>
</div>
</template>

<script>
import axios from "axios";

export default {
  props: {
    apiURL: String
  },

  data() {
    return {
      showForm: false,
      prizeinfo: {
        id: "",
        prizeName: "",
        prizeDescription: "",
        milestone: "",
        userGroup: "",
        numOfPrizes: "",
        startDate: "",
        endDate: ""
      },
      formErrors: false,
      noPrizes: false
    };
  },

  methods: {
    addNewPrize() {
      axios
        .post(
          `${process.env.VUE_APP_REMOTE_API}/api/addPrize`,
          this.prizeinfo,
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("Authorization")
            }
          }
        )
        // eslint-disable-next-line no-unused-vars
        .then(response => {
          axios
            .get(`${process.env.VUE_APP_REMOTE_API}/api/getPrizeList`, {
              headers: {
                Authorization: "Bearer " + localStorage.getItem("Authorization")
              }
            })
            .then(response => {
              this.prizeList = response.data;
            })
            .catch(error => {
              this.noPrizes = true;
              console.log(error + " there was an error");
            });
        })
        .catch(error => {
          console.log(error + " there was an error");
        });
    },
    showFormButton() {
      this.showForm = true;
    },
    hideFormButton() {
      this.showForm = false;
    }
  },


};
</script>



<style>
</style>
