import Vue from 'vue'
import VueRouter from 'vue-router'
import HomePage from '@/views/HomePage.vue'
import RequestorHome from '@/components/Requestor/RequestorHome.vue'
import AdminHome from '@/components/Admin/AdminHome.vue'


Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'HomePage',
        component: HomePage
    },
    {
        path: '/request/',
        name: 'RequestorHome',
        component: RequestorHome
    },
    {
        path: '/admin/',
        name: 'AdminHome',
        component: AdminHome
    }
]


const router = new VueRouter({
    routes
  })

export default router
