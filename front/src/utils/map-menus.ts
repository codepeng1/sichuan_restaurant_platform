import type { IBreadcrumb } from '@/base-ui/breadcrumb'
import type { IMenu } from '@/service/login/type'
import type { RouteRecordRaw } from 'vue-router'

let firstMenu: IMenu

/**
 * 菜单映射为路由
 */
export async function mapMenusToRoutes(userMenus: IMenu[]) {
  // 1.加载所有的routes
  const allRoutes: RouteRecordRaw[] = []
  const routeFiles = import.meta.glob<any>('../router/main/**/*.ts')

  /* 解决动态导入文件异步问题 */
  await Promise.all(
    Object.values(routeFiles).map(file => {
      return new Promise<void>(resolve => {
        file().then(res => {
          allRoutes.push(res.default)
          resolve()
        })
      })
    })
  )

  // 2.通过用户菜单添加路由
  const routes: RouteRecordRaw[] = []
  const _recurse = (userMenus: IMenu[]) => {
    for (const menu of userMenus) {
      if (menu.isLast) {
        const route = allRoutes.find(r => r.path === menu.url)
        if (route) routes.push(route)
        if (!firstMenu) firstMenu = menu
      } else {
        _recurse(menu.children)
      }
    }
  }

  _recurse(userMenus)

  return routes
}

/**
 * 当前路径映射为面包屑数组
 */
export function pathMapToBreadcrumb(userMenus: IMenu[], currentPath: string) {
  const breadcrumb: IBreadcrumb[] = []

  pathMapToMenu(userMenus, currentPath, breadcrumb)

  return breadcrumb
}

/**
 * 当前路径映射为菜单对象
 */
export function pathMapToMenu(
  userMenus: IMenu[],
  currentPath: string,
  breadcrumb?: IBreadcrumb[]
): any {
  for (const menu of userMenus) {
    if (menu.children?.length) {
      const findMenu = pathMapToMenu(menu.children, currentPath)
      if (findMenu) {
        breadcrumb?.push(menu)
        breadcrumb?.push(findMenu)
        return findMenu
      }
    }
    if (menu.url === currentPath) {
      return menu
    }
  }
}

export { firstMenu }
