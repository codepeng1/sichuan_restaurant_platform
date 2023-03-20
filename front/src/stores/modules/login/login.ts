import { requestAccountLogin, requestUserInfo, requestUserMenu } from '@/service/login/login'
import { defineStore } from 'pinia'
import type { IAccount } from '@/service/login/type'
import type { IDataType } from '@/service/types'
import { localCache } from '@/utils/cache'
import { addDynamicRoute } from '@/router'

export const useLoginStore = defineStore('login', () => {
  const startLogin = (account: IAccount) => {
    return new Promise<IDataType>((resolve, reject) => {
      requestAccountLogin(account).then(async res => {
        if (res.data) {
          // 是否记住密码
          if (account.remember) {
            localCache.setCache('remember', account.remember).setCache('account', account)
          } else {
            localCache.removeCache('remember').removeCache('account')
          }

          // 保存token
          localCache.setCache('token', res.data.token)

          // 保存用户信息
          const userInfo = await requestUserInfo(res.data.id)
          localCache.setCache('userInfo', userInfo.data)

          // 保存用户菜单
          const menuList = await requestUserMenu(res.data.id)
          localCache.setCache('menuList', menuList.data)

          // 加载动态路由
          await addDynamicRoute()

          resolve(res)
        } else {
          reject(res)
        }
      })
    })
  }

  return { startLogin }
})
