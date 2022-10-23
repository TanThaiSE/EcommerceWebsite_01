import axios from "./config";

export const fetchAddToOrderDetail = async (data) => {
    return axios.post(`/orderdetail`,data).then((result) => {
        return result;
    });
};

export const fetchGetAllProductInOrderDetail=async ()=>{
    return axios.get(`/orderdetail`).then((result) => {
        return result;
    });
}