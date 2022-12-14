<script setup lang="ts">
function handleLogout() {

}
</script>

<template>
  <div class="navbar">
    <div class="left-side">
      <a-space>
        <img
          alt="logo"
          src="//p3-armor.byteimg.com/tos-cn-i-49unhts6dw/dfdba5317c0c20ce20e64fac803d52bc.svg~tplv-49unhts6dw-image.image"
        />
        <a-typography-title
          :style="{ margin: 0, fontSize: '18px' }"
          :heading="5"
        >
          停车管理系统
        </a-typography-title>
        <!-- <icon-menu-fold
          v-if="appStore.device === 'mobile'"
          style="font-size: 22px; cursor: pointer"
          @click="toggleDrawerMenu"
        /> -->
      </a-space>
    </div>
    <ul class="right-side">
      <!-- 搜索 -->
      <li>
        <a-tooltip content="搜索">
          <a-button
            class="nav-btn"
            type="outline"
            :shape="'circle'"
          >
            <template #icon>
              <icon-search />
            </template>
          </a-button>
        </a-tooltip>
      </li>


      <!-- 通知按钮 -->
      <li>
        <a-tooltip content="通知">
          <a-button
            class="nav-btn"
            type="outline"
            :shape="'circle'"
          >
            <template #icon>
              <icon-notification />
            </template>
          </a-button>
        </a-tooltip>
      </li>

      <!-- 用户头像 -->
      <li>
        <a-dropdown trigger="click">
          <a-avatar
            :size="32"
            :style="{ marginRight: '8px', cursor: 'pointer' }"
          >
            <img alt="avatar" src="https://p1-arco.byteimg.com/tos-cn-i-uwbnlip3yd/8361eeb82904210b4f55fab888fe8416.png~tplv-uwbnlip3yd-webp.webp" />
          </a-avatar>
          <template #content>
            <a-doption>
              <a-space @click="() => {}">
                <icon-settings />
                <span>设置</span>
              </a-space>
            </a-doption>
            <a-doption>
              <a-space @click="handleLogout">
                <icon-export />
                <span>注销</span>
              </a-space>
            </a-doption>
          </template>
        </a-dropdown>
      </li>
    </ul>
  </div>
</template>

<style scoped lang="scss">
.navbar {
  display: flex;
  justify-content: space-between;
  height: 100%;
  background-color: var(--color-bg-2);
  border-bottom: 1px solid var(--color-border);
}

.left-side {
  display: flex;
  align-items: center;
  padding-left: 20px;
}
.right-side {
  display: flex;
  padding-right: 20px;
  list-style: none;
  :deep(.locale-select) {
    border-radius: 20px;
  }
  li {
    display: flex;
    align-items: center;
    padding: 0 10px;
  }
  a {
    color: var(--color-text-1);
    text-decoration: none;
  }
  .nav-btn {
    border-color: rgb(var(--gray-2));
    color: rgb(var(--gray-8));
    font-size: 16px;
  }
  .trigger-btn,
  .ref-btn {
    position: absolute;
    bottom: 14px;
  }
  .trigger-btn {
    margin-left: 14px;
  }
}
</style>
