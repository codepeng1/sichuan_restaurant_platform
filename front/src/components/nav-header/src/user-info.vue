<script lang="ts" setup>
import { useMessage } from '@/hooks/use-message'
import router from '@/router'
import { localCache } from '@/utils/cache'
const message = useMessage()

const handleMenuItemClick = (item: any) => {
  switch (item.key) {
    case 'userInfo':
      message.info('功能开发中...')
      break
    case 'logout': {
      let theme = localCache.getCache('theme')
      localCache.clearCache()
      localCache.setCache('theme', theme)
      router.push({ path: '/login' })
      break
    }
  }
}
</script>

<template>
  <div class="user-info">
    <a-dropdown>
      <user-outlined />
      <template #overlay>
        <a-menu @click="handleMenuItemClick">
          <a-menu-item key="userInfo">用户信息</a-menu-item>
          <a-menu-item key="logout">退出登录</a-menu-item>
        </a-menu>
      </template>
    </a-dropdown>
  </div>
</template>

<style scoped></style>
