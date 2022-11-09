import axios from "./config";

export const fetchSendEmail = async (data) => {
    return axios.post(`/email`, data).then((result) => {
        return result;
    });
};

