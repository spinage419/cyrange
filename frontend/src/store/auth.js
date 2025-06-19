export default {
  namespaced: true,
    state: {
      id: null,
      userName: null, 
      token: null,
      exp: null,
      isAuthenticated: false
    },
    mutations: {
      SET_USER(state, payload) {
        state.id=payload.id
        state.userName = payload.userName
        state.token = payload.token
        state.isAuthenticated = true
        localStorage.setItem('auth', JSON.stringify(payload))
      },
      CLEAR_AUTH(state) {
        state.id = null       
        state.userName = null  
        state.token = null
        state.exp = null
        state.isAuthenticated = false
        localStorage.removeItem('auth')
      }
    },
    actions: {
      checkAuth({ commit }) {
        const auth = JSON.parse(localStorage.getItem('auth'))
        if (auth && auth.exp > Date.now() / 1000) {
          commit('SET_USER', auth)
        } else {
          commit('CLEAR_AUTH')
        }
      },
      logout({ commit }) {
        commit('CLEAR_AUTH')
      }
    }
  }