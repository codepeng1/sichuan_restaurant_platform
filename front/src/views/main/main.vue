<script lang="ts" setup>
import { ref, provide, onMounted, onUnmounted } from 'vue'
import NavMenu from '@/components/nav-menu'
import NavHeader from '@/components/nav-header'
import { useThemeStore } from '@/stores/modules/theme'

const themeStore = useThemeStore()

// 是否折叠
const isCollapse = ref(false)
// 是否全屏显示
const isFullscreen = ref(false)

provide('isCollapse', isCollapse)

const handleSiderCollapse = (collapse: boolean) => {
  isCollapse.value = collapse
}
const handleFullscreenToggle = () => {
  isFullscreen.value = !isFullscreen.value
}
// 监听键盘Esc按键
const handleEscChange = (e: any) => {
  if (e.keyCode === 27) isFullscreen.value = false
}

onMounted(() => {
  document.addEventListener('keyup', handleEscChange)
})

onUnmounted(() => {
  document.removeEventListener('keyup', handleEscChange)
})
</script>

<template>
  <div class="main">
    <a-layout class="main_content">
      <a-layout-sider
        class="main_content_sider"
        width="250"
        collapsible
        @collapse="handleSiderCollapse"
        :theme="themeStore.type"
      >
        <nav-menu :isCollapse="isCollapse" />
      </a-layout-sider>
      <a-layout class="main_body">
        <a-layout-header>
          <nav-header />
        </a-layout-header>
        <div class="main_body_content" :class="{ main_body_content_fullscreen: isFullscreen }">
          <div class="content_fullscreen" @click="handleFullscreenToggle">
            <fullscreen-exit-outlined title="缩小" v-if="isFullscreen" />
            <fullscreen-outlined title="全屏" v-else />
          </div>
          <a-layout-content>
            <router-view></router-view>
          </a-layout-content>
        </div>
      </a-layout>
    </a-layout>
  </div>
</template>

<style scoped lang="stylus">
.main
  height 100%
  .main_content
    height 100%
    .main_content_sider
      border-right var(--border-right)
    :deep(.ant-layout-header)
      padding 0 .3125rem
      background var(--bg-color)
    .main_body
      .main_body_content
        position relative
        box-sizing border-box
        margin .1875rem
        height calc(100% - .375rem)
        width calc(100% - .375rem)
        color var(--text-color)
        background var(--bg-color)
        border-radius .125rem
        padding .5rem .5625rem
        .content_fullscreen
          position absolute
          top -1%
          right 0
          cursor pointer
      .main_body_content_fullscreen
        position fixed
        top 0
        left 0
        z-index 1000
        margin 0
        width 100%
        height 100%
        border-radius 0
</style>
