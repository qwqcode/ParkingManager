import { Message } from '@arco-design/web-vue'
import axios from 'axios'
//@ts-ignore
import qs from 'qs'

const instance = axios.create({
    baseURL: 'http://127.0.0.1:8080/api',
    timeout: 10000,
    withCredentials: true // 请求携带 Cookie
})

instance.interceptors.response.use((resp) => {
    return resp
}, (error) => {
    if (error.response.status === 401) {
        Message.error("登录状态失效，请重新登录")
        location.href = '/'
    }
    return Promise.reject(error)
})

export default instance

export function post(path: string, data: any = {}) {
    return instance({
        method: 'POST',
        headers: { 'content-type': 'application/x-www-form-urlencoded' },
        data: qs.stringify(data),
        url: path,
    })
}

export function get(path: string, data?: any) {
    return instance({
        method: 'GET',
        params: data,
        url: path,
    })
}
