import axios from 'axios'
import type { AxiosInstance, InternalAxiosRequestConfig } from 'axios'
import type { LPRequestInterceptors, LPRequestConfig } from './type'

// import { ElLoading } from 'element-plus'
// import 'element-plus/theme-chalk/el-loading.css'
// import type { LoadingInstance } from 'element-plus/lib/components/loading/src/loading'

const DEFAULT_LOADING = true

class LPRequest {
  instance: AxiosInstance
  interceptors?: LPRequestInterceptors
  // loading?: LoadingInstance
  showLoading: boolean

  constructor(config: LPRequestConfig) {
    // 创建axios实例
    this.instance = axios.create(config)

    // 保存基本信息
    this.interceptors = config.interceptors
    this.showLoading = config.showLoading ?? DEFAULT_LOADING

    // 使用拦截器
    // 1.从config中取出的拦截器是对应的实例的拦截器
    this.instance.interceptors.request.use(
      this.interceptors?.requestInterceptor as any,
      this.interceptors?.requestInterceptorCatch
    )
    this.instance.interceptors.response.use(
      this.interceptors?.responseInterceptor,
      this.interceptors?.responseInterceptorCatch
    )

    // 2.添加所有实例都有的拦截器
    this.instance.interceptors.request.use(
      config => {
        if (this.showLoading) {
          // this.loading = ElLoading.service({
          //   lock: true,
          //   text: '数据加载中...',
          //   background: 'rgba(0,0,0,0.5)'
          // })
        }

        return config
      },
      err => {
        return err
      }
    )
    this.instance.interceptors.response.use(
      res => {
        // 将loading移除
        // this.loading?.close()

        const data = res.data
        if (data.returnCode === '-1001') {
          console.log('请求失败~, 错误信息')
        } else {
          return data
        }
      },
      err => {
        console.log(err)

        // 将loading移除
        // this.loading?.close()

        // 例子: 根据不同的HttpErrorCode显示不同的错误信息
        if (err.response.status === 404) {
          console.log('404的错误信息~')
        }
        return err
      }
    )
  }

  request<T>(config: LPRequestConfig<T>): Promise<T> {
    return new Promise((resolve, reject) => {
      // 1.单个请求对config的处理
      if (config.interceptors?.requestInterceptor) {
        config = config.interceptors.requestInterceptor(config)
      }

      // 2.判断是否需要显示loading
      if (config.showLoading === !DEFAULT_LOADING) {
        this.showLoading = config.showLoading
      }

      this.instance
        .request<any, T>(config)
        .then(res => {
          // 1.单个请求对数据的处理
          if (config.interceptors?.responseInterceptor) {
            config = config.interceptors.responseInterceptor(res) as LPRequestConfig<T>
          }

          // 2.将showLoading设置为true, 这样不会影响下一个请求
          this.showLoading = DEFAULT_LOADING

          // 3.将结果resolve返回出去
          resolve(res)
        })
        .catch(err => {
          // 将showLoading设置为true, 这样不会影响下一个请求
          this.showLoading = DEFAULT_LOADING
          reject(err)
          return err
        })
    })
  }

  get<T>(config: LPRequestConfig<T>): Promise<T> {
    return this.request<T>({ ...config, method: 'GET' })
  }

  post<T>(config: LPRequestConfig<T>): Promise<T> {
    return this.request<T>({ ...config, method: 'POST' })
  }

  delete<T>(config: LPRequestConfig<T>): Promise<T> {
    return this.request<T>({ ...config, method: 'DELETE' })
  }

  patch<T>(config: LPRequestConfig<T>): Promise<T> {
    return this.request<T>({ ...config, method: 'PATCH' })
  }

  put<T>(config: LPRequestConfig<T>): Promise<T> {
    return this.request<T>({ ...config, method: 'PUT' })
  }
}

export default LPRequest
