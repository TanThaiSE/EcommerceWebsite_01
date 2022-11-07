import Cookies from "universal-cookie";
const cookies = new Cookies();
export const saveLogin=(data)=>{
    cookies.set("id",data.id);
    cookies.set("userName",data.userName);
    cookies.set("idRole",data.idRole);
    cookies.set("nameRole",data.nameRole);
    cookies.set("token",data.token);
}