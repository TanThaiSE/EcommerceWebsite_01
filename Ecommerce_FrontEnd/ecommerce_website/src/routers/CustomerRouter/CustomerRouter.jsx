import React from 'react';
import { getLogin } from '../../utils/cookieStorage';
import {Navigate} from "react-router-dom";

const CustomerRouter = ({ children }) => {
    const role = getLogin.getNameRole();
    if (role && role === "ROLE_USER") {
        return <>{children}</>
    }
    else {
        return <Navigate to="/login"/>
    }
}

export default CustomerRouter