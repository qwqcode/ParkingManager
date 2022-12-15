<script setup lang="ts">
import * as request from '@/lib/request'
import { TableColumnData } from '@arco-design/web-vue';

const isLoading = ref(false)

onBeforeMount(() => {
    fetch()
})

function fetch() {
    isLoading.value = true
    request.post('/admin/rec-pays', {}).then(res => {
        data.value = res.data.data
    }).finally(() => {
        isLoading.value = false
    })
}

const columns: TableColumnData[] = [
  {
    title: "#",
    dataIndex: "id",
  },
  {
    title: "车辆 ID",
    dataIndex: "car_id",
  },
  {
    title: "停车记录 ID",
    dataIndex: "rec_id",
  },
  {
    title: "停车场 ID",
    dataIndex: "park_id",
  },
  {
    title: "用户 ID",
    dataIndex: "user_id",
  },
  {
    title: "已付费用",
    dataIndex: "price",
  },
  {
    title: "使用小票 ID",
    dataIndex: "use_ticket_id",
  },
  {
    title: "使用优惠券 ID",
    dataIndex: "use_coupon_id",
  },
  {
    title: "余额支付",
    dataIndex: "is_use_vip_card",
  },
  {
    title: "交易时间",
    dataIndex: "created_at",
  },
  {
    title: '操作',
    slotName: 'optional'
  },
]

const data = ref([])
</script>

<template>
    <div class="rec-pays-page">
        <a-table :columns="columns" :data="data" :loading="isLoading">
            <template #had_bind="{ record }">
                {{!!record.user_id ? '是' : '否'}}
            </template>
            <template #optional="{ record }">
                <a-button v-if="!record.out_at" @click="$modal.info({ title: '详情查看', content: '' })">查看</a-button>
            </template>
        </a-table>
    </div>
</template>

<style scoped lang="scss">
.rec-pays-page {

}
</style>
