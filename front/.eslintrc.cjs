/* eslint-env node */

require('@rushstack/eslint-patch/modern-module-resolution')

module.exports = {
  root: true,

  extends: [
    'plugin:vue/vue3-essential',

    'eslint:recommended',

    '@vue/eslint-config-typescript',

    '@vue/eslint-config-prettier',

    'plugin:prettier/recommended'
  ],

  parserOptions: {
    ecmaVersion: 'latest'
  },

  rules: {
    'prettier/prettier': ['warn', { endOfLine: 'auto' }],
    'no-console': process.env.NODE_ENV === 'production' ? 'warn' : 'off',
    'no-debugger': process.env.NODE_ENV === 'production' ? 'warn' : 'off',
    // template模板可以不写根组件
    'vue/valid-template-root': 'off',
    // 组件名称不用由多个单词组成
    'vue/multi-word-component-names': 'off',
    // 允许类型为any
    '@typescript-eslint/no-explicit-any': 'off',
    // 允许非空断言
    '@typescript-eslint/no-non-null-assertion': 'off'
  }
}
