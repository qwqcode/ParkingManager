<template>
    <view class="rules-page">
        <view class="title">收费规则</view>
        <view class="lines">
            <view>停车时间不满 30 分钟免费</view>
            <view>时间不满一小时按一小时计费</view>
            <view>每小时收费 2 元</view>
        </view>
    </view>
</template>

<script setup lang="ts">

</script>

<style lang="scss">
.rules-page {
    padding: 30px;
    text-align: center;

    .title {
        font-size: 50px;
        margin-bottom: 30px;
    }

    .lines {
        line-height: 2;
    }
}
</style>
