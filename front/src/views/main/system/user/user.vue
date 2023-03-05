<script lang="ts" setup>
import PageSearch from '@/components/page-search'

import { searchFormConfig } from './config/search.config'

const handleResetClick = () => {}
const handleQueryClick = () => {}
</script>

<template>
  <div class="user">
    <page-search
      :search-form-config="searchFormConfig"
      @handle-reset-click="handleResetClick"
      @handle-query-click="handleQueryClick"
    ></page-search>
  </div>
</template>

<style scoped lang="stylus"></style>
