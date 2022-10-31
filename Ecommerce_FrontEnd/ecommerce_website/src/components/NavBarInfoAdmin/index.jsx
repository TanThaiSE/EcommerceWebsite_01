import React from 'react';
import { Link } from 'react-router-dom';
import './index.css';
import { useNavigate } from 'react-router-dom';
import { getLogin } from '../../utils/localStorage';
import { clearLogin } from '../../utils/localStorage';
const NavBarInfoAdmin = () => {
    const navigate = useNavigate();
    const logOut = () => {
        clearLogin.clearLocalStorage();
        navigate('/');
    }
    return (
            <div className='navbarMainInfo-admin'>
                <nav className="navbar navbar-expand-lg container ">
                    <div className="container-fluid">
                        <div className="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul className="navbar-nav ms-auto mb-2 mb-lg-0">
                                <li className="nav-item dropdown">
                                    <a className="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                        {getLogin.getUserName()}
                                    </a>
                                    <ul className="dropdown-menu" aria-labelledby="navbarDropdown">
                                        <li><Link className="dropdown-item" to={`/user/account/profile`}>Tài khoản của tôi</Link></li>
                                        <li><Link className="dropdown-item" to={`/user/purchase`}>Đơn mua</Link></li>
                                        <li><button className="dropdown-item" onClick={() => { logOut(); }}>Đăng xuất</button></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>
            </div>
    )
}

export default NavBarInfoAdmin;