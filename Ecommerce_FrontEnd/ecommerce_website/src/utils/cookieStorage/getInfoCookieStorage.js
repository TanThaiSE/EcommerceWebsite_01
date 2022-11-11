import Cookies from 'universal-cookie';
const cookies = new Cookies();

export const getId=()=>{
    return cookies.get("id")!==undefined ?(cookies.get("id")):(undefined);
}
export const getUserName=()=>{
    return cookies.get("userName")!==undefined ?(cookies.get("userName")):(undefined);
}
export const getIdRole=()=>{
    return cookies.get("idRole")!==undefined ?(cookies.get("idRole")):(undefined);
}
export const getNameRole=()=>{
    return cookies.get("nameRole")!==undefined ?(cookies.get("nameRole")):(undefined);
}
export const getToken=()=>{
    return cookies.get("token")!==undefined ?(cookies.get("token")):(undefined);
}

/*
    FRONT END
        +) props (truyền đc) , state (ko truyền đc): như thế nào
        +) sử lý product còn hàng hay không
        +) account bị active
        +) Check lại xóa hình
    BACK END
        +) tận dụng jpa
        +) flow authen, author 
        +) viết thêm login có bị block chưa. trả lỗi ra. front end nhận show lên.
*/