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
    rec_pay_at: string
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

export interface ITicketPreset {
    id: number
    act: string
    act_val: string
    act_label: string
    cond: string
    cond_val: string
    is_available: number
    created_at: string
    updated_at: string
}

export interface ITicket {
    id: number
    is_available: number
    ticket_key: string
    park_id: number
    preset_id: number
    preset: ITicketPreset
    created_at: string
    updated_at: string
}
