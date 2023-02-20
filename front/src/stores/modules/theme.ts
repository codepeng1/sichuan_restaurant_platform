import { defineStore } from 'pinia'
import type { ITheme } from '../types'

// 主题类型
enum ThemeType {
  Dark = 'dark',
  Light = 'light'
}

export const useThemeStore = defineStore({
  id: 'theme',
  state: (): ITheme => ({
    type: ThemeType.Dark
  }),
  actions: {
    /**
     * 设置主题类型
     */
    setType(type: string) {
      this.type = type
    }
  }
})
