
import axios from "./config";
export const fetchGetAllPayment = async () => {
    return axios.get(`/payment`).then((result) => {
        return result;
    });
};