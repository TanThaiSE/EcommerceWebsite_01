import axios from "./config";
import { getAuthor } from "../utils/localStorage";
export const fetchAddRating = async (data) => {
    return axios.post(`/rating`, data, { headers: getAuthor.getAuthorization() }).then((result) => {
        return result;
    });
};

export const fetchGetRatingAndComment = async (idProduct) => {
    return axios.get(`/rating/${idProduct}`).then((result) => {
        return result;
    });
};