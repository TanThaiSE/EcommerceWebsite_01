import { getAuthor } from "../utils/localStorage";
import axios from "./config";

export const fetchGetCart = async (data) => {
    return axios.get(`/cart`, data,{ headers: getAuthor.getAuthorization() }).then((result) => {
        return result;
    });
};

export const fetchAddCart = async (data) => {
    return axios.post(`/cart`, data,{ headers: getAuthor.getAuthorization() }).then((result) => {
        return result;
    });
};

export const fetchUpdateCart = async (data) => {
    return axios.put(`/cart`, data,{ headers: getAuthor.getAuthorization() }).then((result) => {
        return result;
    });
};

export const fetchDeleteCart = async (data) => {
    return axios.delete(`/cart`, data,{ headers: getAuthor.getAuthorization() }).then((result) => {
        return result;
    });
};