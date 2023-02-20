<script lang="ts" setup>
import LpBreadcrumb from '@/base-ui/breadcrumb'
import HeaderOptions from './header-options.vue'
import { localCache } from '@/utils/cache'
import { pathMapToBreadcrumb } from '@/utils/map-menus'
import { computed } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const breadcrumb = computed(() => {
  return pathMapToBreadcrumb(localCache.getCache('menuList'), route.path)
})
</script>

<template>
  <div class="nav-header">
    <lp-breadcrumb :breadcrumb="breadcrumb" />
    <header-options />
  </div>
</template>

<style scoped lang="stylus">
.nav-header
  height 100%
  display flex
  align-items center
  justify-content space-between
</style>
