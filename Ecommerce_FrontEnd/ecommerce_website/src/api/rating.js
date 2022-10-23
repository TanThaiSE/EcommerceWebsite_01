import axios from "./config";

export const fetchAddRating = async (data) => {
    return axios.post(`/rating`, data).then((result) => {
        return result;
    });
};
