<script lang="ts" setup>
import zhCN from 'ant-design-vue/es/locale/zh_CN'
</script>

<template>
  <a-config-provider :locale="zhCN">
    <div class="app">
      <router-view></router-view>
    </div>
  </a-config-provider>
</template>

<style scoped lang="stylus">
.app
  height 100%
</style>
