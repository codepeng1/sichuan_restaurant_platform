import { ref } from 'vue'
import type PageModal from '@/components/page-modal'
import type { IModal } from '@/components/page-modal/types'

export function usePageModal(modalConfig?: IModal): any {
  const modalRef = ref<InstanceType<typeof PageModal>>()
  const defaultInfo = ref({})
  const handleAddClick = () => {
    if (modalConfig?.title) modalConfig.title = '新增用户'
    defaultInfo.value = {}
    modalRef.value?.setVisible(true)
  }
  const handleEditClick = (record: any) => {
    if (modalConfig?.title) modalConfig.title = '修改用户'
    defaultInfo.value = { ...record }
    modalRef.value?.setVisible(true)
  }

  return [modalRef, defaultInfo, handleAddClick, handleEditClick]
}
