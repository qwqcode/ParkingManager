export interface IUser {
    id: number
    phone: string
    openid: string
    birthday: string
    gender: string
    credit: number
    amount: number
    role: number
    status: number
    created_at: string
    updated_at: string
}

export interface IVipCard {
    id: number
    expire: string
    type: number
    user_id: number
    created_at: string
    updated_at: string
}
