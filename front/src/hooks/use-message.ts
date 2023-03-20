import { message } from 'ant-design-vue'

interface IMessage {
  success: any
  error: any
  info: any
  warning: any
  loading: any
}

export function useMessage(): IMessage {
  return message
}
