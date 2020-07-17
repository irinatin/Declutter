<template>
  <body class="has-background-primary">
     
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        This family does no exist. Do you want to create a new family?
      </div>
<div class="columns">
<div class="column is-half has-text-centered">
        <h1>You're almost there!</h1>
        
        <p>We just need a few more details to finish creating your account.</p>
        <img src="./assets/BookWorm.jpg" alt="BookWormz Photo" class="product-image">
      </div>
  <div class="column">
<form class="form-register" @submit.prevent="userregister">
     <span class="h3 mb-3 font-weight-normal">Create Account</span>
     <br><br>
     <span>
     <label for="firstName">First Name</label>
     <br>
      <input
        type="text"
        id="firstName"
        class="form-control"
        placeholder="First Name"
        v-model="userinfo.firstName"
        required
        autofocus
      /></span>
      <br><br>

      <label for="lastName">Last Name</label><br>
      <input
        type="text"
        id="lastName"
        class="form-control"
        placeholder="Last Name"
        v-model="userinfo.lastName"
        required
        autofocus
      />
      <br><br>
      
      <span v-if="userinfo.newFamily">
      <label for="familyName">Create New Family</label>
      <input
        type="text"
        id="familyName"
        class="form-control"
        placeholder="Family Name"
        v-model="userinfo.familyName"
        required
        autofocus
      />
      </span>
      <br>

      <span v-if="!userinfo.newFamily">
        <label for="newFamilyName">Join Existing Family</label>
      <input
        type="text"
        id="newFamilyName"
        class="form-control"
        placeholder="Family Name"
        v-model="userinfo.familyName"
        required
        autofocus
      />
      </span>
      <br>

      <a v-if="userinfo.newFamily" href="#" v-on:click.prevent="userinfo.newFamily = false" class="buttons">Join Existing Family
      </a>
      <br>

      <a v-if="!userinfo.newFamily" href="#" v-on:click.prevent="userinfo.newFamily = true" class="buttons">Make New Family 
      </a>

      <br>
      <button class="create-account-button" type="submit">
        Submit User Info
      </button>
      <br>
</form>
</div>
</div>
  </body>
</template>

<script>
import axios from 'axios';

export default {
  name: 'userinfo',
data() {
    return {
      userinfo: {
        firstName: '',
        lastName: '',
        familyName: '',
        newFamily: true
      },
      registrationErrors: false
    };
  },
  methods: {
    userregister(){
      axios.post(`${process.env.VUE_APP_REMOTE_API}/userinfo`, this.userinfo)
      
      .then(response => {
        if(response.status === 200){
          this.$router.push({ path: '/login', query: { registration: 'success' } });
          console.log('200');
        }
        else {
          console.log('else fired')
            this.registrationErrors = true;
            console.log(this.registrationErrors);
         }
      })
      .catch(err => {
        this.registrationErrors = true;
        console.log(err);
      })
    }
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Comfortaa:wght@600&display=swap');
.columns {
  font-family: 'Comfortaa', cursive;
  font-size: 20px;
}

h1 {
  font-size: 200%;
}
body {
  padding: 100px 100px;
  padding-bottom: 400px;
  background-size: cover;
  background-position: center;
  font-family: sans-serif;
  height: 100%;
}
.form-register {
  width: 70%;
  height: auto;
  background: rgba(0, 0, 0, 0.55);
  color: #fff;
  box-sizing: border-box;
  box-shadow: 0px 0px 8px rgba(0,0,0,0.9);
  padding-left: 20%;
  padding-top: 20%;
  padding-right: 15%;
  padding-bottom: 20%;
  margin-left: 20px;
}
.create-account-button{
  margin: 10px;
  
}
.product-image {
  width: 50%;
  height: auto;
  margin-left: auto;
  margin-right: auto;
}
.buttons{
  color:mediumblue;
  text-decoration:underline;
}


</style>