<template>
    <view class="pay-page">
        <view v-if="!carPlate" class="query-car">
            <CarEditorVue submit-btn-text="去缴费" @submit="onQueryCar" />
        </view>
        <view v-else>

        </view>
    </view>
</template>

<script setup lang="ts">
import Taro, { useRouter } from '@tarojs/taro'
import { onBeforeMount, reactive, ref } from 'vue'
import { useUserStore } from '@/stores/user'
import * as request from '@/lib/request'
import CarEditorVue from '@/components/CarEditor.vue'


const carPlate = ref('')
const router = useRouter()

onBeforeMount(() => {
    carPlate.value = router.params.car_plate || ''
    if (!carPlate.value) {
        Taro.setNavigationBarTitle({
            title: '代人缴费'
        })
    }
})

function onQueryCar(car) {
    console.log(car)
    carPlate.value = car.plate
}
</script>

<style lang="scss">
.pay-page {
    padding: 40px;
}
</style>
