import React from 'react'
import { Link } from 'react-router-dom';
import './index.css';

const NavBarProfile = ({ type }) => {
    return (
        <div className='nav-admin'>
            <div className="tab-manager">

                <div className="categories">
                    <Link to="/user/account/profile" className='link-navigate navbar-categories' >
                        {type === 1 ? (<p style={{ color: '#ee4d2d' }}>Hồ sơ</p>) : (<p>Hồ sơ</p>)}
                    </Link>
                </div>
                <div className="products">
                    <Link to="/user/account/password" className='link-navigate navbar-products'>
                        {type === 0 ? (<p style={{ color: '#ee4d2d' }}>Đổi mật khẩu</p>) : (<p>Đổi mật khẩu</p>)}
                    </Link>
                </div>
            </div>
        </div>
    )
}

export default NavBarProfile;