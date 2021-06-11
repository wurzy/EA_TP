<template>
  <v-app id="app">
    <Navbar/>
    <router-view></router-view>
  </v-app>
</template>

<script>
import Navbar from './components/Navbar.vue' 

export default {

  name: 'App',
  data() {
    return {
    }
  },
  components:{
    Navbar
  }
};
</script>


<style>

@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+JP&display=swap');

html, body {
  font-family: Arial, Helvetica, sans-serif;
}

#app {
  font-family: Arial, Helvetica, sans-serif;
  height: 64px;
}

body {
    overflow: auto;
    height: 100vh;
} 

html { overflow-y: hidden }


.dropdown {
  overflow: hidden;
}

</style>


