import type { AxiosRequestConfig, AxiosResponse } from 'axios'

export interface LPRequestInterceptors<T = AxiosResponse> {
  requestInterceptor?: (config: AxiosRequestConfig) => AxiosRequestConfig
  requestInterceptorCatch?: (err: any) => any
  responseInterceptor?: (config: T) => T
  responseInterceptorCatch?: (err: any) => any
}

export interface LPRequestConfig<T = AxiosResponse> extends AxiosRequestConfig {
  interceptors?: LPRequestInterceptors<T>
  showLoading?: boolean
}
