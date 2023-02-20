<script lang="ts" setup>
import { computed, ref } from 'vue'
import Logo from '@/components/logo'
import { localCache } from '@/utils/cache'
import router from '@/router'
import { pathMapToMenu } from '@/utils/map-menus'
import { useRoute } from 'vue-router'
import { useThemeStore } from '@/stores/modules/theme'

const themeStore = useThemeStore()

// 菜单默认图标
const defaultIcon = 'menu-outlined'
const mensList = computed(() => {
  return localCache.getCache('menuList')
})
const route = useRoute()
const menu = pathMapToMenu(mensList.value, route.path)
const openKeys = ref<number[]>([menu.parentId])
const selectedKeys = ref<string[]>([menu.url])

const handleMenuItemClick = (item: any) => {
  router.push({
    path: item.key
  })
}
</script>

<template>
  <div class="nav-menu">
    <div class="logo_wrapper">
      <logo />
    </div>
    <a-menu
      :theme="themeStore.type"
      mode="inline"
      @click="handleMenuItemClick"
      v-model:openKeys="openKeys"
      v-model:selectedKeys="selectedKeys"
    >
      <a-sub-menu v-for="menu in mensList" :key="menu.id">
        <template #icon>
          <component :is="menu.icon || defaultIcon" />
        </template>
        <template #title>{{ menu.name }}</template>
        <a-menu-item v-for="child in menu.children" :key="child.url">{{ child.name }}</a-menu-item>
      </a-sub-menu>
    </a-menu>
  </div>
</template>

<style scoped lang="stylus">
.nav-menu
  .logo_wrapper
    padding .2188rem 0
    border-bottom var(--border-right)
</style>
