<script lang="ts" setup>
import PageSearch from '@/components/page-search'
import PageContent from '@/components/page-content'
import PageModal from '@/components/page-modal'

import { searchFormConfig } from './config/search.config'
import { contentTableConfig } from './config/content.config'
import { modalConfig } from './config/modal.config'

import { usePageSearch } from '@/hooks/use-page-search'
import { usePageModal } from '@/hooks/use-page-modal'

const [pageContentRef, handleResetClick, handleQueryClick, addCallback, editCallback] =
  usePageSearch()

const [modalRef, defaultInfo, handleAddClick, handleEditClick] = usePageModal(modalConfig)

const pageName = 'user'
</script>

<template>
  <div class="user">
    <page-search
      :search-form-config="searchFormConfig"
      @handle-reset-click="handleResetClick"
      @handle-query-click="handleQueryClick"
    ></page-search>
    <page-content
      ref="pageContentRef"
      :page-name="pageName"
      :content-table-config="contentTableConfig"
      @handle-add-click="handleAddClick"
      @handle-edit-click="handleEditClick"
    ></page-content>
    <page-modal
      ref="modalRef"
      :page-content-ref="pageContentRef"
      :page-name="pageName"
      :modal-config="modalConfig"
      :default-info="defaultInfo"
      @add-callback="addCallback"
      @edit-callback="editCallback"
    ></page-modal>
  </div>
</template>

<style scoped lang="stylus"></style>
