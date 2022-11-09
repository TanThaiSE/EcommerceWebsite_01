import { getAuthor } from "../utils/cookieStorage";
import axios from "./config";

export const fetchCreateImageProduct = async (data) => {
    return axios.post(`/image`, data, { headers: getAuthor.getAuthorization() }).then((result) => {
        return result;
    });
};

export const fetchDeleteImageProduct = async (idProduct) => {
    return axios.delete(`/${idProduct}/product`, { headers: getAuthor.getAuthorization() }).then((result) => {
        return result;
    });
};