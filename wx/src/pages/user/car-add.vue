<template>
    <view class="car-add-page">
        <CarEditorVue submit-btn-text="添加车辆" @submit="onSubmit" />
    </view>
</template>

<script setup lang="ts">
import Taro from '@tarojs/taro'
import * as request from '@/lib/request'
import CarEditorVue from '@/components/CarEditor.vue'

function onSubmit(data) {
    console.log(data)
    request.post('/user/bind-car', {
        car_plate: data.plate,
        car_plate_type: data.type
    }).then(() => {
        Taro.navigateBack()
    })
}
</script>

<style lang="scss">
.car-add-page {
    padding: 40px;
}
</style>
