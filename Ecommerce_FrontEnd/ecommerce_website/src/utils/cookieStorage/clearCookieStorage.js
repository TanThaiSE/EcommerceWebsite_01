import Cookies from 'universal-cookie';
const cookies = new Cookies();
export const clearLocalStorage=()=>{
    localStorage.clear();
    cookies.remove("id");
    cookies.remove("userName");
    cookies.remove("idRole");
    cookies.remove("nameRole");
    cookies.remove("token");
}