<script setup lang="ts">
import { useAppStore } from '../stores/app'
import * as request from '@/lib/request'
import { Message, PaginationProps, TableColumnData } from '@arco-design/web-vue';

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

function refresh() {
  pagination.current = 1
  fetch(0)
}

const form = reactive({
    edit_id: null,
    car_plate: '浙A 12345',
    car_plate_type: '蓝色'
})

function submitAddRec() {
  if (form.car_plate.trim() == '' || form.car_plate.length < 7) {
    Message.warning("请输入完整车牌号")
    return
  }

  request.post('/internal/car-in', {
    car_plate: form.car_plate,
    car_plate_type: form.car_plate_type,
    park_id: 1
  }).then(res => {
    if (res.data.success) {
      Message.success("模拟停车成功")
      refresh()
    } else {
      Message.error(res.data.msg)
    }
  })
}

function setCarOut(car_plate: string) {
  request.post('/internal/car-out', {
    car_plate
  }).then(res => {
    if (res.data.success) {
      Message.success("模拟出场成功")
      refresh()
    } else {
      Message.error(res.data.msg)
    }
  })
}
</script>

<template>
    <div class="recs-page">
        <a-card title="模拟停车" style="margin-bottom: 10px;">
            <a-form :model="form" layout="inline" @submit="submitAddRec">
                <a-form-item label="车牌号">
                    <a-input v-model="form.car_plate" placeholder="例如，京A 777777" />
                </a-form-item>
                <a-form-item label="车牌类型">
                    <a-select v-model="form.car_plate_type" placeholder="请选择...">
                        <a-option value="蓝色">蓝色</a-option>
                        <a-option value="黑色">黑色</a-option>
                        <a-option value="黄色">黄色</a-option>
                    </a-select>
                </a-form-item>
                <a-form-item label="停车场 ID">
                    <a-input default-value="1" :disabled="true" style="width: 5em" />
                </a-form-item>
                <a-form-item>
                    <a-button type="primary" html-type="submit">
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
                <a-button :disabled="!!record.out_at" @click="setCarOut(record.car_plate)">出场</a-button>
            </template>
        </a-table>
    </div>
</template>

<style scoped lang="scss">
.recs-page {

}
</style>
