import React, { useState, useEffect, Profiler } from "react";
import { Routes, Route, Outlet } from 'react-router-dom';
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
import CustomerRouter from "./routers/CustomerRouter/CustomerRouter";
import AdminRouter from './routers/AdminRouter/AdminRouter';
import AdminAndCustomerRouter from './routers/AdminAndCustomerRouter/AdminAndCustomerRouter';
import SearchPage from "./pages/Search";
import ProfileInfo from './pages/ProfileInfo';
import ProfilePassword from './pages/ProfilePassword';
import ForgotPassword from "./pages/ForgotPassword";
import CreateCustomer from "./pages/CreateCustomer";
function App() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/category/:idCate" element={<ProductPage />} />
      <Route path="/search" element={<SearchPage />} />
      <Route path='/productdetail/:idProduct' element={<ProductDetail />} />
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register />} />
      <Route path="/forgotpassword" element={<ForgotPassword />} />

      <Route path="/" element={
        <CustomerRouter>
          <Outlet />
        </CustomerRouter>
      }>
        <Route path='/cart' element={<Cart />} />
        <Route path="/checkout" element={<Checkout />} />
        <Route path="/user/purchase" element={<Purchase />} />
      </Route>
      <Route path="/" element={
        <AdminRouter>
          <Outlet />
        </AdminRouter>
      }>
        <Route path="/manage-account" element={<Account />} />
        <Route path="/manage-category" element={<Categories />} />
        <Route path="/create-category" element={<CreateCategory />} />
        <Route path="/manage-product" element={<ProductAdmin />} />
        <Route path="/create-product" element={<CreateProduct />} />
        <Route path='/manage-detail-product/:idProduct' element={<EditProduct />} />
        <Route path="/create-customer" element={<CreateCustomer/>} />
      </Route>


      <Route path='/' element={
        <AdminAndCustomerRouter>
          <Outlet />
        </AdminAndCustomerRouter>
      }>
        <Route path="/user/account/profile" element={<ProfileInfo />} />
        <Route path="/user/account/password" element={<ProfilePassword />} />
      </Route>
      <Route path="/*" element={<ErrorPage />} />

    </Routes>
  );
}

export default App;


// <Routes>
// <Route path="/" element={<Home/>}/>
// <Route path="/category/:idCate" element={<ProductPage/>}/>
// <Route path='/productdetail/:idProduct' element={<ProductDetail/>}/>
// <Route path='/cart' element={<Cart/>}/>
// <Route path="/login" element={<Login/>}/>
// <Route path="/register" element={<Register/>} />
// <Route path="/checkout" element={<Checkout/>}/>
// <Route path="/user/purchase" element={<Purchase/>}/> 
// <Route path="/manage-account" element={<Account/>}/>
// <Route path="/manage-category" element={<Categories/>}/>
// <Route path="/create-category" element={<CreateCategory/>}/>
// <Route path="/manage-product" element={<ProductAdmin/>}/>
// <Route path="/create-product" element={<CreateProduct/>}/>
// <Route path='/manage-detail-product/:idProduct' element={<EditProduct/>}/>
// <Route path="/*" element={<ErrorPage/>} /> 