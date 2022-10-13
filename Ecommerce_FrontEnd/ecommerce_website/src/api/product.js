import axios from "./config";

export const fetchGetAllProduct = async (idCategory) => {
    return axios.get(`/product/category/${idCategory}`).then((result) => {
        return result;
    });
};

export const fetchGetDetailProduct = async (idProduct) => {
    return axios.get(`/product/detail-product/${idProduct}`).then((result) => {
        return result;
    });
};