import { Modal } from 'ant-design-vue'
import type { ModalFuncProps } from 'ant-design-vue'
import { createVNode } from 'vue'
import { ExclamationCircleOutlined } from '@ant-design/icons-vue'

export function useModal() {
  const myModal = new Proxy(Modal, {
    get(target, key: any, receiver) {
      // 劫持confirm方法
      if (key === 'confirm') {
        return (props: ModalFuncProps) =>
          Modal.confirm({
            icon: createVNode(ExclamationCircleOutlined),
            okText: '确定',
            okType: 'danger',
            cancelText: '取消',
            centered: true,
            ...props
          })
      }

      return Reflect.get(target, key, receiver)
    }
  })

  return myModal
}
