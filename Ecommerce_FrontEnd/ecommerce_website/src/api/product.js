import axios from "./config";

export const fetchGetAllProduct = async (idCategory,sortPrice,page,offset) => {
    return axios.get(`/category/${idCategory}/product`,{params:{sortPrice:sortPrice,page:page,offset:offset }}).then((result) => {
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

export const fetchGetEntireProduct = async (searchKey,sortPrice,page,offset) => {
    return axios.get(`/product`,{params:{searchKey:searchKey,sortPrice:sortPrice,page:page,offset:offset }}).then((result) => {
        return result;
    });
};