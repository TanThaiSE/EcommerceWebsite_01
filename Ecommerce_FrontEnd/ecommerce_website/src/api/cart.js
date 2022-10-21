import { getAuthor } from "../utils/localStorage";
import axios from "./config";

// export const fetchGetCart = async () => {
//     return axios.get(`/cart`,{ headers: getAuthor.getAuthorization() }).then((result) => {
//         return result;
//     });
// };

// export const fetchAddCart = async (data) => {
//     return axios.post(`/cart`, data,{ headers: getAuthor.getAuthorization() }).then((result) => {
//         return result;
//     });
// };

// export const fetchUpdateCart = async (data) => {
//     return axios.put(`/cart`, data,{ headers: getAuthor.getAuthorization() }).then((result) => {
//         return result;
//     });
// };

// export const fetchDeleteCart = async (data) => {
//     return axios.delete(`/cart`, data,{ headers: getAuthor.getAuthorization() }).then((result) => {
//         return result;
//     });
// };


export const fetchGetCart = async () => {
    return axios.get(`/cart`).then((result) => {
        return result;
    });
};

export const fetchAddCart = async (data) => {
    return axios.post(`/cart`, data).then((result) => {
        return result;
    });
};

export const fetchUpdateCart = async (id,data) => {
    return axios.put(`/cart/${id}/product`, data).then((result) => {
        return result;
    });
};

export const fetchDeleteCart = async (idCart) => {
    return axios.delete(`/cart/${idCart}`).then((result) => {
        return result;
    });
};

export const fetchDeleteMultipleProduct = async (data1) => {
    return axios.delete(`/cart`,{data:data1}).then((result) => {
        return result;
    });
};