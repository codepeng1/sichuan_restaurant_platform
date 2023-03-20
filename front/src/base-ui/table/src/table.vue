<script lang="ts" setup>
import type { TableColumnProps } from 'ant-design-vue'

const props = withDefaults(
  defineProps<{
    tableColumns: TableColumnProps[]
    tableData: any[]
    pagination: object
    bordered?: boolean
    scroll?: object
  }>(),
  {
    tableColumns: () => [],
    tableData: () => [],
    pagination: () => ({}),
    bordered: true,
    scroll: () => ({ y: '8.5rem' })
  }
)

const emit = defineEmits<{
  (e: 'change', pagination: any, filters: any, sorter: any, data: any): void
}>()

const handleTableChange = (pagination: any, filters: any, sorter: any, data: any) => {
  emit('change', pagination, filters, sorter, data)
}

// 初始化tableColumns
for (const col of props.tableColumns) {
  if (!col.align) col.align = 'center'
  if (!col.key) col.key = col.dataIndex as any
}
</script>

<template>
  <div class="table">
    <a-table
      :columns="tableColumns"
      :data-source="tableData"
      :pagination="pagination"
      :bordered="bordered"
      :scroll="scroll"
      @change="handleTableChange"
    >
      <template #headerCell="{ column, record }">
        <template v-if="column.headerSlot">
          <slot :name="column.headerSlot" :record="record"></slot>
        </template>
      </template>

      <template #bodyCell="{ column, record }">
        <template v-if="column.bodySlot">
          <slot :name="column.bodySlot" :record="record"></slot>
        </template>
        <template v-else-if="column.dateFormat">
          <span v-date-format="column.dateFormat">{{ record[column.dataIndex] }}</span>
        </template>
        <template v-else-if="column.dataIndex === 'handler'">
          <slot :name="column.dataIndex" :record="record"></slot>
        </template>
      </template>
    </a-table>
  </div>
</template>

<style scoped lang="stylus"></style>
