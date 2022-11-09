import Cookies from 'universal-cookie';
const cookies = new Cookies();
export const clearLocalStorage=()=>{
    cookies.remove("id",{path:'/'});
    cookies.remove("userName",{path:'/'});
    cookies.remove("idRole",{path:'/'});
    cookies.remove("nameRole",{path:'/'});
    cookies.remove("token",{path:'/'});
    localStorage.clear();
}