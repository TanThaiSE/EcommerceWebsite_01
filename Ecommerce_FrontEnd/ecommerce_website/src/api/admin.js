import { getAuthor } from "../utils/localStorage";
import axios from "./config";

//co autho
export const fetchGetUsers = async (page,offset) => {
    return axios.get(`/admin/users`,{params:{page:page,offset:offset }}).then((result) => {
        return result;
    });
};

export const fetchUpdateBlockedUser = async (idAccount) => {
    return axios.put(`/admin/${idAccount}/access-rights`).then((result) => {
        return result;
    });
};

export const fetchCategory = async (page,offset) => {
    return axios.get(`/admin/category`,{params:{page:page,offset:offset }}).then((result) => {
        return result;
    });
};

export const fetchUpdateCategory = async (data) => {
    return axios.put(`/admin/category`,data).then((result) => {
        return result;
    });
};

export const fetchCreateCategory = async (data) => {
    return axios.post(`/admin/category`,data).then((result) => {
        return result;
    });
};
export const fetchCreateProduct = async (data) => {
    return axios.post(`/admin/product`,data).then((result) => {
        return result;
    });
};

export const fetchGetSize = async () => {
    return axios.get(`/admin/size`).then((result) => {
        return result;
    });
};
export const fetchCreateSizeProduct = async (data) => {
    return axios.post(`/admin/size`,data).then((result) => {
        return result;
    });
};
export const fetchGetColor = async () => {
    return axios.get(`/admin/color`).then((result) => {
        return result;
    });
};

export const fetchCreateColorProduct = async (data) => {
    return axios.post(`/admin/color`,data).then((result) => {
        return result;
    });
};

export const fetchCreateImageProduct = async (data) => {
    return axios.post(`/admin/image`,data).then((result) => {
        return result;
    });
};