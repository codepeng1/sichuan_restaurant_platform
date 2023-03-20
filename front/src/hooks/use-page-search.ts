import { ref } from 'vue'
import type PageContent from '@/components/page-content'
import type { IDataType } from '@/service/types'
import { StatusCode } from '@/service/config'

export function usePageSearch(): any {
  const pageContentRef = ref<InstanceType<typeof PageContent>>()
  const handleResetClick = () => {
    pageContentRef.value?.getPageTableData()
  }
  const handleQueryClick = (queryInfo: any) => {
    pageContentRef.value?.getPageTableData(queryInfo)
  }
  const addCallback = (res: IDataType) => {
    if (res.code === StatusCode.SAVE_OK) {
      pageContentRef.value?.getPageTableData()
    }
  }
  const editCallback = (res: IDataType) => {
    if (res.code === StatusCode.UPDATE_OK) {
      pageContentRef.value?.getPageTableData()
    }
  }

  return [pageContentRef, handleResetClick, handleQueryClick, addCallback, editCallback]
}
