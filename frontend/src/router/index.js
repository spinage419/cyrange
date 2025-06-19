import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/views/Login.vue'
import store from '@/store'
import Challenges from '@/views/Challenges.vue'
import Nof from '@/views/Nof.vue'
import Home from '@/views/Home.vue'
import Register from '@/views/Register.vue'
import Profile from '@/views/Profile.vue'
import Settings from '@/views/Settings.vue'
import Vulnerability from '@/views/Vulnerability.vue'

Vue.use(VueRouter)

const routes = [
  // {
  //   path: '#',
  //   redirect: '/'
  // },
    {
      path: '/',
      component: Home
    },
    {
        path:'/challenges',
        component: Challenges,
        meta: {
          requiresAuth: true, // 这里设置了需要认证
        }
    },
    {
        path: '/vul',
        component: Vulnerability,
    },
    {
        path: '/login',
        component: Login
    },
    {
      path: '/register',
      component: Register
    },
    {
      path: '/profile',
      component: Profile
    },
    {
      path: '/settings',
      component: Settings
    },
    {
        path: "/404",
        component: Nof
    }
    ,
      {
         path:"/:path(.*)",
        redirect: "/404",
      }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
}
)

  if (localStorage.getItem('headUrl')) {
    store.commit('changeHeadimg', localStorage.getItem('headUrl'))
  }
router.beforeEach((to, from, next) => {
  const requiresAuth = to.matched.some(record => record.meta.requiresAuth)
  const isAuthenticated = store.state.auth.isAuthenticated
  store.dispatch('auth/checkAuth');
  if (requiresAuth && !isAuthenticated) {
    next('/login')
  } else if ((to.path === '/login' || to.path === '/register') && isAuthenticated) {
    next('/')
  } 
  else {
    next()
  }
})
export default router
