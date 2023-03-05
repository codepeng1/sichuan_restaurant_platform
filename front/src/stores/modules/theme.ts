import { localCache } from '@/utils/cache'
import { defineStore } from 'pinia'
import { ref } from 'vue'

// 主题类型
enum ThemeType {
  Dark = 'dark',
  Light = 'light'
}

export const useThemeStore = defineStore('theme', () => {
  const theme = localCache.getCache('theme')
  if (theme === 'dark') {
    document.documentElement.removeAttribute('theme')
  } else {
    document.documentElement.setAttribute('theme', theme)
  }
  const type = ref<string>(theme ?? ThemeType.Dark)
  const setType = (value: string) => {
    type.value = value
  }

  return { type, setType }
})
