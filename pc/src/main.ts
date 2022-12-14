import { createApp } from 'vue'
import { createPinia } from 'pinia'
import { createRouter, createWebHistory } from 'vue-router/auto'
import './style.css'
import App from './App.vue'
import ArcoVue from '@arco-design/web-vue'
import ArcoVueIcon from '@arco-design/web-vue/es/icon'
import '@arco-design/web-vue/dist/arco.css'

const pinia = createPinia()
const app = createApp(App);

const router = createRouter({
    history: createWebHistory(),
})

app.use(router)
app.use(pinia)
app.use(ArcoVue)
app.use(ArcoVueIcon)

app.mount('#app')
