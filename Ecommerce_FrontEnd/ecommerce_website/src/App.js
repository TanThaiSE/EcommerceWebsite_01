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
import Account from "./pages/Account";
import Categories from "./pages/Category";
import CreateCategory from "./pages/CreateCategory";
import ProductAdmin from "./pages/ProductAdmin";
import CreateProduct from "./pages/CreateProduct";
import EditProduct from "./pages/EditProduct";

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
      <Route path="/manage-account" element={<Account/>}/>
      <Route path="/manage-category" element={<Categories/>}/>
      <Route path="/create-category" element={<CreateCategory/>}/>
      <Route path="/manage-product" element={<ProductAdmin/>}/>
      <Route path="/create-product" element={<CreateProduct/>}/>
      <Route path='/manage-detail-product/:idProduct' element={<EditProduct/>}/>
      <Route path="/*" element={<ErrorPage/>} />
    </Routes>
  );
}

export default App;
