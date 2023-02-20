import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import router, { addDynamicRoute } from './router'
import globalRegister from './global'

import './assets/css/index.styl'
import './assets/icon'
import 'ant-design-vue/dist/antd.css' // or 'ant-design-vue/dist/antd.less'
import 'lib-flexible/flexible.js'

const app = createApp(App)

app.use(globalRegister)
app.use(createPinia())

addDynamicRoute().then(() => {
  app.use(router)
  app.mount('#app')
})
