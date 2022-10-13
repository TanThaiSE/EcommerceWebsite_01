import axios from "axios";

let Axios=axios;
const defaultUrl=process.env.REACT_APP_SERVER
Axios.defaults.baseURL=`${defaultUrl}/api/v1`;

export default Axios;