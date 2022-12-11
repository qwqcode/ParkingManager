<template>
    <view class="car-query-page">
        <view v-if="!carPlate || !car" class="card query-car">
            <CarEditorVue submit-btn-text="去缴费" @submit="onQueryCar" />
        </view>
        <view v-else>
            <view class="car-banner card">
                {{car.plate}}
            </view>
            <view class="car-info card">
                <view class="card-title">车辆信息</view>
                <view>车牌号码：{{car.plate}}</view>
                <view>车牌类型：{{car.plate_type}}</view>
                <template v-if="car.recs!.length > 0">
                    <view>停车时间：{{utils.getDateFormatted(car.recs![0].in_at)}}</view>
                    <view>出场时间：{{utils.getDateFormatted(car.recs![0].out_at)}}</view>
                    <view>停车时长：{{utils.getTime2HourMin(car.recs![0].parking_time)}}</view>
                    <view>当前状态：{{car.recs![0].status_text}}</view>
                </template>
            </view>

            <view class="card">
                <view class="card-title">费用缴纳</view>
                <view v-if="car.recs!.length > 0" class="rec-list">
                    <view
                        v-for="(rec) in car.recs"
                        :key="rec.id"
                        class="rec-item"
                    >
                        <view>#{{rec.id}} {{ rec.status_text }}</view>
                        <view class="sub">停车日期：{{ utils.getDateFormatted(rec.in_at) }}</view>
                        <view v-if="rec.rec_pay_id == 0" class="pay-btn" @tap="linkToPay(rec.car_id, rec.id)">缴费 ></view>
                    </view>
                </view>
                <view v-else>
                    无停车记录
                </view>
            </view>
        </view>
    </view>
</template>

<script setup lang="ts">
import Taro, { useRouter, useDidShow } from '@tarojs/taro'
import { onBeforeMount, reactive, ref } from 'vue'
import { useUserStore } from '@/stores/user'
import * as utils from '@/lib/utils'
import type * as t from '@/types'
import * as request from '@/lib/request'
import CarEditorVue from '@/components/CarEditor.vue'


const carPlate = ref('')
const car = ref<t.ICar|null>(null)

const router = useRouter()

useDidShow(() => {
    carPlate.value = router.params.car_plate || ''
    if (!carPlate.value) {
        // 无 URL 参数
        Taro.setNavigationBarTitle({ title: '代人缴费' })
    } else {
        queryCar(carPlate.value).then((d) => {
            car.value = d.car
        })
    }
})

function onQueryCar({ plate }) {
    queryCar(plate).then((d) => {
        carPlate.value = plate
        car.value = d.car
    })
}

async function queryCar(car_plate: string) {
    return await request.post('/user/car-query', { car_plate })
}

function linkToPay(car_id: number, rec_id: number) {
    Taro.navigateTo({
        url: '/pages/user/pay?car_id='+car_id+'&rec_id='+rec_id
    })
}
</script>

<style lang="scss">
.car-query-page {
    background: #f4f4f4;
    min-height: 100vh;
    padding: 30px;

    .card {
        background: #fff;
        border-radius: 20px;
        padding: 40px 40px;

        &:not(:last-child) {
            margin-bottom: 20px;
        }

        .card-title {
            font-size: 30px;
            padding-bottom: 30px;
            margin-bottom: 40px;
            text-align: center;
            border-bottom: 1px solid #f4f4f4;
        }
    }

    .car-banner {
        text-align: center;
        font-weight: bold;
        font-size: 39px;
    }

    .car-info {

    }

    .rec-list {
        .rec-item {
            position: relative;
            padding: 20px 0;

            .sub {
                margin-top: 10px;
                color: #747f84;
                font-size: 24px;
            }

            .pay-btn {
                position: absolute;
                right: 0;
                top: 30px;
                padding: 10px 30px;
                background: #2196f3;
                color: #fff;
                border-radius: 100px;
            }
        }
    }
}
</style>
