import axios from "./config";
import { getAuthor } from "../utils/cookieStorage";
//co authen
export const fetchUpdatePassword = async (data) => {
    return axios.put(`/accounts`, data,{ headers: getAuthor.getAuthorization() }).then((result) => {
        return result;
    });
};

export const fetchCreateAccount = async (data) => {
    return axios.post(`/accounts`, data,{ headers: getAuthor.getAuthorization() }).then((result) => {
        return result;
    });
};
