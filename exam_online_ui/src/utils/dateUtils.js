export function dateToString(date) {  
    var year = date.getFullYear();//年
    var month = date.getMonth();//月
    var day = date.getDate();//日
    var hours = date.getHours();//时
    var min = date.getMinutes();//分
    var second = date.getSeconds();//秒
    return year + "-" +
        ((month + 1) > 9 ? (month + 1) : "0" + (month + 1)) + "-" +
        (day > 9 ? day : ("0" + day)) + " " +
        (hours > 9 ? hours : ("0" + hours)) + ":" +
        (min > 9 ? min : ("0" + min)) + ":" +
        (second > 9 ? second : ("0" + second));
}  

export function dateTextToString(text) {
    const date = new Date(text)  
    var year = date.getFullYear();//年
    var month = date.getMonth();//月
    var day = date.getDate();//日
    var hours = date.getHours();//时
    var min = date.getMinutes();//分
    var second = date.getSeconds();//秒
    return year + "-" +
        ((month + 1) > 9 ? (month + 1) : "0" + (month + 1)) + "-" +
        (day > 9 ? day : ("0" + day)) + " " +
        (hours > 9 ? hours : ("0" + hours)) + ":" +
        (min > 9 ? min : ("0" + min)) + ":" +
        (second > 9 ? second : ("0" + second));
}  

export function dateTextToDateString(text) {
    const date = new Date(text)  
    var year = date.getFullYear();//年
    var month = date.getMonth();//月
    var day = date.getDate();//日
    var hours = date.getHours();//时
    var min = date.getMinutes();//分
    var second = date.getSeconds();//秒
    return year + "-" +
        ((month + 1) > 9 ? (month + 1) : "0" + (month + 1)) + "-" +
        (day > 9 ? day : ("0" + day)) ; 
}  

export function dateTextToDatetimeString(text) {
    const date = new Date(text)  
    var year = date.getFullYear();//年
    var month = date.getMonth();//月
    var day = date.getDate();//日
    var hours = date.getHours();//时
    var min = date.getMinutes();//分
    var second = date.getSeconds();//秒
    return year + "-" +
    ((month + 1) > 9 ? (month + 1) : "0" + (month + 1)) + "-" +
    (day > 9 ? day : ("0" + day)) + " " +
    (hours > 9 ? hours : ("0" + hours)) + ":" +
    (min > 9 ? min : ("0" + min)) ;  
}  

export function dateRemoveSeconds(date) {  
    var year = date.getFullYear();//年
    var month = date.getMonth();//月
    var day = date.getDate();//日
    var hours = date.getHours();//时
    var min = date.getMinutes();//分
    // var second = date.getSeconds();//秒
    return year + "-" +
        ((month + 1) > 9 ? (month + 1) : "0" + (month + 1)) + "-" +
        (day > 9 ? day : ("0" + day)) + " " +
        (hours > 9 ? hours : ("0" + hours)) + ":" +
        (min > 9 ? min : ("0" + min)) + ":00" ;
}  