import type { ITable } from '@/base-ui/table/types'

export const contentTableConfig: ITable = {
  tableColumns: [
    {
      title: '用户名',
      dataIndex: 'username'
    },
    {
      title: '手机号',
      dataIndex: 'mobile'
    },
    {
      title: '创建时间',
      dataIndex: 'createTime',
      dateFormat: true
    },
    {
      title: '操作',
      dataIndex: 'handler'
    }
  ]
}
