import axios from "./config";

export const fetchAddRating = async (data) => {
    return axios.post(`/rating`, data).then((result) => {
        return result;
    });
};

export const fetchGetRatingAndComment = async (idProduct) => {
    return axios.get(`/rating/${idProduct}`).then((result) => {
        return result;
    });
};