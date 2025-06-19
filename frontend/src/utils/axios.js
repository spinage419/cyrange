import axios from 'axios';
import store from '@/store';
const axiosInstance = axios.create({
 
});
// axios拦截器示例（src/utils/axios.js）
axiosInstance.interceptors.response.use(
    response => response,
    error => {
      if (error.response.status === 40) {
        store.dispatch('logout')
        router.push('/login')
      }
      return Promise.reject(error)
    }
  )

// 请求拦截器
axiosInstance.interceptors.request.use(
  config => {
    // 从 Vuex store 中获取 token
    const token = store.state.auth.token;
    if (token) {
      // 如果 token 存在，添加到请求头部
      config.headers['token'] = token;
    }
    return config;
  },
  error => {
    // 对请求错误做些什么
    return Promise.reject(error);
  }
);


export default axiosInstance;