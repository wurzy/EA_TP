import Vue from 'vue'
import VueRouter from 'vue-router'
import Utilizadores from '../views/Utilizadores.vue'
import Recursos from '../views/Recursos.vue'
import Publicacao from '../views/Publicacao.vue'
import Home from '../views/Home.vue'
import Perfil from '../views/Perfil.vue'


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
  },
  {
    path: '/perfil',
    name: 'Perfil',
    component: Perfil
  },
  {
    path: '/publicacao',
    name: 'Publicacao',
    component: Publicacao
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
