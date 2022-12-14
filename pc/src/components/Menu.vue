<script setup lang="ts">
const route = useRoute()
const router = useRouter()
const selectedKey = ref<string>(route.path)

interface IMenuItem {
    path: string
    name: string
    icon: string
}

const menus = ref<IMenuItem[]>([
    { path: '/recs', name: '停车记录', icon: 'IconCalendarClock' },
    { path: '/cars', name: '车辆管理', icon: 'IconFire' },
    { path: '/tickets', name: '小票管理', icon: 'IconLayers' },
    { path: '/rec-pays', name: '缴费记录', icon: 'IconArchive' },
    { path: '/users', name: '用户管理', icon: 'IconUser' },
    { path: '/parks', name: '车场管理', icon: 'IconLocation' },
    { path: '/settings', name: '系统设置', icon: 'IconSettings' },
    { path: '/', name: '退出系统', icon: 'IconExport' },

])

function onClickMenuItem(path: string) {
    router.replace(path)
}

router.afterEach((to, from) => {
    selectedKey.value = to.path
})
</script>

<template>
    <div class="menu">
        <a-menu
            :selected-keys="[selectedKey]"
            :style="{ width: '100%' }"
            @menu-item-click="onClickMenuItem"
        >
            <a-menu-item v-for="item in menus" :key="item.path">
                <component :is="item.icon" />
                {{item.name}}
            </a-menu-item>
        </a-menu>
    </div>
</template>

<style scoped lang="scss">
.menu {
    margin-top: 65px;
}
</style>
