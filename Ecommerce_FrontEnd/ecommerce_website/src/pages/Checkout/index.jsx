import React from 'react'
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import './index.css';
import NavBarInfo from '../../components/NavbarInfo';
import NavBarCommon from '../../components/NavBarComon';
import CheckoutComponent from '../../components/CheckoutComponent';
const Checkout = () => {
  return (
    <div>
        <NavBarInfo/>
        <NavBarCommon title={`Giỏ Hàng`}/>
        <CheckoutComponent/>
    </div>
  )
}

export default Checkout;