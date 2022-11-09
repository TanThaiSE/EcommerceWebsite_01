import React from 'react';
import { Link,useNavigate } from 'react-router-dom';

import './index.css';

const ErrorPage = () => {
  const navigate = useNavigate();
  return (
    <div className="error">

      <div className="container-floud">
        <div className="col-xs-12 ground-color text-center">
          <div className="container-error-404">
            <div className="clip"><div className="shadow"><span className="digit thirdDigit"><h1 style={{ fontSize: 100 }}> 4</h1></span></div></div>
            <div className="clip"><div className="shadow"><span className="digit secondDigit"><h1 style={{ fontSize: 100 }}> 0</h1></span></div></div>
            <div className="clip"><div className="shadow"><span className="digit firstDigit"><h1 style={{ fontSize: 100 }}> 4</h1></span></div></div>
            <div className="msg">OH!<span className="triangle"></span></div>
          </div>
          <h2 className="h1">Sorry! Page not found</h2>
          <h2 className="h1">
            
            <Link to={navigate(-1)} style={{ fontSize: 20, marginBottom: 50,textDecoration:'none',color:'#ee4d2d' }}>Quay lại</Link>
          </h2>

        </div>
      </div>
    </div>
  )
}

export default ErrorPage;