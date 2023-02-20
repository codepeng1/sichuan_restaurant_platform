import { getCurrentInstance } from 'vue'

interface IMessage {
  success: any
  error: any
  info: any
  warning: any
  loading: any
}

export function useMessage(): IMessage {
  return getCurrentInstance()?.appContext.config.globalProperties.$message
}
