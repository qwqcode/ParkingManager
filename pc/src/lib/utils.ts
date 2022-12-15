export function randomStr(length: number) {
    var result           = ''
    var characters       = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789'
    var charactersLength = characters.length
    for ( var i = 0; i < length; i++ ) {
        result += characters.charAt(Math.floor(Math.random() * charactersLength))
    }
    return result
}

export function fmtCarPlate(car_plate: string) {
    let plateFmt = car_plate.trim()
    if (plateFmt.length > 2 && plateFmt.charAt(2) !== ' ') {
        plateFmt = plateFmt.slice(0, 2) + ' ' + plateFmt.slice(2)
    }
    return plateFmt
}
