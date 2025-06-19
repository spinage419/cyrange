import axios from 'axios';
import store from '@/store';
// 创建 axios 实例
const apiClient = axios.create({
  baseURL: process.env.VUE_APP_API_BASE_URL || 'http://cyrange.xin/api', // 从环境变量中获取 API 基础地址
  timeout: 10000, // 请求超时时间
  headers: {
    'Content-Type': 'application/json',
  },
});
apiClient.interceptors.request.use(
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
/**
 * 获取用户基本信息
 * @returns {Promise} 包含用户信息的 Promise 对象
 */
export const getUserProfile = () => {
  return apiClient.get('/user/profile')
    .then(response => {
      if (response.status === 200) {
        return response.data;
      }
      throw new Error('Failed to fetch user profile');
    })
    .catch(error => {
      console.error('Error fetching user profile:', error);
      throw error;
    });
};

/**
 * 获取用户解题统计信息
 * @returns {Promise} 包含解题统计信息的 Promise 对象
 */
export const getSolveStats = () => {
  return apiClient.get('/user/stats')
    .then(response => {
      if (response.status === 200) {
        return response.data;
      }
      throw new Error('Failed to fetch solve stats');
    })
    .catch(error => {
      console.error('Error fetching solve stats:', error);
      throw error;
    });
};

/**
 * 获取用户解题记录
 * @param {Object} params 分页参数
 * @param {number} params.page 当前页码
 * @param {number} params.pageSize 每页条数
 * @returns {Promise} 包含解题记录的 Promise 对象
 */
export const getSubmissions = (params) => {
  return apiClient.get('/user/submissions', { params })
    .then(response => {
      if (response.status === 200) {
        return response.data;
      }
      throw new Error('Failed to fetch submissions');
    })
    .catch(error => {
      console.error('Error fetching submissions:', error);
      throw error;
    });
};