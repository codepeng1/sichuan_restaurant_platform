import type { TableColumnProps } from 'ant-design-vue'

export interface CustomColumnProps {
  // 列头插槽
  headerSlot?: string
  // 列内容插槽
  bodySlot?: string
  // stirng:自定义格式化字符串(YYYY-MM-DD HH:mm:ss) boolean=true:开启默认格式化
  dateFormat?: string | boolean
}

export interface ITable {
  tableColumns: (TableColumnProps & CustomColumnProps)[]
  bordered?: boolean
  scroll?: object
}
