<template>
  <view class="login-page">
    <view class="logo"></view>

    <view class="login-form">
        <view class="item">
            <view class="label">手机号</view>
            <view class="input">
                <input type="text" v-model="formData.phone" />
            </view>
        </view>
        <view class="item">
            <view class="label">密码</view>
            <view class="input">
                <input type="password" v-model="formData.password" />
            </view>
        </view>

        <view class="item btn-wrap">
            <button class="login-btn blue-btn" @tap="login">登录</button>
            <view class="signup-note">
                还没有账号？<text class="signup-btn" @tap="linkToSignup">点击注册</text>
            </view>
        </view>
    </view>
  </view>
</template>

<script setup lang="ts">
import Taro from '@tarojs/taro'
import { onMounted } from 'vue'
import { reactive } from 'vue'
import { useUserStore } from '@/stores/user'
import * as request from '@/lib/request'

const user = useUserStore()

const formData = reactive({
    phone: '10086',
    password: '123456'
})

onMounted(() => {
    login()
})

function login() {
    request.post('/login', {
        phone: formData.phone,
        password: formData.password
    }).then((data) => {
        user.setData(data.user)
        user.setVipCard(data.vip_card)

        // Taro.redirectTo({
        //     url: '/pages/index/index'
        // })
        Taro.redirectTo({
            url: '/pages/user/pay'
        })
    })
}

function linkToSignup() {
    Taro.navigateTo({
        url: '/pages/user/signup'
    })
}
</script>

<style lang="scss">
.login-page {
    padding: 40px;

    .logo {
        height: 300px;
        width: 100%;
        background: url(../../static/images/parking.png) no-repeat center;
        background-size: contain;
    }

    .login-form {
        padding: 50px;
    }

    .login-form > .item {
        display: flex;
        flex-direction: row;
        place-items: center;
        padding: 20px 0;

        .label {
            flex-basis: 3em;
        }

        .input {
            flex: auto;
            margin-left: 40px;
        }

        input {
            padding: 10px 20px;
            border-bottom: 1px solid #eee;
        }

        &.btn-wrap {
            flex-direction: column;
        }
    }

    .login-btn {
    }

    .signup-note {
        margin-top: 40px;

        .signup-btn {
            color: #2196f3;
        }
    }
}
</style>
