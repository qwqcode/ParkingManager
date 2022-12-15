<template>
    <view class="car-list-comp">
        <view v-for="(car) in user.cars" :key="car.id" class="car-item" @tap="openCarPage(car.plate)">
            <view class="car-plate">{{ car.plate }}</view>
            <view class="car-status">
                <text v-if="countCarRecsUnpaid(car) > 0" class="badge red">待缴纳 {{countCarRecsUnpaid(car)}}</text>
                <text v-else class="badge">已缴清</text>
            </view>
            <template v-if="car.recs && car.recs.length > 0">
                <view v-for="(rec) in [car.recs[0]]" :key="rec.id" class="details">
                <view>入场：{{utils.getDateFormatted(rec.in_at)}}</view>
                <view>出场：{{utils.getDateFormatted(rec.out_at)}}</view>
                <view>时长：{{utils.getTime2HourMin(rec.parking_time)}}</view>
                <view>状态：{{ rec.status_text }}</view>
                <view v-if="!rec.out_at && rec.rec_pay_at" class="countdown"><CarOutCountdown :car_in_at="rec.rec_pay_at" /></view>
                </view>
            </template>
        </view>
    </view>
</template>

<script setup lang="ts">
import Taro, { useDidShow } from '@tarojs/taro'
import * as utils from '@/lib/utils'
import type * as t from '@/types'
import { useUserStore } from '@/stores/user'
import CarOutCountdown from './CarOutCountdown.vue'

const user = useUserStore()

function countCarRecsUnpaid(car: t.ICar) {
  return car.recs?.filter(r => r.rec_pay_id == 0).length || 0
}

function openCarPage(car_plate: string) {
  Taro.navigateTo({
    url: '/pages/user/car-query?car_plate='+car_plate
  })
}
</script>

<style lang="scss">
.car-list-comp {
    .car-item {
        position: relative;
        padding: 40px 40px;
        margin-bottom: 20px;
        border-bottom: 1px solid #f4f4f4;

        .car-plate {
            font-size: 33px;
        }

        .car-status {
            position: absolute;
            right: 10px;
            top: 20px;
            margin-top: 20px;

            .badge {
                &.red {
                    border-color: #f44336;
                    color: #f44336;
                }

                border: 1px solid #b0b0b0;
                color: #000000;
                border-radius: 100px;
                padding: 7px 24px;
                font-size: 27px;
            }
        }

        .details {
            margin-top: 30px;
            font-size: 28px;

            .countdown {
                margin-top: 30px;
            }
        }
    }
}
</style>
