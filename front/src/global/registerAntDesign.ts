import type { App } from 'vue'
import { message } from 'ant-design-vue'
import * as iconCpns from '@ant-design/icons-vue'

export default function (app: App) {
  // 全局注册图标
  Object.entries(iconCpns).map(cpn => app.component(cpn[0], cpn[1]))

  // 全局挂载
  app.config.globalProperties.$message = message
}
