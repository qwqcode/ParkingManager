import { defineStore } from 'pinia'
import type * as t from '../types'

export const TYPE_NORMAL_VIP = 1 // 普卡
export const TYPE_GOLD_VIP   = 2 // 金卡

export const useUserStore = defineStore('user', {
  state: () => {
    return {
        data: null as (t.IUser|null),
        vip_card: null as (t.IVipCard|null)
    }
  },
  actions: {
    setData(data: t.IUser|null) {
        this.data = data
    },

    setVipCard(vip_card: t.IVipCard|null) {
      this.vip_card = vip_card
    }
  },
  getters: {
    isGoldVIP() {
      return (this.vip_card && this.vip_card.type == TYPE_GOLD_VIP)
    }
  }
})
