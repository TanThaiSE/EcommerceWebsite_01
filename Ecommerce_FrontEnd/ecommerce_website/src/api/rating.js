import axios from "./config";

export const fetchUpdate = async (idCategory) => {
    return axios.get(`/category/${idCategory}/product`).then((result) => {
        return result;
    });
};