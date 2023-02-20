<script lang="ts" setup>
import { ref, provide } from 'vue'
import NavMenu from '@/components/nav-menu'
import NavHeader from '@/components/nav-header'
import { useThemeStore } from '@/stores/modules/theme'

const themeStore = useThemeStore()

// 是否折叠
const isCollapse = ref(false)

provide('isCollapse', isCollapse)

const handleSiderCollapse = (collapse: boolean) => {
  isCollapse.value = collapse
}
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
      <a-layout>
        <a-layout-header>
          <nav-header />
        </a-layout-header>
        <a-layout-content>
          <router-view></router-view>
        </a-layout-content>
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
</style>
