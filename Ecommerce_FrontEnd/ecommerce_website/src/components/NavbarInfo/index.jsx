import React from 'react'
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faGlobe } from '@fortawesome/free-solid-svg-icons';
import './index.css';
import { useNavigate } from 'react-router-dom';
import { getLogin } from '../../utils/localStorage';
import { clearLogin } from '../../utils/localStorage';
const NavBarInfo = () => {
    const navigate = useNavigate();
    const logOut=()=>{
        clearLogin.clearLocalStorage();
        navigate('/');
    }
    return (
        <>
            <div className='navbarMainInfo'>
                <nav className="navbar navbar-expand-lg container ">
                    <div className="container-fluid">
                        <div className="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul className="navbar-nav ms-auto mb-2 mb-lg-0">
                                <li className="nav-item dropdown">
                                    <a className="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                        <FontAwesomeIcon icon={faGlobe} className="text-white" />
                                    </a>
                                    <ul className="dropdown-menu" aria-labelledby="navbarDropdown">
                                        <li><a className="dropdown-item" href="#">Tiếng Việt</a></li>
                                        <li><a className="dropdown-item" href="#">English</a></li>
                                    </ul>
                                </li>



                                {getLogin.getToken() === null ? (
                                    <>
                                        <li className="nav-item">
                                            <Link className="nav-link text-white" to="/register">Đăng ký</Link>
                                        </li>
                                        <li className="nav-item">
                                            <Link className="nav-link text-white" to="/login">Đăng nhập</Link>
                                        </li>
                                    </>
                                ) : (<>
                                        <li className="nav-item dropdown">
                                            <a className="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                {getLogin.getUserName()}
                                            </a>
                                            <ul className="dropdown-menu" aria-labelledby="navbarDropdown">
                                                <li><Link className="dropdown-item" href="#">Tài khoản của tôi</Link></li>
                                                <li><Link className="dropdown-item" href="#">Đơn mua</Link></li>
                                                <li><button className="dropdown-item" onClick={()=>{logOut();}}>Đăng xuất</button></li>
                                            </ul>
                                        </li>
                                </>)}




                            </ul>


                        </div>
                    </div>
                </nav>

            </div>


        </>
    )
}

export default NavBarInfo;