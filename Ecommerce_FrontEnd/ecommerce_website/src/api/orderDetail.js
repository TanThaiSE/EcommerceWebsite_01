import { getAuthor } from "../utils/cookieStorage";
import axios from "./config";

export const fetchAddToOrderDetail = async (data) => {
    return axios.post(`/orderdetail`,data, { headers: getAuthor.getAuthorization() }).then((result) => {
        return result;
    });
};

export const fetchGetAllProductInOrderDetail=async ()=>{
    return axios.get(`/orderdetail`, { headers: getAuthor.getAuthorization() }).then((result) => {
        return result;
    });
}