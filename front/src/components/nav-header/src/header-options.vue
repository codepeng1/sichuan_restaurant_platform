<script lang="ts" setup>
import LpThemeToggle from '@/base-ui/theme-toggle'
import { useThemeStore } from '@/stores/modules/theme'

const themeStore = useThemeStore()
const hanleThemeToggleChange = (type: string) => {
  themeStore.setType(type)
}
</script>

<template>
  <div class="header-options">
    <lp-theme-toggle @change="hanleThemeToggleChange" />
  </div>
</template>

<style scoped></style>
