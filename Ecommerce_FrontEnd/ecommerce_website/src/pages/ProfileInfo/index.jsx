import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import NavBar from '../../components/Navbars/Navbar';
import NavBarInfo from '../../components/Navbars/NavbarInfo';
import NavBarProfile from '../../components/Navbars/NavBarProfile';
import ProfileComponent from '../../components/Accounts/ProfileComponent';
const ProfileInfo = () => {
    return (
        <>
            <NavBarInfo />
            <NavBar />
            <div className='container'>
                <div className="row">
                    <div className="col-md-2">
                        <NavBarProfile type={1} />
                    </div>
                    <div className="col-md-10">
                        <div>
                            <ProfileComponent/>
                        </div>
                    </div>
                </div>

            </div>
        </>
    )
}

export default ProfileInfo;