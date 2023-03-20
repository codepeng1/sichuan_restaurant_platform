import type { App } from 'vue'
import * as iconCpns from '@ant-design/icons-vue'

export default function (app: App) {
  // 全局注册图标
  Object.entries(iconCpns).map(cpn => app.component(cpn[0], cpn[1]))
}
