import Vue from "vue";
import router from "./router";
import ElementUI from 'element-ui';
import App from './App.vue';
import 'element-ui/lib/theme-chalk/index.css';
import store from "./store";
import 'typeface-orbitron'
import axiosInstance from './utils/axios.js'
import './assets/main.css'
Vue.use(router);
Vue.use(ElementUI);
Vue.use(store);
Vue.config.productionTip = false;
Vue.prototype.$axios = axiosInstance;
axiosInstance.defaults.baseURL = 'http://cyrange.xin';
axiosInstance.defaults.headers.post['Contenst-Type'] = 'application/json;'
new Vue({
  router,
  store,
  render: function (h) { return h(App) },
}).$mount('#app')

