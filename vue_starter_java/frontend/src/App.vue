<template>
<body class="has-background-light">
  <div id="app">
    
    <header>
    <navbar class="navbar">
      <div class="container">
        <div class="navbar-brand">
        <a class="navbar-item brand-text"><router-link to="/about"><img src="./views/assets/BWLogo.jpg" alt="BookWormz Photo" class="product-img"/>BookWormz</router-link></a>
          <a role="button" class="navbar-burger" data-target="navbar-menu" onclick="document.querySelector('.navbar-menu').classList.toggle('is-active');" aria-label="menu" aria-expanded="false">
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
           
          
        </a>
        </div>
      </div>

      <div class="navbar-menu">
        <div class="navbar-start">
          <a class="navbar-item brand-text"><router-link to="/thankyou"><img src="./views/assets/Logo2.jpeg" class="product-img"></router-link></a>
          <a class="navbar-item"><router-link to="/">
          Home 
          </router-link></a>
          
          <a class="navbar-item"><router-link to="/login">
          Login 
          </router-link></a>

          <a class="navbar-item" ><router-link to="/register">Register</router-link></a>
          <a class="navbar-item" > <router-link v-on:click.native= "quitter" to="/login" >Logout </router-link></a>
          
        </div>
      </div> 
    </navbar>
    
    <router-view/>
    
    </header>
  </div>
</body>
</template>

<script>
import 'bulma/css/bulma.css'
import auth from './auth';
export default {
  methods: {
  quitter(){
    auth.logout();
    alert("You have logged out!");
    
  },

}
}


</script>
<style>
@import url('https://fonts.googleapis.com/css2?family=Pacifico&display=swap');

.navbar-brand {
font-family: 'Pacifico', cursive;
font-size: 30px;
}

.navbar-menu {
 font-family: 'Comfortaa', cursive;
 
}


.product-img {
  width: auto;
  height: auto;
 
}
.brand-text{
  padding-left: 20%;

 
}
.navbar-start > .navbar-item {
  font-weight: 700;
}
h2{
    padding:10px;
    font-size:24px;
    text-transform: uppercase;
    text-align: center;
    margin: 0px;
    border-top-left-radius: 10px;
    border-top-right-radius: 10px;
}
</style>

