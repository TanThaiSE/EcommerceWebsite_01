import { getAuthor } from "../utils/cookieStorage";
import axios from "./config";

export const fetchGetUsers = async (page, offset) => {
    return axios.get(`/users`, { params: { page: page, offset: offset }, headers: getAuthor.getAuthorization() }).then((result) => {
        return result;
    });
};

export const fetchUpdateBlockedUser = async (idAccount) => {
    return axios.put(`/users/${idAccount}/access-rights`,{}, { headers: getAuthor.getAuthorization() }).then((result) => {
        return result;
    });
};