<template>
    <view class="pay-done-page">
        <view class="card">
            <image class="icon" :src="require('@/static/images/success.png')" />
            <view class="title">缴费成功</view>
            <CarOutCountdown v-if="carInAt !== ''" :car_in_at="carInAt" />

            <view class="link" @tap="goHome()">返回 ></view>
        </view>
    </view>
</template>

<script setup lang="ts">
import Taro, { useDidShow, useRouter } from '@tarojs/taro'
import CarOutCountdown from '@/components/CarOutCountdown.vue'
import { ref } from 'vue'

const router = useRouter()
const carInAt = ref('')

useDidShow(() => {
    if (router.params.show_countdown == '1')
        carInAt.value = new Date().toString()
})

function goHome() {
    Taro.navigateBack()
}
</script>

<style lang="scss">
.pay-done-page {
    background: #f4f4f4;
    min-height: 100vh;
    padding: 30px;
    text-align: center;

    .card {
        border-radius: 20px;
        background: #fff;
        padding: 50px;
    }

    .icon {
        margin-top: 90px;
        width: 150px;
        height: 150px;
    }

    .title {
        margin-top: 40px;
        margin-bottom: 90px;
        font-size: 40px;
    }

    .link {
        color: rgb(25, 98, 233);
        margin-top: 40px;
    }
}
</style>
