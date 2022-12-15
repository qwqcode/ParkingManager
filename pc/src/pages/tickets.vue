<script setup lang="ts">
import * as request from '@/lib/request'
import { Message, TableColumnData } from '@arco-design/web-vue'
import * as utils from '@/lib/utils'
import QRCode from 'qrcode'

onBeforeMount(() => {
    fetch()
})

const isOpenTicket = ref(false)
const ticketOpened = ref<any>(null)

function fetch() {
    request.post('/admin/tickets', {}).then(res => {
        tickets.value = res.data.data
    })

    request.post('/admin/ticket-presets', {}).then(res => {
        presets.value = res.data.data
    })
}


const presetColumns: TableColumnData[] = [
  {
    title: "#",
    dataIndex: "id",
  },
  {
    title: "模板名",
    dataIndex: "title",
  },
  {
    title: "可用条件",
    dataIndex: "cond",
  },
  {
    title: "条件满足值",
    dataIndex: "cond_val",
  },
  {
    title: "执行操作",
    slotName: "act",
  },
  {
    title: "执行操作值",
    dataIndex: "act_val",
  },
  {
    title: "是否可用",
    dataIndex: "is_available",
  },
  {
    title: '操作',
    slotName: 'optional'
  },
]

const ticketColumns: TableColumnData[] = [
  {
    title: "#",
    dataIndex: "id",
  },
  {
    title: "小票密钥",
    dataIndex: "ticket_key",
  },
  {
    title: "小票模板",
    slotName: "preset_id",
  },
  {
    title: "可用停车场",
    dataIndex: "park_id",
  },
  {
    title: "是否可用",
    dataIndex: "is_available",
  },
  {
    title: '操作',
    slotName: 'optional'
  },
]

const presets = ref<any[]>([])
const tickets = ref<any[]>([])

const presetForm = reactive({
    title: '',
    cond: '',
    cond_val: '',
    act: '',
    act_val: ''
})

function submitPreset() {
    request.post('/admin/ticket-preset-create', {
        title: presetForm.title,
        cond: presetForm.cond,
        cond_val: presetForm.cond_val,
        act: presetForm.act,
        act_val: presetForm.act_val,
        is_available: 1
    }).then(res => {
        if (res.data.success) {
            Message.success("小票模板创建成功")
            presetForm.title = ''
            presetForm.cond = ''
            presetForm.cond_val = ''
            presetForm.act = ''
            presetForm.act_val = ''
            fetch()
        } else {
            Message.error(res.data.msg)
        }
    })
}

const ticketForm = reactive({
    ticket_key: '',
    preset_id: undefined,
    park_id: 0,
})

function submitTicket() {
    if (!ticketForm.preset_id) {
        Message.warning("请选择小票模板")
        return
    }

    request.post('/admin/ticket-create', {
        ticket_key: ticketForm.ticket_key,
        preset_id: ticketForm.preset_id,
        park_id: ticketForm.park_id,
        is_available: 1,
    }).then(res => {
        if (res.data.success) {
            Message.success("小票创建成功")
            ticketForm.ticket_key = ''
            ticketForm.preset_id = undefined
            fetch()
        } else {
            Message.error(res.data.msg)
        }
    })
}

function genRandTicketKey() {
    ticketForm.ticket_key = utils.randomStr(20)
}

function openTicket(record: any) {
    isOpenTicket.value = true
    ticketOpened.value = record
}

const ticketOpenedQRCode = ref('')

watch(ticketOpened, (ticket) => {
    if (!ticket) {
        ticketOpenedQRCode.value = ''
        return
    }
    QRCode.toDataURL(ticket.ticket_key, { errorCorrectionLevel: 'H' })
        .then(url => {
            ticketOpenedQRCode.value = url
        })
        .catch(err => {
            console.error(err)
        })
})

function delTicket(id: number) {
    request.post('/admin/ticket-del', {
        id
    }).then((res) => {
        if (res.data.success) {
            Message.success(res.data.msg)
        } else {
            Message.error(res.data.msg)
        }
        fetch()
    })
}
</script>

<template>
    <div class="tickets-page">
        <a-card title="模板创建" style="margin-bottom: 10px;">
            <a-form :model="presetForm" @submit-success="submitPreset">
                <a-form-item field="title" label="模板名" required>
                    <a-input v-model="presetForm.title" placeholder="输入模板名称" />
                </a-form-item>
                <a-form-item label="可用条件名">
                    <a-input v-model="presetForm.cond" placeholder="当用户某条件满足时" />
                </a-form-item>
                <a-form-item label="可用条件值">
                    <a-input v-model="presetForm.cond_val" placeholder="条件满足某值时" />
                </a-form-item>
                <a-form-item field="act" label="操作名称" extra="当用户条件满足时，执行指定操作" required>
                    <a-select v-model="presetForm.act" placeholder="请选择 (可选：monthly, discount, price-de, hours-de)">
                        <a-option value="monthly">月度消费小票 (monthly)</a-option>
                        <a-option value="discount">折扣小票 (discount)</a-option>
                        <a-option value="price-de">价格抵扣小票 (price-de)</a-option>
                        <a-option value="hours-de">小时减免小票 (hours-de)</a-option>
                    </a-select>
                </a-form-item>
                <a-form-item field="act_val" label="操作参数" required>
                    <a-input v-model="presetForm.act_val" placeholder="执行操作携带的参数" />
                </a-form-item>
                <a-form-item>
                    <a-button type="primary" html-type="submit">
                        <template #icon>
                            <IconPlus />
                        </template>
                        创建模板
                    </a-button>
                </a-form-item>
            </a-form>
        </a-card>

        <a-card title="模板列表" style="margin-bottom: 10px;">
            <a-table :columns="presetColumns" :data="presets">
                <template #act="{ record }">
                    {{record.act}} ({{record.act_label}})
                </template>
                <template #optional="{ record }">
                    <a-space>
                        <a-button @click="$modal.info({ title: '编辑', content: '' })">编辑</a-button>
                        <a-button @click="$modal.info({ title: '详情查看', content: '' })">删除</a-button>
                    </a-space>
                </template>
            </a-table>
        </a-card>

        <a-card title="小票创建" style="margin-bottom: 10px;">
            <a-form :model="ticketForm" @submit-success="submitTicket">
                <a-form-item field="preset_id" label="模板选择" required>
                    <a-select v-model="ticketForm.preset_id" placeholder="请选择...">
                        <a-option v-for="p in presets" :key="p.id" :value="p.id">{{p.title}}</a-option>
                    </a-select>
                </a-form-item>
                <a-form-item label="可用停车场" required>
                    <a-input placeholder="所有停车场均可使用" :disabled="true" />
                </a-form-item>
                <a-form-item field="ticket_key" label="小票密钥" required>
                    <a-input v-model="ticketForm.ticket_key" placeholder="" />
                    <a-button style="margin-left: 10px" @click="genRandTicketKey()">随机生成</a-button>
                </a-form-item>
                <a-form-item>
                    <a-button type="primary" html-type="submit">
                        <template #icon>
                            <IconPlus />
                        </template>
                        创建小票
                    </a-button>
                </a-form-item>
            </a-form>
        </a-card>

        <a-card title="小票列表" style="margin-bottom: 10px;">
            <a-table :columns="ticketColumns" :data="tickets">
                <template #preset_id="{ record }">
                    {{ presets.find(p => p.id === record.preset_id)?.title || 'ID: '+record.preset_id }}
                </template>
                <template #optional="{ record }">
                    <a-space>
                        <a-button @click="openTicket(record)">二维码</a-button>
                        <a-button @click="$modal.info({ title: '作废', content: '' })">作废</a-button>
                        <a-button @click="$modal.info({ title: '编辑', content: '' })">编辑</a-button>
                        <a-popconfirm content="是否确认删除小票？" @ok="delTicket(record.id)">
                            <a-button>删除</a-button>
                        </a-popconfirm>
                    </a-space>
                </template>
            </a-table>
        </a-card>

        <a-modal v-model:visible="isOpenTicket" title="小票二维码" :hide-cancel="true">
            <div class="qr-code-modal">
                <div class="img">
                    <img :src="ticketOpenedQRCode" />
                </div>
                <div class="info">
                    {{ ticketOpened?.ticket_key }}
                </div>
            </div>
        </a-modal>
    </div>
</template>

<style scoped lang="scss">
.tickets-page {
    :global(.qr-code-modal) {
        width: 100%;

        .img > img {
            display: block;
            margin: 0 auto;
        }

        .info {
            text-align: center;
        }
    }
}
</style>
