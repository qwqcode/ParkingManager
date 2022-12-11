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

export interface IRec {
    id: number
    car_id: number
    in_at: string
    out_at: string
    park_id: number
    parking_price: number
    parking_time: number
    rec_pay_id: number
    created_at: string
    updated_at: string
    status_text: string
}

export interface ICar {
    id: number
    plate: string
    plate_type: string
    user_id: number
    created_at: string
    updated_at: string
    recs?: IRec[]
}
