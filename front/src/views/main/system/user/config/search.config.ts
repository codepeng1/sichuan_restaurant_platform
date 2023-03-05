import type { IForm } from '@/base-ui/form'

export const searchFormConfig: IForm = {
  formItems: [
    {
      field: 'username',
      type: 'input',
      label: '用户名',
      rules: [],
      placeholder: '请输入'
    },
    {
      field: 'mobile',
      type: 'input',
      label: '手机号', // /^1(3\d|4[5-9]|5[0-35-9]|6[2567]|7[0-8]|8\d|9[0-35-9])\d{8}$/
      rules: [],
      placeholder: '请输入'
    },
    {
      field: 'createTime',
      type: 'datepicker',
      label: '创建时间', // /^1(3\d|4[5-9]|5[0-35-9]|6[2567]|7[0-8]|8\d|9[0-35-9])\d{8}$/
      rules: [],
      placeholder: '请选择'
    }
  ]
}
