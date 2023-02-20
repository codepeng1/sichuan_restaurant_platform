// service 统一出口
import LPRequest from './request'
import { BASE_URL, TIME_OUT } from './request/config'
import { message } from 'ant-design-vue'
import { localCache } from '@/utils/cache'
import router from '@/router'

const lpRequest = new LPRequest({
  baseURL: BASE_URL,
  timeout: TIME_OUT,
  interceptors: {
    requestInterceptor: config => {
      // 携带token的拦截
      const token = localCache.getCache('token')
      if (token) {
        // Bearer token 只是一种格式
        config.headers!.Authorization = `Bearer ${token}`
      }

      return config
    },
    requestInterceptorCatch: err => {
      return err
    },
    responseInterceptor: config => {
      return config
    },
    responseInterceptorCatch: err => {
      // token error
      if (err.response.status === 401) {
        message.error(err.response.data.errorMsg)
        localCache.removeCache('token')
        router.push({
          path: '/login'
        })
      }
      return err
    }
  }
})

export default lpRequest
