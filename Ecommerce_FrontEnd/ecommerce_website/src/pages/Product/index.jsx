import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import { useParams } from 'react-router';
import NavBar from '../../components/Navbar';
import NavBarInfo from '../../components/NavbarInfo';
import CarouselBanner from '../../components/Carousel';
import Product from '../../components/Product';

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