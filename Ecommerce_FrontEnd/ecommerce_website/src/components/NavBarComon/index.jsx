import React from 'react'
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faMagnifyingGlass,faCartShopping } from '@fortawesome/free-solid-svg-icons'; 
import './index.css';
const NavBarCommon = ({title,colors}) => {
    return (
        <>
            <div className='navbarMainCart'>
            <nav className="navbar navbar-expand-lg container">
                <div className="container-fluid">
                    <Link to="/" className="navbar-brand" >
                        <div className='d-flex'>
                            <img src='/brand_logo.png' id='branchLogo' alt='brand_logo' />
                            <div className='line-seperate'>
                            </div>
                            <div className='name-cart'>
                                <p style={{color:colors}}> {title}</p>
                            </div>
                        </div>
                    </Link>
                </div>
            </nav>

            </div>


        </>
    )
}

export default NavBarCommon;