<script lang="ts" setup>
import type { IBreadcrumb } from '../types'

withDefaults(
  defineProps<{
    breadcrumb: IBreadcrumb[]
  }>(),
  {
    breadcrumb: () => []
  }
)
</script>

<template>
  <div class="breadcrumb">
    <a-breadcrumb>
      <template v-for="item in breadcrumb" :key="item.id">
        <a-breadcrumb-item>{{ item.name }}</a-breadcrumb-item>
      </template>
    </a-breadcrumb>
  </div>
</template>

<style scoped lang="stylus">
.breadcrumb
  :deep(.ant-breadcrumb)
    color #aaa
    & > span:last-child
      color var(--text-color)
    .ant-breadcrumb-separator
      color #aaa
</style>
