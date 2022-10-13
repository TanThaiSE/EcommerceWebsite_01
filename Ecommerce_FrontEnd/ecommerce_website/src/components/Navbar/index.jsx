import React from 'react'
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faMagnifyingGlass,faCartShopping } from '@fortawesome/free-solid-svg-icons'; 
import './index.css';
const NavBar = () => {
    return (
        <>
            <div className='navbarMain'>
            <nav className="navbar navbar-expand-lg container ">
                <div className="container-fluid">
                    <Link to="/" className="navbar-brand" >
                        <img src='/brand_logo.png' id='branchLogo' />
                    </Link>
                    <div className="collapse navbar-collapse" id="navbarSupportedContent">
                        <form className="d-flex formSearch" >
                            <input className="form-control me-2 navbarSearchInput" type="search" placeholder="Search" aria-label="Search" />
                            <div className='containIconSearch'>
                                <FontAwesomeIcon icon={faMagnifyingGlass} id="iconSearch" />
                            </div>
                            
                        </form>

                        <div className="navbar-nav me-auto cartMain">
                            <Link to="/cart">
                                <FontAwesomeIcon icon={faCartShopping} id="iconCart" />
                            </Link>
                        </div>


                    </div>
                </div>
            </nav>

            </div>


        </>
    )
}

export default NavBar;