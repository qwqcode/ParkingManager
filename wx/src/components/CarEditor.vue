<template>
    <view class="car-editor">
        <view class="input-note">请输入车牌号码</view>
        <input type="text" class="car-plate-input" v-model="car.plate">
        <view class="car-type-picker">
            <view>车牌颜色：</view>
            <picker mode="selector" :range="carTypes" @change="onCarTypeChange">
                <view class="car-type">{{car.type}} ></view>
            </picker>
        </view>

        <button class="submit-btn blue-btn" @tap="onSubmit()">{{ props.submitBtnText }}</button>
    </view>
</template>

<script setup lang="ts">
import Taro from '@tarojs/taro'
import { reactive, defineEmits } from 'vue'

interface ICarEditorData {
    plate: string
    type: string
}

const props = defineProps({
    submitBtnText: {
        type: String,
        default: '提交'
    }
})

const emits = defineEmits<{
    (event: 'submit', data: ICarEditorData): void
}>()

const car = reactive<ICarEditorData>({
    plate: '浙A',
    type: '蓝色'
})

const carTypes = ['蓝色', '黑色', '黄色']
function onCarTypeChange(e) {
    const type = carTypes[e.detail.value]
    car.type = type
}

function onSubmit() {
    if (car.plate.trim() == "" || car.plate.length < 7) {
        Taro.showToast({
            title: '请输入完整车牌号',
            icon: 'none',
            duration: 2000
        })
        return
    }

    emits('submit', car)
}
</script>

<style lang="scss">
.car-editor {
    .input-note {
        margin-bottom: 30px;
    }

    .car-plate-input {
        background: #f4f4f4;
        border-radius: 50px;
        padding: 20px 40px;
    }

    .car-type-picker {
        display: flex;
        place-items: center;
        flex-direction: row;
        margin-top: 30px;

        .car-type {
            color: #2196f3;
        }
    }

    .submit-btn {
    }
}
</style>
