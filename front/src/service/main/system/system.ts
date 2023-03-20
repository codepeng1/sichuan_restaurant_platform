import lpRequest from '@/service'
import type { IDataType } from '@/service/types'
import type { IPageTableData } from './type'

// 新增数据
export function requestAddData(url: string, data: any) {
  return lpRequest.post<IDataType>({
    url,
    data
  })
}

// 删除
export function requestDeleteData(url: string) {
  return lpRequest.delete<IDataType>({
    url
  })
}

// 修改
export function requestEditData(url: string, data: any) {
  return lpRequest.put<IDataType>({
    url,
    data
  })
}

// 获取页面表格列表数据
export function requestPageTableData(url: string, queryInfo: any) {
  return lpRequest.post<IDataType<IPageTableData>>({
    url,
    data: queryInfo
  })
}
