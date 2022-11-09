import { faStaffSnake } from '@fortawesome/free-solid-svg-icons';
import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { apiCart } from '../../../api';
import { getLogin } from '../../../utils/cookieStorage';
import './index.css';
const CartComponent = () => {
  const navigate = useNavigate();
  const [listProducts, setListProducts] = useState([]);
  const [isEmptyCart, setIsEmptyCart] = useState(false);
  const [editQuantity, setEditQuantity] = useState({});
  const fetchGetProductsInCart = async () => {
    await apiCart.fetchGetCart(getLogin.getId())
      .then((res) => {
        setListProducts(res.data);
      })
      .catch((err) => {
        console.log(err);
        if (err.response.data.code === "404") {
          //not found data
          console.log(err);
          setIsEmptyCart(true);
        }
      })
  }

  const splitString = (st) => {
    if (st.length !== 0) {
      if (st.length > 72) {
        return st.substring(0, 68) + '...';
      }
      else {
        return st;
      }
    }
  }
  const setAmountProduct = async (item, type) => {
    let tempProducts = [...listProducts];
    let findIndex = tempProducts.findIndex((product) => product.id === item.id);
    if (type === 1) {
      if (tempProducts[findIndex].quantity <= 1) {
        tempProducts[findIndex] = { ...tempProducts[findIndex], quantity: 1 }
      }
      else {
        tempProducts[findIndex] = { ...tempProducts[findIndex], quantity: Number(tempProducts[findIndex].quantity) - 1 }
      }
    }
    else if (type === 2) {
      tempProducts[findIndex] = { ...tempProducts[findIndex], quantity: Number(tempProducts[findIndex].quantity) + 1 }
    }
    await apiCart.fetchUpdateCart(tempProducts[findIndex].id, tempProducts[findIndex])
      .then(() => { setListProducts(tempProducts) })
      .catch((err) => console.log('fetchUpdateCart failed ', err))
  };
  const handleInputAmountProduct = (event) => {
    let { value, name } = event.target;
    let item = JSON.parse(name);
    let tempProducts = [...listProducts];
    let findIndex = tempProducts.findIndex((product) => product.id === item.id);
    if (value === '' || Number(value) < 1) {
      tempProducts[findIndex] = { ...tempProducts[findIndex], quantity: 1 }
    }
    else {
      tempProducts[findIndex] = { ...tempProducts[findIndex], quantity: value }
    }
    setListProducts(tempProducts);
  };
  const handleCheckInputAmountProduct = async (event) => {
    let { name } = event.target;
    let item = JSON.parse(name);
    let findIndex = listProducts.findIndex((product) => product.id === item.id);
    await apiCart.fetchUpdateCart(listProducts[findIndex].id, listProducts[findIndex])
      .then(() => { })
      .catch((err) => console.log('fetchUpdateCart failed ', err))
  };
  const handleClickProduct = (e) => {
    const { name, checked } = e.target;
    if (name === "allSelect") {
      let tempProduct = listProducts.map((item) => {
        return { ...item, isChecked: checked }
      });
      setListProducts(tempProduct);
    }
    else {
      let tempProduct = listProducts.map((item) => {
        return item?.id === name ? { ...item, isChecked: checked } : item
      });
      setListProducts(tempProduct);
    }
  }
  const handleRemoveProduct = async (idCart) => {
    await apiCart.fetchDeleteCart(idCart,getLogin.getId)
      .then((res) => {
        if (res.data.code === "200") {
          let tempListProduct = [...listProducts];
          let indexRemove = tempListProduct.findIndex((item) => item.id === idCart);
          if (indexRemove !== -1) {
            tempListProduct.splice(indexRemove, 1);
            setListProducts(tempListProduct);
          }
        }
      })
      .catch((err) => { console.log(err); })
  }
  const displayMoney = (listProducts) => {
    let totalMoney = 0;
    if (listProducts.length > 0) {
      for (let i = 0; i < listProducts.length; i++) {
        if (listProducts[i]?.isChecked) {
          totalMoney += Number(listProducts[i].price) * Number(listProducts[i].quantity);
        }
      }
      return totalMoney;
    }
    else {
      return totalMoney;
    }
  }
  const handleDeleteMultipleChoice = async () => {
    let isNotEmpty = listProducts.some((item) => item?.isChecked === true);
    if (isNotEmpty) {
      let prepareToDelete = listProducts.filter((item) => item?.isChecked === true);
      await apiCart.fetchDeleteMultipleProduct({prepareToDelete}).then((res)=>{}).catch((err)=>{console.log(err);});
      let newCart = listProducts.filter((item) => item?.isChecked !== true);
      setListProducts(newCart);
    }
  }
  const handleCheckout = () => {
    let isNotEmpty = listProducts.some((item) => item?.isChecked === true);
    if (isNotEmpty) {
      let prepareCheckOut = listProducts.filter((item) => item?.isChecked === true);
      localStorage.setItem("CHECKOUT", JSON.stringify(prepareCheckOut));
      navigate('/checkout');
    }
  }
  const displayProducts = (listProducts) => {
    if (listProducts.length > 0) {
      return listProducts.map((item, index) => {
        return (
          <div className="row mt-2 mb-5" key={`listProducts-${index}`}>
            <div className="col-md-1">
              <input type='checkbox'
                className='choose-product'
                name={item.id}
                checked={item?.isChecked || false}
                onChange={handleClickProduct}
              />
            </div>
            <div className="col-md-5">
              <div className="content d-flex" >
                <div className="content-img">
                  <Link to={`/productdetail/${item.productId}`}>
                    <img src={item.imgName} alt={item.productName} />
                  </Link>

                </div>
                <div className="content-product">
                  <Link to={`/productdetail/${item.productId}`} className='content-product-link'>
                    <p>{splitString(item.productName)}</p>
                  </Link>
                </div>
                <div className="content-attribute">
                  <p>{item.sizeName},{item.colorName} </p>
                </div>
              </div>

            </div>
            <div className="col-md-2">
              <p>{item.price}</p>
            </div>
            <div className="col-md-2">
              <div className='d-flex'>
                <button className='btn-color-product ammount-product' onClick={() => { setAmountProduct(item, 1) }}>-</button>
                <input type="number" value={item.quantity} className='amountProduct' onChange={handleInputAmountProduct} onBlur={handleCheckInputAmountProduct} name={JSON.stringify(item)} />
                <button className='btn-color-product ammount-product' onClick={() => { setAmountProduct(item, 2) }}>+</button>
              </div>
            </div>
            <div className="col-md-1">
              <p>{Number(item.price) * Number(item.quantity)}</p>
            </div>
            <div className="col-md-1">
              <button onClick={() => handleRemoveProduct(item.id)} className='btn-remove-product'>Xóa</button>
            </div>
          </div>
        );
      });
    }
    else {
      return (<></>);
    }
  }

  useEffect(() => {
    localStorage.removeItem("CHECKOUT");
    fetchGetProductsInCart();
  }, []);


  return (
    <div className='container'>
      <div className="row">
        <div className="col-md-1">
          {listProducts.length > 0 ? (<input type='checkbox' className='choose-product' name="allSelect"
            checked={!listProducts.some((p) => { return p?.isChecked !== true })}
            onChange={handleClickProduct}
          />) : (<></>)}
        </div>
        <div className="col-md-5">
          <p>Sản phẩm</p>
        </div>
        <div className="col-md-2">
          <p>Đơn giá</p>
        </div>
        <div className="col-md-2">
          <p>Số lượng</p>
        </div>
        <div className="col-md-1">
          <p>Số tiền</p>
        </div>
        <div className="col-md-1">
          <p>Thao tác</p>
        </div>
      </div>
      {displayProducts(listProducts)}
      <div className='row'>
        <div className="col-md-1">
          <input type='checkbox' />
        </div>
        <div className="col-md-4">
          <button onClick={() => { handleDeleteMultipleChoice() }} className='btnDeleteProduct'>Xóa</button>
        </div>
        <div className="col-md-2">
          <p>Tổng thanh toán</p>
        </div>
        <div className="col-md-3">
          {displayMoney(listProducts)}
        </div>
        <div className="col-md-2">
          <button onClick={() => { handleCheckout() }} className='btnBuyProduct'>Mua hàng</button>
        </div>
      </div>
    </div>
  )
}

export default CartComponent