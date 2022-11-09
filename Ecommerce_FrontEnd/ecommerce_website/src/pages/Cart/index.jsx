import React from 'react'
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import './index.css';
import NavBarInfo from '../../components/Navbars/NavbarInfo';
import NavBarCommon from '../../components/Navbars/NavBarComon';
import CartComponent from '../../components/Carts/CartComponent';
const Cart = () => {
  return (
    <>
        <NavBarInfo/>
        <NavBarCommon title={`Giỏ Hàng`}/>
        <CartComponent/>
    </>
  )
}

export default Cart;