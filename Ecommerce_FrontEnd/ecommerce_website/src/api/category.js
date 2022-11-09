import axios from "./config";
import { getAuthor } from "../utils/cookieStorage";
export const fetchCategory = async () => {
    return axios.get(`/category`).then((result) => {
        return result;
    });
};

export const fetchCreateCategory = async (data) => {
    return axios.post(`/category`, data, { headers: getAuthor.getAuthorization() }).then((result) => {
        return result;
    });
};
export const fetchUpdateCategory = async (data) => {
    return axios.put(`/category`, data,{ headers: getAuthor.getAuthorization() }).then((result) => {
        return result;
    });
};

export const fetchCategoryManger = async (page, offset) => {
    return axios.get(`/category/manager`, { params: { page: page, offset: offset },headers: getAuthor.getAuthorization() }).then((result) => {
        return result;
    });
};

export const fetchDeleteEmptyCategory = async (idProduct) => {
    return axios.delete(`/category/${idProduct}`, { headers: getAuthor.getAuthorization() }).then((result) => {
        return result;
    });
};