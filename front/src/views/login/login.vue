<script lang="ts" setup>
import LoginLeft from './cpns/login-left.vue'
import LoginRight from './cpns/login-right.vue'
</script>

<template>
  <div class="login">
    <LoginLeft />
    <LoginRight />
  </div>
</template>

<style scoped lang="stylus">
.login
  height 100%
  overflow: hidden
  display flex
</style>
