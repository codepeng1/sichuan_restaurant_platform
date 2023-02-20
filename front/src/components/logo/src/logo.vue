<script lang="ts" setup>
import { inject, computed } from 'vue'
import type { Ref } from 'vue'

const props = withDefaults(
  defineProps<{
    color?: string
  }>(),
  {
    color: 'var(--text-color)'
  }
)

// 折叠状态
const isCollapse = inject<Ref<boolean>>('isCollapse')

// 动态样式
const logoStyles = computed(() => {
  return {
    margin: isCollapse?.value ? '0' : '0 .1rem'
  }
})
</script>

<template>
  <div class="logo">
    <div class="logo_title">
      <div class="logo_title_img" :style="logoStyles">
        <img src="~@/assets/img/logo.png" alt="" title="川菜内容分享平台" />
      </div>
      <div v-if="!isCollapse" class="logo_title_text" :style="{ color: props.color }">
        川菜内容分享平台
      </div>
    </div>
    <slot></slot>
  </div>
</template>

<style scoped lang="stylus">
.logo
  display flex
  justify-content center
  align-items center
  .logo_title
    font-size .375rem
    display flex
    align-items center
    .logo_title_img
      img
        width .75rem
        height .75rem
</style>
