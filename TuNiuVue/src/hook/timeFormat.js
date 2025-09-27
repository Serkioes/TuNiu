// export const formattedDate = (time) => {
//     const date = time;
//     const year = date.getFullYear();
//     const month = (date.getMonth() + 1).toString().padStart(2, '0');
//     const day = date.getDate().toString().padStart(2, '0');
//     const hour = date.getHours().toString().padStart(2, '0');
//     const minute = date.getMinutes().toString().padStart(2, '0');
//     const second = date.getSeconds().toString().padStart(2, '0');
//     const formattedDate = `${year}-${month}-${day} ${hour}:${minute}:${second}`;
//     return formattedDate;
// }

export const formattedDate = (timestamp) =>{
    const date = new Date(timestamp);
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const day = date.getDate().toString().padStart(2, '0');
    const formattedDate = `${year}-${month}-${day}`;
    return formattedDate
}


export const dateAndweek = (timestamp) => {
    const date = new Date(timestamp);
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const day = date.getDate().toString().padStart(2, '0');
    const show_day = ['周日', '周一', '周二', '周三', '周四', '周五', '周六'];
    const dayOfWeek = date.getDay();
    const formattedDate = `${month}-${day} ${show_day[dayOfWeek]}`;
    return formattedDate
}


export const NoSecond = (timestamp) =>{
    const date = new Date(timestamp);
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const day = date.getDate().toString().padStart(2, '0');
    const hour = date.getHours().toString().padStart(2, '0');
    const minute = date.getMinutes().toString().padStart(2, '0');
    const formattedDate = `${year}-${month}-${day} ${hour}:${minute}`;
    return formattedDate
}

export const yearAndMonthAndDay = (timestamp) =>{
    const date = new Date(timestamp);
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const day = date.getDate().toString().padStart(2, '0');
    const formattedDate = `${year}-${month}-${day}`;
    return formattedDate
}


export const hourAndMinute = (timestamp) =>{
    const date = new Date(timestamp);
    const hour = date.getHours().toString().padStart(2, '0');
    const minute = date.getMinutes().toString().padStart(2, '0');
    const formattedDate = `${hour}:${minute}`;
    return formattedDate
}

export const last = (timestamp) =>{
    if (timestamp){
        const hour = timestamp.split(':')[0].startsWith('0') ? timestamp.split(':')[0].split("0")[1] : timestamp.split(':')[0] //没有0情况
        const minute = timestamp.split(':')[1].startsWith('0') ? timestamp.split(':')[1].substring(1,2) : timestamp.split(':')[1]   //没有0情况
        return `${hour}时${minute}分`;
    }
}


export const back = (timestamp) =>{
    const date = new Date()
    const split = timestamp.split(' ')[0];
    const year = date.getFullYear();
    return `${year}-${split}`
}