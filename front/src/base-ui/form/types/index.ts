type IFormType = 'input' | 'password' | 'select' | 'datepicker'
import type { RuleItem } from 'async-validator'

export interface IFormItem {
  field: string
  type: IFormType
  label: string
  rules?: RuleItem[]
  placeholder?: string
  // 特殊属性
  otherOptions?: any
}

export interface IForm {
  formItems: IFormItem[]
  gutter?: number
  colLayout?: object
}
