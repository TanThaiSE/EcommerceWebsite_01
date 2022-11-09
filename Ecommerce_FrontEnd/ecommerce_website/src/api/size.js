import { getAuthor } from "../utils/cookieStorage";
import axios from "./config";
export const fetchGetSize = async () => {
    return axios.get(`/size`, { headers: getAuthor.getAuthorization() }).then((result) => {
        return result;
    });
};
export const fetchCreateSizeProduct = async (data) => {
    return axios.post(`/size`, data, { headers: getAuthor.getAuthorization() }).then((result) => {
        return result;
    });
};

export const fetchDeleteSizeProduct = async (idProduct) => {
    return axios.delete(`/${idProduct}/product`, { headers: getAuthor.getAuthorization() }).then((result) => {
        return result;
    });
};