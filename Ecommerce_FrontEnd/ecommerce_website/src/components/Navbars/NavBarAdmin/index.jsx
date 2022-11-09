import React from 'react'
import { Link } from 'react-router-dom';
import './index.css';

const NavBarAdmin = () => {
    return (
        <div className='nav-admin'>
            <div className="brand-admin">
                <Link to="/manage-account" className="nav-brand" >
                    <img src='/brand_logo.png' id='branchLogo' alt='brand-logo' />
                </Link>
            </div>
            <div className="tab-manager">

                <div className="categories">
                    <Link to="/manage-category" className='link-navigate navbar-categories'>
                        <p>Categories</p>
                    </Link>
                </div>
                <div className="products">
                    <Link to="/manage-product" className='link-navigate navbar-products'>
                        <p>Products</p>
                    </Link>
                </div>
                <div className="employees">
                    <Link to="/manage-account" className='link-navigate navbar-employees'>
                        <p>Customers</p>
                    </Link>
                </div>
            </div>
        </div>
    )
}

export default NavBarAdmin;