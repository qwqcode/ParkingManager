import { defineStore } from 'pinia'

export const useAppStore = defineStore('app', {
  state: () => ({
    isUseLayout: true
  }),
  getters: {
  },
  actions: {
    setUseLayout(val: boolean) {
        this.isUseLayout = val
    },
  }
})
