<template>
  <v-app id="app">
    <router-view></router-view>
  </v-app>
</template>

<script>

export default {

  name: 'App',
   data() {
    return {
    }
  }
};
</script>


<style>

@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+JP&display=swap');

html, body {
  font-family: 'Noto Sans JP', sans-serif;
}

#app {
  font-family: 'Noto Sans JP', sans-serif;
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


