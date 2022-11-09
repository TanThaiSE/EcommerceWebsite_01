import React,{ useState, useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import { useParams } from 'react-router';
import NavBar from '../../components/Navbars/Navbar';
import NavBarInfo from '../../components/Navbars/NavbarInfo';
import ProductDetailComponent from '../../components/Products/ProductDetailComponent';
const ProductDetail = () => {
  const {idProduct}=useParams();
  return (
    <>
        <NavBarInfo/>
        <NavBar/>
        <ProductDetailComponent idProduct={idProduct}/>
    </>
  )
}

export default ProductDetail;