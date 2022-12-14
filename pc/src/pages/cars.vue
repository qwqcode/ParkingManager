<script setup lang="ts">
import { useAppStore } from '../stores/app'
import * as request from '@/lib/request'
import { PaginationProps, TableColumnData } from '@arco-design/web-vue';

const PAGE_SIZE = 15
const isLoading = ref(false)

onBeforeMount(() => {
    fetch(0)
})

function fetch(offset: number) {
    isLoading.value = true
    request.post('/admin/cars', {
        offset: offset,
        limit: PAGE_SIZE
    }).then(res => {
        pagination.total = res.data.data.total
        data.value = res.data.data.cars
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
    dataIndex: "plate",
  },
  {
    title: "车牌类型",
    dataIndex: "plate_type",
  },
  {
    title: "用户 ID",
    dataIndex: "user_id",
  },
  {
    title: "是否被绑定",
    slotName: "had_bind",
  },
  {
    title: '操作',
    slotName: 'optional'
  },
]

const data = ref([])
</script>

<template>
    <div class="cars-page">
        <a-table :columns="columns" :data="data" :pagination="pagination" :loading="isLoading" @page-change="onPageChange">
            <template #had_bind="{ record }">
                {{!!record.user_id ? '是' : '否'}}
            </template>
            <template #optional="{ record }">
                <!-- <a-button v-if="!record.out_at" @click="$modal.info({ title:'Name', content: '' })">编辑</a-button> -->
            </template>
        </a-table>
    </div>
</template>

<style scoped lang="scss">
.cars-page {

}
</style>
