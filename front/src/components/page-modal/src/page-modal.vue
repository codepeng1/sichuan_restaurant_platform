<script lang="ts" setup>
import { ref, watch } from 'vue'
import type { IModal } from '../types'
import LpForm from '@/base-ui/form'
import { useSystemStore } from '@/stores/modules/main/system'
import { useMessage } from '@/hooks/use-message'
import { StatusCode } from '@/service/config'
import type { IDataType } from '@/service/types'

const props = withDefaults(
  defineProps<{
    modalConfig: IModal
    pageName: string
    defaultInfo?: { [propName: string]: any }
  }>(),
  {
    defaultInfo: () => ({})
  }
)

const emit = defineEmits<{
  (e: 'addCallback', res: IDataType): void
  (e: 'editCallback', res: IDataType): void
}>()

const message = useMessage()
const system = useSystemStore()
const visible = ref(false)

const formData = ref<{ [propName: string]: any }>({})
watch(
  () => props.defaultInfo,
  newValue => {
    for (const item of props.modalConfig.formConfig.formItems) {
      formData.value[item?.field] = newValue[item?.field]
    }
  }
)

const handleOk = async () => {
  if (Object.keys(props.defaultInfo).length) {
    const res = await system.editData(`/${props.pageName}`, {
      ...formData.value,
      id: props.defaultInfo.id
    })
    handleResult(StatusCode.UPDATE_OK, res)
    emit('editCallback', res)
  } else {
    const res = await system.addData(`/${props.pageName}`, formData.value)
    handleResult(StatusCode.SAVE_OK, res)
    emit('addCallback', res)
  }
  setVisible(false)
}

// 处理请求响应结果
const handleResult = (successCode: number, res: IDataType) => {
  if (res.code === successCode) {
    message.success(res.msg)
  } else {
    message.error(res.msg)
  }
}

const setVisible = (boo: boolean) => {
  visible.value = boo
}

defineExpose({ setVisible })
</script>

<template>
  <div class="page-modal">
    <a-modal
      v-model:visible="visible"
      :keyboard="false"
      :maskClosable="false"
      :title="modalConfig?.title"
      @ok="handleOk"
    >
      <lp-form v-bind="modalConfig?.formConfig" v-model="formData"></lp-form>
    </a-modal>
  </div>
</template>

<style scoped></style>
