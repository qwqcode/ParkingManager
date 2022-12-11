import Taro from '@tarojs/taro'

export const API_BASE = 'http://localhost:8080/api'
export const CommonHeader = () => ({
  'Accept': 'application/json',
  'Content-type': 'application/x-www-form-urlencoded',
  'Cookie': Taro.getStorageSync('cookie')
})

/**
 * 封装请求
 */
export async function request(path: string, method: 'GET'|'POST', data: any = {}) {
  const url = `${API_BASE}/${path.replace(/^\//, '')}`
  const header = { ...CommonHeader() }
  const showErr = (errType: string, err: any) => {
    Taro.showModal({
      title: `[${errType}] 请向我们反馈，谢谢！`,
      content: JSON.stringify(err),
      showCancel: false
    })
  }

  // 执行 Taro.request
  let res: Taro.request.SuccessCallbackResult
  try {
    res = await Taro.request({
      url, data, method, header,
      credentials: 'include',
    })
  } catch (err) {
    console.error('网络错误', err)
    showErr('网络错误', err)
    throw err
  }

  if (res.statusCode === 500) {
    console.error('500 错误', res)
    showErr('500 错误', res.data)
    throw res
  }

  // 保存 Cookie 以供下次请求使用
  if (res.header['Set-Cookie']) {
    Taro.setStorageSync('cookie', res.header['Set-Cookie'])
  }

  const resData = res.data
  if (!!resData && resData.success === false) {
    console.error(resData)
    // 使用 zero setTimeout 防止被 Taro.hideLoading 立刻隐藏
    setTimeout(() => {
      Taro.showToast({
        title: resData.msg || '接口错误',
        duration: 2000,
        icon: 'none',
        mask: false // 是否显示透明蒙层，防止触摸穿透
      })
    }, 0)
    throw resData
  }

  return resData.data
}

export function post(path: string, data: any = {}) {
  return request(path, 'POST', data)
}

export function get(path: string, data: any = {}) {
  return request(path, 'GET', data)
}
