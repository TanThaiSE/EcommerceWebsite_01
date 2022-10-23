import React, { useState, useEffect } from "react";
import {Routes,Route} from 'react-router-dom';
import Home from './pages/Home';
import Register from './pages/Register';
import Login from "./pages/Login";
import ErrorPage from "./pages/Error";
import ProductPage from "./pages/Product";
import ProductDetail from "./pages/ProductDetail";
import Cart from "./pages/Cart";
import Checkout from "./pages/Checkout";
import Purchase from "./pages/Purchase";
function App() {
  return (
    <Routes>
      <Route path="/" element={<Home/>}/>
      <Route path="/category/:idCate" element={<ProductPage/>}/>
      <Route path='/productdetail/:idProduct' element={<ProductDetail/>}/>
      <Route path='/cart' element={<Cart/>}/>
      <Route path="/login" element={<Login/>}/>
      <Route path="/register" element={<Register/>} />
      <Route path="/checkout" element={<Checkout/>}/>
      <Route path="/user/purchase" element={<Purchase/>}/> 
      <Route path="/*" element={<ErrorPage/>} />
    </Routes>
  );
}

export default App;
