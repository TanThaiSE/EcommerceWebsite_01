import { getAuthor } from "../utils/cookieStorage";
import axios from "./config";
export const fetchGetColor = async () => {
    return axios.get(`/color`, { headers: getAuthor.getAuthorization() }).then((result) => {
        return result;
    });
};

export const fetchCreateColorProduct = async (data) => {
    return axios.post(`/color`, data, { headers: getAuthor.getAuthorization() }).then((result) => {
        return result;
    });
};

export const fetchDeleteColorProduct = async (idProduct) => {
    return axios.delete(`/${idProduct}/product`, { headers: getAuthor.getAuthorization() }).then((result) => {
        return result;
    });
};