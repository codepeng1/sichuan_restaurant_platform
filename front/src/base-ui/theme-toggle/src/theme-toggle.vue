<script lang="ts" setup>
import { ref } from 'vue'

const emit = defineEmits<{
  (event: 'change', type: string): void
}>()

let type = ref('dark')

const handleThemeToggleClick = (value: string) => {
  type.value = value
  if (value === 'dark') {
    document.documentElement.removeAttribute('theme')
  } else {
    document.documentElement.setAttribute('theme', value)
  }
  emit('change', value)
}
</script>

<template>
  <div class="theme-toggle">
    <div :class="'-' + type">
      <i
        v-if="type === 'dark'"
        class="theme_icon lpIconfont lp-yueliang"
        @click="handleThemeToggleClick('light')"
      ></i>
      <i
        v-else
        class="theme_icon lpIconfont lp-taiyang-copy"
        @click="handleThemeToggleClick('dark')"
      ></i>
    </div>
  </div>
</template>

<style scoped lang="stylus">
.theme-toggle
  .-dark
    .theme_icon
      padding .0375rem .0625rem .0625rem .4375rem
      border-radius .3125rem
      background rgb(47, 47, 47)
      border .0375rem solid rgba(84, 84, 84, .7)
      cursor pointer
      color #fff
      &:hover
        border-color rgba(84, 84, 84, 1)
  .-light
    .theme_icon
      padding .0375rem .4375rem .0375rem .0625rem
      border-radius .3125rem
      background rgb(241, 241, 241)
      border .0375rem solid rgba(60, 60, 60, .29)
      cursor pointer
      &:hover
        border-color rgba(60, 60, 60, .4)
</style>
