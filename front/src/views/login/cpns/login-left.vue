<script lang="ts" setup>
import { reactive, onMounted } from 'vue'
import Logo from '@/components/logo'
import router from '@/router'
import { useLoginStore } from '@/stores/modules/login'
import { useMessage } from '@/hooks/useMessage'
import { localCache } from '@/utils/cache'
const message = useMessage()
const loginStore = useLoginStore()

const formState = reactive({
  username: '',
  password: '',
  remember: false
})

const formRules = {
  username: [{ required: true, message: '请先输入账号!' }],
  password: [
    { required: true, message: '请先输入密码!' },
    {
      min: 6,
      max: 9,
      message: '请输入6-9位数的密码!'
    }
  ]
}

onMounted(() => {
  // 记住密码
  formState.remember = !!localCache.getCache('remember')
  if (formState.remember) {
    const account = localCache.getCache('account')
    formState.username = account.username
    formState.password = account.password
  }
})

const onFinish = () => {
  // 表单验证完成, 开始调用登录请求
  loginStore
    .startLogin(formState)
    .then(res => {
      message.success(res.msg)
      router.push({
        path: '/main'
      })
    })
    .catch(err => {
      message.error(err.msg)
    })
}

const onFinishFailed = () => {
  message.error('请先完善信息~')
}
</script>

<template>
  <div class="login_left">
    <div class="left_form">
      <div class="left_form_header">
        <Logo color="rgb(247, 86, 87)" />
      </div>
      <div class="left_form_body">
        <a-form
          :model="formState"
          name="login"
          autocomplete="off"
          @finish="onFinish"
          @finishFailed="onFinishFailed"
          :rules="formRules"
        >
          <a-form-item name="username">
            <a-input v-model:value="formState.username" placeholder="账号" :maxlength="20">
              <template #prefix><UserOutlined style="color: rgba(0, 0, 0, 0.25)" /></template>
            </a-input>
          </a-form-item>

          <a-form-item name="password">
            <a-input v-model:value="formState.password" placeholder="密码" type="password">
              <template #prefix><LockOutlined style="color: rgba(0, 0, 0, 0.25)" /></template>
            </a-input>
          </a-form-item>

          <a-form-item name="remember">
            <a-checkbox v-model:checked="formState.remember">记住密码</a-checkbox>
          </a-form-item>

          <a-form-item :wrapper-col="{ offset: 8, span: 16 }">
            <a-button type="primary" html-type="submit">登录</a-button>
          </a-form-item>
        </a-form>
      </div>
    </div>
  </div>
</template>

<style scoped lang="stylus">
.login_left
  width 33%
  height 100%
  background #fff
  position relative
  &:before
    display block
    content ''
    position absolute
    top -5%
    right -10%
    width 40%
    height 70%
    background #fff
    border-radius: 50%
  &:after
    display block
    content ''
    position absolute
    bottom -9.25%
    right -13%
    width 20%
    height 50%
    background rgb(247, 86, 87)
    border-radius: 50%
  .left_form
    position absolute
    top 50%
    left 50%
    transform translate(-50%, -50%) scale(1.2)
    padding .3125rem .625rem
    box-shadow .0625rem .0625rem .3125rem #eee
    :deep(.ant-form-item-explain-error)
      font-size .2188rem
    .left_form_header
      margin-bottom .375rem
</style>
