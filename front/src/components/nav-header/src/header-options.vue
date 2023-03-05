<script lang="ts" setup>
import LpThemeToggle from '@/base-ui/theme-toggle'
import UserInfo from './user-info.vue'
import { useThemeStore } from '@/stores/modules/theme'
import { localCache } from '@/utils/cache'

const themeStore = useThemeStore()
const hanleThemeToggleChange = (type: string) => {
  themeStore.setType(type)
  if (type === 'dark') {
    document.documentElement.removeAttribute('theme')
  } else {
    document.documentElement.setAttribute('theme', type)
  }
  localCache.setCache('theme', type)
}
</script>

<template>
  <div class="header-options">
    <lp-theme-toggle @change="hanleThemeToggleChange" />

    <a-badge dot class="bell_wrapper">
      <BellOutlined title="通知消息" />
    </a-badge>

    <user-info />
  </div>
</template>

<style scoped lang="stylus">
.header-options
  color var(--text-color)
  font-size .375rem
  display flex
  align-items center
  justify-content space-between
  margin-right .25rem
  .bell_wrapper
    color var(--text-color)
    font-size .375rem
    margin 0 .3125rem
    cursor pointer
</style>
