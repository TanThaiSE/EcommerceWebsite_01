import React from 'react'
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import './index.css';
import NavBarInfo from '../../components/NavbarInfo';
import NavBarComon from '../../components/NavBarComon';
import CartComponent from '../../components/CartComponent';
const Cart = () => {
  return (
    <>
        <NavBarInfo/>
        <NavBarComon title={`Giỏ Hàng`}/>
        <CartComponent/>
    </>
  )
}

export default Cart;