import axios from "./config";

export const fetchGetAllProduct = async (idCategory) => {
    return axios.get(`/category/${idCategory}/product`).then((result) => {
        return result;
    });
};

export const fetchGetDetailProduct = async (idProduct) => {
    return axios.get(`/product/${idProduct}`).then((result) => {
        return result;
    });
};
export const fetchUpdateQuantityProduct = async (data) => {
    return axios.put(`/product`,data).then((result) => {
        return result;
    });
};