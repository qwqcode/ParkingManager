<template>
    <view class="cars-page">
        <view class="car-list">
            <view v-for="(car) in user.cars" :key="car.id" class="car-item">
                <view class="car-info">
                    <view class="title">车牌号码</view>
                    <view class="plate" @tap="openCarPage(car.plate)">{{car.plate}}</view>
                    <view class="acts">
                        <text class="act-item" @tap="unbindCar(car.id)">解绑</text>
                    </view>
                    <view class="pay-remote">
                        <view>开通无感支付</view>
                        <view class="purchase">开通 ></view>
                    </view>
                </view>
            </view>
            <view class="car-item car-add" @tap="linkToCarAdd">
                + 添加车牌
            </view>
            <view class="car-add-note">
                <view v-if="user.isGoldVIP">温馨提示：金卡会员最多可以添加 5 个车牌</view>
                <view v-else>温馨提示：普卡会员最多可以添加 3 个车牌</view>

                <view>每个每月可解绑 1 次</view>
            </view>
        </view>
    </view>
</template>

<script setup lang="ts">
import Taro, { useDidShow } from '@tarojs/taro'
import * as utils from '@/lib/utils'
import type * as t from '@/types'
import * as request from '@/lib/request'
import { useUserStore } from '@/stores/user'

const user = useUserStore()

function refresh() {
    // 获取绑定的车辆
  request.post('/user/cars').then(data => {
    user.setCars(data.cars)
  })
}

useDidShow(() => {
    refresh()
})

function openCarPage(car_plate: string) {
  Taro.navigateTo({
    url: '/pages/user/car-query?car_plate='+car_plate
  })
}

function unbindCar(car_id: number) {
    Taro.showModal({
        title: '解绑车辆',
        content: '是否解绑车辆',
        success: (res) => {
            if (res.confirm) _unbindCar(car_id)
        }
    })
}

function _unbindCar(car_id: number) {
    request.post('/user/unbind-car', {
        car_id: car_id
    }).then(res => {
        Taro.showToast({
            title: '解绑成功',
            icon: 'success',
            duration: 2000
        })
        refresh()
    })
}

function linkToCarAdd() {
  Taro.navigateTo({
    url: '/pages/user/car-add'
  })
}
</script>

<style lang="scss">
.cars-page {
    background: #f4f4f4;
    min-height: 100vh;
    padding: 30px;

    .car-list {

    }

    .car-item {
        background: #fff;
        padding: 40px;
        border-radius: 20px;

        &:not(:last-child) {
            margin-bottom: 20px;
        }

        .car-info {
            position: relative;
            text-align: center;

            .title {
                margin-bottom: 20px;
            }

            .plate {
                font-size: 40px;
            }

            .acts {
                position: absolute;
                right: 0;
                top: 0;

                .act-item {
                    font-size: 28px;
                    color: grey;
                    border-radius: 50px;
                    padding: 4px 20px;
                    border: 1px solid gray;
                }
            }

            .pay-remote {
                color: grey;
                display: flex;
                flex-direction: row;
                justify-content: space-between;
                margin-top: 50px;
                border-top: 1px solid #eee;
                padding: 0 20px;
                padding-top: 30px;

                .purchase {
                    color: green;
                }
            }
        }

        &.car-add {
            text-align: center;
            font-size: 38px;
            color: grey;
            margin-top: 40px;
        }
    }

    .car-add-note {
        color: #747f84;
        font-size: 24px;
        margin-top: 30px;
    }
}
</style>
