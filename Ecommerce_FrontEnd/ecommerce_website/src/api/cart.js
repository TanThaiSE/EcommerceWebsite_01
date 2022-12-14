import { getAuthor, getLogin } from "../utils/cookieStorage";
import axios from "./config";

export const fetchGetCart = async (accountId) => {
    return axios.get(`/cart/${accountId}`, { headers: getAuthor.getAuthorization() }).then((result) => {
        return result;
    });
};

export const fetchAddCart = async (data) => {
    return axios.post(`/cart`, data, { headers: getAuthor.getAuthorization() }).then((result) => {
        return result;
    });
};

export const fetchUpdateCart = async (id, data) => {
    return axios.put(`/cart/${id}/product`, data, { headers: getAuthor.getAuthorization() }).then((result) => {
        return result;
    });
};

export const fetchDeleteCart = async (idCart,idAccount) => {
    return axios.delete(`/cart/${idCart}/${idAccount}/account`, { headers: getAuthor.getAuthorization() }).then((result) => {
        return result;
    });
};

export const fetchDeleteMultipleProduct = async (data1) => {
    return axios.delete(`/cart`, { data: data1, headers: getAuthor.getAuthorization() }).then((result) => {
        return result;
    });
};

