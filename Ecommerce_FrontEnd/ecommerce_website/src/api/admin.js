import { getAuthor } from "../utils/localStorage";
import axios from "./config";

//co autho
export const fetchGetUsers = async (page,offset) => {
    return axios.get(`/admin/users`,{params:{page:page,offset:offset }}).then((result) => {
        return result;
    });
};

export const fetchUpdateBlockedUser = async (idAccount) => {
    return axios.put(`/admin/${idAccount}/access-rights`).then((result) => {
        return result;
    });
};

export const fetchCategory = async (page,offset) => {
    return axios.get(`/admin/category`,{params:{page:page,offset:offset }}).then((result) => {
        return result;
    });
};
