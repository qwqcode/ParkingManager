<script lang="ts" setup>
import Navbar from '../components/Navbar.vue'
import Menu from '../components/Menu.vue'
import { useAppStore } from '../stores/app'

const app = useAppStore()
</script>

<template>
  <a-layout v-if="app.isUseLayout" class="layout">
    <div class="layout-navbar">
      <Navbar />
    </div>

    <a-layout>
      <a-layout>

        <a-layout-sider collapsible breakpoint="xl">
          <div class="menu-wrapper">
            <Menu />
          </div>
        </a-layout-sider>

        <a-layout class="layout-content">
          <a-layout-header style="height: 60px;margin-bottom: 20px;"></a-layout-header >

          <a-layout style="padding: 0 24px;">
            <a-layout-content>
              <slot />
            </a-layout-content>

            <a-layout-footer style="padding: 10px 0;color: grey;">© 2022 期末大作业 - 2022-12 - 219050423</a-layout-footer>
          </a-layout>

          </a-layout>
        </a-layout>

    </a-layout>
  </a-layout>
  <div v-else>
    <slot />
  </div>
</template>

<style scoped lang="scss">
.layout {
  width: 100vw;
  height: 100vh;
}

.layout-navbar {
  position: fixed;
  top: 0;
  left: 0;
  z-index: 100;
  width: 100%;
  height: 60px;
}

.menu-wrapper {
  height: 100%;
  overflow: auto;
  overflow-x: hidden;

  :deep(.arco-menu) {
    ::-webkit-scrollbar {
      width: 12px;
      height: 4px;
    }
    ::-webkit-scrollbar-thumb {
      border: 4px solid transparent;
      background-clip: padding-box;
      border-radius: 7px;
      background-color: var(--color-text-4);
    }
    ::-webkit-scrollbar-thumb:hover {
      background-color: var(--color-text-3);
    }
  }
}

.layout-content {
  min-height: 100vh;
  overflow-y: hidden;
  background-color: var(--color-fill-2);
  transition: padding 0.2s cubic-bezier(0.34, 0.69, 0.1, 1);
}
</style>
