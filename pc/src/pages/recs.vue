<script setup lang="ts">
import { useAppStore } from '../stores/app'
import * as request from '@/lib/request'
import { PaginationProps, TableColumnData } from '@arco-design/web-vue';

const app = useAppStore()
const PAGE_SIZE = 15
const isLoading = ref(false)

onBeforeMount(() => {
    app.setUseLayout(true)
    fetch(0)
})

function fetch(offset: number) {
    isLoading.value = true
    request.post('/admin/recs', {
        offset: offset,
        limit: PAGE_SIZE
    }).then(res => {
        pagination.total = res.data.data.total
        data.value = res.data.data.recs
    }).finally(() => {
        isLoading.value = false
    })
}

const pagination = reactive<PaginationProps>({
    total: 0,
    current: 1,
    defaultCurrent: 1,
    pageSize: PAGE_SIZE,
})

function onPageChange(page: number) {
    pagination.current = page
    fetch(page*PAGE_SIZE)
}

const columns: TableColumnData[] = [
  {
    title: "#",
    dataIndex: "id",
  },
  {
    title: "车辆",
    dataIndex: "car_plate",
  },
  {
    title: "停车场",
    dataIndex: "park_id",
  },
  {
    title: "入场",
    dataIndex: "in_at_formatted",
  },
  {
    title: '出场',
    dataIndex: 'out_at_formatted',
  },
  {
    title: '时间',
    dataIndex: 'parking_time_text',
  },
  {
    title: '费用',
    slotName: 'price',
  },
  {
    title: '状态',
    dataIndex: 'status_text'
  },
  {
    title: '操作',
    slotName: 'optional'
  },
]

const data = ref([])

const form = reactive({
    edit_id: null,
    car_plate: '浙A 12345',
})

function submitAddRec() {

}
</script>

<template>
    <div class="recs-page">
        <a-card title="模拟停车" style="margin-bottom: 10px;">
            <a-form :model="form" layout="inline" @submit="submitAddRec">
                <a-form-item field="name" label="车牌号">
                    <a-input v-model="form.car_plate" placeholder="例如，京A 777777" />
                </a-form-item>
                <a-form-item field="name" label="停车场 ID">
                    <a-input default-value="1" :disabled="true" style="width: 5em" />
                </a-form-item>
                <a-form-item>
                    <a-button type="primary">
                        <template #icon>
                            <IconArrowRight />
                        </template>
                        进入停车场
                    </a-button>
                </a-form-item>
            </a-form>
        </a-card>

        <a-table :columns="columns" :data="data" :pagination="pagination" :loading="isLoading" @page-change="onPageChange">
            <template #price="{ record }">
                {{record.parking_price}} 元
            </template>
            <template #optional="{ record }">
                <a-button v-if="!record.out_at" @click="$modal.info({ title:'Name', content: '' })">模拟出场</a-button>
            </template>
        </a-table>
    </div>
</template>

<style scoped lang="scss">
.recs-page {

}
</style>
