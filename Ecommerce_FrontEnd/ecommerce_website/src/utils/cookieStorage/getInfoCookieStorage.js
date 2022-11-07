import Cookies from 'universal-cookie';
const cookies = new Cookies();

export const getId=()=>{
    return cookies.get("id")!==undefined ?(cookies.get("id")):(undefined);
}
export const getUserName=()=>{
    return cookies.get("userName")!==undefined ?(cookies.get("userName")):(undefined);
}
export const idRole=()=>{
    return cookies.get("idRole")!==undefined ?(cookies.get("idRole")):(undefined);
}
export const nameRole=()=>{
    return cookies.get("nameRole")!==undefined ?(cookies.get("nameRole")):(undefined);
}
export const getToken=()=>{
    return cookies.get("token")!==undefined ?(cookies.get("token")):(undefined);
}