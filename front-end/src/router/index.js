import Vue from 'vue'
import VueRouter from 'vue-router'
import Utilizadores from '../views/Utilizadores.vue'
import Recursos from '../views/Recursos.vue'
import Home from '../views/Home.vue'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/recursos',
    name: 'Recursos',
    component: Recursos
  },
  {
    path: '/utilizadores',
    name: 'Utilizadores',
    component: Utilizadores
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
