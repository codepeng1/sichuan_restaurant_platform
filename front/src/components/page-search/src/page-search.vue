<script lang="ts" setup>
import { ref } from 'vue'
import LpForm from '@/base-ui/form'
import type { IForm } from '@/base-ui/form/types'
import { useMessage } from '@/hooks/useMessage'

const props = withDefaults(
  defineProps<{
    searchFormConfig: IForm
  }>(),
  {}
)

const emit = defineEmits<{
  (e: 'handleResetClick'): void
  (e: 'handleQueryClick', queryInfo: { [propName: string]: any }): void
}>()

const formRef = ref()
const message = useMessage()

// 双向绑定的属性应该是由配置文件的field来决定
// 1.formData中的属性应该动态来决定
const formItems = ref(props.searchFormConfig.formItems)
const formOriginData: { [propName: string]: any } = {}
for (const item of formItems.value) {
  formOriginData[item.field] = ''
}
const formData = ref(formOriginData)

// 2.当用户点击重置
const handleResetClick = () => {
  // formData.value = formOriginData
  Object.keys(formOriginData).map(key => {
    formData.value[key] = formOriginData[key]
  })
  emit('handleResetClick')
}

// 3.当用户点击搜索
const handleQueryClick = () => {
  formRef.value
    ?.validateForm()
    .then(() => {
      emit('handleQueryClick', formData.value)
    })
    .catch(() => {
      message.error('请正确填写信息!')
    })
}
</script>

<template>
  <div class="page-search">
    <lp-form ref="formRef" v-bind="searchFormConfig" v-model="formData">
      <template #footer>
        <div class="footer_btn">
          <a-button type="primary" @click="handleQueryClick">搜索</a-button>
        </div>
        <div class="footer_btn">
          <a-button @click="handleResetClick">重置</a-button>
        </div>
      </template>
    </lp-form>
  </div>
</template>

<style scoped lang="stylus">
.page-search
  .footer_btn
    margin 0 .1875rem
</style>
