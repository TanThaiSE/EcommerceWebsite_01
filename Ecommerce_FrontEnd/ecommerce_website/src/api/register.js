import axios from "./config";

export const fetchRegister = async (data) => {
    return axios.post(`/signup`, data).then((result) => {
        return result;
    });
};
