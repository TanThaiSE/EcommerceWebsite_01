import React from 'react'
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import './index.css';
import NavBarComon from '../../components/NavBarComon';

const Register = () => {
  return (
    <>
      <NavBarComon title={`Đăng ký`} colors={'black'} />
      <div className="loginBackground">
        <div className="container bg-login">

            <div className="colm-form-register">
              <div className="form-container-register">
                <p className='title-register'>Đăng ký</p>
                <input type="text" placeholder="Email address or phone number" />
                <input type="password" placeholder="Password" />
                <input type="password" placeholder="Repeat Password" />
                <button className="btn-login">Login</button>
                <a href="#">Forgotten password?</a>
                <button className="btn-new">Create new Account</button>
              </div>
            </div>

        </div>
      </div>
    </>
  )
}

export default Register;