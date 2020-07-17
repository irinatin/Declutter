<template>
  <body class="has-background-primary">
    <div class="columns">
      <div class="column is-half has-text-centered">
        <h1>Welcome to BookWormz:</h1>
        <h1>A Family Reading Tracker</h1>
        <p>We help make reading fun for the whole family.</p>
        <img src="./assets/BookWorm.jpg" alt="BookWormz Photo" class="product-image">
      </div>
      <div class="column">
       <div id="login" class="text-center">
          <form class="form-signin" @submit.prevent="login">
          <span class="sign-in-message">Please Sign In</span>
          <br><br>
            <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
              Invalid username and password!
            </div>
            <div class="alert alert-success" role="alert" v-if="this.$route.query.registration">
              Thank you for registering, please sign in.
            </div>
            <span>
            <label for="username" class="login">Username</label><br>
              <input
                type="text"
                id="username"
                class="form-control"
                placeholder="Username"
                v-model="user.username"
                required
                autofocus
              /></span>
              <br><br>
              <label for="password" class="login">Password</label><br>
              <input
                type="password"
                id="password"
                class="form-control"
                placeholder="Password"
                v-model="user.password"
                required
              />
              <br>
              <button type="submit" class="sign-in-button">Sign in</button>
              <br><br>

             <span><router-link :to="{ name: 'register' }" class="need-account">Need an account?</router-link></span>
          </form>
    
        </div>
      </div>
    </div>
   
  
 
</body>
</template>

<script>
import auth from '../auth';

export default {
  name: 'login',
  components: {},
  data() {
    return {
      user: {
        username: '',
        password: '',
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/login`, {
        method: 'POST',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(this.user),
      })
        .then((response) => {
          if (response.ok) {
            return response.text();
          } else {
            this.invalidCredentials = true;
          }
        })
        .then((token) => {
          if (token != undefined) {
            if (token.includes('"')) {
              token = token.replace(/"/g, '');
            }
            auth.saveToken(token);
            this.$router.push('/');
          }
        })
        .catch((err) => console.error(err));
    },
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
/* #greeting {
  top: 10%;
  left: 50%;
  position: absolute;
  transform: translate(-50%, -50%);
} */
.form-signin{
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
}
.need-account{
  color:mediumblue;
  text-decoration:underline;
}

.product-image {
  width: 50%;
  height: auto;
  margin-left: auto;
  margin-right: auto;
}
/* .sign-in-message{
  top: 10%;
  left: 50%;
  text-align: start;

} */
/* .form-control{
  margin: 10px;
}

.sign-in-button{
  margin: 10px;

  
} */


</style>
