function pad(v: number) {
    return (v<10)?'0'+v:v
}

export function getDateFormatted(d: Date|string) {
    if (!d) return "-"

    if (typeof d === 'string') {
        try {
            d = new Date(d)
        } catch {
            return "-"
        }
    }

    let year = d.getFullYear()
    let month = pad(d.getMonth()+1)
    let day = pad(d.getDate())
    let hour = pad(d.getHours())
    let min = pad(d.getMinutes())
    let sec = pad(d.getSeconds())
    //YYYYMMDDhhmmss
    // return year+month+day+hour+min+sec
    //YYYY-MM-DD hh:mm:ss
    return year+"-"+month+"-"+day+" "+hour+":"+min+":"+sec
}

export function getTime2HourMin(minutes: number) {
    if (minutes < 60) return `${minutes} 分钟`
    return `${Math.floor(minutes/60)} 小时 ${(minutes%60)} 分钟`
}

export function getTime2MinSec(seconds: number) {
    if (seconds < 60) return `${seconds} 秒`
    return `${Math.floor(seconds/60)} 分钟 ${(Math.floor(seconds%60))} 秒`
}

export function fmtCarPlate(car_plate: string) {
    let plateFmt = car_plate.trim()
    if (plateFmt.length > 2 && plateFmt.charAt(2) !== ' ') {
        plateFmt = plateFmt.slice(0, 2) + ' ' + plateFmt.slice(2)
    }
    return plateFmt
}
