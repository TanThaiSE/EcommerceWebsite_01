import axios from "./config";

export const fetchAddProfile = async (data) => {
    return axios.post(`/profile`, data).then((result) => {
        return result;
    });
};
