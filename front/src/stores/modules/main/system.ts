import {
  requestAddData,
  requestDeleteData,
  requestEditData,
  requestPageTableData
} from '@/service/main/system/system'
import { defineStore } from 'pinia'
import type { IDataType } from '@/service/types'
import type { IPageTableData } from '@/service/main/system/type'

export const useSystemStore = defineStore('system', () => {
  const getPageTableData = (url: string, queryInfo: any) => {
    return new Promise<IDataType<IPageTableData>>((resolve, reject) => {
      requestPageTableData(url, queryInfo)
        .then(res => {
          resolve(res)
        })
        .catch(err => reject(err))
    })
  }

  const addData = (url: string, data: any) => {
    return requestAddData(url, data)
  }

  const deleteData = (url: string) => {
    return requestDeleteData(url)
  }

  const editData = (url: string, data: any) => {
    return requestEditData(url, data)
  }

  return { getPageTableData, addData, deleteData, editData }
})
