<script lang="ts" setup>
import LpTable from '@/base-ui/table'
import type { ITable } from '@/base-ui/table/types'
import type { CustomColumnProps } from '@/base-ui/table/types'
import { useSystemStore } from '@/stores/modules/main/system'
import { localCache } from '@/utils/cache'
import { pathMapToBreadcrumb } from '@/utils/map-menus'
import { reactive, ref, computed } from 'vue'
import { useRoute } from 'vue-router'
import { useModal } from '@/hooks/use-modal'
import { StatusCode } from '@/service/config'
import { useMessage } from '@/hooks/use-message'

const props = withDefaults(
  defineProps<{
    contentTableConfig: ITable
    pageName: string
  }>(),
  {}
)

const emit = defineEmits<{
  (e: 'handleAddClick'): void
  (e: 'handleEditClick', record: any): void
}>()

const systemStore = useSystemStore()
const modal = useModal()
const message = useMessage()

const route = useRoute()
const contentTitle = computed(() => {
  const breadcrumb = pathMapToBreadcrumb(localCache.getCache('menuList'), route.path)
  return breadcrumb.length ? breadcrumb[breadcrumb.length - 1].name : ''
})

const tableData = ref<any>([])
const pagination = reactive({
  current: 1,
  pageSize: 10,
  total: 0,
  showSizeChanger: true,
  showTotal: (total: number) => `总数 ${total} 条`,
  position: ['bottomCenter']
})

const tableSlotNames = props.contentTableConfig.tableColumns.reduce(
  (pre: string[], cur: CustomColumnProps) => {
    if (cur.headerSlot) pre.push(cur.headerSlot)
    if (cur.bodySlot) pre.push(cur.bodySlot)
    return pre
  },
  []
)

let queryInfoClone = reactive({})
const getPageTableData = async (queryInfo?: any) => {
  queryInfoClone = { ...queryInfo }
  const { data } = await systemStore.getPageTableData(
    `/${props.pageName}/list?current=${pagination.current}&size=${pagination.pageSize}`,
    {
      ...queryInfo
    }
  )
  tableData.value = data.list
  pagination.total = data.total
}
getPageTableData()

const handleTableChange = (p: any) => {
  pagination.current = p.current
  pagination.pageSize = p.pageSize
  getPageTableData(queryInfoClone)
}

const handleAddClick = () => {
  emit('handleAddClick')
}
const handleEditClick = (record: any) => {
  emit('handleEditClick', record)
}
const handleDeleteClick = (record: any) => {
  // 删除数据后页码是否需要向前偏移
  const isOffset = pagination.current > 1 && tableData.value.length === 1

  modal.confirm({
    title: '确定永久删除该数据?',
    onOk: async () => {
      const res = await systemStore.deleteData(`/${props.pageName}/${record.id}`)
      if (res.code === StatusCode.DELETE_OK) {
        message.success(res.msg)
        if (isOffset) pagination.current -= 1
        getPageTableData()
      }
    },
    onCancel: () => {
      message.info('取消删除~')
    }
  })
}

defineExpose({
  getPageTableData
})
</script>

<template>
  <div class="page-content">
    <div class="page_content_header">
      <span>{{ contentTitle }}</span>
      <div class="header_btns">
        <a-button type="primary" @click="handleAddClick">
          <template #icon><plus-outlined /></template>
          新增
        </a-button>
      </div>
    </div>
    <lp-table
      v-bind="contentTableConfig"
      :table-data="tableData"
      :pagination="pagination"
      @change="handleTableChange"
    >
      <template v-for="name in tableSlotNames" #[name]="slotProps">
        <slot :name="name" :record="slotProps.record"></slot>
      </template>
      <template #handler="slotProps">
        <a-button type="link" @click="handleEditClick(slotProps.record)">编辑</a-button>
        <a-button type="link" danger @click="handleDeleteClick(slotProps.record)">删除</a-button>
      </template>
    </lp-table>
  </div>
</template>

<style scoped lang="stylus">
.page-content
  margin .625rem 0
  .page_content_header
    margin-bottom .1875rem
    display flex
    justify-content space-between
</style>
