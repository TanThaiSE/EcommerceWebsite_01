import axios from "./config";

export const fetchAddToOrderDetail = async (data) => {
    return axios.post(`/orderdetail`,data).then((result) => {
        return result;
    });
};
