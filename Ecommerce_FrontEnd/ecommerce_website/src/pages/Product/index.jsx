import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import { useParams } from 'react-router';
import NavBar from '../../components/Navbars/Navbar';
import NavBarInfo from '../../components/Navbars/NavbarInfo';
import CarouselBanner from '../../components/Carousel';
import Product from '../../components/Products/Product';

const ProductPage = () => {
    const {idCate}=useParams();
  return (
    <>
        <NavBarInfo/>
        <NavBar/>
        <CarouselBanner/>
        <Product idCategory={idCate}/>
    </>
  )
}

export default ProductPage;