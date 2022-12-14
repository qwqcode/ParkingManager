import axios from 'axios'
//@ts-ignore
import qs from 'qs'

const instance = axios.create({
    baseURL: 'http://127.0.0.1:8080/api',
    timeout: 10000,
    withCredentials: true // 请求携带 Cookie
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
