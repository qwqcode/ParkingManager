// @ts-nocheck

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import AutoImport from 'unplugin-auto-import/vite'
import VueRouter from 'unplugin-vue-router/vite'
import { VueRouterAutoImports } from 'unplugin-vue-router'
import Components from 'unplugin-vue-components/vite'
import path from 'path'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    VueRouter(),
    Components({
      resolvers: []
    }),
    AutoImport({
      imports: ['vue', VueRouterAutoImports],
      resolvers: [],
    }),
  ],
  resolve: {
    alias: {
      '@': path.resolve(__dirname, "src"),
      '~': path.resolve(__dirname, '..'),
    }
  },
  server: {
    open: true,
  },
})
