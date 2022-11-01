import axios from "./config";

//KO
export const fetchAddProfile = async (data) => {
    return axios.post(`/profile`, data).then((result) => {
        return result;
    });
};

//co authen
export const fetchGetDetailProfile = async (idAccount) => {
    return axios.get(`/profile/${idAccount}/detail-infor`).then((result) => {
        return result;
    });
};
