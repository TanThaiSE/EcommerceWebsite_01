import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import NavBar from '../../components/Navbars/Navbar';
import NavBarInfo from '../../components/Navbars/NavbarInfo';
import CarouselBanner from '../../components/Carousel';
import SearchProduct from '../../components/Products/SearchProduct';

const SearchPage = () => {
   
  return (
    <>
        <NavBarInfo/>
        <NavBar/>
        <CarouselBanner/>
        <SearchProduct/>
    </>
  )
}

export default SearchPage;