import { createRouter, createWebHistory } from 'vue-router'
import ProcesoLargo from '../components/ProcesoLargo.vue'

const routes = [
  {
    path: '/',
    redirect: '/proceso'
  },
  {
    path: '/proceso',
    name: 'ProcesoLargo',
    component: ProcesoLargo
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
