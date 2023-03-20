<script lang="ts" setup>
import { ref, watch } from 'vue'
import type { IFormItem } from '../types'
import { Form } from 'ant-design-vue'

const props = withDefaults(
  defineProps<{
    modelValue: { [propName: string]: any }
    formItems: IFormItem[]
    gutter?: number
    colLayout?: object
  }>(),
  {
    modelValue: () => ({}),
    formItems: () => [],
    gutter: 20,
    colLayout: () => ({
      span: 24,
      xs: {
        span: 24
      },
      xl: {
        span: 12
      },
      xxl: {
        span: 8
      }
    })
  }
)

const emit = defineEmits<{
  (e: 'update:modelValue', newValue: object): object
}>()

let formState = ref({ ...props.modelValue })
const formRules = ref<{ [propName: string]: any }>({})
for (const item of props.formItems) {
  formRules.value[item.field] = item.rules
}

const { validate } = Form.useForm(formState, formRules)
const validateForm = () => {
  return validate()
}

watch(props.modelValue, (newValue: object) => {
  formState.value = { ...newValue }
})

watch(
  formState,
  (newValue: object) => {
    emit('update:modelValue', newValue)
  },
  { deep: true }
)

defineExpose({
  validateForm
})
</script>

<template>
  <div class="form">
    <a-form :model="formState" autocomplete="off">
      <a-row :gutter="gutter">
        <template v-for="item in formItems" :key="item.field">
          <a-col v-bind="colLayout">
            <a-form-item :label="item.label" :name="item.field" :rules="item.rules">
              <template v-if="item.type === 'input'">
                <a-input v-model:value="formState[item.field]" :placeholder="item.placeholder" />
              </template>
              <template v-if="item.type === 'password'">
                <a-input-password
                  v-model:value="formState[item.field]"
                  :placeholder="item.placeholder"
                />
              </template>
              <template v-if="item.type === 'select'">
                <a-select v-model:value="formState[item.field]" :placeholder="item.placeholder" />
              </template>
              <template v-if="item.type === 'datepicker'">
                <a-date-picker
                  v-model:value="formState[item.field]"
                  v-bind="item.otherOptions"
                  :placeholder="item.placeholder"
                />
              </template>
            </a-form-item>
          </a-col>
        </template>
      </a-row>
    </a-form>
    <div class="form_footer">
      <slot name="footer"></slot>
    </div>
  </div>
</template>

<style scoped lang="stylus">
.form
  :deep(.ant-form-item-label)
    & > label
      color var(--text-color)
  :deep(.ant-picker)
    width 100%
  .form_footer
    display flex
    justify-content center
</style>
