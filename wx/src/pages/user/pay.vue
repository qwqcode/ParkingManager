<template>
    <view v-if="!!car && !!rec" class="pay-page">
        <view class="card">
            <view class="card-title">收费明细</view>
            <view>停车时间：{{ utils.getDateFormatted(rec.in_at) }} 至 {{ rec.out_at ? utils.getDateFormatted(rec.out_at) : '现在' }}</view>
            <view>停车时长：{{ utils.getTime2HourMin(rec.parking_time) }}</view>
            <view>停车费用：{{ Number(rec.parking_price).toFixed(2) }} 元</view>
            <view>当前状态：{{ rec.status_text }}</view>
        </view>
        <view class="card">
            <view class="card-title">小票抵扣</view>
            <view class="ticket">
                <view v-if="!ticket" @tap="openScanTicket()" class="scan-ticket">+ 扫描二维码使用小票</view>
                <view v-else class="added-ticket">
                    <view class="ticket-title">{{ ticket.preset.act_label }} {{ ticket.preset.act_val }} 小时 <text class="badge">有效</text></view>
                    <view class="acts"><text @tap="showTicketDetail()">查看明细</text> · <text @tap="dropTicket()">删除</text></view>
                </view>
            </view>
        </view>
        <view class="pay-act card">
            <view class="card-title">付款</view>
            <view class="price">最终应付 <text v-if="priceFinal !== null" class="num">{{ priceFinal.toFixed(2) }} 元</text></view>
            <view class="pay-methods">
                <view class="item checked">微信支付</view>
                <view class="item">会员卡支付</view>
                <view class="item">积分支付</view>
            </view>
            <view class="pay-btn" @tap="submitPay()">立即支付</view>
        </view>
    </view>
</template>

<script setup lang="ts">
import Taro, { useRouter } from '@tarojs/taro'
import { onBeforeMount, ref, reactive } from 'vue'
import * as request from '@/lib/request'
import * as utils from '@/lib/utils'
import * as t from '@/types'

const car = ref<t.ICar|null>(null)
const rec = ref<t.IRec|null>(null)
const router = useRouter()

const ticket = ref<t.ITicket|null>(null)
const priceFinal = ref<number|null>(null)

onBeforeMount(() => {
    const { rec_id, car_id } = router.params

    if (!rec_id || !car_id) {
        Taro.showToast({ title: 'rec_id 和 car_id 不能为空', icon: 'none', duration: 2000 })
        return
    }

    request.post('/user/car-query', { car_id }).then((d) => {
        console.log(d)
        car.value = d.car
        rec.value = car.value?.recs?.find(r => r.id == Number(rec_id)) || null

        reCalcPrice()
    })
})

function openScanTicket() {
    Taro.scanCode({
        onlyFromCamera: true,
        success: (res) => {
            console.log("二维码识别内容：", res.result)
            tryUseTicket(res.result)
        }
    })
}

function tryUseTicket(ticket_key: string) {
    request.post('/user/ticket-query', { ticket_key }).then(d => {
        console.log(d.ticket, d.preset)
        const t = d.ticket
        t.preset = d.preset
        ticket.value = t

        reCalcPrice()
    })
}

function showTicketDetail() {
    Taro.showModal({
        title: '小票明细',
        content: JSON.stringify(ticket.value),
        showCancel: false
    })
}

function dropTicket() {
    ticket.value = null

    reCalcPrice()
}

function reCalcPrice() {
    request.post('/user/rec-pay', {
        rec_id: rec.value?.id || 0,
        ticket_key: ticket.value?.ticket_key || '',
        is_challenge: 1,
    }).then(d => {
        console.log(d)
        priceFinal.value = Number(d)
    })
}

function submitPay() {
    Taro.showModal({
        title: '模拟付款',
        content: '是否立即付款',
        success: (res) => {
            if (res.confirm) _submitPay()
        }
    })
}

function _submitPay() {
    request.post('/user/rec-pay', {
        rec_id: rec.value?.id || 0,
        ticket_key: ticket.value?.ticket_key || '',
        is_challenge: 0,
    }).then(d => {
        // Taro.navigateBack()
        // setTimeout(() => {
        //     Taro.showToast({
        //         title: '缴费成功',
        //         icon: 'none',
        //         duration: 2000
        //     })
        // }, 80)

        Taro.redirectTo({
            url: '/pages/user/pay-done?show_countdown=' + (!rec.value?.out_at ? 1 : 0)
        })
    })
}
</script>

<style lang="scss">
.pay-page {
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

    .ticket {
        .scan-ticket {
            padding: 50px 40px;
            border-radius: 5px;
            text-align: center;
            border: 1px solid #f4f4f4;
        }

        .added-ticket {
            position: relative;
            padding: 50px 55px 50px 40px;
            border-radius: 5px;
            text-align: center;
            border: 4px solid #4caf50;

            .badge {
                top: 30px;
                right: 20px;
                position: absolute;
                background: #4caf50;
                color: #fff;
                padding: 2px 10px;
                border-radius: 5px;
                margin-right: 10px;
                font-size: 24px;
            }

            .ticket-title {
                font-size: 32px;
                color: #2b762e;
            }

            .acts {
                margin-top: 30px;
                font-size: 27px;
            }
        }
    }

    .pay-act {
        .price {
            text-align: center;
            .num {
                color: red;
                font-weight: bold;
                font-size: 38px;
            }
        }

        .pay-methods {
            padding: 50px 40px;

            .item {
                position: relative;
                display: block;
                width: 100%;
                padding: 10px 10px;

                &:after {
                    position: absolute;
                    display: block;
                    content: '';
                    right: 20px;
                    top: 20px;
                    width: 20px;
                    height: 20px;
                    background: #e0e0e0;
                    border: 2px solid #e0e0e0;
                    border-radius: 50%;
                }

                &.checked:after {
                    border: 2px solid #e32625;
                    background: #e32625;
                    background-repeat: no-repeat;
                    background-size: 70%;
                    background-position: center;
                    background-image: url("data:image/svg+xml,%3Csvg width='48' height='48' viewBox='0 0 48 48' fill='none' xmlns='http://www.w3.org/2000/svg'%3E%3Cpath d='M41.6776 11.0502L19.0502 33.6776L6.32227 20.9497' stroke='white' stroke-width='6'/%3E%3C/svg%3E");
                }
            }

            .item:not(:last-child) {
                margin-bottom: 10px;
                border-bottom: 1px solid #f4f4f4;
            }
        }

        .pay-btn {
            padding: 20px 40px;
            text-align: center;
            width: 100%;
            display: block;
            color: #fff;
            background: #e32625;
            border-radius: 100px;
        }
    }
}
</style>
