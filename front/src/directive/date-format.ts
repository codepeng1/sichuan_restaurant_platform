import type { App } from 'vue'
import type { DirectiveBinding } from 'vue'
import dayjs from 'dayjs'

export default function dateFormat(app: App) {
  const dateFormat = {
    mounted(el: HTMLElement, binding: DirectiveBinding) {
      const formatStr = typeof binding.value === 'string' ? binding.value : 'YYYY-MM-DD HH:mm:ss'
      const timestamp = el.textContent ? new Date(el.textContent).getTime() : Date.now()

      el.textContent = dayjs(timestamp).format(formatStr)
    }
  }

  app.directive('date-format', dateFormat)
}
