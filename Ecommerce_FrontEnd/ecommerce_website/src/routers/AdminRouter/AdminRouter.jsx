import React from 'react';
import { getLogin } from '../../utils/cookieStorage';
import AccessDenied from '../../pages/AccessDenied';

const AdminRouter = ({ children }) => {
    const role = getLogin.getNameRole();
    if (role && role === "ROLE_ADMIN") {
        return <>{children}</>
    }
    else {
        return <AccessDenied/>
    }
}

export default AdminRouter;