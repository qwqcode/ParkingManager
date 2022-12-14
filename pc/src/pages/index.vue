<script setup lang="ts">
import { useAppStore } from '../stores/app'
import LoginBanner from '../components/LoginBanner.vue'
import * as request from '@/lib/request'
import { Message } from '@arco-design/web-vue';

const router = useRouter()
const app = useAppStore()

const form = reactive({
    phone: '10086',
    password: '123456',
    remember: false
})

onMounted(() => {
  app.setUseLayout(false)
})

function handleSubmit() {
  request.post('/login', {
    phone: form.phone,
    password: form.password
  }).then(res => {
    if (res.data.success) {
      router.push('/recs')
    } else {
      Message.error(res.data.msg)
    }
  })
}
</script>

<template>
  <div class="login-page">
    <LoginBanner />

    <div class="content">
        <div class="content-inner">
            <a-form :model="form" :style="{ width: '600px' }" @submit="handleSubmit">
                <a-form-item field="phone" label="手机号">
                    <a-input
                    v-model="form.phone"
                    placeholder="请输入手机号"
                    />
                </a-form-item>
                <a-form-item field="post" label="密码">
                    <a-input type="password" v-model="form.password" placeholder="请输入密码" />
                </a-form-item>
                <a-form-item field="remember">
                    <a-checkbox v-model="form.remember"> 自动登录</a-checkbox>
                </a-form-item>
                <a-form-item>
                    <a-button type="primary" html-type="submit">登录</a-button>
                </a-form-item>
            </a-form>
        </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
.login-page {
    display: flex;
    height: 100vh;

    .banner {
      width: 550px;
      background: linear-gradient(163.85deg, #1d2129 0%, #00308f 100%);
    }
    .content {
      position: relative;
      display: flex;
      flex: 1;
      align-items: center;
      justify-content: center;
      padding-bottom: 40px;
    }
    .footer {
      position: absolute;
      right: 0;
      bottom: 0;
      width: 100%;
    }
  }
  .logo {
    position: fixed;
    top: 24px;
    left: 22px;
    z-index: 1;
    display: inline-flex;
    align-items: center;

    &-text {
      margin-right: 4px;
      margin-left: 4px;
      color: var(--color-fill-1);
      font-size: 20px;
    }
}
</style>
