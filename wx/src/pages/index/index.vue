<template>
  <view class="home-page">
    <view v-if="user.data" class="card user">
        <view class="avatar"></view>
        <view class="user-info">
          <view class="phone">{{ user.data.phone }}</view>
          <view class="credit">
            <text class="credit-item"><text>{{ user.data.credit }}</text> 积分</text>
            <text class="credit-item"><text>0</text> 优惠券</text>
          </view>
        </view>
        <view v-if="user.isGoldVIP" class="vip-type gold">金卡</view>
        <view v-else class="vip-type">普卡</view>
    </view>

    <view class="card cars">
      <view class="bind-card-notice">
        <view class="text">您是普卡会员，最多可以绑定 2 张车牌</view>
        <view class="car-add-btn">+ 添加车辆</view>
      </view>
    </view>

    <view class="card funcs">
      <view
        v-for="(item, i) in funcList"
        :key="i"
        class="func-item"
      >
        <view class="func-icon" :style="{ backgroundImage: `url(${item.icon})` }"></view>
        <view class="func-name">{{ item.name }}</view>
      </view>
    </view>
  </view>
</template>

<script setup lang="ts">
import './index.scss'
import Taro from '@tarojs/taro'
import { onBeforeMount } from 'vue'
import { useUserStore } from '@/stores/user'

const user = useUserStore()

onBeforeMount(() => {
  if (!user.data) {
    Taro.redirectTo({
      url: '/pages/user/login'
    })
  }
})

const funcList = [{
  name: '缴费记录',
  icon: require('@/static/icons/recs.png')
}, {
  name: '车牌管理',
  icon: require('@/static/icons/cars.png')
}, {
  name: '代人缴费',
  icon: require('@/static/icons/pay.png')
}, {
  name: '收费规则',
  icon: require('@/static/icons/rule.png')
}, {
  name: '停车券',
  icon: require('@/static/icons/coupons.png')
}, {
  name: '意见反馈',
  icon: require('@/static/icons/feedback.png')
}, {
  name: '开发票',
  icon: require('@/static/icons/invoice.png')
}, {
  name: '积分换礼',
  icon: require('@/static/icons/gift.png')
}, {
  name: '会员服务协议',
  icon: require('@/static/icons/doc.png')
}, {
  name: '用户隐私政策摘要',
  icon: require('@/static/icons/doc.png')
}, {
  name: '用户隐私政策',
  icon: require('@/static/icons/doc.png')
}]
</script>
