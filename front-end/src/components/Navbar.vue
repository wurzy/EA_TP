<template>
  
  <div id="app">
  <v-app id="inspire">
    <v-layout row justify-center>
      <v-toolbar app color="#57a2bf" class="hidden-xs-and-down">
        <v-img
        class="logo"
        max-height="40"
        max-width="40"
        src="../assets/redu.png"
        ></v-img>

        <v-toolbar-items>
         <v-btn class="navbar-button">
            <router-link class="hyperlink" to="/">Home</router-link>
         </v-btn>
         <v-btn class="navbar-button">
            <router-link class="hyperlink" to="/recursos">Recursos</router-link>
         </v-btn>
         <v-btn class="navbar-button">
            <router-link class="hyperlink" to="/utilizadores">Utilizadores</router-link>
         </v-btn>
        </v-toolbar-items>
        <v-spacer></v-spacer>
        <v-toolbar-items>
         <v-btn class="navbar-button">
            <router-link class="hyperlink" to="/">Perfil</router-link>
         </v-btn>
         <v-btn class="navbar-button">
            <router-link class="hyperlink" to="/">Logout</router-link>
         </v-btn>
        </v-toolbar-items>
        </v-toolbar>
    </v-layout>
  </v-app>
  </div>

</template>

<script>
//import HelloWorld from "./components/HelloWorld";

export default {
  name: "App",
  data () {
    return {
      dialog: false,
      nav: [
        {
          icon: 'home',
          text: 'Home',
          link: '../views/Recurso.vue',
          title: '../views/Recurso.vue',
          active: true
        },
        {
          icon: 'recursos',
          text: 'Recursos',
          //title: '',
          active: false
        },
        {
          icon: 'utilizadores',
          text: 'Utilizadores',
          //title: '',
          active: false
        },
        {
          icon: 'perfil',
          text: 'Perfil',
          //title: '',
          active: false
        },
        {
          icon: 'logout',
          text: 'Logout',
          //title: '',
          active: false
        }
      ]
    }
  }
};
</script>

<style>
.navbar-button {
    background-color: #57a2bf !important; 
    padding-top: 10px !important; 
    box-shadow: 0px 0px 0px 0px rgb(0 0 0 / 20%) !important;
}

.hyperlink {
    text-decoration: none;
    color: #212121 !important;
}

.logo{
    margin-left: 10px;
    margin-right: 10px;
    margin-top: 10px;
}
</style>

