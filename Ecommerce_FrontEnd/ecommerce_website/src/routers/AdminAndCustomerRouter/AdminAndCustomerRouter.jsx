import React from 'react';
import { getLogin } from '../../utils/cookieStorage';
import AccessDenied from '../../pages/AccessDenied';

const AdminAndCustomerRouter = ({ children }) => {
    const role = getLogin.getNameRole();
    if (role && (role === "ROLE_ADMIN" || role === "ROLE_USER")) {
        return <>{children}</>
    }
    else {
        return <AccessDenied />
    }
}

export default AdminAndCustomerRouter;