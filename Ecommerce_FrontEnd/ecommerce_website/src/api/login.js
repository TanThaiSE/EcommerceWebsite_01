import axios from "./config";

export const fetchLogin = async (data) => {
    return axios.post(`/login`, data).then((result) => {
        return result;
    });
};

// export const fetchLogin = async () => {
//     return axios.post(`/login`, { dataSend: data }, { headers: { 'Authorization': 'Bearer ' + isToken } }).then((result) => {
//         return result;
//       });
// };