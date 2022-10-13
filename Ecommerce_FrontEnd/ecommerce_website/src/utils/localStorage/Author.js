import { getLogin} from ".";

export const getAuthorization=()=>{
    return { 'Authorization': 'Bearer ' + getLogin.getToken() };
}
