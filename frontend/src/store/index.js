
import Vue from 'vue'
import Vuex from 'vuex'
import auth from './auth.js'

Vue.use(Vuex)

// const state = {
// //   token: '',
// token: localStorage.getItem('token') || null,
// }

// const mutations = {
//     SET_TOKEN(state, token) {
//        state.token = token; 
//        localStorage.setItem('token', token);
//     }
   
//   };

export default new Vuex.Store({
  state: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    auth // 注册auth模块
  }
})
