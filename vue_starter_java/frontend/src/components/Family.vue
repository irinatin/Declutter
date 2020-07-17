<template>
  <div>
    <h2 class="label pink">Family Members</h2>
    <div v-for="user in family" v-bind:key="user.firstName">{{user.firstName}} {{user.lastName}} ({{user.role}})</div>
    <form class="form-register" @submit.prevent="registerChild">
        <br>
        <button class="button is-info is-small" v-if="!addChildForm && isParent" v-on:click="showForm" type="submit">Add Child</button>
        <br />
        <br>
        <button class="button is-info is-small" v-if="addChildForm && isParent" v-on:click="hideForm" type="submit">Hide Form</button>
        
      <div>
      </div>
<br />
      <span v-if="addChildForm">
        <label for="username">Username: </label>
        <input
          type="text"
          id="username"
          class="form-control"
          placeholder="Child Username"
          v-model="child.username"
          required
          autofocus
        />
      </span>
      <br>
      <span v-if="addChildForm">
        <label for="password">Password: </label>
        <input
          type="text"
          id="password"
          class="form-control"
          placeholder="Child Password"
          v-model="child.password"
          required
          autofocus
        />
      </span>
      <br>
      <span v-if="addChildForm">
        <label for="firstName">First Name: </label>
        <input
          type="text "
          id="firstName"
          class="form-control"
          placeholder="First Name"
          v-model="child.firstName"
          required
          autofocus
        />
      </span>
      <br>
      <span v-if="addChildForm">
        <label for="lastName">Last Name: </label>
        <input
          type="text"
          id="lastName"
          class="form-control"
          placeholder="Last Name"
          v-model="child.lastName"
          required
          autofocus
        />
      </span>

      <br />
      <button v-if="addChildForm" class="button is-info is-small" type="submit">Submit Child Info</button>
     
    </form>
  </div>
</template>



<script>
import {eventBus} from "../main.js";
import axios from "axios";
//import 'bulma/css/bulma.css'
export default {
  props: {
  },
  data() {
    return {
      child: {
        username: "",
        password: "",
        firstName: "",
        lastName: ""
      },
      family: {},
      currentUser: {
        userId: "",
        userName: "",
        password: "",
        confirmPassword: "",
        passwordMatching: "",
        role: ""
      },
      isParent: false,

      addChildForm: false
    };
  },

  methods: {
    showForm() {
      this.addChildForm = true;
    },
    hideForm() {
      this.addChildForm = false;
    },
    getFamilyList() {
      axios
        .get(`${process.env.VUE_APP_REMOTE_API}/api/getFamilyList`, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("Authorization")
          }
        })
        .then(response => {
          this.family = response.data;
        })
        .catch(error => {
          console.log(error + " there was an error");
        });
    },
    registerChild() {
      axios
        .post(`${process.env.VUE_APP_REMOTE_API}/api/addChild`, this.child, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("Authorization")
          }
        })
        // eslint-disable-next-line no-unused-vars
        .then(response => {
          eventBus.$emit("refreshFamily");
          this.addChildForm = false;
          this.getFamilyList();
          this.child.username = "";
          this.child.password = "";
          this.child.firstName = "";
          this.child.lastName = "";

        })
        .catch(err => {
          this.registrationErrors = true;
          console.log(err);
        });
    }
  },
  created() {
    axios
      .get(`${process.env.VUE_APP_REMOTE_API}/api/getFamilyList`, {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("Authorization")
        }
      })
      .then(response => {
        this.family = response.data;
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
  }
};
</script>



<style scoped>
h2 {
  background-color:#ff6666;
}
</style>
