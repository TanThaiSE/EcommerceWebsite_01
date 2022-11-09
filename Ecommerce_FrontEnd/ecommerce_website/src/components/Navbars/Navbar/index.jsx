import React,{useState } from 'react'
import { Link} from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faMagnifyingGlass,faCartShopping } from '@fortawesome/free-solid-svg-icons'; 

import './index.css';
import { getLogin } from '../../../utils/cookieStorage';
const NavBar = () => {
    const [itemSearch, setItemSearch] = useState(null);
    const handleSearch = () => {
        if (!itemSearch || itemSearch.length === 0) {
            return;
        }
        window.open(`/search?keyword=${itemSearch}`,'_self');
    }

    return (
        <>
        {getLogin.getNameRole()!=='ROLE_ADMIN'?(
            <div className='navbarMain'>
            <nav className="navbar navbar-expand-lg container ">
                <div className="container-fluid">
                    <Link to="/" className="navbar-brand" >
                        <img src='/brand_logo.png' id='branchLogo' alt='logoBranch'/>
                    </Link>
                    <div className="collapse navbar-collapse" id="navbarSupportedContent">
                        <form className="d-flex formSearch" >
                            <input className="form-control me-2 navbarSearchInput" type="search" placeholder="Search" aria-label="Search"  onChange={e => setItemSearch(e.target.value)}/>
                            <div className='containIconSearch'>
                                <FontAwesomeIcon icon={faMagnifyingGlass} id="iconSearch" onClick={()=>{handleSearch()}}/>
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
        ):(<></>)}



        </>
    )
}

export default NavBar;