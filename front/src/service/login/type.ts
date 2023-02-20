export interface IAccount {
  username: string
  password: string
  remember?: boolean
}

export interface ILoginResult {
  id: number
  name: string
  token: string
}

export interface IUserInfo {
  id: number
  username: string
  mobile: string
  wx: string
  createTime: Date
}

export interface IMenu {
  id: number
  name: string
  url: string
  icon: string
  code: string
  level: number
  parentId: number
  children: IMenu[]
  isLast: boolean
}
