import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import CarouselBanner from '../../components/Carousel';
import NavBar from '../../components/Navbars/Navbar';
import NavBarInfo from '../../components/Navbars/NavbarInfo';
import Category from '../../components/Categories/Category';
const Home = () => {
  return (
    <>
        <NavBarInfo/>
        <NavBar/>
        <CarouselBanner/>
        <Category/>
    </>
  )
}

export default Home;