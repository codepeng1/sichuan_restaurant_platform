<script lang="ts" setup>
import router from '@/router'

const handleBackHomeBtnClick = () => {
  router.push({
    path: '/main'
  })
}
</script>

<template>
  <div class="not-found">
    <img src="~@/assets/img/404.svg" alt="" />
    <h1>页面没有找到, 404~</h1>
    <a-button type="primary" size="large" @click="handleBackHomeBtnClick"
      >返回首页 <home-outlined
    /></a-button>
  </div>
</template>

<style scoped lang="stylus">
.not-found
  display flex
  flex-direction column
  align-items center
  .not-found-back-icon
    margin-left 5px
</style>
