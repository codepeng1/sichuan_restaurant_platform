import lpRequest from '../index'

import type { IUserInfo, IAccount, ILoginResult, IMenu } from './type'
import type { IDataType } from '../types'

enum LoginAPI {
  AccountLogin = '/login',
  UserInfo = '/user/',
  Menu = '/menu/'
}

// 登录
export function requestAccountLogin(account: IAccount) {
  return lpRequest.post<IDataType<ILoginResult>>({
    url: LoginAPI.AccountLogin,
    data: account
  })
}

// 获取用户信息
export function requestUserInfo(id: number) {
  return lpRequest.get<IDataType<IUserInfo>>({
    url: LoginAPI.UserInfo + id
  })
}

// 获取用户菜单
export function requestUserMenu(id: number) {
  return lpRequest.get<IDataType<IMenu[]>>({
    url: LoginAPI.Menu + 'user/' + id
  })
}
