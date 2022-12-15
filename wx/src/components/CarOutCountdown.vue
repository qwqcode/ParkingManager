<template>
    <view class="car-out-countdown-comp">
        <template v-if="timeLeft > 0">
            <view class="main">请在 <text class="count-down">{{timeCountdown}}</text> 内驶出停车场</view>
            <view class="sub">否则将产生额外费用</view>
        </template>
        <template v-else>
            <view class="repay-note">出场超时，请重新缴费</view>
        </template>
    </view>
</template>

<script setup lang="ts">
import { computed, ref, onMounted, onUnmounted } from 'vue'
import * as utils from '@/lib/utils'

const time = ref(0)
const timerFn = ref(0)

const props = defineProps({
    car_in_at: {
        type: String,
        required: true,
    }
})

const timeLeft = computed(() => {
    const left = 30*60 - time.value
    return (left > 0) ? left : 0
})

const timeCountdown = computed(() => {
    return utils.getTime2MinSec(timeLeft.value)
})

onMounted(() => {
    time.value = getDiff()
    timerFn.value = setInterval(() => {
        time.value = getDiff()
    }, 1000)
})

onUnmounted(() => {
    if (timerFn.value) clearInterval(timerFn.value)
})

function getDiff() {
    const inAt = new Date(props.car_in_at).getTime()
    const now = new Date().getTime()
    const diff = now - inAt;

    const secondDiff = diff / 1000;
    return secondDiff
}
</script>

<style lang="scss">
.car-out-countdown-comp {
    font-size: 33px;
    text-align: center;

    .main {
        font-size: 35px;
        margin-bottom: 20px;
    }

    .count-down {
        color: red;
        font-size: 39px;
    }

    .sub {
        color: grey;
    }

    .repay-note {
        color: red;
    }
}
</style>
