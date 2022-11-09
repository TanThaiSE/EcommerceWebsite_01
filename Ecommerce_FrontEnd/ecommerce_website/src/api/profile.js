import axios from "./config";
import { getAuthor } from "../utils/cookieStorage";
//KO
export const fetchAddProfile = async (data) => {
    return axios.post(`/profile`, data).then((result) => {
        return result;
    });
};

//co authen
export const fetchGetDetailProfile = async (idAccount) => {
    return axios.get(`/profile/${idAccount}/detail-infor`,{ headers: getAuthor.getAuthorization() }).then((result) => {
        return result;
    });
};

export const fetchEditInfoProfile=async(data)=>{
    return axios.put(`/profile`,data,{ headers: getAuthor.getAuthorization() }).then((result) => {
        return result;
    });
};
