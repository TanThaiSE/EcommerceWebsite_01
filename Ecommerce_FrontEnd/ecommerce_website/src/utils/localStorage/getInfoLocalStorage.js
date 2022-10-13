export const getToken=()=>{
    return localStorage.getItem("token")!==null ?(localStorage.getItem("token")):(null);
}
export const getUserId=()=>{
    return localStorage.getItem("userId")!==null ?(localStorage.getItem("userId")):(null);
}
export const getUserName=()=>{
    return localStorage.getItem("userName")!==null ?(localStorage.getItem("userName")):(null);
}
