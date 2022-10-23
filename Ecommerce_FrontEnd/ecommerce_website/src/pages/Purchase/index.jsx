import React,{ useState, useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import NavBar from '../../components/Navbar';
import NavBarInfo from '../../components/NavbarInfo';
import OrderDetail from '../../components/OrderDetail';

const Purchase = () => {
  return (
    <>
        <NavBarInfo/>
        <NavBar/>
        <OrderDetail/>
    </>
  )
}

export default Purchase