import type { App } from 'vue'
import dateFormat from './date-format'

export default function initDirective(app: App) {
  dateFormat(app)
}
