import { createRouter, createWebHistory } from 'vue-router'
import { localCache } from '@/utils/cache'
import { firstMenu, mapMenusToRoutes } from '@/utils/map-menus'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: '/main'
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('@/views/login/login.vue')
    },
    {
      path: '/main',
      name: 'main',
      component: () => import('@/views/main/main.vue')
    },
    {
      path: '/:pathMatch(.*)*',
      name: 'not-found',
      component: () => import('@/views/not-found/not-found.vue')
    }
  ]
})

router.beforeEach(to => {
  if (to.path !== '/login') {
    const token = localCache.getCache('token')

    if (!token) {
      return '/login'
    }
  }

  if (to.path === '/main') {
    return firstMenu.url
  }
})

// 加载动态路由
export async function addDynamicRoute() {
  const menusList = localCache.getCache('menuList')
  if (menusList) {
    const routes = await mapMenusToRoutes(menusList)
    for (const route of routes) {
      router.addRoute('main', route)
    }
  }
}

export default router
