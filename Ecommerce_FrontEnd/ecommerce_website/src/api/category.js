import axios from "./config";

export const fetchCategory = async () => {
    return axios.get(`/category`).then((result) => {
        return result;
    });
};